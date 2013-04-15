package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.util.Env;
import fatworm.util.Util;

public class Join extends Plan {

	public Plan left,right;
	public Record curLeft;
	public Schema schema;
	public Join(Plan left, Plan right) {
		super();
		this.left = left;
		this.right = right;
		this.left.parent = this;
		this.right.parent = this;
		curLeft = null;
		myAggr.addAll(this.left.getAggr());
		myAggr.addAll(this.right.getAggr());
	}

	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		Env env = envGlobal.clone();
		left.eval(env);
		right.eval(env);
	}
	@Override
	public String toString(){
		return "Join (left="+left.toString()+", right="+right.toString()+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Join not eval");
		return right.hasNext();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Join not eval");
		Record r = right.next();
		if(curLeft == null)
			curLeft = left.next();
		Record ret = new Record(schema);
		ret.cols.addAll(curLeft.cols);
		ret.cols.addAll(r.cols);
		if(!right.hasNext()){
			right.reset();
			curLeft = left.next();
		}
		return ret;
	}

	@Override
	public void reset() {
		left.reset();
		right.reset();
		curLeft = null;
	}
}
