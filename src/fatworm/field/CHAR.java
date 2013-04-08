package fatworm.field;

public class CHAR extends Field {

	String v;
	public CHAR(String x) {
		v = x;
		type = java.sql.Types.CHAR;
	}

}
