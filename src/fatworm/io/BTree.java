package fatworm.io;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Timestamp;

import fatworm.page.BTreePage;
import fatworm.page.RawPage;
import fatworm.field.BOOL;
import fatworm.field.CHAR;
import fatworm.field.DATE;
import fatworm.field.DECIMAL;
import fatworm.field.FLOAT;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.TIMESTAMP;
import fatworm.field.VARCHAR;
import fatworm.util.Util;

public class BTree {

	// every node fills up a whole page.

	public static final int MODOFFSET = (int) (File.btreePageSize / 4); //how many slots could there be
	public static final int BytesPerChar = (int) Charset.defaultCharset().newEncoder().maxBytesPerChar();
	public static final int LongSize = Long.SIZE / Byte.SIZE;
	public static final int IntSize = Integer.SIZE / Byte.SIZE;
	//public int fanout;
	public int type; //key type
	public BTreePage root;
	public BufferManager bm;

	public BTree(BufferManager bm, int type) throws Throwable {
		this.bm = bm;
		this.type = type;
		root = bm.getBTreePage(this, bm.newPage(), type, true);
	}
	public BTree(BufferManager bm, Integer pageID, int type) throws Throwable {
		this.bm = bm;
		this.type = type;
		root = bm.getBTreePage(this, pageID, type, false);
	}
	
	private static class IntKey extends BKey{

		public int k;
		public IntKey(int z){
			k = z;
		}
		@Override
		public void delete() {}

		@Override
		public int compareTo(BKey o) {
			int z = ((IntKey)o).k;
			return k < z ? -1 : ( k>z? 1 : 0);
		}

		@Override
		public byte[] toByte() {
			return Util.intToByte(k);
		}
		
		@Override
		public int hashCode() {
			return k;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof BKey)
				return 0 == compareTo((BKey)o);
			else return false;
		}
		
		@Override
		public String toString(){
			return "" + k;
		}
	}
	
	private static class FloatKey extends BKey{

		public float k;
		public FloatKey(float k){
			this.k = k;
		}
		public FloatKey(int z){
			this.k = Float.intBitsToFloat(z);
		}
		@Override
		public void delete() {}

		@Override
		public int compareTo(BKey o) {
			float z = ((FloatKey)o).k;
			return k < z? -1 : (k>z? 1 : 0);
		}

		@Override
		public byte[] toByte() {
			return Util.intToByte(Float.floatToIntBits(k));
		}
		@Override
		public int hashCode() {
			return new Float(k).hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof BKey)
				return 0 == compareTo((BKey)o);
			else return false;
		}
		
		@Override
		public String toString(){
			return "" + k;
		}
	}
	
	private class DecimalKey extends BKey{

		public BigDecimal k;
		public int encodedOffset = -1;
		// encodedOffset = pageID * 1024 + slotOffset in page
		// XXX note here we assume at most 16GB of space to index so that encodedOffset is just an 4 byte int
		public int pageID = -1;
		public DecimalKey(BigDecimal v) {
			k = v;
		}
		public DecimalKey(int v) throws Throwable {
			encodedOffset = v;
			pageID = encodedOffset / MODOFFSET;
			fromPage();
		}

		private void fromPage() throws Throwable {
			RawPage rp = bm.getRawPage(pageID, false);
			int slotOffset = encodedOffset % MODOFFSET;
			int curOffset = 8;
			for(int i=0;i<slotOffset;i++){
				curOffset += 4 + rp.getInt(curOffset);
			}
			curOffset += 4;// skip the length
			k = rp.getDecimal(curOffset);
		}
		
		private void toPage() throws Throwable {
			RawPage rp = bm.newRawPage(4 + RawPage.getSize(k));
			rp.beginTransaction();
			pageID = rp.getID();
			int curOffset = 0;
			curOffset+=4;
			int cnt = rp.cnt;
			rp.newEntry();
			encodedOffset =pageID * MODOFFSET + cnt;
			curOffset+=4;
			for(int i=0;i<cnt;i++){
				curOffset += 4 + rp.getInt(curOffset);
			}
			int length = rp.putDecimal(curOffset + 4, k);
			rp.putInt(curOffset, length);
			rp.commit();
		}
		@Override
		public void delete() throws Throwable {
			// FIXME
//			RawPage rp = bm.getRawPage(pageID, false);
//			int curOffset = 4;
//			int cnt = rp.getInt(curOffset)-1;
//			rp.beginTransaction();
//			rp.putInt(curOffset, cnt);
//			if(cnt <= 0)
//				bm.releasePage(pageID);
//			else
//				rp.commit();
		}
		@Override
		public int compareTo(BKey o) {
			BigDecimal z = ((DecimalKey)o).k;
			return k.compareTo(z);
		}

		@Override
		public byte[] toByte() throws Throwable {
			toPage();
			return Util.intToByte(encodedOffset);
		}
		
		@Override
		public int hashCode() {
			return k.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof BKey)
				return 0 == compareTo((BKey)o);
			else return false;
		}
		
		@Override
		public String toString(){
			return "" + k;
		}
	}
	
	private static class TimestampKey extends BKey{

		public Timestamp k;
		public TimestampKey(Timestamp v) {
			k = v;
		}
		public TimestampKey(Long v) {
			k = new Timestamp(v);
		}

		@Override
		public void delete() {}

		@Override
		public int compareTo(BKey o) {
			Timestamp z = ((TimestampKey)o).k;
			return k.compareTo(z);
		}

		@Override
		public int keySize() {
			return LongSize;
		}

		@Override
		public byte[] toByte() {
			return Util.longToByte(k.getTime());
		}
		@Override
		public int hashCode() {
			return k.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof BKey)
				return 0 == compareTo((BKey)o);
			else return false;
		}
		
		@Override
		public String toString(){
			return "TIMESTAMP=" + k;
		}
	}
	
	private class StringKey extends BKey{

		public String k;
		public int encodedOffset;
		public int pageID = -1;
		public StringKey(String v) {
			k = v;
		}
		public StringKey(int v) throws Throwable{
			encodedOffset = v;
			pageID = encodedOffset / MODOFFSET;
			fromPage();
		}

		private void fromPage() throws Throwable {
			RawPage rp = bm.getRawPage(pageID, false);
			int slotOffset = encodedOffset % MODOFFSET;
			int curOffset = 8;
			for(int i=0;i<slotOffset;i++){
				curOffset += 4 + rp.getInt(curOffset);
			}
			k = rp.getString(curOffset);
		}
		
		private void toPage() throws Throwable {
			RawPage rp = bm.newRawPage(4 + RawPage.getSize(k));
			rp.beginTransaction();
			pageID = rp.getID();
			int curOffset = 0;
			curOffset+=4;
			int cnt = rp.cnt;
			rp.newEntry();
			encodedOffset =pageID * MODOFFSET + cnt;
			curOffset+=4;
			for(int i=0;i<cnt;i++){
				curOffset += 4 + rp.getInt(curOffset);
			}
			rp.putString(curOffset, k);
			rp.commit();
		}
		
		@Override
		public void delete() throws Throwable {
			// FIXME not correct
//			RawPage rp = bm.getRawPage(pageID, false);
//			int curOffset = 4;
//			int cnt = rp.getInt(curOffset)-1;
//			rp.beginTransaction();
//			rp.putInt(curOffset, cnt);
//			if(cnt <= 0)
//				bm.releasePage(pageID);
//			else
//				rp.commit();
		}

		@Override
		public int compareTo(BKey o) {
			String z = ((StringKey)o).k;
			return k.compareToIgnoreCase(z);
		}

		@Override
		public byte[] toByte() throws Throwable {
			toPage();
			return Util.intToByte(encodedOffset);
		}
		@Override
		public int hashCode() {
			return k.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof BKey)
				return 0 == compareTo((BKey)o);
			else return false;
		}
		
		@Override
		public String toString(){
			return "'" + k + "'";
		}
	}

	public BKey newBKey(Field f){
		switch(f.type){
		case java.sql.Types.BOOLEAN:
			return new IntKey(((BOOL)f).v ? 1 : 0);
		case java.sql.Types.INTEGER:
			return new IntKey(((INT)f).v);
		case java.sql.Types.FLOAT:
			return new FloatKey(((FLOAT)f).v);
		case java.sql.Types.CHAR:
			return new StringKey(((CHAR)f).v);
		case java.sql.Types.VARCHAR:
			return new StringKey(((VARCHAR)f).v);
		case java.sql.Types.DECIMAL:
			return new DecimalKey(((DECIMAL)f).v);
		case java.sql.Types.DATE:
			return new TimestampKey(((DATE)f).v);
		case java.sql.Types.TIMESTAMP:
			return new TimestampKey(((TIMESTAMP)f).v);
			default:
				Util.error("meow@BTree");
		}
		return null;
	}
	
	public BKey getBKey(int n, int type) throws Throwable{
		switch(type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
			return new IntKey(n);
		case java.sql.Types.FLOAT:
			return new FloatKey(n);
		case java.sql.Types.CHAR:
		case java.sql.Types.VARCHAR:
			return new StringKey(n);
		case java.sql.Types.DECIMAL:
			return new DecimalKey(n);
			default:
				Util.error("meow@BTree");
		}
		return null;
	}
	
	public BKey getBKey(long n, int type){
		switch(type){
		case java.sql.Types.DATE:
		case java.sql.Types.TIMESTAMP:
			return new TimestampKey(n);
			default:
				Util.error("meow@BTree");
		}
		return null;
	}

}
