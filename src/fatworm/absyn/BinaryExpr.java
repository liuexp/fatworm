package fatworm.absyn;

import java.math.BigDecimal;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.driver.Schema;
import fatworm.field.BOOL;
import fatworm.field.DECIMAL;
import fatworm.field.FLOAT;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.util.Env;
import fatworm.util.Util;
import static java.sql.Types.INTEGER;
import static java.sql.Types.DECIMAL;
import static java.sql.Types.FLOAT;

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
		value=isConst ? eval() : null;
		myAggr.addAll(l.getAggr());
		myAggr.addAll(r.getAggr());
		type = evalType(null);
	}
	
	private Field eval() {
		return evalHelper(l.value, r.value);
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
		String ls = l.toString();
		if(l.depth>1)ls = "(" + ls + ")";
		String rs = r.toString();
		if(r.depth>1)rs = "(" + rs + ")";
		
		return ls + op.toString() + rs;
	}
	public Field evalHelper(Field lval, Field rval) {
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
			case AND:
				return new BOOL(Util.toBoolean(lval)&&Util.toBoolean(rval));
			case OR:
				return new BOOL(Util.toBoolean(lval)||Util.toBoolean(rval));
			default:
				error("Missing ops");
			}
		} catch (Exception e) {
			// FIXME on exception, this should return a null field rather than an exception
			e.printStackTrace();
		}
		return NULL.getInstance();
	}
	
	@Override
	public Field eval(Env env) {
		if(isConst)return value;
		Field lval = l.eval(env);
		Field rval = r.eval(env);
		return evalHelper(lval, rval);
	}

	private Field mymod(Field lval, Field rval) {
		if(lval.type == INTEGER && rval.type == INTEGER){
			int a = ((INT)lval).v;
			int b = ((INT)rval).v;
			return new INT(a%b);
		}
		Util.warn("mymod falling back to null");
		return NULL.getInstance();
	}

	private Field mydiv(Field lval, Field rval) {
		BigDecimal ret = lval.toDecimal().divide(rval.toDecimal(), 9, BigDecimal.ROUND_HALF_EVEN);
		if(lval.type == INTEGER && rval.type == INTEGER)
			return new FLOAT(ret.intValue());
		if(lval.type == DECIMAL || rval.type == DECIMAL)
			return new DECIMAL(ret);
		if(lval.type == FLOAT || rval.type == FLOAT)
			return new FLOAT(ret.floatValue());
		error("missing type");
		return NULL.getInstance();
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
		return NULL.getInstance();
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
		return NULL.getInstance();
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
		return NULL.getInstance();
	}
	
	public int evalType(Schema src){
		int ltype,rtype;
		ltype = src ==null?l.getType():l.getType(src);
		rtype = src == null?r.getType():r.getType(src);
		switch(op){
		case LESS:
		case GREATER:
		case LESS_EQ:
		case GREATER_EQ:
		case EQ:
		case NEQ:
		case AND:
		case OR:
			return java.sql.Types.BOOLEAN;
		case PLUS:
		case MINUS:
		case MULTIPLY:
			if(ltype==INTEGER&&rtype==INTEGER)
				return INTEGER;
			if(ltype == DECIMAL || rtype== DECIMAL)
				return DECIMAL;
			if(ltype == FLOAT || rtype== FLOAT)
				return FLOAT;
			return java.sql.Types.NULL;
		case DIVIDE:
			if(ltype==INTEGER&&rtype == INTEGER)
				return FLOAT;
			if(ltype== DECIMAL || rtype == DECIMAL)
				return DECIMAL;
			if(ltype== FLOAT || rtype == FLOAT)
				return FLOAT;
			return java.sql.Types.NULL;
		case MODULO:
			return INTEGER;
		default:
			error("Missing ops");
		}
		return java.sql.Types.NULL;
	}

	@Override
	public int getType(Schema schema) {
		if(type == java.sql.Types.NULL)
			return evalType(schema);
		return type;
	}
	
	public BinaryExpr toCNF(){
		// TODO what about a AndList?
		Expr left = (l instanceof BinaryExpr)?((BinaryExpr)l).toCNF() : l;
		Expr right = (r instanceof BinaryExpr)?((BinaryExpr)r).toCNF() : r;
		BinaryExpr ret = null;
		if(this.op == BinaryOp.OR){
			if(left.isAnd()){
				ret = new BinaryExpr(
						new BinaryExpr(((BinaryExpr)left).l, BinaryOp.OR, right),
						BinaryOp.AND,
						new BinaryExpr(((BinaryExpr)left).r, BinaryOp.OR, right)
						);
			} else if(right.isAnd()){
				ret = new BinaryExpr(
						new BinaryExpr(left, BinaryOp.OR, ((BinaryExpr)right).l),
						BinaryOp.AND,
						new BinaryExpr(left, BinaryOp.OR, ((BinaryExpr)right).r)
						);
			}
		}
		this.l = left;
		this.r = right;
		if(ret == null) ret = this;
		else ret = ret.toCNF();
		return ret;
	}
	


	@Override
	public List<String> getRequestedColumns() {
		List<String> z = l.getRequestedColumns();
		Util.addAllCol(z, r.getRequestedColumns());
		return z;
	}
}
