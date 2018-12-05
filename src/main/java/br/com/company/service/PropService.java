package br.com.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropService {

	private static final Logger LOG = LoggerFactory.getLogger(PropService.class);

	@SuppressWarnings("finally")
	public Properties get(String fileName) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
			if (inputStream != null) {
				properties.load(inputStream);
			}
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		} finally {
			return properties;
		}
	}

}
