package fatworm.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.util.Env;
import fatworm.util.Util;

// FIXME temporary table in memory.
public class Table {
	public Schema schema;
	public List<Record> records = new ArrayList<Record>();
	public Table(CommonTree t){
		schema = new Schema(t);
	}
	
	public int delete(Expr e){
		int ret = 0;
		for(Iterator<Record> itr = records.iterator();itr.hasNext();){
			Record r = itr.next();
			Env env = new Env();
			env.appendFromRecord(r);
			if(e!=null&&!e.evalPred(env))continue;
			itr.remove();
			ret++;
			
		}
		return ret;
	}
	
	public int update(List<String> colName, List<Expr> expr, Expr e){
		int ret = 0;
		for(Record r:records){
			Env env = new Env();
			env.appendFromRecord(r);
			if(e!=null && !e.evalPred(env))continue;
			for(int i=0;i<expr.size();i++){
				Env localEnv = env.clone();
				r.cols.set(r.schema.findIndex(colName.get(i)), expr.get(i).eval(localEnv));
			}
			ret++;
		}
		return ret;
	}

	public int insert(Tree tree) {
		int ret = 0;
		Record r = new Record(schema);
		r.autoFill();
		for(int i=0;i<tree.getChildCount();i++){
			Tree c = tree.getChild(i);
			String colName = schema.columnName.get(i);
			r.setField(colName, Util.getField(schema.columnDef.get(colName), c));
		}
		records.add(r);
		ret++;
		return ret;
	}
}
