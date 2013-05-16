package fatworm.driver;

import java.io.Serializable;
import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.io.Cursor;
import fatworm.util.Env;
import fatworm.util.Util;

public abstract class Table implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5595620741840746862L;
	public Schema schema;
	//public List<Integer> pageIDs;
	public Integer firstPageID; // so that dynamically splitting the page becomes possible
	//public List<Integer> freePageList;
	public int delete(Expr e) throws Throwable {
		int ret = 0;
		for(Cursor c = open();c.hasThis();c.next()){
			Record r = c.fetchRecord();
			Env env = new Env();
			env.appendFromRecord(r);
			if(e!=null&&!e.evalPred(env))continue;
			c.delete();
			ret++;
		}
		return ret;
	}

	public int insert(Tree t){
		int ret = 0;
		Record r = new Record(schema);
		r.autoFill();
		for(int i=0;i<t.getChildCount();i++){
			Tree c = t.getChild(i);
			String colName = schema.columnName.get(i);
			r.setField(colName, Util.getField(schema.getColumn(colName), c));
		}
		addRecord(r);
		ret++;
		return ret;
	}
	public int insert(CommonTree t, Tree v){
		int ret = 0;
		Record r = new Record(schema);
		r.autoFill();
		for(int i=0;i<v.getChildCount();i++){
			String colName = t.getChild(i+1).getText();
			//Column c = r.schema.getColumn(i);
			//if(c.type == java.sql.Types.CHAR || c.type == java.sql.Types.VARCHAR)
				//trim to c.M
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
		addRecord(r);
		ret++;
		return ret;
	}
	public Schema getSchema() {
		return schema;
	}
	public abstract int update(List<String> colName, List<Expr> expr, Expr e);
	public abstract void addRecord(Record r);
	
	public abstract void deleteAll();
	public abstract Cursor open();
	

}
