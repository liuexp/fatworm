package fatworm.logicplan;

import java.util.List;

import fatworm.driver.Scan;

public class Order extends Node {

	public Node src;
	public List<String> orderField;
	public List<Integer> orderType;
	public Order(Node src, List<String> a, List<Integer> b) {
		super(null);
		this.src = src;
		src.parent = this;
		orderField = a;
		orderType = b;
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
