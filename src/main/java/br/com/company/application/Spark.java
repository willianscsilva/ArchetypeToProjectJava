package br.com.company.application;

import br.com.company.spark.SparkContextSingleton;
import org.apache.spark.sql.SQLContext;

public abstract class Spark extends Application {

	private static SQLContext sqlContext;

	public SQLContext getSqlContext() {
		if (sqlContext == null) {
			sqlContext = new SQLContext(SparkContextSingleton.get());
		}
		return sqlContext;
	}

}
