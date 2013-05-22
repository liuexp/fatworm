package fatworm.absyn;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import fatworm.field.DECIMAL;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.util.Env;

public class IntLiteral extends Expr {
	
	public Field i;

	public IntLiteral(BigInteger i) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new DECIMAL(i.toString());
		value = this.i;
		type = java.sql.Types.DECIMAL;
	}
	
	public IntLiteral(int i) {
		super();
		this.isConst = true;
		this.size=1;
		this.i = new DECIMAL(i.toString());
		value = this.i;
		type = java.sql.Types.INTEGER;
	}
	@Override
	public String toString() {
		return i.toString();
	}

	@Override
	public boolean evalPred(Env env) {
		return i.applyWithComp(BinaryOp.EQ, new INT(0)) ? true : false;
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
