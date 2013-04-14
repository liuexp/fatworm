package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Scan;
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
	public abstract Scan eval(Env env);

	public abstract String toString();
	
	// FIXME change to abstract
	public boolean hasNext(){
		return false;
	}
	
	// FIXME change to abstract
	public Record next(){
		return null;
	}
}
