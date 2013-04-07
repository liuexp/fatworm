package fatworm.logicplan;

import org.antlr.runtime.tree.Tree;

import fatworm.driver.Scan;

public class Project extends Node {
	
	Node src;
	Tree names;

	public Project(Node src, Tree names) {
		super(null);
		this.src = src;
		this.names = names;
		src.parent = this;
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
