package fatworm.driver;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public class SelectResult extends ResultSet {

	public SelectResult() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean next() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
