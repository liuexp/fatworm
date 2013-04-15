package fatworm.logicplan;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.driver.Scan;

public class Group extends Plan {

	public Plan src;
	public String by;
	public Expr having;
	public List<Expr> func;
	public Group(Plan src, List<Expr> func, String by, Expr having) {
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
	@Override
	public String toString(){
		return "Group (from="+src.toString()+", by=" + by + ", having=" + having.toString()+")";
	}
}
