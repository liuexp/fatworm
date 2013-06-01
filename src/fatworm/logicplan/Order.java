package fatworm.logicplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.parser.FatwormParser;
import fatworm.util.Env;
import fatworm.util.Util;

public class Order extends Plan {

	// TODO External Sorting
	public Plan src;
	public List<String> orderField;
	public List<Integer> orderType;
	public List<Record> results;
	public List<Expr> expr;
	public List<String> expandedCols = new ArrayList<String>();
	public Schema schema;
	public int ptr;
	
	public Order(Plan src, List<Expr> func, List<String> a, List<Integer> b) {
		super();
		this.src = src;
		src.parent = this;
		orderField = a;
		orderType = b;
		myAggr.addAll(this.src.getAggr());
		this.expr = func;
		this.schema = new Schema();
		this.schema.fromList(func, src.getSchema());
		Schema scm = src.getSchema();
		Set<String> neededAttr = new HashSet<String>();
		for(String x:orderField){
			neededAttr.add(Util.getAttr(x).toLowerCase());
		}
		for(String colName : scm.columnName){
			String ocol = colName;
//			colName = Util.getAttr(colName);
//			if(this.schema.columnDef.containsKey(colName)||this.schema.columnDef.containsKey(ocol))
			if(this.schema.columnDef.containsKey(ocol))
				continue;
			if(!neededAttr.contains(Util.getAttr(ocol).toLowerCase()))
				continue;
			this.schema.columnDef.put(ocol, scm.getColumn(ocol));
			this.schema.columnName.add(ocol);
			this.expandedCols.add(ocol);
		}
	}

	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		ptr = 0;
		results = new ArrayList<Record>();
		Env env = envGlobal.clone();
		src.eval(env);
		while(src.hasNext()){
			Record r = src.next();
			//System.out.println(r.toString());
			env.appendFromRecord(r);
			Record pr = new Record(schema);
			pr.addColFromExpr(env, expr);
			for(int i=0;i<expandedCols.size();i++){
				pr.addCol(env.get(expandedCols.get(i)));
			}
			//System.out.println(pr.toString());
			results.add(pr);
		}
//		Util.warn("Start mem-ordering.");
		Collections.sort(results, new Comparator<Record>(){
			public int compare(Record a, Record b){
				for(int i=0;i<orderField.size();i++){
					Field l = a.getCol(orderField.get(i));
					Field r = b.getCol(orderField.get(i));
					if(orderType.get(i) == FatwormParser.ASC){
						if(l.applyWithComp(BinaryOp.GREATER, r))
							return 1;
						if(l.applyWithComp(BinaryOp.LESS, r))
							return -1;
					} else {
						if(l.applyWithComp(BinaryOp.GREATER, r))
							return -1;
						if(l.applyWithComp(BinaryOp.LESS, r))
							return 1;
					}
				}
				return 0;
			}
		});
	}
	@Override
	public String toString(){
		return "order (from="+src.toString()+", field="+Util.deepToString(orderField)+", type="+Util.deepToString(orderType)+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Order not eval");
		return ptr != results.size();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Order not eval");
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
		List<String> z = src.getRequestedColumns();
		Util.removeAllCol(z, src.getColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		// FIXME
		src.rename(oldName, newName);
	}
}
