package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.*;

public class FuncCall extends Expr {

	// TODO How to specify the return type?
	int func;
	public String col;
	public FuncCall(int func, String col) {
		this.col = col;
		this.func = func;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		// TODO remember to put func value into env!!!
		return env.get(this.toString());
	}
	@Override
	public String toString() {
		return Util.getFuncName(func) + "(" + col + ")";
	}
 
}
