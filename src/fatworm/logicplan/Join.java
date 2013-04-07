package fatworm.logicplan;

import fatworm.driver.Scan;

public class Join extends Node {

	public Node left,right;
	public Join(Node left, Node right) {
		super(null);
		this.left = left;
		this.right = right;
		this.left.parent = this;
		this.right.parent = this;
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
