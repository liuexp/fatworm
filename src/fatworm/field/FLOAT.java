package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;

public class FLOAT extends Field {

	public double v;

	public FLOAT() {
		type = java.sql.Types.FLOAT;
	}

	public FLOAT(String x) {
		// TODO Auto-generated constructor stub
		this();
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
		return new BigDecimal(v).setScale(10, BigDecimal.ROUND_HALF_UP);
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
		error("FLOAT.compWith missing type");
		return false;
	}

}
