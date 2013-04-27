package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
import fatworm.util.Util;

public class CHAR extends Field {

	String v;
	//int l;
	public CHAR(){
		type = java.sql.Types.CHAR;
	}
	public CHAR(String x) {
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
	public void pushByte(ByteBuilder b) {
		//FIXME check if this l is needed here
		//b.putInt(l);
		b.putString(v);
	}
}
