package fatworm.logicplan;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;

public class Select extends Plan {
	public Plan src;

	public Expr pred;
	public Env env;
	public Record current;
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
		fetchNext();
	}

	private void fetchNext() {
		Record ret = null;
		while(src.hasNext()){
			Record r = src.next();
			Env localEnv = env.clone();
			localEnv.appendFromRecord(r);
			if(pred.evalPred(localEnv)){
				ret = r;
				break;
			}
		}
		current = ret;
	}

	@Override
	public boolean hasNext() {
		return current == null;
	}

	@Override
	public Record next() {
		Record ret = current;
		fetchNext();
		return ret;
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
