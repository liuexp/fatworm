package fatworm.page;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.io.BKey;
import fatworm.io.File;

public class RecordPage extends RawPage {

	// for each page/block, offset in the file is #pageid * pageSize
	// header: #records, prevPageID, nextPageID, offsetTable(specifically for variable length record, remember to ensure space by moving records & updating table before writing out)
	// note that if the record is partial, then offsetTable contains one entry -1, or -2 to indicate the end;
	// for each record:
	// first the nullmap, then go for each field according to metadata.
	private int cntRecord=0;
	private List<Integer> offsetTable = new ArrayList<Integer>();
	private List<Record> records = new ArrayList<Record>();
	public byte [] partialBytes;
	
	public RecordPage(File f, int pageid, boolean create) throws Throwable {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		byte [] tmp = new byte[File.pageSize];
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
			// FIXME maybe I should reverse the buffer somehow
			buf.get(partialBytes);
		}else{
		}
		this.buf = buf;
	}
	
	@Override
	public byte[] toBytes() throws Throwable {
		buf.position(0);
		buf.putInt(cntRecord);
		buf.putInt(prevPageID);
		buf.putInt(nextPageID);
		for(int i=0;i<cntRecord;i++){
			buf.putInt(offsetTable.get(i));
		}
		//TODO put records
		// and if not enough space then separate
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
		buf.position(headerSize());
		for(int i=0;i<cntRecord;i++){
			byte [] tmpb = null;
			//FIXME maybe I should reverse the buffer somehow
			records.add(Record.fromByte(schema, tmpb));
		}
		//
	}
	
	public void addRecord(Record r){
		parseRecord(r.schema);
		//TODO
	}
	
	public void delRecord(Schema schema, int idx){
		parseRecord(schema);
		//TODO
	}
}
