package fatworm.logicplan;

import fatworm.driver.DBEngine;
import fatworm.driver.Record;
import fatworm.driver.ResultSetMetaData;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;
import fatworm.driver.Table;

public class FetchTable extends Plan {

	//FIXME for now we use temporary database in memory 
	public String tableName;
	public Table table;
	public int ptr = 0;
	public FetchTable(String table) {
		super();
		tableName = table;
		this.table = DBEngine.getInstance().getTable(table);
		if(table==null)Util.error("meow");
	}

	// TODO Range Fetch?
	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		hasEval = true;
		ptr = 0;
	}
	@Override
	public String toString(){
		return "Table (from="+tableName+")";
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return ptr < table.records.size();
	}

	@Override
	public Record next() {
		return table.records.get(ptr++);
	}

	@Override
	public void reset() {
		ptr = 0;
	}

	@Override
	public Schema getSchema() {
		return table.schema;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
