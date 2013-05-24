package fatworm.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.io.Cursor;
import fatworm.util.Env;
import fatworm.util.Util;

// FIXME temporary table in memory.
public class MemTable extends Table {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437348728477933924L;
	public  List<Record> records;//transient
	public MemTable(){
		//pageIDs = new LinkedList<Integer>();
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
	public void addRecord(Record r) {
		records.add(r);
	}
	@Override
	public void deleteAll() {
		records = new ArrayList<Record>();
	}
	@Override
	public Cursor open() {
		return new MemCursor();
	}
	
	public class MemCursor implements Cursor{

		public int idx = 0;
		@Override
		public void reset() {
			idx = 0;
		}

		@Override
		public void next() {
			idx ++;
		}

		@Override
		public void prev() {
			idx--;
		}

		@Override
		public Object fetch(String col) {
			return fetchRecord().getCol(col);
		}

		@Override
		public Object[] fetch() {
			return fetchRecord().cols.toArray();
		}

		@Override
		public void delete() throws Throwable {
			records.remove(idx);
		}

		@Override
		public void close() {
			
		}

		@Override
		public boolean hasNext() {
			return idx < records.size() - 1;
		}

		@Override
		public Record fetchRecord() {
			return records.get(idx);
		}

		@Override
		public Integer getIdx() {
			return idx;
		}

		@Override
		public boolean hasThis() {
			return idx >=0 && idx < records.size();
		}
		
	}
	
}
