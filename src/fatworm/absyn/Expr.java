package fatworm.absyn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.util.Env;

public abstract class Expr {
	
	public Integer size,depth;
	public Field value;
	public boolean isConst;
	public List<FuncCall> myAggr;
	public int type;

	public Expr() {
		size=1;
		depth=1;
		isConst = false;
		myAggr = new ArrayList<FuncCall>();
		value = null;
	}
	//eval as a predicate
	public abstract boolean evalPred(Env env);
	
	//eval as an expression
	public abstract Field eval(Env env);
	
	public List<FuncCall> getAggr() {
		return myAggr;
	}
	public boolean hasAggr() {
		return !myAggr.isEmpty();
	}
	public int getType(Schema schema){
		return type;
	}
	public int getType(){
		return type;
	}
	public abstract List<String> getRequestedColumns();
	public boolean isAnd(){
		return this instanceof BinaryExpr && ((BinaryExpr)this).op == BinaryOp.AND;
	}
	public void collectCond(Collection<Expr> c){
		if(!isAnd()){
			c.add(this);
		}else{
			((BinaryExpr)this).l.collectCond(c);
			((BinaryExpr)this).r.collectCond(c);
		}
	}
	
//	public abstract boolean canEvalOn(Schema schema);
	public abstract void rename(String oldName, String newName);
	public abstract boolean hasSubquery();
}
