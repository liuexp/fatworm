package fatworm.field;
import static java.sql.Types.*;
import fatworm.absyn.BinaryOp;
import fatworm.util.Env;

public abstract class Field {

	public int type;
	public Field() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object o){
		if(o == null)return false;
		return this.toString().equals(o.toString());
	}
	
	/*public static Field fromString(int type, String x){
		switch(type){
		case BOOLEAN:
			return new BOOL(x);
		case CHAR:
			return new CHAR(x);
		case DATE:
			return new DATE(x);
		case DECIMAL:
			return new DECIMAL(x);
		case FLOAT:
			return new FLOAT(x);
		case INTEGER:
			return new INT(x);
		case NULL:
			return new NULL();
		case TIMESTAMP:
			return new TIMESTAMP(x);
		case VARCHAR:
			return new VARCHAR(x);
			
			default:
				return null;
		}
	}*/
	
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
	
	public abstract boolean applyWithComp(Env env, BinaryOp op, Field x);
	//extract an int
	public abstract int getInt(Env env);
}
