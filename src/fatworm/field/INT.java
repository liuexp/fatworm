package fatworm.field;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import fatworm.absyn.BinaryOp;

public class INT extends Field {

	int v;
	public INT(){
		this.type = java.sql.Types.INTEGER;
	}
	public INT(int v) {
		this();
		this.v = v;
	}
	
	public INT(ByteBuffer b){
		this();
		v = b.getInt();
	}

	public INT(String x) {
		this();
		v = Integer.valueOf(x);
	}

	public BigDecimal toDecimal() {
		return new BigDecimal(v).setScale(10);
	}

	@Override
	public int hashCode() {
		return v;
	}
	@Override
	public String toString() {
		return "" + v;
	}

	@Override
	public boolean applyWithComp(BinaryOp op, Field x) {
		BigDecimal value = toDecimal();
		switch(x.type){
		case java.sql.Types.DECIMAL:
			return Field.cmpHelper(op, value, ((DECIMAL)x).v);
		case java.sql.Types.INTEGER:
			return Field.cmpHelper(op, value, ((INT)x).toDecimal());
		case java.sql.Types.FLOAT:
			return Field.cmpHelper(op, value, ((FLOAT)x).toDecimal());
		}
		error("INT.compWith missing type");
		return false;
	}

}
