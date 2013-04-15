package fatworm.absyn;

import fatworm.field.DATE;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;

public class TimestampLiteral extends Expr {

	public DATE i;
	public TimestampLiteral(java.sql.Timestamp v) {
		this.isConst = true;
		this.size=1;
		this.i = new DATE(v);
		value = this.i;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(i);
	}

	@Override
	public Field eval(Env env) {
		return i;
	}

}
