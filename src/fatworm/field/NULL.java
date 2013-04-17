package fatworm.field;

import fatworm.absyn.BinaryOp;

public class NULL extends Field {

	//FIXME maybe one instance for NULL is enough
	static NULL instance;
	public NULL() {
		type = java.sql.Types.NULL;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		error("NULL.compWith missing type.");
		return false;
	}

	@Override
	public String toString() {
		return "null";
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	public synchronized static Field getInstance(){
		if(instance == null)instance = new NULL();
		return instance;
	}
}
