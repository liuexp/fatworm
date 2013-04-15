package fatworm.logicplan;

import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Scan;

public class Select extends Plan {
	public Plan src;

	public Expr pred;
	public Select(Plan src, Expr pred) {
		super(null);
		this.src = src;
		this.pred = pred;
		src.parent = this;
	}
	
	@Override
	public String toString(){
		return "select (from="+src.toString()+", where="+pred.toString()+")";
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
