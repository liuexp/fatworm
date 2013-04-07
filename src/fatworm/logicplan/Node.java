package fatworm.logicplan;

import fatworm.driver.Scan;

public abstract class Node {

	public Node parent;
	public boolean hasEval;
	public Node(Node parent) {
		this.parent = parent;
		hasEval = false;
	}
	
	abstract public Scan eval();

}
