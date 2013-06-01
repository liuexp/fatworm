package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class RenameTable extends Plan {

	public Plan src;
	String alias;
	public Schema schema;
	public RenameTable(Plan src, String alias) {
		super();
		this.src = src;
		this.alias = alias;
		this.src.parent = this;
		myAggr.addAll(this.src.getAggr());
		this.schema = new Schema(alias);
		for(int i = 0; i < src.getSchema().columnName.size(); ++i){
			String a = src.getSchema().columnName.get(i);
			String b = schema.tableName + "." + Util.getAttr(a);
			
			schema.columnName.add(b);
			schema.columnDef.put(b, src.getSchema().getColumn(a));
		}
		schema.primaryKey = src.getSchema().primaryKey;
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		src.eval(env);
	}
	@Override
	public String toString(){
		return "renameTable (from="+src.toString()+")";
	}

	@Override
	public boolean hasNext() {
		return src.hasNext();
	}

	@Override
	public Record next() {
		Record r = src.next();
		Record ret = new Record(schema);
		ret.cols.addAll(r.cols);
		//System.out.println(ret.toString());
		return ret;
	}

	@Override
	public void reset() {
		src.reset();
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		src.close();
	}
	
}
