package fatworm.logicplan;

import fatworm.driver.Scan;

// This Logic plan implements the upper levels of the interpreter
// TODO add liveness analysis

public abstract class Plan {

	public Plan parent;
	public boolean hasEval;
	public Plan(Plan parent) {
		this.parent = parent;
		hasEval = false;
	}
	
	public abstract Scan eval();

	public abstract String toString();
}
