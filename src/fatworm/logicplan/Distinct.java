package fatworm.logicplan;

import fatworm.driver.Scan;

public class Distinct extends Plan {

	public Plan src;
	public Distinct(Plan src) {
		super(null);
		this.src = src;
		src.parent = this;
	}

	// TODO memory distinct and disk distinct
	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
