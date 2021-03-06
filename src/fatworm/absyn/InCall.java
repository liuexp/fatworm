package fatworm.absyn;

import java.util.List;

import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;
import fatworm.util.Util;

public class InCall extends Expr {

	public boolean not;
	public Plan src;
	public Expr expr;
	public InCall(Plan src, Expr expr, boolean not) {
		super();
		this.src = src;
		this.expr = expr;
		this.not = not;
		myAggr.addAll(this.src.getAggr());
		myAggr.addAll(this.expr.getAggr());
		type = java.sql.Types.BOOLEAN;
	}

	@Override
	public boolean evalPred(Env env) {
		Field l = expr.eval(env);
		src.eval(env);
		boolean ret=false;
		while(src.hasNext()){
			if(l.applyWithComp(BinaryOp.EQ, src.next().cols.get(0))){
				ret=true;
				break;
			}
		}
		return not ^ ret;
	}

	@Override
	public Field eval(Env env) {
		return new BOOL(evalPred(env));
	}
	@Override
	public String toString() {
		return expr.toString() + (not? " not ":" ") + "in " + src.toString();
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = src.getRequestedColumns();
		Util.addAllCol(z, expr.getRequestedColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

	@Override
	public Expr clone() {
		return new InCall(src, expr.clone(), not);
	}

}
