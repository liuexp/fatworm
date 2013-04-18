package fatworm.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;


public class Driver implements java.sql.Driver {
	static {
			try {
				DriverManager.registerDriver(new Driver());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		//TODO translate connection url
		return new fatworm.driver.Connection();
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith("jdbc:fatworm:");
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		return new DriverPropertyInfo[0];
	}

	@Override
	public int getMajorVersion() {

		return 0;
	}

	@Override
	public int getMinorVersion() {

		return 0;
	}

	@Override
	public boolean jdbcCompliant() {

		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {

		return null;
	}

}
