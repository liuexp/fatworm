package fatworm.logicplan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.absyn.Id;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.parser.FatwormParser;
import fatworm.util.Env;
import fatworm.util.Util;

public class ThetaJoin extends Plan {

	public Plan left;
	public Plan right;
	public List<Expr> condList;
	public Record curLeft;
	public Record current;
	public Schema schema;
	private Env env;
	private boolean hasMergeJoin = false;
	private String leftName;
	private String rightName;
	private List<Record> results = new ArrayList<Record>();
	private int ptr = 0;
	public ThetaJoin(Join p) {
		left = p.left;
		right = p.right;
		left.parent = this;
		right.parent = this;
		condList = new LinkedList<Expr>();
		curLeft = null;
		myAggr = p.getAggr();
		schema = p.getSchema();
	}

	@Override
	public void eval(Env envGlobal) {
		// TODO wrap left & right with Order 
		hasEval = true;
		curLeft = null;
		this.env = envGlobal;
		BinaryExpr aEQb = findMergeJoin();
		if(aEQb != null){
			List<String> lattr = new ArrayList<String>();
			List<String> rattr = new ArrayList<String>();
			List<Integer> orderType = new ArrayList<Integer>();
			orderType.add(FatwormParser.ASC);
			hasMergeJoin = true;
			if(left.getSchema().findIndex(aEQb.l.toString())>=0 && right.getSchema().findIndex(aEQb.r.toString())>=0){
				leftName = aEQb.l.toString();
				rightName = aEQb.r.toString();
				lattr.add(leftName);
				rattr.add(rightName);
			}else if(right.getSchema().findIndex(aEQb.l.toString())>=0 && left.getSchema().findIndex(aEQb.r.toString())>=0) {
				leftName = aEQb.r.toString();
				rightName = aEQb.l.toString();
				lattr.add(leftName);
				rattr.add(rightName);
			}else{
				Util.warn("meow@ThetaJoin eval");
				hasMergeJoin = false;
			}
			if(hasMergeJoin){
				left = new Order(left, new ArrayList<Expr>(), lattr, orderType);
				left.parent = this;
				right = new Order(right, new ArrayList<Expr>(), rattr, orderType);
				right.parent = this;
			}
		}
		left.eval(env);
		right.eval(env);
//		hasMergeJoin = false;
		if(!hasMergeJoin){
			Util.warn("Watch me I'm Theta-Join!!!");
			fetchNext();
		}else{
			if(!left.hasNext() || !right.hasNext())return;
			Util.warn("Watch me I'm Merge-Join!!!"+leftName+"="+ rightName);
			Record curl=left.next();
			Record curr=right.next();
			List<Record> lastRight = new ArrayList<Record>();
			Field lval = curl.getCol(leftName);
			Field rval = curr.getCol(rightName);
			Field lastr = null;
			while(true){
				lval = curl.getCol(leftName);
				if(lastr!=null && lval.applyWithComp(BinaryOp.EQ, lastr)){
					for(Record r : lastRight){
						Record tmp = productRecord(curl, r);
						if(predTest(tmp))
							results.add(tmp);
					}
				}else{
					lastRight = new ArrayList<Record>();
					while(lval.applyWithComp(BinaryOp.EQ, rval)){
						Record tmp = productRecord(curl, curr);
						if(predTest(tmp))
							results.add(tmp);
						lastRight.add(curr);
						if(!right.hasNext())
							break;
						curr = right.next();
						rval = curr.getCol(rightName);
					}
					lastr = lval;
				}
				if(!left.hasNext())break;
				curl = left.next();
			}
		}
	}

	private BinaryExpr findMergeJoin() {
		for(Expr e:condList){
			if(e instanceof BinaryExpr){
				BinaryExpr ret = (BinaryExpr) e;
				if(ret.op == BinaryOp.EQ && ret.l instanceof Id && ret.r instanceof Id){
					return ret;
				}
			}
		}
		return null;
	}

	@Override
	public String toString(){
		return "ThetaJoin (left="+left.toString()+", right="+right.toString()+")";
	}

	private boolean srcHasNext() {
		if(!hasEval)Util.error("ThetaJoin not eval");
		return (curLeft!=null||left.hasNext()) &&right.hasNext();
	}
	@Override
	public boolean hasNext() {
		return hasMergeJoin?ptr < results.size() : current != null;
	}

	@Override
	public Record next() {
		if(!hasMergeJoin){
			Record ret = current;
			fetchNext();
			return ret;
		}else{
			return results.get(ptr++);
		}
	}
	
	private Record srcNext() {
		if(!hasEval)Util.error("ThetaJoin not eval");
		Record r = right.next();
		if(curLeft == null)
			curLeft = left.next();
		Record ret = productRecord(curLeft, r);
		if(!right.hasNext()&&left.hasNext()){
			right.reset();
			curLeft = left.next();
		}
		return ret;
	}

	private Record productRecord(Record l, Record r) {
		Record ret = new Record(schema);
		ret.cols.addAll(l.cols);
		ret.cols.addAll(r.cols);
		return ret;
	}
	private void fetchNext() {
		Record ret = null;
		while(srcHasNext()){
			Record r = srcNext();
			if(predTest(r)){
				ret = r;
				break;
			}
		}
		current = ret;
	}
	private boolean predTest(Record r) {
		Env localEnv = env.clone();
		localEnv.appendFromRecord(r);
		for(Expr pred : condList){
			if(!pred.evalPred(localEnv))
				return false;
		}
		return true;
	}

	@Override
	public void reset() {
		left.reset();
		right.reset();
		curLeft = null;
		ptr = 0;
		if(!hasMergeJoin){
			current = null;
			fetchNext();
		}
	}

	@Override
	public Schema getSchema() {
		return schema;
	}

	@Override
	public void close() {
		left.close();
		right.close();
	}


	@Override
	public List<String> getColumns() {
		List<String> z = new LinkedList<String> (left.getColumns());
		z.addAll(right.getColumns());
		return z;
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = new LinkedList<String> (left.getRequestedColumns());
		z.addAll(right.getRequestedColumns());
		return z;
	}


	@Override
	public void rename(String oldName, String newName) {
		//FIXME you'd better not call me...
		left.rename(oldName, newName);
		right.rename(oldName, newName);
	}

	public void add(Expr pred) {
		condList.add(pred);
	}

}
