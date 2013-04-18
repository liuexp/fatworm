package fatworm.absyn;

import fatworm.field.Field;
import fatworm.field.NULL;
import fatworm.util.Env;
import fatworm.util.Util;


public class Id extends Expr {
	
	public String name;

	public Id(String sym) {
		super();
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
		if(name.equalsIgnoreCase("null"))return NULL.getInstance();
		Field ret=env.get(name);
		if(ret == null)
			ret = env.get(Util.getAttr(name));
		return ret;
	}
}
