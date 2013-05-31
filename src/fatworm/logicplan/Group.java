package fatworm.logicplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public int byIdx1 = -1;
//	public int byIdx2 = -1;
	public Expr having;
	public List<Expr> func;
	public int ptr;
	public List<Record> results;
	public List<String> expandedCols = new ArrayList<String>();
	List<String> alias;
	boolean hasAlias;
	public Schema schema;
	
	List<String> nameList;
	List<Integer> offsetList;
	Set<String> neededAttr;
	
	public Group(Plan src, List<Expr> func, String by, Expr having, List<String> alias, boolean expand, boolean hasAlias) {
		super();
		this.src = src;
		this.by = by;
		this.having = having;
		this.func = func;
		src.parent = this;
		this.hasAlias = hasAlias;
		ptr = 0;
		this.alias = alias;
		neededAttr = new HashSet<String> ();
		// note that aggregate functions from source must be eval on source, not here.
		for(int i=0;i<func.size();i++){
			myAggr.addAll(func.get(i).getAggr());
			List<String> tmp = func.get(i).getRequestedColumns();
			for(String xx:tmp){
				neededAttr.add(xx.toLowerCase());
			}
		}
		
		if(this.having != null){
			myAggr.addAll(this.having.getAggr());
			List<String> tmp = having.getRequestedColumns();
			for(String xx:tmp){
				neededAttr.add(xx.toLowerCase());
			}
		}
		this.schema = new Schema();
		this.schema.fromList(func, src.getSchema());
		// Expand the table for now.
		Schema scm = src.getSchema();
		for(String colName : scm.columnName){
			String ocol = colName;
			colName = Util.getAttr(colName);
			if(this.schema.columnDef.containsKey(colName)||this.schema.columnDef.containsKey(ocol))
				continue;
			if(!neededAttr.contains(ocol.toLowerCase()) && !neededAttr.contains(colName.toLowerCase()))
				continue;
			this.schema.columnDef.put(colName, scm.getColumn(ocol));
			this.schema.columnName.add(colName);
			this.expandedCols.add(colName);
		}
		if(by!=null)
			byIdx1 = scm.findIndex(by);
		Util.warn("This is group : expanded=" + Util.deepToString(expandedCols));
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
		LinkedList<FuncCall> evalAggr = new LinkedList<FuncCall>();
		Set<String> aggrNeed = new HashSet<String>();
		for(FuncCall a : myAggr){
			if(a.canEvalOn(schema)){
				env.remove(a.toString());
				evalAggr.add(a);
				aggrNeed.add(a.col.toLowerCase());
			}
		}
		nameList = new ArrayList<String>();
		offsetList = new ArrayList<Integer>();
		for(String x:aggrNeed){
			nameList.add(x);
			offsetList.add(src.getSchema().findStrictIndex(x));
		}
		
		LinkedList<Record> tmpTable = new LinkedList<Record>();
		// First propagate for aggregation by evalCont().
		while(src.hasNext()){
			Record r = src.next();
			Field f = by==null? NULL.getInstance(): r.getCol(byIdx1);
			Env tmpEnv = aggrHelper.get(f);
			if(tmpEnv == null)
				tmpEnv = env.clone();
//			tmpEnv.appendFromRecord(r);
			tmpEnv.appendFromRecord(nameList, offsetList, r.cols);
			for(FuncCall a : evalAggr){
				a.evalCont(tmpEnv);
			}
			aggrHelper.put(f, tmpEnv);
			tmpTable.addLast(r);
		}
//		src.reset();

		nameList = new ArrayList<String>();
		offsetList = new ArrayList<Integer>();
		for(String x:neededAttr){
			nameList.add(x);
			offsetList.add(src.getSchema().findStrictIndex(x));
		}
		// Now we can fill all FuncCall with ContField's final results by calling eval() as we fillCol().
		while(!tmpTable.isEmpty()){
			Record r = tmpTable.pollFirst();
			Field f = by==null? NULL.getInstance(): r.getCol(byIdx1);
			Record pr = groupHelper.get(f);

			if(pr == null){
				Env tmpEnv = aggrHelper.get(f);
//				env.appendFrom(tmpEnv);
//				env.appendFromRecord(r);
				tmpEnv.appendFromRecord(nameList, offsetList, r.cols);
				pr = new Record(schema);
				groupHelper.put(f, pr);
				pr.addColFromExpr(tmpEnv, func);
				for(int i=0;i<expandedCols.size();i++){
					pr.addCol(tmpEnv.get(expandedCols.get(i)));
				}
			}
		}
		if(!hasAlias){
			for(Field f : groupHelper.keySet()){
				Record r = groupHelper.get(f);
				Env tmpEnv = aggrHelper.get(f);
//				env.appendFrom(tmpEnv);
//				env.appendFromRecord(r);
				if(having==null||having.evalPred(tmpEnv)){
					results.add(r);
				}
			}
		}else{
			Env tmpEnv = null;
			for(Field f : groupHelper.keySet()){
				Record r = groupHelper.get(f);
				tmpEnv = aggrHelper.get(f);
//				env.appendFrom(tmpEnv);
//				tmpEnv.appendFromRecord(r);
				tmpEnv.appendAlias(schema.tableName, func, alias);
				if(having==null||having.evalPred(tmpEnv)){
					results.add(r);
				}
			}
			env.appendFrom(tmpEnv);
		}
		if(results.size() == 0 && (having==null||having.evalPred(env))){
			Record pr = new Record(schema);
			pr.addColFromExpr(new Env(), func);
			for(int i=0;i<expandedCols.size();i++){
				pr.addCol(env.get(expandedCols.get(i)));
			}
			results.add(pr);
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

	@Override
	public void close() {
		src.close();
		results = new ArrayList<Record>();
	}

	@Override
	public List<String> getColumns() {
		return new LinkedList<String> (schema.columnName);
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = src.getRequestedColumns();
		Util.removeAllCol(z, src.getColumns());
		if(having!=null)
			Util.addAllCol(z, having.getRequestedColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		// FIXME
		src.rename(oldName, newName);
	}

}
