package fatworm.absyn;

import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;

public class AnyCall extends Expr {

	public boolean isAll;
	public Plan src;
	public Expr expr;
	public BinaryOp op;
	public AnyCall(Plan src, Expr expr, BinaryOp op, boolean isAll) {
		this.src = src;
		this.expr = expr;
		this.op = op;
		this.isAll = isAll;
	}

	@Override
	public boolean evalPred(Env env) {
		Field l = expr.eval(env);
		src.eval(env);
		if(isAll){
			while(src.hasNext()){
				if(!l.applyWithComp(op, src.next().cols.get(0)))return false;
			}
			return true;
		} else {
			while(src.hasNext()){
				if(l.applyWithComp(op, src.next().cols.get(0)))return true;
			}
			return false;
		}
	}

	@Override
	public Field eval(Env env) {
		return new BOOL(evalPred(env));
	}
	@Override
	public String toString() {
		return expr.toString() + op.toString() + (isAll? "all ":"any") + src.toString();
	}
}
