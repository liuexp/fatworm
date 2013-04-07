package fatworm.logicplan;

import fatworm.driver.ResultSet;

public class Distinct extends Node {

	public Node src;
	public Distinct(Node src) {
		super(null);
		this.src = src;
		src.parent = this;
	}

	// TODO memory distinct and disk distinct
	@Override
	public ResultSet eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
