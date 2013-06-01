package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
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
	@Override
	public void pushByte(ByteBuilder b, int A, int B) {
		String z = v.substring(0, Math.max(0, Math.min(v.length(), A)));
//		Util.warn("truncated to "+z + " from " +v + " A=" + A);
		b.putString(z);
	}

}
