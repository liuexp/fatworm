package fatworm.absyn;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
	private String myName;
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
		if(!hasEvalCont)Util.warn("Mie!!!You didn't give me anything to aggregate!!!");
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
	
	public ContField evalCont(ContField f, Field val) {
		hasEvalCont = true;
		if(f == null){
			f = ContField.newContField(func);
		}
		if(f instanceof ContField){
			((ContField) f).applyWithAggr(val);
		} else {
			Util.error("Mie!!");
		}
		return f;
	}
	@Override
	public String toString() {
//		return Util.getFuncName(func) + "(" + col + ")";
		if(myName!=null)return myName;
		myName = ""+Env.getNewTemp();
		return myName;
	}
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof FuncCall){
			return o.toString().equals(toString());
		}
		return false;
	}
	
	public boolean canEvalOn(Schema schema){
		return schema.findStrictIndex(col)>=0;
	}
	
	// XXX A hack to make Record work with ContField
	// in particular to tackle cases like SELECT AVG( a + ab ) FROM  `meow` GROUP BY a
	public abstract static class ContField extends Field{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 6030892886920992221L;

		public ContField(){
		}
		@Override
		public boolean applyWithComp(BinaryOp op, Field x) {
			Util.warn("ContField@applyWithComp should never reach, too aggresive on alias?");
			return getFinalResults().applyWithComp(op, x);
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
				Util.warn("ContField never reach.");
				return null;
			}
		}
		
		@Override
		public String toString(){
			return getFinalResults().toString();
		}
	}
	
	public static class AvgContField extends ContField{

		/**
		 * 
		 */
		private static final long serialVersionUID = -2476740024589020129L;
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
		public void pushByte(ByteBuilder b, int A, int B) {
			Util.error("should never reach here");
		}
	}
	public static class CountContField extends ContField{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8135247904034503486L;
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
		public void pushByte(ByteBuilder b, int A, int B) {
			Util.error("should never reach here");
		}
	}
	public static class MaxContField extends ContField{

		/**
		 * 
		 */
		private static final long serialVersionUID = 7003864920493570359L;
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
		public void pushByte(ByteBuilder b, int A, int B) {
			Util.error("should never reach here");
		}
	}
	public static class MinContField extends ContField{

		/**
		 * 
		 */
		private static final long serialVersionUID = -3681209761738752462L;
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
		public void pushByte(ByteBuilder b, int A, int B) {
			Util.error("should never reach here");
		}
	}
	public static class SumContField extends ContField{

		/**
		 * 
		 */
		private static final long serialVersionUID = 3363863800491382294L;
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
		public void pushByte(ByteBuilder b, int A, int B) {
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

	@Override
	public List<String> getRequestedColumns() {
		List<String> z = new LinkedList<String> ();
		z.add(col);
		return z;
	}

	@Override
	public void rename(String oldName, String newName) {
		if(col.equalsIgnoreCase(oldName)){
			col = newName;
		}
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

	@Override
	public Expr clone() {
		return new FuncCall(func, col);
	}
}
