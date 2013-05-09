package fatworm.util;

import java.nio.ByteBuffer;

public class ByteBuilder {

	ByteBuffer buf;
	int size;
	
	public void putBool(boolean v) {
		putByte(v?(byte)1:(byte)0);
	}

	public void putByte(byte b) {
		ensureCapacity(1);
		buf.put(b);
		updateSize();
	}
	
	private void updateSize() {
		size = Math.max(buf.position(),size);
	}

	public void putChar(char v){
		ensureCapacity(2);
		buf.putChar(v);
		updateSize();
	}
	
	public void putInt(int v) {
		ensureCapacity(4);
		buf.putInt(v);
		updateSize();
	}

	private void ensureCapacity(int i) {
		if(buf.capacity() >= size + i)
			return;
		int ptr = buf.position();
		byte[] nbuff = new byte[buf.capacity() * 2];
		byte[] obuff = buf.array();
		System.arraycopy(obuff, 0, nbuff, 0, size);
		buf = ByteBuffer.wrap(nbuff);
		buf.position(ptr);
	}

	public void putString(String v) {
		putBytes(v.getBytes());
	}

	public void putBytes(byte[] b) {
		putInt(b.length);
		putBytes(b, 0, b.length);
	}

	public void putBytes(byte[] b, int i, int length) {
		ensureCapacity(length);
		buf.put(b, i, length);
		updateSize();
	}

	public void putLong(long v) {
		ensureCapacity(8);
		buf.putLong(v);
		updateSize();
	}

	public void putDouble(double v) {
		ensureCapacity(8);
		buf.putDouble(v);
		updateSize();
	}
	
	public byte[] getByteArray(int size){
		buf.limit(size);
		return buf.array();
	}

}
