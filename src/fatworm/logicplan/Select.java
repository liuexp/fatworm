package fatworm.logicplan;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class Select extends Plan {
	public Plan src;

	public Expr pred;
	public Env env;
	public Select(Plan src, Expr pred) {
		super();
		this.src = src;
		this.pred = pred;
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
	}
	
	@Override
	public String toString(){
		return "select (from="+src.toString()+", where="+pred.toString()+")";
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		src.eval(env);
		this.env = env;
	}

	// FIXME while hasNext() == true, next() may get nothing at all!!
	@Override
	public boolean hasNext() {
		return src.hasNext();
	}

	@Override
	public Record next() {
		while(src.hasNext()){
			Record r = src.next();
			Env localEnv = env.clone();
			localEnv.appendFromRecord(r);
			if(pred.evalPred(localEnv))
				return r;
		}
		// FIXME return an empty ResultSet instead?
		return null;
	}

	@Override
	public void reset() {
		src.reset();
	}

	@Override
	public Schema getSchema() {
		return src.getSchema();
	}

}
