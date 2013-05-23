package fatworm.absyn;

import java.util.List;

import fatworm.driver.Schema;
import fatworm.field.BOOL;
import fatworm.field.Field;
import fatworm.logicplan.Plan;
import fatworm.util.Env;
import fatworm.util.Util;

public class AnyCall extends Expr {

	public boolean isAll;
	public Plan src;
	public Expr expr;
	public BinaryOp op;
	public AnyCall(Plan src, Expr expr, BinaryOp op, boolean isAll) {
		super();
		this.src = src;
		this.expr = expr;
		this.op = op;
		this.isAll = isAll;
		myAggr.addAll(this.expr.getAggr());
		myAggr.addAll(this.src.getAggr());
		type = java.sql.Types.BOOLEAN;
	}

	@Override
	public boolean evalPred(Env env) {
//		System.out.println(env.toString());
		Field l = expr.eval(env);
		src.eval(env);
//		System.out.println(env.toString());
		if(isAll){
			while(src.hasNext()){
				Field r = src.next().cols.get(0);
				//System.out.println("l="+l.toString()+",r="+r.toString()+",res="+l.applyWithComp(op, r));
				if(!l.applyWithComp(op, r))return false;
			}
			return true;
		} else {
			while(src.hasNext()){
				if(l.applyWithComp(op, src.next().cols.get(0)))return true;
			}
			return false;
		}
	}

	@Override
	public Field eval(Env env) {
		return new BOOL(evalPred(env));
	}
	@Override
	public String toString() {
		return expr.toString() + op.toString() + (isAll? "all ":"any ") + src.toString();
	}

	@Override
	public int getType(Schema schema) {
		return type;
	}

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = src.getRequestedColumns();
		Util.addAllCol(z, expr.getRequestedColumns());
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
//		expr.rename(oldName, newName);
		//FIXME also rename src
	}

	@Override
	public boolean hasSubquery() {
		return true;
	}

}
