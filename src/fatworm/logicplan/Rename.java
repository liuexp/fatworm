package fatworm.logicplan;

import java.util.List;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;
public class Rename extends Plan {
	
	public List<String> as;
	public Plan src;

	public Schema schema;
	
	public Rename(Plan src, List<String> as) {
		super();
		this.src = src;
		this.as = as;
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
		//FIXME HOW to do this at all?
		this.schema = new Schema(src.getSchema().tableName);
		for(int i = 0; i < src.getSchema().columnName.size(); ++i){
			String a = src.getSchema().columnName.get(i);
			String b = schema.tableName + "." + Util.getAttr(a);
			schema.columnName.add(b);
			schema.columnDef.put(b, src.getSchema().columnDef.get(a));
		}
		schema.primaryKey = src.getSchema().primaryKey;
	}

	@Override
	public String toString(){
		return "rename (from="+src.toString()+", as="+Util.deepToString(as)+")";
	}
	
	@Override
	public void eval(Env env) {
		hasEval = true;
		src.eval(env);
	}

	@Override
	public boolean hasNext() {
		return src.hasNext();
	}

	@Override
	public Record next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		src.reset();
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

}
