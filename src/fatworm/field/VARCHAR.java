package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.Util;

public class VARCHAR extends Field {

	public String v;

	public VARCHAR(){
		type = java.sql.Types.VARCHAR;
	}
	public VARCHAR(String x) {
		this();
		v = Util.trim(x);
	}
	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		return Field.cmpString(op, this.toString(), x.toString());
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
