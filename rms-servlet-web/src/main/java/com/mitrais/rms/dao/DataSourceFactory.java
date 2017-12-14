package com.mitrais.rms.dao;

import com.mitrais.rms.helper.PropertiesFileHelper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database. It
 * implements singleton pattern See
 * <a href="http://www.oodesign.com/singleton-pattern.html">Singleton
 * Pattern</a>
 */
public class DataSourceFactory {
	private final DataSource dataSource;

	DataSourceFactory() {
		MysqlDataSource dataSource = new MysqlDataSource();
		Properties prop = PropertiesFileHelper.Read("database.properties");

		dataSource.setDatabaseName(prop.getProperty("DatabaseName"));
		dataSource.setServerName(prop.getProperty("ServerName"));
		dataSource.setPort(Integer.parseInt(prop.getProperty("Port")));
		dataSource.setUser(prop.getProperty("User"));
		dataSource.setPassword(prop.getProperty("Password"));
		this.dataSource = dataSource;
	}

	/**
	 * Get a data source to database
	 *
	 * @return DataSource object
	 */
	public static Connection getConnection() throws SQLException {
		return SingletonHelper.INSTANCE.dataSource.getConnection();
	}

	private static class SingletonHelper {
		private static final DataSourceFactory INSTANCE = new DataSourceFactory();

	}
}
