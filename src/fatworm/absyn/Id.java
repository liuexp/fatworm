package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.Env;


public class Id extends Expr {
	
	public String name;

	public Id(String sym) {
		this.name = sym;
		this.size=1;
	}
	
	@Override
	public String toString() {
		return name.toString();
	}

	@Override
	public boolean evalPred(Env env) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field eval(Env env) {
		// TODO Auto-generated method stub
		return null;
	}
}
