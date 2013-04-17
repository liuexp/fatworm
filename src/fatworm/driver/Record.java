package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.absyn.FuncCall;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;

public class Record {

	public List<Field> cols = new ArrayList<Field>();
	public Schema meta;
	public Record() {
		// TODO Auto-generated constructor stub
	}
	public Record(Schema meta2) {
		meta = meta2;
	}
	public Field getCol(String by) {
		return cols.get(meta.findIndex(by));
	}
	public void addCol(Field x){
		cols.add(x);
	}
	public void fillCol(Env env, List<Expr> func) {
		for(int i=0;i<func.size();i++){
			Expr e = func.get(i);
			cols.set(i, e.eval(env));
		}
	}
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Record))
			return false;

		Record z = (Record) o;

		return Util.deepEquals(cols, z.cols);
	}
	
	@Override
	public int hashCode() {
		return Util.deepHashCode(cols);
	}
}
