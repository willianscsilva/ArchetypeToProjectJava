package br.com.company.jdbc;

import br.com.company.constants.Environment;
import br.com.company.service.PropService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {

	private String dbDriver = "mysql";

	private Connection connection;

	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return this.dbDriver;
	}

	/**
	 * @param dbDriver
	 */
	public JDBCConnection setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
		return this;
	}

	/**
	 * 
	 * @return the
	 */
	public Properties getProperties() {
		String filename = String.format("%s-%s.properties", this.dbDriver, Environment.getAppEnv());
		return new PropService().get(filename);
	}

	/**
	 * 
	 * @return
	 */
	public Connection open() {
		try {
			this.connection = DriverManager.getConnection(this.getProperties().getProperty("host"),
					this.getProperties().getProperty("user"), this.getProperties().getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return this.connection;
	}

}
