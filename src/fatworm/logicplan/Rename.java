package fatworm.logicplan;

import java.util.List;

import fatworm.driver.Scan;
public class Rename extends Node {
	
	public List<String> as;
	public Node src;

	public Rename(Node src, List<String> as) {
		super(null);
		this.src = src;
		this.as = as;
		src.parent = this;
	}

	@Override
	public String toString(){
		return "rename (from="+src.toString()+")";
	}
	
	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
