package fatworm.logicplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatworm.absyn.Expr;
import fatworm.absyn.FuncCall;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.util.Env;
import fatworm.util.Util;

public class Group extends Plan {

	public Plan src;
	public String by;
	public Expr having;
	public List<Expr> func;
	public int ptr;
	public List<Record> results;
	public Schema schema;
	
	public Group(Plan src, List<Expr> func, String by, Expr having) {
		super();
		this.src = src;
		this.by = by;
		this.having = having;
		this.func = func;
		src.parent = this;
		ptr = 0;
		// note that aggregate functions from source must be eval on source, not here.
		for(int i=0;i<func.size();i++){
			myAggr.addAll(func.get(i).getAggr());
		}
		if(this.having != null)
			myAggr.addAll(this.having.getAggr());
		this.schema = new Schema();
		this.schema.fromList(func, src.getSchema());
	}

	//TODO: Memory GroupBy and disk group by. 
	//NOTE:
	//		On eval I generate a list of results. Group by field's hash code.
	//		For each record from source, partially aggregate it with groupHelper.
	// 		Do I need a separate container?
	//		* Memory: List<Record>
	//		* Disk:		????
	//		--------------------------------
	//		workaround for FuncCall within having clause: extract all FuncCall out from subquery as well, then on each record, greedily calculate every functions possible to calculate.
	//		Note: for each record it's necessary to re-eval on each FuncCall node, to tackle cases like max(a+b) group by c, 
	//				but it's not necessary to re-eval on the whole expression tree, which can be expensive as this tree could be a sub-query.
	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		results = new ArrayList<Record>();
		ptr = 0;
		Map<Field, Record> groupHelper = new HashMap<Field, Record>();
		Map<Field, Env> aggrHelper = new HashMap<Field, Env>();
		Env env = envGlobal.clone();
		
		src.eval(env);
		for(FuncCall a : myAggr){
			if(a.canEvalOn(schema))env.remove(a.toString());
		}
		
		// First propagate for aggregation by evalCont().
		while(src.hasNext()){
			Record r = src.next();
			Field f = by==null? NULL.getInstance(): r.getCol(by);
			Env tmpEnv = aggrHelper.get(f);
			if(tmpEnv == null)
				tmpEnv = env.clone();
			tmpEnv.appendFromRecord(r);
			for(FuncCall a : myAggr){
				if(a.canEvalOn(schema))
					a.evalCont(tmpEnv);
			}
			aggrHelper.put(f, tmpEnv);
		}
		src.reset();

		// Now we can fill all FuncCall with ContField's final results by calling eval() as we fillCol().
		while(src.hasNext()){
			Record r = src.next();
			Field f = by==null? NULL.getInstance(): r.getCol(by);
			Record pr = groupHelper.get(f);

			if(pr == null){
				Env tmpEnv = aggrHelper.get(f);
				env.appendFrom(tmpEnv);
				env.appendFromRecord(r);
				pr = new Record(schema);
				groupHelper.put(f, pr);
				pr.addColFromExpr(env, func);
			}
		}
		//System.out.println("mie");
		for(Field f : groupHelper.keySet()){
			//System.out.println("meow");
			Record r = groupHelper.get(f);
			Env tmpEnv = aggrHelper.get(f);
			env.appendFromRecord(r);
			env.appendFrom(tmpEnv);
			System.out.println(Util.deepToString(env.res));
			if(having==null||having.evalPred(env)){
				System.out.println(Util.deepToString(env.res));
				results.add(r);
			}
		}
	}
	
	@Override
	public String toString(){
		return "Group (from="+src.toString()+", by=" + by + ", having=" + (having == null? "null": having.toString())+", expr="+Util.deepToString(func)+", myAggr="+Util.deepToString(myAggr)+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Group not eval");
		return ptr != results.size();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Group not eval");
		return results.get(ptr++);
	}

	@Override
	public void reset() {
		ptr = 0;
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

}
