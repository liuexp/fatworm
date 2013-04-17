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
			String old = src.getSchema().columnName.get(i);
			//Actually here according to grammar, asName shouldn't have a '.' in it.
			String now = schema.tableName + "." + Util.getAttr(as.get(i));
			schema.columnDef.put(now, src.getSchema().columnDef.get(old));
			schema.columnName.add(now);
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

}
