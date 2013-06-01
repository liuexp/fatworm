package fatworm.page;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import fatworm.io.File;

public class RawPage implements Page {

	public RawPage() {
		// TODO Auto-generated constructor stub
	}

	public RawPage(File f, int pageid, boolean create) {
		lastTime = System.currentTimeMillis();
		dataFile = f;
		pageID = pageid;
		if(!create){
			loadPage();
		}else{
			nextPageID = -1;
			prevPageID = -1;
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
	
	@Override
	public void flush() throws IOException {
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
		return Integer.SIZE / Byte.SIZE;
	}

	public synchronized int putLong(int offset, long val) {
		buf.position(offset);
		buf.putLong(val);
		dirty = true;
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
	@Override
	public void fromBytes(byte[] b) {
		buf = ByteBuffer.wrap(b);
	}
	@Override
	public byte[] toBytes() {
		return buf.array();
	}

}
