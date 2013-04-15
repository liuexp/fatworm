package fatworm.absyn;

import fatworm.absyn.Expr;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;


public class BinaryExpr extends Expr {
	
	public Expr l, r;
	public BinaryOp op;
	
    
	public BinaryExpr(Expr lhs, BinaryOp op, Expr rhs) {
			l = lhs;
			r = rhs;
			this.op = op;
			
		size=l.size+r.size+1;
		depth=max(l.depth,r.depth)+1;
		isConst=l.isConst&&r.isConst;
		value=isConst ? eval(null) : null;
	}
	
	public boolean evalPred(Env env){
		Field x = eval(env);
		return Util.toBoolean(x);
	}
	
	private boolean calc(BinaryOp o, boolean a, boolean b) {
		int ia = a?1:0;
		int ib = b?1:0;
		switch(o){
		case AND:
			return a && b;
		case OR:
			return a || b;
		case EQ:
			return a==b;
		case NEQ:
			return a!=b;
		case LESS:
			return ia<ib;
		case LESS_EQ:
			return ia<=ib;
		case GREATER:
			return ia>ib;
		case GREATER_EQ:
			return ia>=ib;
			default:
				return false;
		}
	}
	
	private Integer max(Integer a, Integer b) {
		return a>b?a:b;
	}
	
	private static void error(String string) {
		throw new RuntimeException("mie@BinaryExpr");
	}
	
	@Override
	public String toString(){
		return l.toString() + op.toString() + r.toString();
	}
	@Override
	public Field eval(Env env) {
		//Note that value!=null shouldn't count for isConst
		if(isConst)return value;
		switch(op){
		case LESS:
		case GREATER:
		case LESS_EQ:
		case GREATER_EQ:
		case EQ:
		case NEQ:
			return l.eval(env).applyWithComp(op, r.eval(env));
		}
	}
}
