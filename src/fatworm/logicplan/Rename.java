package fatworm.logicplan;

import java.util.List;

import fatworm.driver.Scan;
import fatworm.util.Env;
public class Rename extends Plan {
	
	public List<String> as;
	public Plan src;

	public Rename(Plan src, List<String> as) {
		super(null);
		this.src = src;
		this.as = as;
		src.parent = this;
	}

	@Override
	public String toString(){
		return "rename (from="+src.toString()+")";
	}
	
	@Override
	public Scan eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}

}
