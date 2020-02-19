package com.ud.sys.user.service;

import com.ud.sys.config.AppConfig;
import com.ud.sys.db.DBConnectionPooling;
import com.ud.sys.db.DatabaseAccess;

public class UserServiceImpl implements IUserService {
	private final DBConnectionPooling dbConnectionPool = AppConfig.getConnectionPool();

	@Override
	public String listAllUsers(String StoredProcedureInput) throws Exception {
		String response = null;
		DatabaseAccess databaseAccess = null;
		String storedProcedureName = "listAllUsersSP";
		databaseAccess = new DatabaseAccess(storedProcedureName, StoredProcedureInput, dbConnectionPool);
		response = databaseAccess.executeStoredProcedure();
		return response;
	}

	@Override
	public String findUserByID(String StoredProcedureInput) throws Exception {
		String response = null;
		DatabaseAccess databaseAccess = null;
		String storedProcedureName = "findUserByIDSP";
		databaseAccess = new DatabaseAccess(storedProcedureName, StoredProcedureInput, dbConnectionPool);
		response = databaseAccess.executeStoredProcedure();
		return response;
	}

}
