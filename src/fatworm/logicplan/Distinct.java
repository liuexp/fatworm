package fatworm.logicplan;

import fatworm.driver.Scan;

public class Distinct extends Node {

	public Node src;
	public Distinct(Node src) {
		super(null);
		this.src = src;
		src.parent = this;
	}

	// TODO memory distinct and disk distinct
	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}