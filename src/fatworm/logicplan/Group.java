package fatworm.logicplan;

import java.util.List;

import fatworm.absyn.Expr;
import fatworm.driver.Scan;
import fatworm.util.Env;

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

	//TODO: Memory GroupBy and disk group by. On eval I generate a list of results. Group by field's hash code.
	// 		Do I need a separate container?
	//		* Memory: List<Record>
	//		* Disk:		????
	@Override
	public Scan eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "Group (from="+src.toString()+", by=" + by + ", having=" + (having == null? "": having.toString())+")";
	}
}
