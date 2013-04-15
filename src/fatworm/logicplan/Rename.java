package fatworm.logicplan;

import java.util.List;

import fatworm.driver.Record;
import fatworm.util.Env;
public class Rename extends Plan {
	
	public List<String> as;
	public Plan src;

	public Rename(Plan src, List<String> as) {
		super();
		this.src = src;
		this.as = as;
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
	}

	@Override
	public String toString(){
		return "rename (from="+src.toString()+")";
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
