package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.driver.Column;
import fatworm.driver.Schema;
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
		if(ret == null)
			Util.warn("Id not found:"+name+","+env.toString());
		return ret;
	}

	@Override
	public int getType(Schema schema) {
//		System.out.println(schema.toString());
//		System.out.println(name);
//		System.out.println(Util.deepToString(schema.columnDef));
		Column c = schema.getColumn(name);
		if(c!=null)return schema.getColumn(name).type;
		return java.sql.Types.NULL;
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = new LinkedList<String>();
		z.add(name);
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
//		if(Util.getAttr(oldName).equalsIgnoreCase(Util.getAttr(name))){
		if((!name.contains(".")&&Util.getAttr(oldName).equalsIgnoreCase(Util.getAttr(name)))||
				oldName.equalsIgnoreCase(name)){
			name = newName;
		}
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

	@Override
	public Expr clone() {
		return new Id(name);
	}
}
