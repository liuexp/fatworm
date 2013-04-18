package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class None extends Plan {
	private static None _instance = null;

	public Schema schema;
	public static synchronized Plan getInstance() {
		if (_instance == null) {
			_instance = new None();
		}
		return _instance;
	}
	
	public None() {
		super();
		schema = new Schema("MEOW_NONE");
	}

	public None(Plan parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "NoneResults";
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Record next() {
		return null;
	}

	@Override
	public void reset() {
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

}
