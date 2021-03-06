package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.ByteBuilder;

public class NULL extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2494072860665295387L;
	//FIXME maybe one instance for NULL is enough
	static NULL instance;
	public NULL() {
		type = java.sql.Types.NULL;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		error2("NULL.compWith missing type!!!");
		return false;
	}

	@Override
	public String toString() {
		return "NULL";
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	public synchronized static Field getInstance(){
		if(instance == null)instance = new NULL();
		return instance;
	}

	@Override
	public void pushByte(ByteBuilder b, int A, int B) {
		// TODO or do nothing
	}
}
