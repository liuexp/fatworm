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
	public boolean markedSkip = false;
	
	List<String> nameList;
	List<Integer> offsetList;
	
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
//		nameList = new ArrayList<String>();
//		offsetList = new ArrayList<Integer>();
//		for(String x:pred.getRequestedColumns()){
//			nameList.add(x);
//			offsetList.add(getSchema().findStrictIndex(x));
//		}
//		Util.warn("nameList = "+Util.deepToString(nameList)+", offsetList = "+Util.deepToString(offsetList));
		fetchNext();
	}

	private void fetchNext() {
		if(markedSkip){
			current = src.hasNext()? src.next():null;
			return;
		}
		Record ret = null;
		while(src.hasNext()){
			Record r = src.next();
			Env localEnv = env.clone();
			localEnv.appendFromRecord(r);
			// TODO I dont know why this doesn't work
//			localEnv.appendFromRecord(nameList, offsetList, r.cols);
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

	@Override
	public void rename(String oldName, String newName) {
		src.rename(oldName, newName);
		if(pred.getType(src.getSchema())==java.sql.Types.NULL){
			pred.rename(oldName, newName);
		}
	}

	public boolean isPushable() {
		if(pred.hasSubquery())
			return false;
		if(src instanceof Group || src instanceof FetchTable || src instanceof One || src instanceof None)
			return false;
		if(src instanceof ThetaJoin || src instanceof Rename )
			return false;
		if(src instanceof Project)
			return ((Project)src).isConst();
		if(src instanceof Select || src instanceof RenameTable )//||src instanceof Rename)
			return true;
		if(src instanceof Join)
			return Util.subsetof(pred.getRequestedColumns(), ((Join)src).left.getColumns()) || Util.subsetof(pred.getRequestedColumns(), ((Join)src).right.getColumns());
		
		Util.warn("Select isPushable meow!!!");
		return false;
	}

}
