package br.com.company.application;

import br.com.company.jdbc.JDBCConnection;
import br.com.company.multichannel.Context;
import br.com.company.spark.SparkContextSingleton;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

public abstract class Model extends Application implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(Model.class);

	protected Context context;

}
