package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.field.Field;
import fatworm.util.Env;
import fatworm.util.Util;

public class Record {

	public List<Field> cols = new ArrayList<Field>();
	public Schema schema;
	public Record() {
		// TODO Auto-generated constructor stub
	}
	public Record(Schema scm) {
		schema = scm;
	}
	public Field getCol(String by) {
		return cols.get(schema.findIndex(by));
	}
	public void addCol(Field x){
		cols.add(x);
	}
	public void addColFromExpr(Env env, List<Expr> func) {
		for(int i=0;i<func.size();i++){
			Expr e = func.get(i);
			cols.add(e.eval(env));
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
	@Override
	public String toString(){
		return "[\r\n" + schema.toString() + "\r\n]" + Util.deepToString(cols);
	}
}
