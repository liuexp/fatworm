package fatworm.driver;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119817433846198928L;
	public String name;
	public Map<String, Table> tableList = new HashMap<String, Table>();
	public Database(String name){
		this.name = name;
	}
	public void addTable(String tbl, MemTable table) {
		tableList.put(tbl, table);
	}
	public void delTable(String tbl) {
		tableList.remove(tbl);
	}
	public Table getTable(String tbl) {
		return tableList.get(tbl);
	}
}
