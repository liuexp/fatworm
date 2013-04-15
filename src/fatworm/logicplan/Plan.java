package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.util.Env;

// This Logic plan implements the upper levels of the interpreter
// TODO add liveness analysis

public abstract class Plan {

	public Plan parent;
	public boolean hasEval;

	public Plan(Plan parent) {
		this.parent = parent;
		hasEval = false;
	}
	
	// FIXME for subquery we need nested Env?
	public abstract void eval(Env env);

	public abstract String toString();
	
	// FIXME change to abstract
	public abstract boolean hasNext();
	
	// FIXME change to abstract
	public abstract Record next();
}
