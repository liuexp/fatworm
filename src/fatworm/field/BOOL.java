package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;
import fatworm.util.Util;

public class BOOL extends Field {

	public boolean v;
	public BOOL(boolean x){
		v = x;
		type = java.sql.Types.BOOLEAN;
	}
	public BOOL(String x) {
		v = Util.toBoolean(x);
		type = java.sql.Types.BOOLEAN;
	}
	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		switch(x.type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
		case java.sql.Types.DECIMAL:
		case java.sql.Types.FLOAT:
			return Field.cmpHelper(op, toDecimal(), x.toDecimal());
		}
		error2("applyWithComp missing types!");
		return false;
	}
	@Override
	public String toString() {
		return v ? "true" : "false";
	}
	@Override
	public int hashCode() {
		return v?1:0;
	}
	@Override
	public BigDecimal toDecimal() {
		return new BigDecimal(v?1:0).setScale(10);
	}
}
