package fatworm.absyn;


public abstract class Expr {
	
	public Integer size,depth;
	public Object value;
	public boolean isConst;
	public boolean hasAggr;

	public Expr() {
		size=1;
		depth=1;
		isConst = false;
		hasAggr = false;
	}
}
