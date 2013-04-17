package fatworm.absyn;


public class UnaryExpr extends Expr {
	
	public UnaryOp op;
	public Expr expr;

	public UnaryExpr(UnaryOp op, Expr expr) {
		this.op = op;
		this.expr = expr;
		this.size=expr.size;
		depth=expr.depth;
	}

}
