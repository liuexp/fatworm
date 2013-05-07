package fatworm.absyn;

import java.util.LinkedList;
import java.util.List;

import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.field.INT;

public class IntLiteral extends Expr {
	
	public INT i;

	public IntLiteral(int i) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new INT(i);
		value = this.i;
		type = java.sql.Types.INTEGER;
	}
	
	@Override
	public String toString() {
		return String.format("%d", i.v);
	}

	@Override
	public boolean evalPred(Env env) {
		return i.v != 0 ? true : false;
	}

	@Override
	public Field eval(Env env) {
		return i;
	}

	@Override
	public List<String> getRequestedColumns() {
		return new LinkedList<String>();
	}

}
