package fatworm.logicplan;

import fatworm.driver.Scan;

// This Logic plan implements the upper levels of the interpreter
// TODO add liveness analysis

public abstract class Node {

	public Node parent;
	public boolean hasEval;
	public Node(Node parent) {
		this.parent = parent;
		hasEval = false;
	}
	
	abstract public Scan eval();

}
