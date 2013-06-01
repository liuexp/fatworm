package fatworm.page;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.TreeSet;

import fatworm.io.File;
import fatworm.util.Util;

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
	public RawPage(File f, int pageid, boolean create) throws Throwable {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		if(!create){
			loadPage();
		}else{
			nextPageID = -1;
			prevPageID = -1;
			size = 4;
			synchronized (pool){
				pool.add(new rpEntry(remainingSize(), pageID));
			}
		}
	}

	protected int pageID;
	public int nextPageID;
	public int prevPageID;
	public boolean dirty = false;
	protected File dataFile;
	protected Long lastTime;
	public int size;
	public ByteBuffer buf = ByteBuffer.wrap(new byte[(int) File.btreePageSize]);//ByteBuffer.allocateDirect(File.pageSize);
	public int cnt = 0;
	public boolean hasFlushed = false;
	public int inTransaction = 0;
	
	@Override
	public synchronized void flush() throws Throwable {
		
		write();
//		hasFlushed = true;
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
		return (int) (File.btreePageSize - size);
	}
	@Override
	public void markFree() {
		dirty = false;
	}

	public synchronized void loadPage() throws Throwable {
		dataFile.read(buf, pageID);
		fromBytes(buf.array());
	}

	public synchronized void write() throws Throwable {
		if(!dirty) return;
		toBytes();
		// TODO verify if it's in transaction, then there's no need to flush at this moment
		if(isInTransaction()){
			Util.warn("I'm "+pageID +" in transaction, fake flushed");
			return;
		}
		hasFlushed = true;
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
	
	public synchronized int putByteArray(int offset, byte[] byteval) {
		buf.position(offset);
		buf.put(byteval);
		dirty = true;
		updateSize(buf.position());
		return byteval.length;
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
	public void fromBytes(byte[] b) throws Throwable {
		buf = ByteBuffer.wrap(b);
		buf.position(0);
		size = buf.getInt();
		cnt = buf.getInt();
		buf.position(0);
	}
	@Override
	public byte[] toBytes() throws Throwable {
		buf.position(0);
		buf.putInt(size);
		buf.putInt(cnt);
		return buf.array();
	}
	
	public synchronized void updateSize(int o){
		synchronized(pool){
			pool.remove(new rpEntry(remainingSize(), pageID));
			if(size < o){
				dirty = true;
				size = o;
			}
			pool.add(new rpEntry(remainingSize(), pageID));
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof RawPage)
			return pageID==((RawPage)o).pageID;
		return false;
	}

	public synchronized static Integer newPoolPage(int expect) {
		Iterator<rpEntry> itr = pool.iterator();
		while(itr.hasNext()){
			rpEntry p = itr.next();
			if(p.remainingSize * 4 < File.btreePageSize)
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
	@Override
	public void beginTransaction() {
		inTransaction++;
		//dirty = true;
	}
	@Override
	public synchronized void commit() throws Throwable {
		if(inTransaction>0)inTransaction--;
		if(hasFlushed){
			write();
		}
	}
	@Override
	public boolean isInTransaction() {
		return inTransaction != 0;
	}
}
