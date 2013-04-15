package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

import fatworm.absyn.Expr;
import fatworm.absyn.FuncCall;
import fatworm.field.Field;
import fatworm.util.Env;

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
	public void updateColWithAggr(Env env, List<Expr> func) {
		for(int i=0;i<func.size();i++){
			Expr e = func.get(i);
			if(e instanceof FuncCall)
				cols.set(i, e.eval(env));
		}
	}
	public void initCol(Env env, List<Expr> func) {
		for(int i=0;i<func.size();i++){
			Expr e = func.get(i);
			if(!(e instanceof FuncCall))
				cols.set(i, e.eval(env));
		}
	}
}
