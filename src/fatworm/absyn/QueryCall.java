package fatworm.absyn;

import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.logicplan.Plan;
import fatworm.util.Env;
import fatworm.util.Util;

public class QueryCall extends Expr {

	Plan src;
	public QueryCall(Plan src) {
		this.src = src;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		src.eval(env);
		if(src.hasNext())return src.next().cols.get(0);
		else return new NULL();
	}
	@Override
	public String toString() {
		return "@subquery("+src.toString() +")";
	}
 
}
