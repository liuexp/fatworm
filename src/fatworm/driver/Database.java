package fatworm.driver;

import java.util.Map;

public class Database {
	public String name;
	public Map<String, Table> tableList;
	public Database(String name){
		this.name = name;
	}
}
