package fatworm.driver;

import java.util.List;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fatworm.absyn.Expr;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.io.Cursor;
import fatworm.util.Env;
import fatworm.util.Util;

public class IOTable extends Table {

	public IOTable() {
		// TODO Auto-generated constructor stub
	}
	public IOTable(CommonTree t) {
		this();
		schema = new Schema(t);
	}
	@Override
	public int delete(Expr e) {
		// TODO Auto-generated method stub
		int ret = 0;
		Cursor c = open();
		while(c.hasNext()){
			
			Record r = null;
			Env env = new Env();
			env.appendFromRecord(r);
			if(e!=null&&!e.evalPred(env))continue;
			c.remove();
			ret++;
			
			c.next();
		}
		return ret;
	}

	public Cursor open() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(List<String> colName, List<Expr> expr, Expr e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Tree child) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(CommonTree t, Tree v) {
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

	@Override
	public void addRecord(Record r) {
		// TODO Auto-generated method stub
	}

	@Override
	public Schema getSchema() {
		// TODO Auto-generated method stub
		return null;
	}

}
