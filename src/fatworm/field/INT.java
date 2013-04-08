package fatworm.field;

import java.nio.ByteBuffer;

public class INT extends Field {

	int v;
	public INT(int v) {
		this.v = v;
		this.type = java.sql.Types.INTEGER;
	}
	
	public INT(ByteBuffer b){
		v = b.getInt();
	}

	public INT(String x) {
		v = Integer.valueOf(x);
	}

}
