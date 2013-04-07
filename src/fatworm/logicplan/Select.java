package fatworm.logicplan;

import org.antlr.runtime.tree.Tree;


public class Select extends Node {
	public Node from;

	public Tree where;
	public Select(Node from, Tree where) {
		this.from = from;
		this.where = where;
	}
	
	@Override
	public String toString(){
		return "select (from="+from.toString()+", where="+where.toStringTree()+")";
	}

}
