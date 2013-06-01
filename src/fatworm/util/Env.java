package fatworm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatworm.absyn.Expr;
import fatworm.driver.Record;
import fatworm.field.*;

public class Env {

	public Map<String, Field> res;
	private static int tempCnt = 0;
	public synchronized static int getNewTemp(){
		return tempCnt++;
	}
	public Env() {
		res = new HashMap<String, Field> ();
	}
	
	public Env(Map<String, Field> res2) {
		res = res2;
	}

	public void put(String a, Field b){
		res.put(a.toLowerCase(), b);
	}

	public Field get(String a){
		return res.get(a.toLowerCase());
	}
	
	public Field get(String tbl, String col){
		Field ret = get(col);
		if(ret == null)ret = get(tbl + "." + Util.getAttr(col));
		if(ret == null)ret = NULL.getInstance();
		return ret;
	}
	
	public Field remove(String a){
		return res.remove(a.toLowerCase());
	}
	
	public Field remove(String tbl, String col){
		Field ret = null;
		String key = tbl + "." + Util.getAttr(col);
		if(res.containsKey(col.toLowerCase()))ret = res.remove(col.toLowerCase());
		if(res.containsKey(key.toLowerCase()))ret = res.remove(key.toLowerCase());
		return ret;
	}
	
	public Env clone(){
		return new Env(new HashMap<String, Field>(res));
	}
	
	public void appendFromRecord(Record x){
		if(x == null)return;
//		for(int i=0;i<x.cols.size();i++){
//			String ocol = x.schema.columnName.get(i);
//			String colName = Util.getAttr(ocol);
//			String tblName = x.schema.tableName;
//			put(ocol, x.cols.get(i));
//			if(!ocol.contains("."))
//				put(tblName+"."+colName, x.cols.get(i));
//			else
//				put(colName, x.cols.get(i));
//		}
		List<String> name1 = x.schema.getCanonicalName1();
		List<String> name2 = x.schema.getCanonicalName2();
		for(int i=0;i<x.cols.size();i++){
			put(name1.get(i), x.cols.get(i));
			put(name2.get(i), x.cols.get(i));
		}
	}
	public void appendFromRecord(List<String> name, List<Integer> offset, List<Field> cols){
		for(int i=0;i<name.size();i++){
			int j = offset.get(i);
			if(j<0)
				continue;
			put(name.get(i), cols.get(j));
		}
	}
	
	public void appendFromRecord(List<String> name1, List<String> name2, Record x){
		if(x == null)return;
		for(int i=0;i<x.cols.size();i++){
			put(name1.get(i), x.cols.get(i));
			put(name2.get(i), x.cols.get(i));
		}
	}
	
	public void appendAlias(String tbl, List<Expr> expr, List<String> alias){
		for(int i=0;i<alias.size();i++){
			String colName = expr.get(i).toString();
			put(alias.get(i), get(tbl, colName));
			put(tbl + "." + alias.get(i), get(colName));
		}
	}
	public void appendFrom(Env x){
		if(x == null)return;
		res.putAll(x.res);
	}
	
	@Override
	public String toString(){
		assert res!=null;
		return Util.deepToString(res);
	}
}
