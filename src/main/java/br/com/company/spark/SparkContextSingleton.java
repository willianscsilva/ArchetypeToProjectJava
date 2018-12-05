package br.com.company.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkContextSingleton {

	private static final Logger LOG = LoggerFactory.getLogger(SparkContextSingleton.class);

	private static JavaSparkContext CONTEXT;

	/**
	 * 
	 * @return SparkConf
	 */
	protected static SparkConf getSparkConf() {
		SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("WorkerApp")
				.set("spark.driver.memory", "10g").set("spark.executor.memory", "10g")
				.set("spark.driver.allowMultipleContexts", "true")
				.set("spark.cassandra.connection.compression", "SNAPPY")
				.set("spark.ui.port", "4050")
				.set("spark.cassandra.connection.timeout_ms", "60000")
				.set("spark.network.timeout", "600s")
				.set("spark.cassandra.input.consistency.level", "TWO");

		return sparkConf;
	}

	/**
	 * @return void
	 */
	public static void open() {
		CONTEXT = new JavaSparkContext(SparkContextSingleton.getSparkConf());
	}

	/**
	 * 
	 * @return JavaSparkContext
	 */
	public static JavaSparkContext get() {
		if (CONTEXT == null) {
			SparkContextSingleton.open();
		}

		return CONTEXT;
	}

	/**
	 * @return void
	 */
	public static void close() {
		if (CONTEXT != null) {
			CONTEXT.close();
			CONTEXT = null;
		}
	}
}
