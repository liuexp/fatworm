package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.field.Field;

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
	public void updateColWithAggr(String by, Field f, List<Expr> func) {
		// TODO Auto-generated method stub
		
	}
}
