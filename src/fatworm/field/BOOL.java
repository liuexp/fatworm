package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.Env;
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
	public boolean applyWithComp(Env env, BinaryOp op, Field x) {
		switch(x.type){
		case java.sql.Types.BOOLEAN:
		case java.sql.Types.INTEGER:
			switch(op){
			case EQ:
				return getInt(env) == x.getInt(env);
			case NEQ:
				return getInt(env) != x.getInt(env);
			default:
				throw new RuntimeException("applyWithComp missing op!");
			}
		}
		throw new RuntimeException("applyWithComp missing types!");
	}
	public int getInt(Env env) {
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
}
