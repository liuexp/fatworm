package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.Env;
import fatworm.util.Util;

public class CHAR extends Field {

	String v;
	public CHAR(){
		type = java.sql.Types.CHAR;
	}
	public CHAR(String x) {
		this();
		v = Util.trim(x);
	}
	@Override
	public boolean applyWithComp(Env env, BinaryOp op, Field x) {
		return Field.cmpString(op, v, x.toString());
	}
	@Override
	public int getInt(Env env) {
		error("CHAR.getInt dead end.");
		return 0;
	}
	@Override
	public String toString() {
		return "'" + v + "'";
	}
	@Override
	public int hashCode() {
		return v.hashCode();
	}
}
