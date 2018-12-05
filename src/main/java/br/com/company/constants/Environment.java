package br.com.company.constants;

import br.com.company.service.PropService;

public class Environment {

	private static final String FILE_ENV = ".environment.properties";

	private static final String APP_ENV = "APP_ENV";

	public static final String DEVELOPMENT = "development";

	public static final String HOMOLOGATION = "homologation";

	public static final String PRODUCTION = "production";

	public static boolean isDevelopment() {
		return Environment.getAppEnv().equals(Environment.DEVELOPMENT);
	}

	public static boolean isHomologation() {
		return Environment.getAppEnv().equals(Environment.HOMOLOGATION);
	}

	public static boolean isProduction() {
		return Environment.getAppEnv().equals(Environment.PRODUCTION);
	}

	public static String getAppEnv() {
		String appEnv = new PropService().get(Environment.FILE_ENV).getProperty(Environment.APP_ENV);
		if (appEnv == null || appEnv.isEmpty() || appEnv.trim().equals(Environment.PRODUCTION)) {
			return Environment.PRODUCTION;
		}

		return appEnv.trim();
	}

}
