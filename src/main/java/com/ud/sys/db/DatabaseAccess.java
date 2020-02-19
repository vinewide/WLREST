package com.ud.sys.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ud.sys.literals.AppLiterals;
import com.ud.sys.utils.DefaultErrorMessageEnum;

public class DatabaseAccess {

	private static final Logger logger = Logger.getLogger(DatabaseAccess.class);

	private String storedProcedureName;
	private String storedProcedureInput;
	private DBConnectionPooling databaseConnectionPool;

	public DatabaseAccess(String storedProcedureName, String storedProcedureInput,DBConnectionPooling databaseConnectionPool) {
		this.storedProcedureName = storedProcedureName;
		this.storedProcedureInput = storedProcedureInput;
		this.databaseConnectionPool = databaseConnectionPool;
	}

	public String executeStoredProcedure() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		JSONObject mainJSONObject = null;
		JSONArray rsJSONArray = null;
		try {
			mainJSONObject = new JSONObject();
			String auth_DBUSER_access_SQL = null;
			if (storedProcedureInput != null) {
				auth_DBUSER_access_SQL = "{call " + storedProcedureName + "(" + storedProcedureInput + ")}";
			} else {
				auth_DBUSER_access_SQL = "{call " + storedProcedureName + "}";
			}
			logger.info(auth_DBUSER_access_SQL);

			connection = databaseConnectionPool.getDBConnection();
			preparedStatement = connection.prepareStatement(auth_DBUSER_access_SQL);
			boolean isResultSet = preparedStatement.execute();
			while (true) {
				if (isResultSet) {
					ResultSet resultSet = preparedStatement.getResultSet();
					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					rsJSONArray = new JSONArray();
					while (resultSet.next()) {
						JSONObject tempJSONObject = new JSONObject();
						for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
							if (resultSetMetaData.getColumnName(i).equals(AppLiterals.STATUS)) {
								if (resultSet.getString(i).equals(AppLiterals.TRUE)) {
									mainJSONObject.put(AppLiterals.STATUS, true);
								} else {
									mainJSONObject.put(AppLiterals.STATUS, false);
								}
							} else {
								String tableColumnName = resultSetMetaData.getColumnLabel(i);
								if (resultSet.getObject(tableColumnName) == null) {
									tempJSONObject.put(tableColumnName, "");
								} else {
									tempJSONObject.put(tableColumnName, resultSet.getObject(tableColumnName));
								}
							}
						}
						rsJSONArray.put(tempJSONObject);
					}
					resultSet.close();
				} else {
					if (preparedStatement.getUpdateCount() == -1)
						break;
				}
				isResultSet = preparedStatement.getMoreResults();
			}
			mainJSONObject.put("rs", rsJSONArray);
			if (rsJSONArray.length() == 0) {
				mainJSONObject.put(AppLiterals.STATUS, false);
				JSONObject exceptionJSON = new JSONObject();
				exceptionJSON.put(AppLiterals.USER_MESSAGE, DefaultErrorMessageEnum.NO_RECORDS_FOUND_MESSAGE.getText());
				exceptionJSON.put(AppLiterals.ERROR_MESSAGE,DefaultErrorMessageEnum.NO_RECORDS_FOUND_MESSAGE.getText());
				rsJSONArray.put(exceptionJSON);
			}
			connection.close();
			connection = null;
			preparedStatement.close();
		} catch (Exception e) {
			rsJSONArray = new JSONArray();
			JSONObject tempJSONObject = new JSONObject();
			tempJSONObject.put(AppLiterals.USER_MESSAGE, DefaultErrorMessageEnum.STANDARD_ERROR_MESSAGE.getText());
			tempJSONObject.put(AppLiterals.ERROR_MESSAGE, e.getClass().getCanonicalName() + ": " + e.getMessage());
			rsJSONArray.put(tempJSONObject);
			mainJSONObject.put(AppLiterals.STATUS, false);
			mainJSONObject.put(AppLiterals.RS, rsJSONArray);
			logger.error(e);
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return mainJSONObject.toString();
	}
}