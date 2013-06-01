package fatworm.driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fatworm.io.BKey;
import fatworm.io.BTree;
import fatworm.io.Cursor;
import fatworm.page.BTreePage.BCursor;
import fatworm.util.Util;

public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119817433846198928L;
	public String name;
	public Map<String, Table> tableList = new HashMap<String, Table>();
	public Map<String, Index> indexList = new HashMap<String, Index>();
	public Database(String name){
		this.name = name;
	}
	public void addTable(String tbl, Table table) {
		tableList.put(tbl, table);
	}
	public void delTable(String tbl) {
		tableList.remove(tbl);
	}
	public Table getTable(String tbl) {
		return tableList.get(tbl);
	}
	public void createIndex(String idx, String tbl, String col, boolean unique) {
		Table table = tableList.get(tbl);
		createIndexWithTable(idx, col, unique, table);
	}
	public void createIndexWithTable(String idx, String col, boolean unique,
			Table table) {
		Index index = new Index(idx, table, table.schema.getColumn(col), unique);
		try {
			BTree b = new BTree(DBEngine.getInstance().btreeManager, index.column.type, index.table);
			for(Cursor c = table.open();c.hasThis();c.next()){
				Record r = c.fetchRecord();
				createIndexForRecord(index, b, c, r);
			}
			index.pageID = b.root.getID();
		} catch (Throwable e) {
//			Util.error(e.getMessage());
			e.printStackTrace();
		}
		indexList.put(idx, index);
		table.tableIndex.add(index);
//		DBEngine.getInstance().addIndex(index);
	}
	public static void createIndexForRecord(Index index, BTree b, Cursor c, Record r)
			throws Throwable {
		BKey key = b.newBKey(r.getCol(index.columnIdx));
		BCursor bc = b.root.lookup(key);
		if(index.unique){
			if(bc.valid() && bc.getKey().equals(key))
				Util.warn("duplicated keys in unique index!!!");
			bc.insert(key, c.getIdx());
		}else{
			if(bc.valid() && bc.getKey().equals(key)){
				index.buckets.get(bc.getValue()).add(c.getIdx());
			}else{
				List<Integer> tmp = new ArrayList<Integer>();
				tmp.add(c.getIdx());
				bc.insert(key, index.buckets.size());
				index.buckets.add(tmp);
			}
		}
	}
	public void dropIndex(String idx) {
		Index index = indexList.remove(idx);
		try {
			new BTree(DBEngine.getInstance().btreeManager, index.pageID, index.column.type, index.table).root.delete();
		} catch (Throwable e) {
			Util.error(e.getMessage());
		}
		index.table.tableIndex.remove(index);

//		DBEngine.getInstance().removeIndex(index);
	}
	
	public static class Index implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3715310062485085860L;
		public String indexName;
		public Table table;
		public Column column;
		public int columnIdx;
		public boolean unique;
		public Integer pageID;
		public List<List<Integer>> buckets = new ArrayList<List<Integer>>();
		public Index(String a, Table b, Column c, boolean u){
			this(a,b,c);
			unique = u;
		}
		public Index(String a, Table b, Column c){
			indexName = a;
			table = b;
			column = c;
			unique = false;
			columnIdx = b.schema.findIndex(c.name);
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof Index){
				Index z = (Index)o;
				return z.indexName.equals(indexName) && z.table.equals(table);
			}
			return false;
		}
		
		@Override
		public int hashCode(){
			return pageID ^ indexName.hashCode();
		}
		
		@Override
		public String toString(){
			return indexName + " on " +table.toString()+" on " + column.toString() +" with pageID="+pageID;
		}
	}
}
