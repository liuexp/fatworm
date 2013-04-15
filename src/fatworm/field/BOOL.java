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
			switch(op){
			case EQ:
				return getInt() == x.getInt();
			case NEQ:
				return getInt() != x.getInt();
			default:
				throw new RuntimeException("applyWithComp missing op!");
			}
		}
		throw new RuntimeException("applyWithComp missing types!");
	}
	public int getInt() {
		return v?1:0;
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
