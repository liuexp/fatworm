package fatworm.driver;

import java.util.HashMap;
import java.util.Map;

public class Database {
	public String name;
	public Map<String, Table> tableList = new HashMap<String, Table>();
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
}
