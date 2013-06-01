package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
import fatworm.util.Util;

public class FLOAT extends Field {

	public double v;

	public FLOAT() {
		type = java.sql.Types.FLOAT;
	}

	public FLOAT(String x) {
		this();
		v = Float.valueOf(Util.trim(x));
	}
	
	public FLOAT(float x){
		this();
		v = x;
	}

	public FLOAT(double x) {
		this();
		v = x;
	}

	public BigDecimal toDecimal() {
		return new BigDecimal(v).setScale(10, BigDecimal.ROUND_HALF_EVEN);
	}
	
	@Override
	public int hashCode() {
		return new Float(v).hashCode();
	}
	@Override
	public String toString() {
		return "" + v;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		BigDecimal value = toDecimal();
		switch(x.type){
		case java.sql.Types.DECIMAL:
			return Field.cmpHelper(op, value, ((DECIMAL)x).v);
		case java.sql.Types.INTEGER:
			return Field.cmpHelper(op, value, ((INT)x).toDecimal());
		case java.sql.Types.FLOAT:
			return Field.cmpHelper(op, value, ((FLOAT)x).toDecimal());
		}
		error2("FLOAT.compWith missing type");
		return false;
	}

	@Override
	public void pushByte(ByteBuilder b) {
		b.putDouble(v);
	}

}
