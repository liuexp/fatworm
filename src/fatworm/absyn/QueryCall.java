package fatworm.absyn;

import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;

//TODO how to do this?
public class QueryCall extends Expr {

	Plan src;
	public QueryCall(Plan src) {
		this.src = src;
	}

	@Override
	public boolean evalPred(Env env) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field eval(Env env) {
		src.eval(null);
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return "@subquery("+src.toString() +")";
	}
 
}
