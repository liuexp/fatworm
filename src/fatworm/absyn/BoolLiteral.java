package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.driver.Schema;
import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.util.Env;

public class BoolLiteral extends Expr {

	public BOOL i;
	public BoolLiteral(boolean v) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new BOOL(v);
		value = this.i;
		type = java.sql.Types.BOOLEAN;
	}
	@Override
	public String toString() {
		return i.toString();
	}
 
	@Override
	public boolean evalPred(Env env) {
		return i.v;
	}

	@Override
	public Field eval(Env env) {
		return i;
	}
	@Override
	public int getType(Schema schema) {
		return type;
	}
	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}


}
