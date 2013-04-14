package fatworm.absyn;

import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.util.Env;

public class BoolLiteral extends Expr {

	public BOOL i;
	public BoolLiteral(boolean v) {
		this.isConst = true;
		this.size=1;
		this.i = new BOOL(v);
		value = this.i;
	}
	@Override
	public String toString() {
		return i.toString();
	}
 
	@Override
	public boolean evalPred(Env env) {
		return i.v;
	}

	@Override
	public Field eval(Env env) {
		return i;
	}


}
