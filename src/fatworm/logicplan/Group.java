package fatworm.logicplan;

import org.antlr.runtime.tree.Tree;

import fatworm.driver.Scan;

public class Group extends Node {

	public Node src;
	public String by;
	public Tree having;
	public Tree func;
	public Group(Node src, Tree func, String by, Tree having) {
		super(null);
		this.src = src;
		this.by = by;
		this.having = having;
		this.func = func;
		src.parent = this;
	}

	//TODO: Memory GroupBy and disk group by
	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
