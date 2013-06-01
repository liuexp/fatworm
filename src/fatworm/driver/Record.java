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
	public Integer nullMap = 0;
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
		if(func.size()==0||Util.trim(func.get(0).toString()).equals("*")){
			for(int i=0;i<schema.columnName.size();i++){
				String col = schema.columnName.get(i);
				Field f = env.get(col);
				if(f==null)f = env.get(Util.getAttr(col));
				cols.add(f);
			}
		}else{
			for(int i=0;i<func.size();i++){
				Expr e = func.get(i);
				Field f = env.get(e.toString());
				if(f == null||f instanceof FuncCall.ContField) f= e.eval(env);
				cols.add(f);
			}
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
	public void autoFill() {
		for(int i=0;i<schema.columnName.size();i++){
			String colName = schema.columnName.get(i);
			Column col = schema.getColumn(colName);
			if(col.hasDefault() || col.type == java.sql.Types.TIMESTAMP || col.type == java.sql.Types.DATE){
				cols.add(Util.getField(col, null));
			} else 
				cols.add(null);
		}
	}
	public void setField(String colName, Field field) {
		cols.set(schema.findIndex(colName), field);
	}
}
