package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.Env;

public class TIMESTAMP extends Field {

	public java.sql.Timestamp v;

	public TIMESTAMP(){
		type = java.sql.Types.TIMESTAMP;
	}
	public TIMESTAMP(String x) {
		// TODO Auto-generated constructor stub
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
	public boolean applyWithComp(Env env, BinaryOp op, Field x) {
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
		error("TIMESTAMP.compWith missing type");
		return false;
	}
	@Override
	public int getInt(Env env) {
		error("TIMESTAMP.getInt dead end.");
		return v.hashCode();
	}
}
