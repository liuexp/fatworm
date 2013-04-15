package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;


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
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		return env.get(name);
	}
}
