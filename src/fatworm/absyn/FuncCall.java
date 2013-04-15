package fatworm.absyn;

import fatworm.field.Field;
import fatworm.util.*;

public class FuncCall extends Expr {

	// TODO How to specify the return type?
	// FIXME this might not be just a column name and rather an expression?
	int func;
	public String col;
	public ContField cont;
	public FuncCall(int func, String col) {
		this.col = col;
		this.func = func;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		// TODO remember to put func value into env!!!
		// FIXME what's this doing!!!
		return env.get(this.toString());
	}
	@Override
	public String toString() {
		return Util.getFuncName(func) + "(" + col + ")";
	}
	
	// XXX A hack to make Record work with ContField
	// in particular to tackle cases like SELECT AVG( a + ab ) FROM  `meow` GROUP BY a
	public static class ContField extends Field{

		public int func;
		
		public ContField(){
			
		}
		@Override
		public boolean applyWithComp(BinaryOp op, Field x) {
			error("ContField never reac");
			return false;
		}
		
		public ContField applyWithAggr(){
			//TODO
			return null;
		}
	}
}
