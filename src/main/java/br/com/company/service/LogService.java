package br.com.company.service;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogService {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String path;

	private String filename;

	/**
	 * @param path
	 * @return
	 */
	public LogService setPath(String path) {
		this.path = path;
		return this;
	}

	/**
	 * @param filename
	 * @return
	 */
	public LogService setFilename(String filename) {
		this.filename = filename;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	private String generateFullPath() {
		String separator = "/";
		if (this.path.substring(this.path.length() - 1) == "/") {
			separator = "";
		}

		return String.format("%s%s%s", this.path, separator, this.filename);
	}

	/**
	 * 
	 * @return
	 */
	public Logger create() {
		return this.create(true);
	}

	/**
	 * 
	 * @param append
	 * @return
	 */
	public Logger create(boolean append) {
		LOGGER.setLevel(Level.ALL);
		try {
			new File(this.path).mkdirs();
			FileHandler file = new FileHandler(this.generateFullPath(), append);
			file.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(file);
			return LOGGER;
		} catch (Exception e) {
			LOGGER.severe(e.getLocalizedMessage() + " --> " + e.getMessage());
			return LOGGER;
		}
	}

}
