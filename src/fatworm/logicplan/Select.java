package fatworm.logicplan;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.util.Env;

public class Select extends Plan {
	public Plan src;

	public Expr pred;
	public Select(Plan src, Expr pred) {
		super();
		this.src = src;
		this.pred = pred;
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
	}
	
	@Override
	public String toString(){
		return "select (from="+src.toString()+", where="+pred.toString()+")";
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
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
