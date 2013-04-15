package fatworm.logicplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fatworm.absyn.BinaryOp;
import fatworm.driver.Record;
import fatworm.field.Field;
import fatworm.parser.FatwormParser;
import fatworm.util.Env;
import fatworm.util.Util;

public class Order extends Plan {

	// TODO External Sorting
	public Plan src;
	public List<String> orderField;
	public List<Integer> orderType;
	public List<Record> results;
	public int ptr;
	
	public Order(Plan src, List<String> a, List<Integer> b) {
		super();
		this.src = src;
		src.parent = this;
		orderField = a;
		orderType = b;
		ptr = 0;
		results = new ArrayList<Record>();
		myAggr.addAll(this.src.getAggr());
	}

	@Override
	public void eval(Env envGlobal) {
		hasEval = true;
		Env env = envGlobal.clone();
		src.eval(env);
		while(src.hasNext()){
			results.add(src.next());
		}
		Collections.sort(results, new Comparator<Record>(){
			public int compare(Record a, Record b){
				for(int i=0;i<orderField.size();i++){
					Field l = a.getCol(orderField.get(i));
					Field r = b.getCol(orderField.get(i));
					if(orderType.get(i) == FatwormParser.ASC){
						if(l.applyWithComp(BinaryOp.GREATER, r))
							return 1;
						if(l.applyWithComp(BinaryOp.LESS, r))
							return -1;
					} else {
						if(l.applyWithComp(BinaryOp.GREATER, r))
							return -1;
						if(l.applyWithComp(BinaryOp.LESS, r))
							return 1;
					}
				}
				return 0;
			}
		});
	}
	@Override
	public String toString(){
		return "order (from="+src.toString()+")";
	}

	@Override
	public boolean hasNext() {
		if(!hasEval)Util.error("Order not eval");
		return ptr != results.size();
	}

	@Override
	public Record next() {
		if(!hasEval)Util.error("Order not eval");
		return results.get(ptr++);
	}

	@Override
	public void reset() {
		ptr = 0;
	}
}
