package fatworm.logicplan;

import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.absyn.BinaryExpr;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Select extends Plan {
	public Plan src;

	public Expr pred;
	public Env env;
	public Record current;
	public boolean hasPushed;
	public Select(Plan src, Expr pred) {
		super();
		this.src = src;
		if(pred instanceof BinaryExpr){
			this.pred = ((BinaryExpr)pred).toCNF();
		} else {
			this.pred = pred;
		}
		src.parent = this;
		myAggr.addAll(this.src.getAggr());
		hasPushed = false;
	}
	
	@Override
	public String toString(){
		return "select (from="+src.toString()+", where="+pred.toString()+")";
	}

	@Override
	public void eval(Env env) {
		hasEval = true;
		current = null;
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
//		System.out.println("got "+(current == null? "null": current.toString()));
	}

	@Override
	public boolean hasNext() {
		return current != null;
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
		current = null;
		fetchNext();
	}

	@Override
	public Schema getSchema() {
		return src.getSchema();
	}

	@Override
	public void close() {
		src.close();
	}

	@Override
	public List<String> getColumns() {
		return new LinkedList<String> (src.getColumns());
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = src.getRequestedColumns();
		z.removeAll(src.getColumns());
		Util.addAllCol(z, pred.getRequestedColumns());
		return z;
	}

}
