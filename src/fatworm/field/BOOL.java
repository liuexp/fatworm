package fatworm.field;

public class BOOL extends Field {

	boolean v;
	public BOOL(boolean x){
		v = x;
		type = java.sql.Types.BOOLEAN;
	}
	public BOOL(String x) {
		//FIXME 0 and 1
		v = Boolean.valueOf(x);
		type = java.sql.Types.BOOLEAN;
	}

}
