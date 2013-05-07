package fatworm.logicplan;

import java.util.LinkedList;
import java.util.List;

import fatworm.driver.DBEngine;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;
import fatworm.driver.MemTable;

public class FetchTable extends Plan {

	//FIXME for now we use temporary database in memory 
	public String tableName;
	public MemTable table;
	public int ptr = 0;
	Schema schema;
	// FIXME why don't we just use table.schema, even for database on disk
	public FetchTable(String table) {
		super();
		tableName = table;
		this.table = (MemTable) DBEngine.getInstance().getTable(table);
		if(table==null)Util.error("meow");
		this.schema = new Schema(this.table.getSchema().tableName);
		for(String old:this.table.getSchema().columnName){
			String now = this.table.schema.tableName + "." + Util.getAttr(old);
			schema.columnDef.put(now, this.table.getSchema().getColumn(old));
			schema.columnName.add(now);
		}
		schema.primaryKey = this.table.getSchema().primaryKey;
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
		return schema;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getColumns() {
		List<String> z = new LinkedList<String> (schema.columnName);
		return z;
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}
}
