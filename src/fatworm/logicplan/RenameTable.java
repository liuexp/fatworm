package fatworm.logicplan;

import fatworm.util.Env;

public class RenameTable extends Plan {

	public Plan src;
	String alias;
	public RenameTable(Plan src, String alias) {
		super(null);
		this.src = src;
		this.alias = alias;
		this.src.parent = this;
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "renameTable (from="+src.toString()+")";
	}
	
}
