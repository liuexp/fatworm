package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;

public class DECIMAL extends Field{

	public BigDecimal v;
	public DECIMAL(String x) {
		// TODO Auto-generated constructor stub
		this();
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
		error("DECIMAL.compWith missing type");
		return false;
	}

	@Override
	public int getInt() {
		error("DECIMAL.getInt dead end.");
		return 0;
	}
	@Override
	public int hashCode() {
		return v.hashCode();
	}
	@Override
	public String toString() {
		return "'" + v.toString() + "'";
	}

}
