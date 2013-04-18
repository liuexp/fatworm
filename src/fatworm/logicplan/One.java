package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class One extends Plan {

	public Schema schema;
	public Record record;
	int ptr = 0;
	public One() {
		super();
		schema = new Schema("ONE_MEOW");
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		reset();
	}
	@Override
	public String toString(){
		return "ONE";
	}

	@Override
	public boolean hasNext() {
		return ptr == 0;
	}

	@Override
	public Record next() {
		if(ptr > 0)return null;
		ptr++;
		return new Record(schema);
	}

	@Override
	public void reset() {
		ptr = 0;
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		ptr = 1;
	}
}
