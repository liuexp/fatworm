package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.ResultSetMetaData;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class FetchTable extends Plan {

	//public ResultSetMetaData table;
	public String tableName;
	public FetchTable(String table) {
		super();
		tableName = table;
	}

	// TODO Range Fetch?
	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		hasEval = true;
	}
	@Override
	public String toString(){
		return "Table (from="+tableName+")";
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
		return new Schema(tableName);
	}
}
