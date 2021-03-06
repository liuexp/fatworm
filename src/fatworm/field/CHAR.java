package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
import fatworm.util.Util;

public class CHAR extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 762754381819824863L;
	public String v;
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
	public void pushByte(ByteBuilder b, int A, int B) {
		String z = v.substring(0, Math.max(0, Math.min(v.length(), A)));
//		Util.warn("truncated to "+z + " from " +v + " A=" + A);
		/*
		for(int i=v.length();i<A;i++){
			z = z + " ";
		}*/
		b.putString(z);
	}
}
