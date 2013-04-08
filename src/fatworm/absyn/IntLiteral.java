package fatworm.absyn;

public class IntLiteral extends Expr {
	
	public int i;

	public IntLiteral(int i) {
		this.i = i;
		this.isConst = true;
		this.size=1;
		value=i;
	}
	
	@Override
	public String toString() {
		return String.format("%d", i);
	}

}
