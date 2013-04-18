package fatworm.driver;

import java.util.Map;

public class Database {
	public String name;
	public Map<String, Table> tableList;
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
