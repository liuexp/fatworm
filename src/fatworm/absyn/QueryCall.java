package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.logicplan.Plan;
import fatworm.util.Env;
import fatworm.util.Util;

public class QueryCall extends Expr {

	public final Plan src;
	public QueryCall(Plan src) {
		super();
		this.src = src;
		myAggr.addAll(this.src.getAggr());
		type = src.getSchema().getColumn(0).type;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		src.eval(env);
		if(src.hasNext())return src.next().cols.get(0);
		else return NULL.getInstance();
	}
	@Override
	public String toString() {
		return "@QueryCall("+src.toString() +")";
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}

	@Override
	public void rename(String oldName, String newName) {
		// TODO 
		
	}

	@Override
	public boolean hasSubquery() {
		return true;
	}

	@Override
	public Expr clone() {
		return new QueryCall(src);
	}
 
}
