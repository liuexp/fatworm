package fatworm.logicplan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Distinct extends Plan {

	public Plan src;
	List<Record> results;
	int ptr;
	public Distinct(Plan src) {
		super();
		this.src = src;
		src.parent = this;
		
		myAggr.addAll(this.src.getAggr());
	}

	// TODO memory distinct and disk distinct
	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		results = new ArrayList<Record>();
		ptr = 0;
		Env env = envGlobal.clone();
		src.eval(env);
		Set<Record> set = new HashSet<Record>();
		while(src.hasNext()){
			Record r = src.next();
			if(!set.contains(r)){
				results.add(r);
				set.add(r);
			}
		}
	}
	@Override
	public String toString(){
		return "distinct (from="+src.toString()+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Distinct not eval");
		return ptr != results.size();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Distinct not eval");
		return results.get(ptr++);
	}

	@Override
	public void reset() {
		ptr = 0;
	}

	@Override
	public Schema getSchema() {
		return src.getSchema();
	}

	@Override
	public void close() {
		src.close();
		results = new ArrayList<Record>();
	}

	@Override
	public List<String> getColumns() {
		return src.getColumns();
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = src.getRequestedColumns();//new LinkedList(src.getRequestedColumns());
		Util.removeAllCol(z, src.getRequestedColumns());
		return z;
	}
}
