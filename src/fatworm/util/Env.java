package fatworm.util;

import java.util.HashMap;
import java.util.Map;

import fatworm.field.*;

public class Env {

	public Map<String, Field> res;
	public Env() {
		res = new HashMap<String, Field> ();
	}
	
	public void put(String a, Field b){
		res.put(a, b);
	}

	public Field get(String a){
		return res.get(a);
	}
}
