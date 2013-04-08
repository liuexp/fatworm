package fatworm.absyn;

public class StringLiteral extends Expr {
	
	public String s;

	public StringLiteral(String s) {
		this.s = s;
		this.isConst = true;
		this.size=1;
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\"", s);
	}

}
