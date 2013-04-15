package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.util.Env;

public class One extends Plan {

	public One() {
		super(null);
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString(){
		return "ONE";
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
