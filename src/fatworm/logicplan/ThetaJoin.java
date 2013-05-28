package fatworm.logicplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import fatworm.absyn.BinaryExpr;
import fatworm.absyn.BinaryOp;
import fatworm.absyn.Expr;
import fatworm.absyn.Id;
import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.Field;
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
		hasEval = true;
		curLeft = null;
		this.env = envGlobal;
		BinaryExpr aEQb = findMergeJoin();
		if(aEQb != null){
//			List<String> lattr = new ArrayList<String>();
//			List<String> rattr = new ArrayList<String>();
//			List<Integer> orderType = new ArrayList<Integer>();
//			orderType.add(FatwormParser.ASC);
			hasMergeJoin = true;
			if(left.getSchema().findStrictIndex(aEQb.l.toString())>=0 && right.getSchema().findStrictIndex(aEQb.r.toString())>=0){
				leftName = aEQb.l.toString();
				rightName = aEQb.r.toString();
//				lattr.add(leftName);
//				rattr.add(rightName);
			}else if(right.getSchema().findStrictIndex(aEQb.l.toString())>=0 && left.getSchema().findStrictIndex(aEQb.r.toString())>=0) {
				leftName = aEQb.r.toString();
				rightName = aEQb.l.toString();
//				lattr.add(leftName);
//				rattr.add(rightName);
			}else{
				Util.warn("meow@ThetaJoin eval: Not merge-join, falling back to normal theta join.");
				hasMergeJoin = false;
			}
//			if(hasMergeJoin){
//				left = new Order(left, new ArrayList<Expr>(), lattr, orderType);
//				left.parent = this;
//				right = new Order(right, new ArrayList<Expr>(), rattr, orderType);
//				right.parent = this;
//			}
		}
		left.eval(env);
		right.eval(env);
//		hasMergeJoin = false;
		if(!hasMergeJoin){
			Util.warn("Watch me I'm Theta-Join!!!" +schema.tableName +" s.t. "+ Util.deepToString(condList));
			fetchNext();
		}else{
			LinkedList<Record> lresults = new LinkedList<Record>();
			while(left.hasNext()){
				lresults.add(left.next());
			}
			Collections.sort(lresults, new Comparator<Record>(){
				public int compare(Record a, Record b){
					Field l = a.getCol(leftName);
					Field r = b.getCol(leftName);
					if(l.applyWithComp(BinaryOp.GREATER, r))
						return 1;
					if(l.applyWithComp(BinaryOp.LESS, r))
						return -1;
					return 0;
				}
			});
			LinkedList<Record> rresults = new LinkedList<Record>();
			while(right.hasNext()){
				rresults.add(right.next());
			}
			Collections.sort(rresults, new Comparator<Record>(){
				public int compare(Record a, Record b){
					Field l = a.getCol(rightName);
					Field r = b.getCol(rightName);
					if(l.applyWithComp(BinaryOp.GREATER, r))
						return 1;
					if(l.applyWithComp(BinaryOp.LESS, r))
						return -1;
					return 0;
				}
			});
			if(lresults.isEmpty() || rresults.isEmpty())return;
			Util.warn("Watch me I'm Merge-Join!!!"+leftName+"="+ rightName);
			Record curl=lresults.pollFirst();
			Record curr=rresults.pollFirst();
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
					while(lval.applyWithComp(BinaryOp.GREATER, rval) && !rresults.isEmpty()){
						curr = rresults.pollFirst();
						rval = curr.getCol(rightName);
					}
					while(lval.applyWithComp(BinaryOp.EQ, rval)){
						Record tmp = productRecord(curl, curr);
						if(predTest(tmp))
							results.add(tmp);
						lastRight.add(curr);
						if(rresults.isEmpty())
							break;
						curr = rresults.pollFirst();
						rval = curr.getCol(rightName);
					}
					lastr = lval;
				}
				if(lresults.isEmpty())
					break;
				curl = lresults.pollFirst();
			}
		}
//		Util.warn("Watch me I'm Merge-Join!!! I got "+results.size()+" results");
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
