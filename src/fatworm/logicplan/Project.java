package fatworm.logicplan;

import java.util.List;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Project extends Plan {
	
	Plan src;
	List<Expr> expr;
	List<String> names;
	Schema schema;
	Env env;

	public Project(Plan src, List<Expr> expr) {
		super();
		this.src = src;
		this.expr = expr;
		this.src.parent = this;
		this.myAggr.addAll(this.src.getAggr());
		// NOTE that there shouldn't be any aggregate on names
		this.schema = new Schema();
		this.schema.fromList(expr, src.getSchema());
		this.names = this.schema.columnName;
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		src.eval(env);
		this.env = env;
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
		localenv.appendFromRecord(src.next());
		Record ret = new Record(schema);
		//System.out.println(localenv.toString());
		ret.addColFromExpr(localenv, expr);
		//System.out.println(ret.toString());
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
}
