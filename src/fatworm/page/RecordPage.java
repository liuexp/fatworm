package fatworm.page;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.io.BKey;
import fatworm.io.BufferManager;
import fatworm.io.File;
import fatworm.util.ByteBuilder;

public class RecordPage extends RawPage {

	// for each page/block, offset in the file is #pageid * pageSize
	// header: #records, prevPageID, nextPageID, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// note that if the record is partial, then offsetTable contains one entry -1, or -2 to indicate the end;
	// for each record:
	// first the nullmap, then go for each field according to metadata.
	private Integer cntRecord=0;
	private List<Integer> offsetTable = new ArrayList<Integer>();
	private List<Record> records = null;
	private byte [] partialBytes;
	private BufferManager bm;
	
	public RecordPage(BufferManager bm, File f, int pageid, boolean create) throws Throwable {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		byte [] tmp = new byte[File.pageSize];
		this.bm = bm;
		if(!create){
			dataFile.read(tmp, pageID);
			fromBytes(tmp);
		}else{
			nextPageID = -1;
			prevPageID = -1;
			dirty = true;
		}
	}

	@Override
	public void fromBytes(byte[] b) throws Throwable {
		ByteBuffer buf = ByteBuffer.wrap(b);
		cntRecord = buf.getInt();
		prevPageID = buf.getInt();
		nextPageID = buf.getInt();
		for(int i=0;i<cntRecord;i++){
			offsetTable.add(buf.getInt());
		}
		if(isPartial()){
			int length = buf.getInt();
			partialBytes = new byte[length];
			buf.get(partialBytes);
		}else{
//			int length = offsetTable.get(offsetTable.size()-1);
			int length = File.pageSize - buf.position();
			partialBytes = new byte[length];
			buf.position(File.pageSize - length);
			buf.get(partialBytes);
			Collections.reverse(Arrays.asList(partialBytes));
		}
		this.buf = buf;
	}
	
	@Override
	public byte[] toBytes() throws Throwable {
		//TODO put records
		// and if not enough space then separate
		if(partialBytes.length + headerSize() < File.pageSize){
			buf.position(0);
			buf.putInt(cntRecord);
			buf.putInt(prevPageID);
			buf.putInt(nextPageID);
			for(int i=0;i<cntRecord;i++){
				buf.putInt(offsetTable.get(i));
			}
			byte[] tmp = partialBytes.clone();
			Collections.reverse(Arrays.asList(tmp));
			buf.position(File.pageSize - tmp.length);
			buf.put(tmp);
		}else{
			// TODO put it into a for loop
			// put as many as possible
			buf.position(0);
			int remainingSize = partialBytes.length;
			Integer nid = bm.newPage();
			beginTransaction();
			dirty = true;
			// TODO how to do this?
			RecordPage rp = bm.getRecordPage(nextPageID, false);
			rp.beginTransaction();
			rp.prevPageID = nid;
			rp.commit();
			
			commit();
		}
		return buf.array();
	}
	
	public boolean isPartial(){
		return cntRecord == 1 && offsetTable.get(0) == -1;
	}
	
	public boolean endOfPartial(){
		return cntRecord == 1 && offsetTable.get(0) == -2;
	}
	
	public int headerSize(){
		return (3+cntRecord) * Integer.SIZE / Byte.SIZE;
	}
	
	public List<Record> getRecords(Schema schema){
		parseRecord(schema);
		return records;
	}
	
	private void parseRecord(Schema schema){
		if(records != null) return ;
		records = new ArrayList<Record>();
		buf.position(headerSize());
		int lastOffset = 0;
		for(int i=0;i<cntRecord;i++){
			int length = offsetTable.get(i) - lastOffset;
			byte [] tmpb = new byte[length];
			System.arraycopy(partialBytes, lastOffset, tmpb, 0, length);
			records.add(Record.fromByte(schema, tmpb));
		}
	}
	
	// XXX this method is not for record insertion!!! only for record updating!!
	public void addRecord(Record r, int idx){
		dirty = true;
		parseRecord(r.schema);
		records.add(idx, r);
		byte[] recordBytes = Record.toByte(r);
		int lengthOfRecord = recordBytes.length;
		byte[] newb = new byte[partialBytes.length + lengthOfRecord];
		int lastOffset = idx>0?offsetTable.get(idx-1):0;
		System.arraycopy(partialBytes, 0, newb, 0, lastOffset);
		System.arraycopy(recordBytes, 0, newb, lastOffset, lengthOfRecord);
		System.arraycopy(partialBytes, lastOffset, newb, lastOffset + lengthOfRecord, partialBytes.length - lastOffset);
		partialBytes = newb;
		offsetTable.add(idx, lastOffset + lengthOfRecord);
		for(int i=idx+1;i<offsetTable.size();i++)
			offsetTable.set(i, offsetTable.get(i) + lengthOfRecord);
		cntRecord++;
	}
	
	public boolean tryAppendRecord(Record r){
		parseRecord(r.schema);
		byte[] recordBytes = Record.toByte(r);
		int lengthOfRecord = recordBytes.length;
		if(partialBytes.length + lengthOfRecord + headerSize() > File.pageSize)
			return false;
		addRecord(r, cntRecord);
		return true;
	}
	
	public void delRecord(Schema schema, int idx){
		dirty = true;
		parseRecord(schema);
		records.remove(idx);
		int lastOffset = (idx>0?offsetTable.get(idx-1):0);
		int lengthOfRecord = offsetTable.get(idx) - lastOffset;
		byte[] newb = new byte[partialBytes.length - lengthOfRecord];
		System.arraycopy(partialBytes, 0, newb, 0, lastOffset);
		System.arraycopy(partialBytes, offsetTable.get(idx), newb, 0, partialBytes.length - offsetTable.get(idx));
		partialBytes = newb;
		offsetTable.remove(idx);
		for(int i=idx;i<offsetTable.size();i++)
			offsetTable.set(i, offsetTable.get(i) - lengthOfRecord);
		cntRecord--;
	}
	
	public void delete(){
		cntRecord = null;
		partialBytes = null;
		offsetTable = null;
		records = null;
	}

	public byte [] getPartialBytes() {
		return partialBytes;
	}

	public void setPartialBytes(byte [] partialBytes) {
		this.partialBytes = partialBytes;
		dirty = true;
	}
}
