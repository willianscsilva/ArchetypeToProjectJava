package br.com.company.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;

public class FileService {

	private static final Logger LOG = LoggerFactory.getLogger(FileService.class);

	private String charset = "UTF-8";

	/**
	 * @param charset
	 * @return
	 */
	public FileService setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	/**
	 * @param filename
	 * @return
	 */
	public String get(String filename) {
		InputStream inputStream = null;
		String dataFile = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(filename);

			if (inputStream != null) {
				StringWriter writer = new StringWriter();
				IOUtils.copy(inputStream, writer, this.charset);
				dataFile = writer.toString();
			}

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		return dataFile;
	}

	public boolean exists(String name) {
		return getClass().getClassLoader().getResource(name) != null;
	}

	/**
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public boolean createDirectory(String path, String name) {
		try {
			if (new File(path).exists() && !name.isEmpty()) {
				String separator = path.substring(path.length() - 1);
				String directoryName = (separator == "/") ? path.concat(name) : path.concat("/").concat(name);
				new File(directoryName).mkdir();
				return true;
			}
			return false;
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			return false;
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public boolean delete(String file) {
		File directories = new File(file);
		return this.delete(directories);
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private boolean delete(File file) {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
				return true;
			} else {

				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}

				if (file.list().length == 0) {
					file.delete();
					return true;
				}
			}
		} else {
			file.delete();
			return true;
		}
		return false;
	}
}
