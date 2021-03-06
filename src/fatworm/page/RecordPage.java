package fatworm.page;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.io.BufferManager;
import fatworm.io.File;
import fatworm.util.Util;

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
		byte [] tmp = new byte[(int) File.recordPageSize];
		this.bm = bm;
		if(!create){
			dataFile.read(tmp, pageID);
			fromBytes(tmp);
		}else{
			nextPageID = pageID;
			prevPageID = pageID;
			dirty = true;
			this.buf = ByteBuffer.wrap(tmp);
			int length = offsetTable.size() > 0 ? offsetTable.get(offsetTable.size()-1) : 0;
			partialBytes = new byte[length];
			buf.position((int) (File.recordPageSize - length));
			buf.get(partialBytes);
//			Collections.reverse(Arrays.asList(partialBytes));
			ArrayUtils.reverse(partialBytes);
			records = new ArrayList<Record>();
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
			// FIXME why did I chose the second length at first ?
			int length = offsetTable.size() > 0 ? offsetTable.get(offsetTable.size()-1) : 0;
			// int length = File.pageSize - buf.position();
			partialBytes = new byte[length];
			buf.position((int) (File.recordPageSize - length));
			buf.get(partialBytes);
			ArrayUtils.reverse(partialBytes);
//			Collections.reverse(Arrays.asList(partialBytes));
		}
		this.buf = buf;
	}
	
	@Override
	public byte[] toBytes() throws Throwable {
		if(partialBytes.length + headerSize() <= File.recordPageSize){
//			beginTransaction();
			dirty = true;
			buf.position(0);
			buf.putInt(cntRecord);
			buf.putInt(prevPageID);
			buf.putInt(nextPageID);
			for(int i=0;i<cntRecord;i++){
				buf.putInt(offsetTable.get(i));
			}
			
			if(!isPartial()){
				// first expand the partial bytes
				byte[] tmp = expandAndReverse(partialBytes);
				buf.position((int) (File.recordPageSize - tmp.length));
				buf.put(tmp);
			} else {
				buf.putInt(partialBytes.length);
				buf.put(partialBytes);
			}
//			commit();
		}else{
			int thisid = pageID;
			int curOffset = 0;
			int realNext = nextPageID;
			List<Integer> bakOffset = new ArrayList<Integer>(offsetTable);
			List<Record> bakRecord = new ArrayList<Record>(records);
			int bakCntRecord = cntRecord;
			while(curOffset < bakCntRecord){
				int cntFit = 1;
				for(;cntFit+curOffset<bakCntRecord && canThisFit(bakOffset, curOffset, cntFit);cntFit++);
				cntFit = Math.min(bakCntRecord - curOffset, cntFit);
				if(!canThisFit(bakOffset, curOffset, cntFit))
					cntFit--;
				Util.warn("page "+ pageID + " separates with "+ (bakCntRecord-curOffset) + " remaining, thisid=" +thisid);
				if(curOffset == 0){
					cntRecord = Math.max(1, cntFit);
					offsetTable = offsetTable.subList(0, cntRecord);
					records = records.subList(0, cntRecord);
				}
				if(cntFit >= 1){
					// case 1 at least one record fits the page
					thisid = fitRecords(thisid, bakRecord.subList(curOffset, curOffset + cntFit));
					curOffset += cntFit;
				}else {
					// case 2 the first record (in sequential order) only fits partially
					int recordSize = bakOffset.get(curOffset) - (curOffset>0?bakOffset.get(curOffset-1):0);
					thisid = fitPartial(bm, partialBytes, thisid, curOffset, recordSize);
					curOffset += 1;
				}
			}
			RecordPage rp = bm.getRecordPage(realNext, false);
			rp.beginTransaction();
			rp.dirty = rp.prevPageID!=thisid;
			rp.prevPageID = thisid;
			rp.commit();
			rp = bm.getRecordPage(thisid, false);
			rp.beginTransaction();
			rp.dirty = rp.nextPageID!=realNext;
			rp.nextPageID = realNext;
			rp.commit();
			
		}
		return buf.array();
	}

	private boolean canThisFit(List<Integer> bakOffset, int curOffset, int cntFit) {
		return bakOffset.get(curOffset + cntFit-1) - (curOffset>0?bakOffset.get(curOffset-1):0) +getHeaderSize(cntFit) <= File.recordPageSize;
	}

	private Integer fitRecords(int thisid, List<Record> rs)
			throws Throwable {
		int previd;
		previd = thisid;
		thisid = bm.newPage();
		if(previd >= 0){
			RecordPage pp = bm.getRecordPage(previd, false);
			pp.beginTransaction();
			pp.dirty = pp.nextPageID!=thisid;
			pp.nextPageID = thisid;
			pp.commit();
		}
		RecordPage thisp = bm.getRecordPage(thisid, true);
		thisp.beginTransaction();
		thisp.dirty = true;
		thisp.prevPageID = previd;
		int cntFit = rs.size();
		for(int j = 0;j<cntFit;j++){
			boolean flag = thisp.tryAppendRecord(rs.get(j));
			assert flag;
		}
		thisp.commit();
		return thisid;
	}
	
	private Integer fitRecords(Integer thisid, Record r)
			throws Throwable {
		Integer previd;
		previd = thisid;
		thisid = bm.newPage();
		if(previd != null){
			RecordPage pp = bm.getRecordPage(previd, false);
			pp.beginTransaction();
			pp.dirty = true;
			pp.nextPageID = thisid;
			pp.commit();
		}
		RecordPage thisp = bm.getRecordPage(thisid, true);
		thisp.beginTransaction();
		thisp.dirty = true;
		thisp.prevPageID = previd;
		boolean flag = thisp.tryAppendRecord(r);
		assert flag;
		thisp.commit();
		return thisid;
	}

	private static Integer fitPartial(BufferManager bm, byte[] bytes, Integer thisid, int startOffset, int recordSize)
			throws Throwable {
		Util.warn("Partial record detected!!!");
		Integer previd;
		int fittedSize = 0;
		int maxFitSize = getMaxFitSize();
		while(fittedSize < recordSize){
			previd = thisid;
			thisid = bm.newPage();
			if(previd != null){
				RecordPage pp = bm.getRecordPage(previd, false);
				pp.beginTransaction();
				pp.dirty = true;
				pp.nextPageID = thisid;
				pp.commit();
			}
			RecordPage thisp = bm.getRecordPage(thisid, true);
			thisp.beginTransaction();
			thisp.dirty = true;
			thisp.prevPageID = previd;
			thisp.putPartial(bytes, startOffset + fittedSize, maxFitSize, fittedSize + maxFitSize >= recordSize);
			fittedSize += maxFitSize;
			thisp.commit();
		}
		return thisid;
	}
	
	private static int getMaxFitSize(){
		return (int) (File.recordPageSize - getHeaderSize(1) - Integer.SIZE / Byte.SIZE);
	}
	
	private void putPartial(byte[] b, int s, int maxFitSize,
			boolean isEnd) throws Throwable {
		beginTransaction();
		dirty = true;
		cntRecord = 1;
		records = new ArrayList<Record>();
		int length = Math.min(maxFitSize, b.length - s);
		partialBytes = new byte[length];
		System.arraycopy(b, s, partialBytes, 0, length);
		offsetTable = new ArrayList<Integer>();
		offsetTable.add(isEnd ? -2 : -1);
		commit();
	}

	private byte[] expandAndReverse(byte[] z) {
		byte[] tmp = new byte[(int) (File.recordPageSize - headerSize())];
		System.arraycopy(z, 0, tmp, 0, z.length);
//		Collections.reverse(Arrays.asList(tmp));
		ArrayUtils.reverse(tmp);
		return tmp;
	}

	public boolean isPartial(){
		return cntRecord == 1 && offsetTable.get(0) == -1;
	}
	
	public boolean endOfPartial(){
		return cntRecord == 1 && offsetTable.get(0) == -2;
	}
	
	public int headerSize(){
		return getHeaderSize(cntRecord);
	}
	
	public static int getHeaderSize(int cnt){
		return (3+cnt) * Integer.SIZE / Byte.SIZE;
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
			lastOffset = offsetTable.get(i);
		}
	}
	
	// XXX this method is not for record insertion!!! only for record updating!! so that the size in memory can be guaranteed.
	public void addRecord(Record r, int idx){
		dirty = true;
		parseRecord(r.schema);
		records.add(idx, r);
		byte[] recordBytes = Record.toByte(r);
		int lengthOfRecord = recordBytes.length;
		int totalLength = offsetTable.size() > 0 ? offsetTable.get(offsetTable.size()-1) : 0;
		byte[] newb = new byte[totalLength + lengthOfRecord];
		int lastOffset = idx>0?offsetTable.get(idx-1):0;
		System.arraycopy(partialBytes, 0, newb, 0, lastOffset);
		System.arraycopy(recordBytes, 0, newb, lastOffset, lengthOfRecord);
		System.arraycopy(partialBytes, lastOffset, newb, lastOffset + lengthOfRecord, totalLength - lastOffset);
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
		
		if(lengthOfRecord > getMaxFitSize())
			return appendPartialRecord(recordBytes, r, true);
		else if(partialBytes.length + lengthOfRecord + getHeaderSize(cntRecord+1) > File.recordPageSize)
			return appendPartialRecord(recordBytes, r, false);
		addRecord(r, cntRecord);
		return true;
	}
	
	private boolean appendPartialRecord(byte[] recordBytes, Record r, boolean split) {
		try {
			dirty = true;
			Integer realNext = nextPageID;
			Integer thisid = split?fitPartial(bm, recordBytes, pageID, 0, recordBytes.length):fitRecords(pageID, r);
			RecordPage rp = bm.getRecordPage(realNext, false);
			rp.beginTransaction();
			rp.dirty = rp.prevPageID!=thisid;
			rp.prevPageID = thisid;
			rp.commit();
			rp = bm.getRecordPage(thisid, false);
			rp.beginTransaction();
			rp.dirty = rp.nextPageID!=realNext;
			rp.nextPageID = realNext;
			rp.commit();
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delRecord(Schema schema, int idx){
		dirty = true;
		parseRecord(schema);
		records.remove(idx);
		int totalLength = offsetTable.size() > 0 ? offsetTable.get(offsetTable.size()-1) : 0;
		int lastOffset = (idx>0?offsetTable.get(idx-1):0);
		int lengthOfRecord = offsetTable.get(idx) - lastOffset;
		byte[] newb = new byte[totalLength - lengthOfRecord];
		System.arraycopy(partialBytes, 0, newb, 0, lastOffset);
		System.arraycopy(partialBytes, offsetTable.get(idx), newb, 0, totalLength - offsetTable.get(idx));
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

	public boolean tryReclaim() {
		if(!canReclaim() )return false;
		try {
			RecordPage rp = bm.getRecordPage(prevPageID, false);
			rp.beginTransaction();
			rp.dirty = true;
			rp.nextPageID = nextPageID;
			rp.commit();
			rp = bm.getRecordPage(nextPageID, false);
			rp.beginTransaction();
			rp.dirty = true;
			rp.prevPageID = prevPageID;
			rp.commit();
			delete();
			bm.releasePage(pageID);
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean canReclaim() {
		return cntRecord <= 0 && nextPageID!=pageID;
	}
}
