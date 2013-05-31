package fatworm.logicplan;

import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.absyn.LinearCombinationList;
import fatworm.driver.Column;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Project extends Plan {
	
	public Plan src;
	public final List<Expr> expr;
	final List<String> names;
	Schema schema;
	Env env;
	boolean hasNullCol=false;
	boolean hasProjectAll=false;
	boolean noTablePrefix = false;
	boolean useListEnv = false;

	public Project(Plan src, List<Expr> expr, boolean hasProjectAll) {
		super();
		this.src = src;
		this.expr = expr;
		this.src.parent = this;
		this.myAggr.addAll(this.src.getAggr());
		// NOTE that there shouldn't be any aggregate on names
		this.schema = new Schema();
		if(hasProjectAll){
			Schema scm = src.getSchema();
			this.schema.columnDef.putAll(scm.columnDef);
			this.schema.columnName.addAll(scm.columnName);
		}
		this.schema.fromList(expr, src.getSchema());
		this.names = this.schema.columnName;
		this.hasProjectAll = hasProjectAll;
		if(src.getSchema().isJoin)
			noTablePrefix = true;
		Util.warn("This is Project's schema:"+this.schema.toString());
		
		// TODO if expr Requested nothing from outside
		if(expr.size()==1){
			Expr e = expr.get(0);
			if(e instanceof LinearCombinationList){
				LinearCombinationList l = (LinearCombinationList) e;
				l.fillIndex(src.getSchema());
				useListEnv = true;
			}
		}
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		src.eval(env);
		this.env = env;
		for(Column c:schema.columnDef.values()){
			if(c.type == java.sql.Types.NULL){
//				hasNullCol=true;
				c.type = java.sql.Types.VARCHAR;
			}
		}
	}
	@Override
	public String toString(){
		return "Project (from="+src.toString()+", names="+Util.deepToString(names)+")";
	}

	@Override
	public boolean hasNext() {
		return src.hasNext();
	}

	@Override
	public Record next() {
		Env localenv = env.clone();
		Record tmpr = src.next();
		Record ret = new Record(schema);
		//System.out.println(localenv.toString());
		if(hasProjectAll){
			ret.cols.addAll(tmpr.cols);
		}
		if(useListEnv){
			for(int i=0;i<expr.size();i++){
				LinearCombinationList e = (LinearCombinationList) expr.get(i);
				ret.cols.add(e.evalByIndex(tmpr));
			}
		}else{
			localenv.appendFromRecord(tmpr);
			ret.addColFromExpr(localenv, expr);
		}
		return ret;
	}

	@Override
	public void reset() {
		src.reset();
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		src.close();
	}

	@Override
	public List<String> getColumns() {
		return new LinkedList<String>(schema.columnName);
	}

	@Override
	public List<String> getRequestedColumns() {
		List <String> z = new LinkedList<String>(src.getRequestedColumns());
		Util.removeAllCol(z, src.getColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		src.rename(oldName, newName);
		for(Expr e:expr){
			if(e.getType(src.getSchema())==java.sql.Types.NULL){
				e.rename(oldName, newName);
			}
		}
	}
	
	public boolean isConst(){
		for(Expr e:expr){
			if(!e.isConst)return false;
		}
		return true;
	}
}
