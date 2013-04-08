package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.Env;

//FIXME Is this needed at all

public class UnaryExpr extends Expr {
	
	public UnaryOp op;
	public Expr expr;

	public UnaryExpr(UnaryOp op, Expr expr) {
		this.op = op;
		this.expr = expr;
		this.size=expr.size;
		depth=expr.depth;
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

}
