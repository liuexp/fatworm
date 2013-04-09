package fatworm.absyn;

import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;

public class ExistCall extends Expr {

	public boolean not;
	public Plan src;
	public ExistCall(Plan src, boolean not) {
		this.src = src;
		this.not = not;
	}

	@Override
	public boolean evalPred(Env env) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return (not? "not ":"") + "exist " + src.toString();
	}
}
