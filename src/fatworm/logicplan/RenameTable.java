package fatworm.logicplan;

import fatworm.driver.Scan;

public class RenameTable extends Node {

	public Node src;
	String alias;
	public RenameTable(Node src, String alias) {
		super(null);
		this.src = src;
		this.alias = alias;
		this.src.parent = this;
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
