package fatworm.absyn;
import fatworm.field.Field;
import fatworm.util.Env;

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
	public abstract boolean evalPred(Env env);
}
