package fatworm.logicplan;

import fatworm.driver.Record;
import fatworm.util.Env;

public class RenameTable extends Plan {

	public Plan src;
	String alias;
	public RenameTable(Plan src, String alias) {
		super();
		this.src = src;
		this.alias = alias;
		this.src.parent = this;
		myAggr.addAll(this.src.getAggr());
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
	}
	@Override
	public String toString(){
		return "renameTable (from="+src.toString()+")";
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
