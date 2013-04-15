package fatworm.logicplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.driver.Scan;
import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.util.Env;

public class Group extends Plan {

	public Plan src;
	public String by;
	public Expr having;
	public List<Expr> func;
	public int ptr;
	public List<Record> results;
	public Schema meta;
	
	public Group(Plan src, List<Expr> func, String by, Expr having) {
		super(null);
		this.src = src;
		this.by = by;
		this.having = having;
		this.func = func;
		src.parent = this;
		ptr = 0;
		// TODO build schema
	}

	//TODO: Memory GroupBy and disk group by. 
	//NOTE:
	//		On eval I generate a list of results. Group by field's hash code.
	//		For each record from source, partially aggregate it with groupHelper.
	// 		Do I need a separate container?
	//		* Memory: List<Record>
	//		* Disk:		????
	@Override
	public Scan eval(Env envGlobal) {
		results = new ArrayList<Record>();
		ptr = 0;
		Map<Field, Record> groupHelper = new HashMap<Field, Record>();
		Env env = envGlobal.clone();
		src.eval(env);
		while(src.hasNext()){
			Record r = src.next();
			Field f = by==null? NULL.getInstance(): r.getCol(by);
			Record pr = groupHelper.get(f);
			env.appendFromRecord(r);
			if(pr == null){
				pr = new Record(meta);
				groupHelper.put(f, pr);
				pr.initCol(env, func);
			}
			pr.updateColWithAggr(env, func);
		}
		return null;
	}
	@Override
	public String toString(){
		return "Group (from="+src.toString()+", by=" + by + ", having=" + (having == null? "null": having.toString())+")";
	}

	@Override
	public boolean hasNext() {
		return ptr != results.size();
	}

	@Override
	public Record next() {
		return results.get(ptr++);
	}

}
