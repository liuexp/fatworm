package fatworm.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.util.Env;
import fatworm.util.Util;

// FIXME temporary table in memory.
public class MemTable extends Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437348728477933924L;
	public transient List<Record> records;
	public MemTable(){
		initTransient();
	}
	public MemTable(CommonTree t){
		this();
		schema = new Schema(t);
	}
	
	private void initTransient() {
		records = new ArrayList<Record>();
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
	    in.defaultReadObject();
	    initTransient();
	}

	@Override
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
	
	@Override
	public int update(List<String> colName, List<Expr> expr, Expr e){
		int ret = 0;
		for(Record r:records){
			Env env = new Env();
			env.appendFromRecord(r);
			if(e!=null && !e.evalPred(env))continue;
			for(int i=0;i<expr.size();i++){
				//Env localEnv = env.clone();
				//localEnv.appendFromRecord(r);
				Field res = expr.get(i).eval(env);
				r.cols.set(r.schema.findIndex(colName.get(i)), res);
				env.put(colName.get(i), res);
			}
			ret++;
		}
		return ret;
	}

	@Override
	public int insert(Tree t) {
		int ret = 0;
		Record r = new Record(schema);
		r.autoFill();
		for(int i=0;i<t.getChildCount();i++){
			Tree c = t.getChild(i);
			String colName = schema.columnName.get(i);
			r.setField(colName, Util.getField(schema.getColumn(colName), c));
		}
		records.add(r);
		ret++;
		return ret;
	}

	@Override
	public int insert(CommonTree t, Tree v) {
		int ret = 0;
		Record r = new Record(schema);
		r.autoFill();
		for(int i=0;i<v.getChildCount();i++){
			String colName = t.getChild(i+1).getText();
			r.setField(colName, Util.getField(schema.getColumn(colName), v.getChild(i)));
		}
		for(int i=0;i<r.cols.size();i++){
			if(r.cols.get(i) != null)continue;
			Column c = r.schema.getColumn(i);
			if(c.notNull){
				r.cols.set(i, new INT(c.getAutoInc()));
			} else {
				r.cols.set(i, NULL.getInstance());
			}
		}
		records.add(r);
		ret++;
		return ret;
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void addRecord(Record r) {
		records.add(r);
	}
	
}
