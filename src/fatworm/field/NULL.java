package fatworm.field;

import fatworm.absyn.BinaryOp;
import fatworm.util.Env;

public class NULL extends Field {

	public NULL() {
		type = java.sql.Types.NULL;
	}

	@Override
	public boolean applyWithComp(Env env, BinaryOp op, Field x) {
		// FIXME Not implemented
		error("NULL.compWith missing type.");
		return false;
	}

	@Override
	public int getInt(Env env) {
		error("NULL.getInt dead end.");
		return 0;
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
