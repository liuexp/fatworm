package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class One extends Plan {

	public Schema schema;
	public Record record;
	public One() {
		super();
		schema = new Schema("ONE_MEOW");
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		hasEval = true;
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

	@Override
	public Schema getSchema() {
		return schema;
	}
}
