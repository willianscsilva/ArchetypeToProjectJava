package br.com.company.spark;

import br.com.company.application.Spark;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SaveMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;

public class SparkParquet extends Spark implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SparkParquet.class);

	private String path = "";

	private String filename = "";

	/**
	 * 
	 * @param path
	 * @return
	 */
	public SparkParquet setPath(String path) {
		this.path = path;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public String getPath() {
		String path = this.getStoragePath().concat("/").concat(this.path);
		if (!this.path.isEmpty()) {
			String[] pathArray = path.split("/");
			path = "/";
			for (int i = 0; i < pathArray.length; i++) {
				if (!pathArray[i].isEmpty()) {
					path = path.concat(pathArray[i].concat("/"));
				}
			}
		}

		return path;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public SparkParquet setFilename(String filename) {
		this.filename = filename;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * 
	 * @param directoryName
	 * @return
	 */
	public SparkParquet createDirectory() {
		try {
			if (!this.path.isEmpty()) {
				String path = this.getStoragePath();
				String[] directoryArray = this.path.split("/");
				for (int i = 0; i < directoryArray.length; i++) {
					this.fileService().createDirectory(path, directoryArray[i]);
					path = path.concat("/").concat(directoryArray[i]);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean fileExists() {
		try {
			File file = new File(String.format("%s%s", this.getPath(), this.getFilename()));
			boolean _SUCCESS = false;

			if (file.list().length <= 0) {
				return false;
			}

			File[] listOfFiles = file.listFiles();
			if (listOfFiles != null) {
				for (File files : listOfFiles) {
					if (files.isFile() && files.getName().trim().equals("_SUCCESS")) {
						_SUCCESS = true;
					}
				}
			}

			if (!_SUCCESS) {
				return false;
			}

			return file.exists();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
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
			if (this.hasStorage() && !this.filename.isEmpty()) {
				this.createDirectory();
				DataFrame dataFrame = this.getSqlContext().createDataFrame(dataRDD, beanClass);
				dataFrame.write().mode(mode).parquet(this.getPath().concat(this.filename));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 */
	public DataFrame read() {
		try {
			File file = new File(this.getPath().concat(this.filename));
			if (!file.isDirectory()) {
				System.out.println("WARNING: DIRECTORY -" + this.getPath().concat(this.filename) + " - NOT EXISISTS!");
				return null;
			}
			return this.getSqlContext().read().parquet(this.getPath().concat(this.filename));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

}
