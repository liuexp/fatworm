package fatworm.absyn;


public class Id extends Expr {
	
	public String name;

	public Id(String sym) {
		this.name = sym;
		this.size=1;
	}
	
	@Override
	public String toString() {
		return name.toString();
	}
}
