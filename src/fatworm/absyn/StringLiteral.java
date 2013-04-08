package fatworm.absyn;

import fatworm.field.Field;
import fatworm.field.VARCHAR;
import fatworm.util.Env;
import fatworm.util.Util;

public class StringLiteral extends Expr {
	
	public String s;

	public StringLiteral(String s) {
		this.s = s;
		this.isConst = true;
		this.size=1;
		this.value = new VARCHAR(s);
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\"", s);
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(s);
	}

	@Override
	public Field eval(Env env) {
		return value;
	}

}
