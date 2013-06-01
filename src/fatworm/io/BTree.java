package fatworm.io;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.sql.Timestamp;

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

	public int type; //key type
	public BTree() {
		// TODO Auto-generated constructor stub
	}
	
	private class BCursor {
		
	}
	
	private interface BKey {
		public void delete();	// to reclaim space from data/string page.
		public int compareTo(Object o);
		public int keySize();
		public byte[] toByte();
	}
	
	private class IntKey implements BKey{

		public int k;
		public IntKey(int z){
			k = z;
		}
		@Override
		public void delete() {}

		@Override
		public int compareTo(Object o) {
			int z = ((IntKey)o).k;
			return k < z ? -1 : ( k>z? 1 : 0);
		}

		@Override
		public int keySize() {
			return Integer.SIZE / Byte.SIZE;
		}

		@Override
		public byte[] toByte() {
			return Util.intToByte(k);
		}
		
	}
	
	private class FloatKey implements BKey{

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
		public int compareTo(Object o) {
			float z = ((FloatKey)o).k;
			return k < z? -1 : (k>z? 1 : 0);
		}

		@Override
		public int keySize() {
			return Integer.SIZE / Byte.SIZE;
		}

		@Override
		public byte[] toByte() {
			return Util.intToByte(Float.floatToIntBits(k));
		}
		
	}
	
	private class DecimalKey implements BKey{

		public DecimalKey(BigDecimal v) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int keySize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public byte[] toByte() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class TimestampKey implements BKey{

		public TimestampKey(Timestamp v) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int keySize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public byte[] toByte() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private class StringKey implements BKey{

		public StringKey(String v) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int keySize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public byte[] toByte() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	public BKey getBKey(Field f){
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
				Util.error("meow@BTreePage");
		}
		return null;
	}
}
