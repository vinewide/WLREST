package com.ud.sys.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ud.sys.db.DBConnectionPooling;
import com.ud.sys.item.controller.ItemController;
import com.ud.sys.user.controller.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class AppConfig extends Application {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	public static final Properties properties = new Properties();
	private static final DBConnectionPooling connectionPool = new DBConnectionPooling();

	public static DBConnectionPooling getConnectionPool() {
		return connectionPool;
	}

	private Properties readProperties() {
		try {
			logger.info("DB_U_R >>> " + System.getProperty("DB_P").toString());
			logger.info("DB_P_R >>> " + System.getProperty("DB_P").toString());
			logger.info("DB_URL_R >>> " + System.getProperty("DB_URL").toString());
			properties.put("DB_U_R", System.getProperty("DB_U").toString());
			properties.put("DB_P_R", System.getProperty("DB_P").toString());
			properties.put("DB_URL_R", System.getProperty("DB_URL").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	@Override
	public Set<Class<?>> getClasses() {
		// Set up your Jersey resources
		Set<Class<?>> rootResources = new HashSet<Class<?>>();
		rootResources.add(ItemController.class);
		rootResources.add(UserController.class);
		// Read the properties file
		readProperties();
		return rootResources;
	}
}
