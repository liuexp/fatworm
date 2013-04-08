package fatworm.field;

import fatworm.absyn.BinaryOp;

public class NULL extends Field {

	public NULL() {
		type = java.sql.Types.NULL;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		// FIXME Not implemented
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
}
