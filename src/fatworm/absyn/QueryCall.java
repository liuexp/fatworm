package fatworm.absyn;

import fatworm.field.Field;
import fatworm.logicplan.Node;
import fatworm.util.Env;

//TODO how to do this?
public class QueryCall extends Expr {

	Node src;
	public QueryCall(Node src) {
		this.src = src;
	}

	@Override
	public boolean evalPred(Env env) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field eval(Env env) {
		src.eval();
		// TODO Auto-generated method stub
		return null;
	}

}
