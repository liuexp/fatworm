package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.field.Field;
import fatworm.field.VARCHAR;
import fatworm.util.Env;
import fatworm.util.Util;

public class StringLiteral extends Expr {
	
	public String s;

	public StringLiteral(String s) {
		super();
		this.s = s;
		this.isConst = true;
		this.size=1;
		this.value = new VARCHAR(s);
		type = java.sql.Types.VARCHAR;
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\"", s);
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(s);
	}

	@Override
	public Field eval(Env env) {
		return value;
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}

	@Override
	public void rename(String oldName, String newName) {
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

}
