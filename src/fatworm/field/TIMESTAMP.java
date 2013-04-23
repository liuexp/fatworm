package fatworm.field;

import java.sql.Timestamp;

import fatworm.absyn.BinaryOp;
import fatworm.util.Util;

public class TIMESTAMP extends Field {

	public java.sql.Timestamp v;

	public TIMESTAMP(){
		type = java.sql.Types.TIMESTAMP;
	}
	public TIMESTAMP(String x) {
		this();
		v = Util.parseTimestamp(Util.trim(x));
	}
	public TIMESTAMP(Timestamp timestamp) {
		this();
		v = timestamp;
	}
	@Override
	public String toString() {
		return v.toString();
	}
	@Override
	public int hashCode() {
		return v.hashCode();
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
		error2("TIMESTAMP.compWith missing type");
		return false;
	}
}
