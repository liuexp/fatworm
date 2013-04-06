package fatworm.logicplan;

import fatworm.driver.ResultSet;

public abstract class Node {

	public Node parent;
	public Node(Node parent) {
		this.parent = parent;
	}
	
	abstract public ResultSet eval();

}
