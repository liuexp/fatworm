package fatworm.logicplan;

import java.util.List;

import fatworm.absyn.Expr;
import fatworm.driver.Scan;

public class Project extends Plan {
	
	Plan src;
	List<Expr> names;

	public Project(Plan src, List<Expr> names) {
		super(null);
		this.src = src;
		this.names = names;
		src.parent = this;
	}

	@Override
	public Scan eval() {
		// TODO Auto-generated method stub
		return null;
	}

}
