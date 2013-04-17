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
	
	public Field remove(String a){
		return res.remove(a);
	}
	
	public Env clone(){
		return new Env(new HashMap<String, Field>(res));
	}
	
	public void appendFromRecord(Record x){
		for(int i=0;i<x.cols.size();i++){
			res.put(x.meta.columnName.get(i), x.cols.get(i));
		}
	}
	
	public static Env appendFromRecord(Record x, Env y){
		Env ret = y.clone();
		for(int i=0;i<x.cols.size();i++){
			ret.res.put(x.meta.columnName.get(i), x.cols.get(i));
		}
		return ret;
	}
}
