package fatworm.driver;

import java.util.ArrayList;
import java.util.List;

public class Schema {

	public String tableName;
	public String primaryKey;
	
	public List<String> columnName = new ArrayList<String> ();
	
	public int findIndex(String x) {
		for(int i=0;i<columnName.size();i++){
			String y = columnName.get(i);
			if(x.equalsIgnoreCase(y) || x.equalsIgnoreCase(this.tableName + "." + y))
				return i;
			if(!y.contains("."))continue;
			// FIXME hack for table name mismatch
			if(x.equalsIgnoreCase(y.substring(y.indexOf('.')+1)))
				return i;
		}
		return -1;
	}

}
