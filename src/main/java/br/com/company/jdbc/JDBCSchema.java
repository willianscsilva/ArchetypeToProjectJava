package br.com.company.jdbc;

import com.mysql.jdbc.DatabaseMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;

public class JDBCSchema {

	private static final Logger LOG = LoggerFactory.getLogger(JDBCSchema.class);

	private String dbDriver = "mysql";

	/**
	 * 
	 * @param table
	 * @return
	 */
	public boolean tableExists(String table) {
		try {
			Connection connection = new JDBCConnection().setDbDriver(this.dbDriver).open();
			DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
			ResultSet tables = databaseMetaData.getTables(null, null, table, null);
			connection.close();
			return tables.next();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	}
	
	/**
	 * 
	 * @param table
	 * @param dbDriver
	 * @return
	 */
	public boolean tableExists(String table, String dbDriver) {
		this.dbDriver = dbDriver;
		return this.tableExists(table);
	}
	
	/**
	 * 
	 * @param table
	 * @param column
	 * @return
	 */
	public boolean columnExists(String table, String column) {
		try {
			Connection connection = new JDBCConnection().setDbDriver(this.dbDriver).open();
			DatabaseMetaData databaseMetaData = (DatabaseMetaData) connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, null, table, column);
			connection.close();
			return resultSet.next();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	}
	
	/**
	 * 
	 * @param table
	 * @param column
	 * @param dbDriver
	 * @return
	 */
	public boolean columnExists(String table, String column, String dbDriver) {
		this.dbDriver = dbDriver;
		return this.columnExists(table, column);
	}

}
