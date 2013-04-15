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
		if(isConst){
			//value=calc(op,l.value,r.value);
		}
	}
	public boolean evalPred(Env env){
		boolean a = l.evalPred(env);
		boolean b = r.evalPred(env);
		return Util.toBoolean(calc(op, a, b));
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
		}
	}
	public static Field calc(BinaryOp o, Field aa, Field bb) {
		switch(o){
		case MULTIPLY:
			return multiply(a,b);
		case DIVIDE:
			return a/b;
		case MODULO:
			return a%b;
		case MINUS:
			return a-b;
		case AND:
			return (a!=0&&b!=0)?1:0;
		case OR:
			return (a!=0||b!=0)?1:0;
		case PLUS:
			return a+b;
		case EQ:
			return a==b?1:0;
		case NEQ:
			return a!=b?1:0;
		case LESS:
			return a<b?1:0;
		case LESS_EQ:
			return a<=b?1:0;
		case GREATER:
			return a>b?1:0;
		case GREATER_EQ:
			return a>=b?1:0;
		case ASSIGN:
			error("non-lvalue");
			return 0;
		case COMMA:
			return b;
		default:
			error(o.toString()+" : looks like 2012 stuff.");
			return 0;
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
}
