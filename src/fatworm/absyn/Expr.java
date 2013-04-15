package fatworm.absyn;
import java.util.ArrayList;
import java.util.List;

import fatworm.field.Field;
import fatworm.util.Env;

public abstract class Expr {
	
	public Integer size,depth;
	public Field value;
	public boolean isConst;
	public List<FuncCall> myAggr;

	public Expr() {
		size=1;
		depth=1;
		isConst = false;
		myAggr = new ArrayList<FuncCall>();
		value = null;
	}
	//eval as a predicate
	public abstract boolean evalPred(Env env);
	
	//eval as an expression
	public abstract Field eval(Env env);
	
	// FIXME: extract Aggr from every plan
	public List<FuncCall> getAggr() {
		return myAggr;
	}
	public boolean hasAggr() {
		return !myAggr.isEmpty();
	}
}
