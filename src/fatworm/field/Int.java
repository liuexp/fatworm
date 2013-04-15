package fatworm.field;

import java.nio.ByteBuffer;

public class Int extends Field {

	int v;
	public Int(int v) {
		this.v = v;
		this.type = java.sql.Types.INTEGER;
	}
	
	public Int(ByteBuffer b){
		v = b.getInt();
	}

}
