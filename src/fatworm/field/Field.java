package fatworm.field;

import java.math.BigDecimal;

import fatworm.absyn.BinaryOp;

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
				throw new RuntimeException("cmpString missing op!");
		}
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
				throw new RuntimeException("cmpString missing op!");
		}
	}
	
	public static void error(String e){
		throw new RuntimeException(e);
	}
	
	public abstract boolean applyWithComp(BinaryOp op, Field x);
	
	public BigDecimal toDecimal(){
		return null;
	}
	
}
