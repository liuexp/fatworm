package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.util.Env;

public class Distinct extends Plan {

	public Plan src;
	public Distinct(Plan src) {
		super(null);
		this.src = src;
		src.parent = this;
	}

	// TODO memory distinct and disk distinct
	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "distinct (from="+src.toString()+")";
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
}
