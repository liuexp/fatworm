package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.field.INT;

public class IntLiteral extends Expr {
	
	public INT i;

	public IntLiteral(int i) {
		this.isConst = true;
		this.size=1;
		this.i = new INT(i);
		value = this.i;
	}
	
	@Override
	public String toString() {
		return String.format("%d", i.v);
	}

	@Override
	public boolean evalPred(Env env) {
		return i.v != 0 ? true : false;
	}

	@Override
	public Field eval(Env env) {
		return i;
	}

}
