package fatworm.logicplan;

import fatworm.util.Env;

public class Join extends Plan {

	public Plan left,right;
	public Join(Plan left, Plan right) {
		super(null);
		this.left = left;
		this.right = right;
		this.left.parent = this;
		this.right.parent = this;
	}

	@Override
	public void eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return "Join (left="+left.toString()+", right="+right.toString()+")";
	}
}
