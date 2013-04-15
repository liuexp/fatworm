package fatworm.absyn;

import java.math.BigDecimal;

import fatworm.absyn.Expr;
import fatworm.field.BOOL;
import fatworm.field.DECIMAL;
import fatworm.field.FLOAT;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.util.Env;
import fatworm.util.Util;
import static java.sql.Types.*;

public class BinaryExpr extends Expr {
	
	public Expr l, r;
	public BinaryOp op;
	
    
	public BinaryExpr(Expr lhs, BinaryOp op, Expr rhs) {
		super();
		l = lhs;
		r = rhs;
		this.op = op;
		size=l.size+r.size+1;
		depth=max(l.depth,r.depth)+1;
		isConst=l.isConst&&r.isConst;
		value=isConst ? eval(null) : null;
		myAggr.addAll(l.getAggr());
		myAggr.addAll(r.getAggr());
	}
	
	public boolean evalPred(Env env){
		Field x = eval(env);
		return Util.toBoolean(x);
	}
	
	private Integer max(Integer a, Integer b) {
		return a>b?a:b;
	}
	
	private static void error(String s) {
		throw new RuntimeException("mie@BinaryExpr: "+s);
	}
	
	@Override
	public String toString(){
		return "("+l.toString()+")" + op.toString() +"("+ r.toString()+")";
	}
	@Override
	public Field eval(Env env) {
		//Note that value!=null shouldn't count for isConst
		
		if(isConst)return value;
		Field lval = l.eval(env);
		Field rval = r.eval(env);
		try {
			switch(op){
			case LESS:
			case GREATER:
			case LESS_EQ:
			case GREATER_EQ:
			case EQ:
			case NEQ:
				return new BOOL(lval.applyWithComp(op, rval));
			case PLUS:
				return myadd(lval, rval);
			case MINUS:
				return myminus(lval, rval);
			case MULTIPLY:
				return mymult(lval, rval);
			case DIVIDE:
				return mydiv(lval, rval);
			case MODULO:
				return mymod(lval, rval);
			default:
				error("Missing ops");
			}
		} catch (Exception e) {
			// FIXME on exception, this should return a null field rather than an exception
			e.printStackTrace();
		}
		return new NULL();
	}

	private Field mymod(Field lval, Field rval) {
		if(lval.type == INTEGER && rval.type == INTEGER){
			int a = ((INT)lval).v;
			int b = ((INT)rval).v;
			return new INT(a%b);
		}
		return new NULL();
	}

	private Field mydiv(Field lval, Field rval) {
		BigDecimal ret = lval.toDecimal().divide(rval.toDecimal(), 9, BigDecimal.ROUND_HALF_UP);
		if(lval.type == INTEGER && rval.type == INTEGER)
			return new INT(ret.intValue());
		if(lval.type == DECIMAL || rval.type == DECIMAL)
			return new DECIMAL(ret);
		if(lval.type == FLOAT || rval.type == FLOAT)
			return new FLOAT(ret.floatValue());
		error("missing type");
		return new NULL();
	}

	private Field mymult(Field lval, Field rval) {
		BigDecimal ret = lval.toDecimal().multiply(rval.toDecimal());
		if(lval.type == INTEGER && rval.type == INTEGER)
			return new INT(ret.intValue());
		if(lval.type == DECIMAL || rval.type == DECIMAL)
			return new DECIMAL(ret);
		if(lval.type == FLOAT || rval.type == FLOAT)
			return new FLOAT(ret.floatValue());
		error("missing type");
		return new NULL();
	}

	private Field myminus(Field lval, Field rval) {
		BigDecimal ret = lval.toDecimal().subtract(rval.toDecimal());
		if(lval.type == INTEGER && rval.type == INTEGER)
			return new INT(ret.intValue());
		if(lval.type == DECIMAL || rval.type == DECIMAL)
			return new DECIMAL(ret);
		if(lval.type == FLOAT || rval.type == FLOAT)
			return new FLOAT(ret.floatValue());
		error("missing type");
		return new NULL();
	}

	private Field myadd(Field lval, Field rval) {
		BigDecimal ret = lval.toDecimal().add(rval.toDecimal());
		if(lval.type == INTEGER && rval.type == INTEGER)
			return new INT(ret.intValue());
		if(lval.type == DECIMAL || rval.type == DECIMAL)
			return new DECIMAL(ret);
		if(lval.type == FLOAT || rval.type == FLOAT)
			return new FLOAT(ret.floatValue());
		error("missing type");
		return new NULL();
	}
}
