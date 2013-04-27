package fatworm.absyn;

import java.math.BigDecimal;
import java.sql.Types;

import fatworm.driver.Schema;
import fatworm.field.DECIMAL;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.field.NULL;
import fatworm.parser.FatwormParser;
import fatworm.util.*;

public class FuncCall extends Expr {

	int func;
	boolean hasEvalCont;
	// FIXME this might not be just a column name rather than an expression?
	public String col;
	public FuncCall(int func, String col) {
		super();
		this.col = col;
		this.func = func;
		myAggr.add(this);
		hasEvalCont = false;
		type = java.sql.Types.NULL;
		if(func == FatwormParser.AVG || func == FatwormParser.SUM)
			type = java.sql.Types.DECIMAL;
		if(func == FatwormParser.COUNT)
			type = java.sql.Types.INTEGER;
	}

	@Override
	public boolean evalPred(Env env) {
		return Util.toBoolean(eval(env));
	}

	@Override
	public Field eval(Env env) {
		if(!hasEvalCont)Util.error("Mie!!!You didn't give me anything to aggregate!!!");
		Field f = env.get(this.toString());
		if(f == null){
			f = ContField.newContField(func);
			env.put(this.toString(), f);
		}
		if(f instanceof ContField){
			return ((ContField) f).getFinalResults();
		}
		return f;
	}
	
	public void evalCont(Env env) {
		hasEvalCont = true;
		Field f = env.get(this.toString());
		if(f == null){
			f = ContField.newContField(func);
			env.put(this.toString(), f);
		}
		if(f instanceof ContField){
			((ContField) f).applyWithAggr(env.get(col));
		} else {
			Util.error("Mie!!");
		}
	}
	@Override
	public String toString() {
		return Util.getFuncName(func) + "(" + col + ")";
	}
	
	public boolean canEvalOn(Schema schema){
		return schema.findStrictIndex(col)>=0;
	}
	
	// XXX A hack to make Record work with ContField
	// in particular to tackle cases like SELECT AVG( a + ab ) FROM  `meow` GROUP BY a
	public abstract static class ContField extends Field{
		
		public ContField(){
		}
		@Override
		public boolean applyWithComp(BinaryOp op, Field x) {
			error("ContField never reach");
			return false;
		}
		
		public abstract void applyWithAggr(Field x);
		
		public abstract Field getFinalResults();
		
		public static ContField newContField(int func){
			switch(func){
			case FatwormParser.AVG:
				return new AvgContField();
			case FatwormParser.COUNT:
				return new CountContField();
			case FatwormParser.MAX:
				return new MaxContField();
			case FatwormParser.MIN:
				return new MinContField();
			case FatwormParser.SUM:
				return new SumContField();
			default:
				error("ContField never reach.");
				return null;
			}
		}
		
		@Override
		public String toString(){
			return getFinalResults().toString();
		}
	}
	
	public static class AvgContField extends ContField{

		BigDecimal res;
		int cnt;
		boolean isNull;
		
		public AvgContField(){
			cnt = 0;
			isNull=true;
			res = new BigDecimal(0).setScale(10);
		}
		
		public void applyWithAggr(Field x){
			if(x instanceof NULL || x.type == java.sql.Types.NULL)return;
			isNull = false;
			res = res.add(x.toDecimal());
			cnt++;
		}
		
		public Field getFinalResults(){
			return isNull? NULL.getInstance() : 
				new DECIMAL(res.divide(new BigDecimal(cnt), 10, BigDecimal.ROUND_HALF_EVEN));
		}

		@Override
		public void pushByte(ByteBuilder b) {
			Util.error("should never reach here");
		}
	}
	public static class CountContField extends ContField{

		int cnt;
		
		public CountContField(){
			cnt = 0;
		}
		
		public void applyWithAggr(Field x){
			cnt++;
		}
		
		public Field getFinalResults(){
			return new INT(cnt);
		}

		@Override
		public void pushByte(ByteBuilder b) {
			Util.error("should never reach here");
		}
	}
	public static class MaxContField extends ContField{

		Field res;
		boolean isNull;
		
		public MaxContField(){
			res = NULL.getInstance();
			isNull = true;
		}
		
		public void applyWithAggr(Field x){
			if(x instanceof NULL || x.type == java.sql.Types.NULL)return;
			if(isNull){
				res = x;
				isNull = false;
				return;
			}
			if(res.applyWithComp(BinaryOp.LESS, x))
				res = x;
		}
		
		public Field getFinalResults(){
			return isNull? NULL.getInstance(): res;
		}

		@Override
		public void pushByte(ByteBuilder b) {
			Util.error("should never reach here");
		}
	}
	public static class MinContField extends ContField{

		Field res;
		boolean isNull;
		
		public MinContField(){
			res = NULL.getInstance();
			isNull = true;
		}
		
		public void applyWithAggr(Field x){
			if(x instanceof NULL || x.type == java.sql.Types.NULL)return;
			if(isNull){
				res = x;
				isNull = false;
				return;
			}
			if(res.applyWithComp(BinaryOp.GREATER, x))
				res = x;
		}
		
		public Field getFinalResults(){
			return isNull? NULL.getInstance(): res;
		}

		@Override
		public void pushByte(ByteBuilder b) {
			Util.error("should never reach here");
		}
	}
	public static class SumContField extends ContField{

		BigDecimal res;
		boolean isNull;
		
		public SumContField(){
			isNull=true;
			res = new BigDecimal(0).setScale(10);
		}
		
		public void applyWithAggr(Field x){
			if(x instanceof NULL || x.type == java.sql.Types.NULL)return;
			isNull = false;
			res = res.add(x.toDecimal());
		}
		
		public Field getFinalResults(){
			return isNull? NULL.getInstance() : 
				new DECIMAL(res);
		}

		@Override
		public void pushByte(ByteBuilder b) {
			Util.error("should never reach here");
		}
	}
	@Override
	public int getType(Schema schema) {
		if(type == java.sql.Types.NULL){
			type = schema.getColumn(col).type;
		}
		return type;
	}
}
