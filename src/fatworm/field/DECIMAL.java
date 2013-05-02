package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;
import fatworm.util.Util;

public class DECIMAL extends Field{

	public BigDecimal v;
	public DECIMAL(String x) {
		this();
		v = new BigDecimal(Util.trim(x)).setScale(10, BigDecimal.ROUND_HALF_EVEN);
	}
	public DECIMAL() {
		type = java.sql.Types.DECIMAL;
	}
	public DECIMAL(BigDecimal x){
		this();
		v = x;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		switch(x.type){
		case java.sql.Types.DECIMAL:
			return Field.cmpHelper(op, v, ((DECIMAL)x).v);
		case java.sql.Types.INTEGER:
			return Field.cmpHelper(op, v, ((INT)x).toDecimal());
		case java.sql.Types.FLOAT:
			return Field.cmpHelper(op, v, ((FLOAT)x).toDecimal());
		}
		error2("DECIMAL.compWith missing type");
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
	public BigDecimal toDecimal(){
		return v;
	}
	@Override
	public void pushByte(ByteBuilder b) {
		//b.putString(v.toPlainString());
		b.putInt(v.scale());
		b.putBytes(v.unscaledValue().toByteArray());
	}
}
