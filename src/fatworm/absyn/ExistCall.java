package fatworm.absyn;

import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;

public class ExistCall extends Expr {

	public boolean not;
	public Plan src;
	public ExistCall(Plan src, boolean not) {
		super();
		this.src = src;
		this.not = not;
		myAggr.addAll(this.src.getAggr());
	}

	@Override
	public boolean evalPred(Env env) {
		src.eval(env);
		return not ^ src.hasNext();
	}

	@Override
	public Field eval(Env env) {
		return new BOOL(evalPred(env));
	}
	@Override
	public String toString() {
		return (not? "not ":"") + "exist " + src.toString();
	}
}
