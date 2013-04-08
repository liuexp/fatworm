package fatworm.absyn;

import fatworm.absyn.Expr;


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
			value=calc(op,l.value,r.value);
		}
	}
	public static Object calc(BinaryOp o, Object aa, Object bb) {
		if(aa == null || !(aa instanceof Integer)|| !(aa instanceof Float))return null;
		if(bb == null || !(bb instanceof Integer)|| !(bb instanceof Float))return null;
		//FIXME Floating point?
		Integer a=(Integer) aa,b=(Integer) bb;
		switch(o){
		case MULTIPLY:
			return a*b;
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
