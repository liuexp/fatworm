package fatworm.logicplan;

import org.antlr.runtime.tree.Tree;

import fatworm.driver.Scan;

public class Select extends Node {
	public Node src;

	public Tree pred;
	public Select(Node src, Tree pred) {
		super(null);
		this.src = src;
		this.pred = pred;
		src.parent = this;
	}
	
	@Override
	public String toString(){
		return "select (from="+src.toString()+", where="+pred.toStringTree()+")";
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
