package fatworm.absyn;
import fatworm.field.Field;

public abstract class Expr {
	
	public Integer size,depth;
	public Field value;
	public boolean isConst;
	public boolean hasAggr;

	public Expr() {
		size=1;
		depth=1;
		isConst = false;
		hasAggr = false;
	}
}