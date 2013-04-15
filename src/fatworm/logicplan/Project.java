package fatworm.logicplan;

import java.util.List;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.util.Env;

public class Project extends Plan {
	
	Plan src;
	List<Expr> names;

	public Project(Plan src, List<Expr> names) {
		super();
		this.src = src;
		this.names = names;
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
		// NOTE that there shouldn't be any aggregate on names
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
	}
	@Override
	public String toString(){
		return "Project (from="+src.toString()+")";
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
