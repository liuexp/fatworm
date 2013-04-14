package fatworm.logicplan;

import fatworm.driver.Scan;
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
	public Scan eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "distinct (from="+src.toString()+")";
	}
}
