package com.ud.sys.db;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBConnectionPooling {

	private static final Logger logger = Logger.getLogger(DBConnectionPooling.class);

	private Connection dbConnection = null;

	public DBConnectionPooling() {
		this.createDBConnection();
	}

	public void createDBConnection() {
		try {
			String lookup_live = "java:comp/env/jdbc/ds_live";
			final Context ctx_live = new InitialContext();
			final DataSource ds_live = (DataSource) ctx_live.lookup(lookup_live);
			this.dbConnection = ds_live.getConnection();
			logger.info(ds_live.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getDBConnection() {
		this.createDBConnection();
		return dbConnection;
	}
}
