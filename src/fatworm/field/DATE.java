package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
import fatworm.util.Util;

public class DATE extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866417704280885487L;
	public java.sql.Timestamp v;
	public DATE(){
		type = java.sql.Types.DATE;
	}
	public DATE(String x) {
		this();
		v = Util.parseTimestamp(Util.trim(x));
	}
	public DATE(Long x) {
		this();
		v = new java.sql.Timestamp(x);
	}
	public DATE(java.sql.Timestamp x){
		this();
		v = x;
	}
	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		switch(x.type){
		case java.sql.Types.TIMESTAMP:
			return Field.cmpHelper(op, v, ((TIMESTAMP)x).v);
		case java.sql.Types.DATE:
			return Field.cmpHelper(op, v, ((DATE)x).v);
		case java.sql.Types.CHAR:
			return Field.cmpHelper(op, v, java.sql.Timestamp.valueOf(((CHAR)x).v));
		case java.sql.Types.VARCHAR:
			return Field.cmpHelper(op, v, java.sql.Timestamp.valueOf(((VARCHAR)x).v));
		}
		error2("DATE.compWith missing type");
		return false;
	}
	@Override
	public int hashCode() {
		return v.hashCode();
	}
	@Override
	public String toString() {
		return "'" + v.toString() + "'";
	}
	@Override
	public void pushByte(ByteBuilder b, int A, int B) {
		b.putLong(v.getTime());
	}
}
