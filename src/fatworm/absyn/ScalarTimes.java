package fatworm.absyn;

import java.util.List;

import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.util.Env;
import fatworm.util.Util;

public class ScalarTimes extends Expr {

	public int c;
	public Id x;
	private String myName;
	public ScalarTimes(Id x, int c) {
		Util.warn("using ScalarTimes");
		this.x = x;
		this.c= c;
		size=x.size+1;
		depth=x.depth+1;
		isConst=x.isConst;
		value=isConst ? new INT(evalHelper(x.value)) : null;
	}

	private int evalHelper(Field value) {
		if(value.type == java.sql.Types.INTEGER)
			return c * ((INT)value).v;
		Util.error("ScalarTimes missing type");
		return 0;
	}

	@Override
	public boolean evalPred(Env env) {
		Field x = eval(env);
		return Util.toBoolean(x);
	}

	@Override
	public Field eval(Env env) {
		if(isConst)return value;
		Field rval = x.eval(env);
		return new INT(evalHelper(rval));
	}

	@Override
	public List<String> getRequestedColumns() {
		return x.getRequestedColumns();
	}

	@Override
	public void rename(String oldName, String newName) {
		x.rename(oldName, newName);
	}

	@Override
	public boolean hasSubquery() {
		return x.hasSubquery();
	}

	@Override
	public Expr clone() {
		return new ScalarTimes(x, c);
	}
	@Override
	public int getType(Schema schema) {
		return java.sql.Types.INTEGER;
	}
	@Override
	public int getType() {
		return java.sql.Types.INTEGER;
	}
	@Override
	public String toString(){
		if(myName != null)
			return myName;
		myName = "" + Env.getNewTemp();
		return myName;
	}
}
