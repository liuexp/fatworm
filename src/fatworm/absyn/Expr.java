package fatworm.absyn;


public abstract class Expr {
	
	public Integer size,depth;
	public int value;
	public boolean isConst;

	public Expr() {
		size=1;
		depth=1;
		isConst = false;
	}
}
