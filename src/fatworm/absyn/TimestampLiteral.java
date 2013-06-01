package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.field.DATE;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;

public class TimestampLiteral extends Expr {

	public DATE i;
	public TimestampLiteral(java.sql.Timestamp v) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new DATE(v);
		value = this.i;
		type = java.sql.Types.TIMESTAMP;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(i);
	}

	@Override
	public Field eval(Env env) {
		return i;
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}

	@Override
	public void rename(String oldName, String newName) {
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

}
