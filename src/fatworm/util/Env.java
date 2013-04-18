package fatworm.util;

import java.util.HashMap;
import java.util.Map;

import fatworm.driver.Record;
import fatworm.field.*;

public class Env {

	public Map<String, Field> res;
	public Env() {
		res = new HashMap<String, Field> ();
	}
	
	public Env(Map<String, Field> res2) {
		res = res2;
	}

	public void put(String a, Field b){
		res.put(a, b);
	}

	public Field get(String a){
		return res.get(a);
	}
	
	public Field get(String tbl, String col){
		Field ret = get(col);
		if(ret == null)ret = get(tbl + "." + Util.getAttr(col));
		if(ret == null)ret = NULL.getInstance();
		return ret;
	}
	
	public Field remove(String a){
		return res.remove(a);
	}
	
	public Field remove(String tbl, String col){
		Field ret = null;
		String key = tbl + "." + Util.getAttr(col);
		if(res.containsKey(col))ret = res.remove(col);
		if(res.containsKey(key))ret = res.remove(key);
		return ret;
	}
	
	public Env clone(){
		return new Env(new HashMap<String, Field>(res));
	}
	
	public void appendFromRecord(Record x){
		if(x == null)return;
		for(int i=0;i<x.cols.size();i++){
			res.put(x.schema.columnName.get(i), x.cols.get(i));
			//FIXME how to resolve the name solely on canonical names?
			res.put(Util.getAttr(x.schema.columnName.get(i)), x.cols.get(i));
		}
	}
	public void appendFrom(Env x){
		if(x == null)return;
		res.putAll(x.res);
	}
}
