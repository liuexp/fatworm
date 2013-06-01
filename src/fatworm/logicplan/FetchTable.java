package fatworm.logicplan;

import java.util.LinkedList;
import java.util.List;

import fatworm.driver.DBEngine;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.driver.Table;
import fatworm.util.Env;
import fatworm.util.Util;
import fatworm.io.Cursor;

public class FetchTable extends Plan {

	//FIXME for now we use temporary database in memory 
	public String tableName;
	public Table table;
	Schema schema;
	Cursor cursor;
	// FIXME why don't we just use table.schema, even for database on disk
	public FetchTable(String table) {
		super();
		tableName = table;
		this.table = DBEngine.getInstance().getTable(table);
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
		hasEval = true;
		cursor = table.open();
	}
	@Override
	public String toString(){
		return "Table (from="+tableName+")";
	}

	@Override
	public boolean hasNext() {
		return cursor.hasThis();
	}

	@Override
	public Record next() {
		Record ret = cursor.fetchRecord();
		try {
			cursor.next();
		} catch (Throwable e) {
//			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void reset() {
		try {
			cursor.reset();
		} catch (Throwable e) {
//			e.printStackTrace();
		}
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		cursor.close();
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
