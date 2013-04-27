package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;
import fatworm.absyn.FuncCall;

public abstract class Field {

	public int type;
	public Field() {
		// TODO Auto-generated constructor stub
	}

	// FIXME watch & check this!!! Cause equals is used in HashMap besides hash code!!!
	public boolean equals(Object o){
		if(o == null)return false;
		return this.toString().equals(o.toString());
	}
	
	public static Field fromString(int type, String x){
		if(x.equalsIgnoreCase("null"))return NULL.getInstance();
		switch(type){
		case java.sql.Types.BOOLEAN:
			return new BOOL(x);
		case java.sql.Types.CHAR:
			return new CHAR(x);
		case java.sql.Types.DATE:
			return new DATE(x);
		case java.sql.Types.DECIMAL:
			return new DECIMAL(x);
		case java.sql.Types.FLOAT:
			return new FLOAT(x);
		case java.sql.Types.INTEGER:
			return new INT(x);
		case java.sql.Types.NULL:
			return NULL.getInstance();
		case java.sql.Types.TIMESTAMP:
			return new TIMESTAMP(x);
		case java.sql.Types.VARCHAR:
			return new VARCHAR(x);
			
			default:
				return null;
		}
	}
	
	public static <T> boolean cmpHelper(BinaryOp op, Comparable<T> a, T b){
		switch(op){
		case LESS:
			return a.compareTo(b)<0;
		case LESS_EQ:
			return a.compareTo(b)<=0;
		case EQ:
			return a.compareTo(b)==0;
		case NEQ:
			return a.compareTo(b)!=0;
		case GREATER:
			return a.compareTo(b)>0;
		case GREATER_EQ:
			return a.compareTo(b)>=0;
			default:
				error2("cmpString missing op!");
		}
		return false;
	}
	
	public static boolean cmpString(BinaryOp op, String a, String b){
		switch(op){
		case LESS:
			return a.compareToIgnoreCase(b)<0;
		case LESS_EQ:
			return a.compareToIgnoreCase(b)<=0;
		case EQ:
			return a.compareToIgnoreCase(b)==0;
		case NEQ:
			return a.compareToIgnoreCase(b)!=0;
		case GREATER:
			return a.compareToIgnoreCase(b)>0;
		case GREATER_EQ:
			return a.compareToIgnoreCase(b)>=0;
			default:
				error2("cmpString missing op!");
		}
		return false;
	}
	
	public static void error(String e){
		throw new RuntimeException(e);
	}
	
	public static void error2(String e){
		System.err.println(e);
	}
	
	public abstract boolean applyWithComp(BinaryOp op, Field x);
	
	public BigDecimal toDecimal(){
		return null;
	}
	public static Object getObject(Field f){
		if(f instanceof FuncCall.ContField)f = ((FuncCall.ContField) f).getFinalResults();
		int type = f.type;
		switch(type){
		case java.sql.Types.BOOLEAN:
			return new Boolean(((BOOL)f).v);
		case java.sql.Types.CHAR:
			return new String(((CHAR)f).v);
		case java.sql.Types.DATE:
			return new java.sql.Date(((DATE)f).v.getTime());
		case java.sql.Types.DECIMAL:
			return ((DECIMAL)f).toDecimal();
		case java.sql.Types.FLOAT:
			return new Float(((FLOAT)f).v);
		case java.sql.Types.INTEGER:
			return new Integer(((INT)f).v);
		case java.sql.Types.TIMESTAMP:
			return new java.sql.Timestamp(((TIMESTAMP)f).v.getTime());
		case java.sql.Types.VARCHAR:
			return new String(((VARCHAR)f).v);
		case java.sql.Types.NULL:
			return null;
			//return new String(f.toString());
			default:
				error2("Meow!!"+f.toString());
				return new String(f.toString());
		}
	}
	public static Field fromObject(Object f){
		if(f instanceof Field) return (Field) f;
		if(f instanceof Boolean)
			return new BOOL(((Boolean)f));
		else if(f instanceof String && ((String)f).length() == 1)
			return new CHAR((String)f);
		else if(f instanceof java.sql.Date)
			return new DATE(new java.sql.Timestamp(((java.sql.Date)f).getTime()));
		else if(f instanceof BigDecimal)
			return new DECIMAL((BigDecimal)f);
		else if(f instanceof Float)
			return new FLOAT((Float)f);
		else if(f instanceof Integer)
			return new INT((Integer)f);
		else if(f instanceof java.sql.Timestamp)
			return new TIMESTAMP((java.sql.Timestamp)f);
		else if(f instanceof String)
			return new VARCHAR((String)f);
		else
			return NULL.getInstance();
	}
	/*@Override
	public boolean equals(Object x){
		if(!(o instanceof Field))
			return false;
		Field r = (Field) x;
		return this.applyWithComp(BinaryOp.EQ, r);
	}*/
}
