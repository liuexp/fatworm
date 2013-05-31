package fatworm.absyn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fatworm.driver.Record;
import fatworm.driver.Schema;
import fatworm.field.Field;
import fatworm.field.INT;
import fatworm.util.Env;
import fatworm.util.Util;

public class LinearCombinationList extends Expr {

	ArrayList<Integer> cs;
	ArrayList<Expr> xs;  
	LinkedList<String> requested;
	String myName;
	public LinearCombinationList(ArrayList<Expr> xs, ArrayList<Integer> cs) {
		this.xs = xs;
		this.cs= cs;
		size=xs.size()+1;
	}

	private int evalHelper(Integer c, Field value) {
		if(value.type == java.sql.Types.INTEGER)
			return c * ((INT)value).v;
		Util.error("LinearCombinationList missing type");
		return 0;
	}

	@Override
	public boolean evalPred(Env env) {
		Field x = eval(env);
		return Util.toBoolean(x);
	}

	@Override
	public Field eval(Env env) {
		if(isConst)return value;
		int ret = 0;
		for(int i=0;i<cs.size();i++){
			ret += evalHelper(cs.get(i), xs.get(i).eval(env));
		}
		return new INT(ret);
	}

	@Override
	public List<String> getRequestedColumns() {
		if(requested==null){
			requested = new LinkedList<String>();
			for(Expr e: xs){
				requested.addAll(e.getRequestedColumns());
			}
		}
		return requested;
	}

	@Override
	public void rename(String oldName, String newName) {
		for(Expr e: xs){
			e.rename(oldName, newName);
		}
	}

	@Override
	public boolean hasSubquery() {
		return false;
	}

	@Override
	public Expr clone() {
		return new LinearCombinationList(xs, cs);
	}
	@Override
	public int getType(Schema schema) {
		return java.sql.Types.INTEGER;
	}
	@Override
	public int getType() {
		return java.sql.Types.INTEGER;
	}
	@Override
	public String toString(){
		if(myName != null)
			return myName;
		myName = ""+Env.getNewTemp();
		return myName;
	}

	public void fillIndex(Schema schema) {
		for(Expr e:xs){
			((Id)e).fillIndex(schema);
		}
	}

	public Field evalByIndex(Record r) {
		if(isConst)return value;
		int ret = 0;
		for(int i=0;i<cs.size();i++){
			ret += evalHelper(cs.get(i), ((Id)xs.get(i)).evalByIndex(r));
		}
		return new INT(ret);
	}

}
