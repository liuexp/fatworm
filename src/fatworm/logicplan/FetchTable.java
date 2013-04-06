package fatworm.logicplan;

import fatworm.driver.ResultSet;
import fatworm.driver.ResultSetMetaData;

public class FetchTable extends Node {

	//public ResultSetMetaData table;
	public String tableName;
	public FetchTable(String table) {
		super(null);
		tableName = table;
	}

	// TODO Range Fetch?
	@Override
	public ResultSet eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
