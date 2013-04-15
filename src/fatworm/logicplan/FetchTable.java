package fatworm.logicplan;

import fatworm.driver.ResultSetMetaData;
import fatworm.util.Env;

public class FetchTable extends Plan {

	//public ResultSetMetaData table;
	public String tableName;
	public FetchTable(String table) {
		super(null);
		tableName = table;
	}

	// TODO Range Fetch?
	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "Table (from="+tableName+")";
	}
}
