package fatworm.page;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import fatworm.io.File;

public class RawPage implements Page {
	// first 4 byte: size overall
	// next 4 byte: cnt of not marked deleted
	public static TreeSet<rpEntry> pool = new TreeSet<rpEntry> ();
	private static class rpEntry implements Comparable<rpEntry>{
		public int remainingSize,pageID;
		rpEntry(int rem, int pid){
			remainingSize = rem;
			pageID = pid;
		}
		@Override
		public int compareTo(rpEntry o) {
			Integer aa = remainingSize;
			Integer bb = o.remainingSize;
			Integer p1 = pageID;
			Integer p2 = o.pageID;
			return 0==aa.compareTo(bb) ? p1.compareTo(p2) : aa.compareTo(bb);
		}
		@Override
		public boolean equals(Object o){
			if(o instanceof rpEntry)
				return pageID == ((rpEntry)o).pageID;
			return false;
		}
	}
	public RawPage(){}
	public RawPage(File f, int pageid, boolean create) {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		if(!create){
			loadPage();
		}else{
			nextPageID = -1;
			prevPageID = -1;
			size = 4;
			pool.add(new rpEntry(remainingSize(), pageID));
		}
	}

	protected Integer pageID;
	public Integer nextPageID;
	public Integer prevPageID;
	public boolean dirty = false;
	protected File dataFile;
	protected Long lastTime;
	public int size;
	public ByteBuffer buf = ByteBuffer.allocateDirect(File.pageSize);
	public int cnt = 1;
	
	@Override
	public synchronized void flush() throws IOException {
		write();
	}
	@Override
	public Long getTime() {
		return lastTime;
	}
	@Override
	protected void finalize() throws Throwable {
		flush();
	}
	@Override
	public Integer getID() {
		return pageID;
	}
	@Override
	public boolean isPartial() {
		return false;
	}
	@Override
	public int headerSize() {
		return -1;
	}
	@Override
	public int remainingSize() {
		return File.pageSize - size;
	}
	@Override
	public void markFree() {
		dirty = false;
	}

	public synchronized void loadPage() {
		dataFile.read(buf, pageID);
		fromBytes(buf.array());
	}

	public synchronized void write() {
		if(!dirty) return;
		dataFile.write(buf, pageID);
		dirty = false;
	}

	public synchronized void append() {
		dataFile.append(buf);
	}

	public synchronized int getInt(int offset) {
		buf.position(offset);
		return buf.getInt();
	}
	
	public synchronized long getLong(int offset) {
		buf.position(offset);
		return buf.getLong();
	}
	
	public synchronized int putInt(int offset, int val) {
		buf.position(offset);
		buf.putInt(val);
		dirty = true;
		updateSize(buf.position());
		return Integer.SIZE / Byte.SIZE;
	}

	public synchronized int putLong(int offset, long val) {
		buf.position(offset);
		buf.putLong(val);
		dirty = true;
		updateSize(buf.position());
		return Long.SIZE / Byte.SIZE;
	}
	
	public synchronized String getString(int offset) {
		buf.position(offset);
		int len = buf.getInt();
		byte[] byteval = new byte[len];
		buf.get(byteval);
		return new String(byteval);
	}

	public synchronized int putString(int offset, String val) {
		dirty = true;
		return putBytes(offset, val.getBytes());
	}
	
	public synchronized byte[] getBytes(int offset) {
		buf.position(offset);
		int len = buf.getInt();
		byte[] byteval = new byte[len];
		buf.get(byteval);
		return byteval;
	}
	
	public synchronized int putBytes(int offset, byte[] byteval) {
		buf.position(offset);
		buf.putInt(byteval.length);
		buf.put(byteval);
		dirty = true;
		updateSize(buf.position());
		return byteval.length + Integer.SIZE / Byte.SIZE;
	}
	
	public synchronized BigDecimal getDecimal(int offset) {
		int scale = getInt(offset);
		offset += 4;
		byte[] bval = getBytes(offset);
		return new BigDecimal(new BigInteger(bval), scale);
	}
	
	public synchronized int putDecimal(int offset, BigDecimal val) {
		int ret = 0;
		ret += putInt(offset, val.scale());
		offset += 4;
		ret += putBytes(offset, val.unscaledValue().toByteArray());
		dirty = true;
		return ret;
	}
	
	public static int getSize(BigDecimal v){
		return 4 + getSize(v.unscaledValue().toByteArray());
	}
	public static int getSize(byte[] v) {
		return 4 + v.length;
	}
	public static int getSize(String v){
		return getSize(v.getBytes());
	}

	@Override
	public void fromBytes(byte[] b) {
		buf = ByteBuffer.wrap(b);
		buf.rewind();
		size = buf.getInt();
		cnt = buf.getInt();
		buf.rewind();
	}
	@Override
	public byte[] toBytes() {
		buf.position(0);
		buf.putInt(size);
		buf.putInt(cnt);
		return buf.array();
	}
	
	public void updateSize(int o){
		pool.remove(new rpEntry(remainingSize(), pageID));
		size = Math.max(size, o);
		pool.add(new rpEntry(remainingSize(), pageID));
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof RawPage)
			return pageID.equals(((RawPage)o).pageID);
		return false;
	}

	public synchronized static Integer newPoolPage(int expect) {
		Iterator<rpEntry> itr = pool.iterator();
		while(itr.hasNext()){
			rpEntry p = itr.next();
			if(p.remainingSize * 4 < File.pageSize)
				itr.remove();
			if(p.remainingSize >= expect)
				return p.pageID;
		}
		return null;
	}
	public void newEntry() {
		cnt ++;
		dirty = true;
	}
}
