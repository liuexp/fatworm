package fatworm.field;
import static java.sql.Types.*;

public abstract class Field {

	public int type;
	public Field() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object o){
		if(o == null)return false;
		return this.toString().equals(o.toString());
	}
	
	public static Field fromString(int type, String x){
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
	}
}
