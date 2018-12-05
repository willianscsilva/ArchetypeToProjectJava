package br.com.company.spark;

import br.com.company.application.Spark;
import br.com.company.jdbc.JDBCConnection;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SaveMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Properties;

public class SparkJDBC extends Spark implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SparkJDBC.class);

	private String table;

	/**
	 * 
	 * @return
	 */
	public Properties getProperties() {
		return new JDBCConnection().getProperties();
	}

	/**
	 * 
	 * @param table
	 * @return
	 */
	public SparkJDBC setTable(String table) {
		this.table = table;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public DataFrame read() {
		try {
			return this.getSqlContext().read().jdbc(this.getJdbcProperties().getProperty("host"), this.table,
					this.getJdbcProperties());
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public DataFrame readWhere(String condition) {
		try {
			return this.getSqlContext().read()
					.jdbc(this.getJdbcProperties().getProperty("host"), this.table, this.getJdbcProperties())
					.where(condition);
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	/**
	 * 
	 * @param dataRDD
	 * @param beanClass
	 */
	public void writeOverwrite(JavaRDD<?> dataRDD, Class<?> beanClass) {
		this.write(dataRDD, beanClass, SaveMode.Overwrite);
	}

	/**
	 * 
	 * @param dataRDD
	 * @param beanClass
	 */
	public void writeAppend(JavaRDD<?> dataRDD, Class<?> beanClass) {
		this.write(dataRDD, beanClass, SaveMode.Append);
	}

	/**
	 * 
	 * @param dataRDD
	 * @param beanClass
	 */
	public void writeIgnore(JavaRDD<?> dataRDD, Class<?> beanClass) {
		this.write(dataRDD, beanClass, SaveMode.Ignore);
	}

	/**
	 * 
	 * @param dataRDD
	 * @param beanClass
	 */
	public void writeErrorIfExists(JavaRDD<?> dataRDD, Class<?> beanClass) {
		this.write(dataRDD, beanClass, SaveMode.ErrorIfExists);
	}

	/**
	 * 
	 * @param dataRDD
	 * @param beanClass
	 * @param mode
	 */
	private void write(JavaRDD<?> dataRDD, Class<?> beanClass, SaveMode mode) {
		try {
			DataFrame dataFrame = this.getSqlContext().createDataFrame(dataRDD, beanClass);
			dataFrame.write().mode(mode).jdbc(this.getJdbcProperties().getProperty("host"), this.table,
					this.getJdbcProperties());
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
	}

}
