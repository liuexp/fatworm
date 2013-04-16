package fatworm.logicplan;

import java.util.ArrayList;
import java.util.List;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.absyn.FuncCall;

// This Logic plan implements the upper levels of the interpreter
// TODO add liveness analysis

public abstract class Plan {

	public Plan parent;
	public boolean hasEval;
	public List<FuncCall> myAggr;
	public Plan() {
		hasEval = false;
		myAggr = new ArrayList<FuncCall>();
	}
	public Plan(Plan parent) {
		this();
		this.parent = parent;
	}
	
	
	public abstract void eval(Env env);

	public abstract String toString();
	
	public abstract boolean hasNext();
	
	public abstract Record next();

	public abstract void reset();

	public List<FuncCall> getAggr() {
		return myAggr;
	}
	public abstract Schema getSchema();
}
