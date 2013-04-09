package fatworm.logicplan;

import fatworm.driver.ResultSetMetaData;
import fatworm.driver.Scan;

public class FetchTable extends Plan {

	//public ResultSetMetaData table;
	public String tableName;
	public FetchTable(String table) {
		super(null);
		tableName = table;
	}

	// TODO Range Fetch?
	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
