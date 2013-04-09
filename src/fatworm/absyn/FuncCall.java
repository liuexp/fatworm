package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.*;

public class FuncCall extends Expr {

	int func;
	public String col;
	public FuncCall(int func, String col) {
		this.col = col;
		this.func = func;
	}

	@Override
	public boolean evalPred(Env env) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return Util.getFuncName(func) + "(" + col + ")";
	}
 
}
