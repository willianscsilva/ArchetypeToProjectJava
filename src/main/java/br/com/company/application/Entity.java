package br.com.company.application;

public abstract class Entity extends Application {

	/**
	 * @param string
	 * @return
	 */
	public String trim(String string) {
		if (string != null) {
			return string.trim();
		}

		return string;
	}

}
