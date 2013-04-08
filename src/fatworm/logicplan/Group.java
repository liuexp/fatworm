package fatworm.logicplan;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Scan;

public class Group extends Node {

	public Node src;
	public String by;
	public Expr having;
	public List<Expr> func;
	public Group(Node src, List<Expr> func, String by, Expr having) {
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
