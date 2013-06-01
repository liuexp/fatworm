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
		Index index = new Index(idx, table, table.schema.getColumn(col), unique);
		try {
			BTree b = new BTree(DBEngine.getInstance().btreeManager, index.column.type);
			index.pageID = b.root.getID();
			for(Cursor c = table.open();c.hasThis();c.next()){
				Record r = c.fetchRecord();
				BKey key = b.newBKey(r.getCol(index.column.name));
				BCursor bc = b.root.lookup(key);
				if(index.unique){
					bc.insert(key, c.getIdx());
				}else{
					if(bc.getKey().equals(key)){
						index.buckets.get(bc.getValue()).add(c.getIdx());
					}else{
						List<Integer> tmp = new ArrayList<Integer>();
						tmp.add(c.getIdx());
						bc.insert(key, index.buckets.size());
						index.buckets.add(tmp);
					}
				}
			}
		} catch (Throwable e) {
			Util.error(e.getMessage());
		}
		indexList.put(idx, index);
		table.tableIndex.add(index);
	}
	public void dropIndex(String idx) {
		Index index = indexList.get(idx);
		try {
			new BTree(DBEngine.getInstance().btreeManager, index.pageID, index.column.type).root.delete();
		} catch (Throwable e) {
			Util.error(e.getMessage());
		}
		index.table.tableIndex.remove(index);
	}
	
	public static class Index implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3715310062485085860L;
		public String indexName;
		public Table table;
		public Column column;
		public boolean unique;
		public Integer pageID;
		public List<List<Integer>> buckets = new ArrayList<List<Integer>>();
		public Index(String a, Table b, Column c, boolean u){
			this(a,b,c);
			unique = true;
		}
		public Index(String a, Table b, Column c){
			indexName = a;
			table = b;
			column = c;
			unique = false;
		}
		
		@Override
		public boolean equals(Object o){
			if(o instanceof Index){
				Index z = (Index)o;
				return z.indexName.equals(indexName) && z.table.equals(table);
			}
			return false;
		}
	}
}
