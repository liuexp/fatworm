package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.field.FLOAT;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;

public class FloatLiteral extends Expr {

	public FLOAT i;
	public FloatLiteral(double v) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new FLOAT(v);
		value = this.i;
		type = java.sql.Types.FLOAT;
	}

	@Override
	public String toString() {
		return i.toString();
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

}
