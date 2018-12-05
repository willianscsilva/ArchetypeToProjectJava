package br.com.company.application;

import br.com.company.entity.AccountEntity;
import br.com.company.jdbc.JDBCConnection;
import br.com.company.jdbc.JDBCSchema;
import br.com.company.service.*;
import br.com.company.spark.SparkJDBC;
import br.com.company.spark.SparkParquet;

import java.io.File;
import java.util.Properties;

public abstract class Application {

	/**
	 * 
	 * @return
	 */
	public FileService fileService() {
		return new FileService();
	}

	/**
	 * 
	 * @return
	 */
	public PeriodService periodService() {
		return new PeriodService();
	}

	/**
	 * 
	 * @param format
	 * @return
	 */
	public PeriodService periodService(String format) {
		return new PeriodService(format);
	}

	/**
	 * 
	 * @return
	 */
	public PropService propService() {
		return new PropService();
	}

	/**
	 * 
	 * @return
	 */
	public String getProjectPath() {
		return new File("").getAbsoluteFile().getAbsolutePath();
	}

	/**
	 * 
	 * @return
	 */
	public String getStoragePath() {
		return String.format("%s/storage", this.getProjectPath());
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasStorage() {
		this.fileService().createDirectory(this.getProjectPath(), "storage");
		return true;
	}

	/**
	 * return
	 * 
	 * @return
	 */
	public JDBCConnection jdbcConnection() {
		return new JDBCConnection();
	}

	/**
	 * 
	 * @return
	 */
	public Properties getJdbcProperties() {
		return this.jdbcConnection().getProperties();
	}

	/**
	 * 
	 * @return
	 */
	public JDBCSchema jdbcSchema() {
		return new JDBCSchema();
	}

	/**
	 * 
	 * @return
	 */
	public SparkJDBC sparkJDBC() {
		return new SparkJDBC();
	}

	/**
	 * 
	 * @return
	 */
	public SparkParquet sparkParquet() {
		return new SparkParquet();
	}

}
