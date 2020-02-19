package com.ud.sys.item.service;

import com.ud.sys.config.AppConfig;
import com.ud.sys.db.DBConnectionPooling;
import com.ud.sys.db.DatabaseAccess;

public class ItemServiceImpl implements IItemService {

	private final DBConnectionPooling dbConnectionPool = AppConfig.getConnectionPool();

	@Override
	public String verifyDBStatus(String StoredProcedureInput) throws Exception {
		String response = null;
		DatabaseAccess databaseAccess = null;
		String storedProcedureName = "dbStatusSP";
		databaseAccess = new DatabaseAccess(storedProcedureName, StoredProcedureInput, dbConnectionPool);
		response = databaseAccess.executeStoredProcedure();
		return response;
	}

	@Override
	public String listAllItems(String StoredProcedureInput) throws Exception {
		String response = null;
		DatabaseAccess databaseAccess = null;
		String storedProcedureName = "listAllItemsSP";
		databaseAccess = new DatabaseAccess(storedProcedureName, StoredProcedureInput, dbConnectionPool);
		response = databaseAccess.executeStoredProcedure();
		return response;
	}

	@Override
	public String findItemByID(String StoredProcedureInput) throws Exception {
		String response = null;
		DatabaseAccess databaseAccess = null;
		String storedProcedureName = "findItemByIDSP";
		databaseAccess = new DatabaseAccess(storedProcedureName, StoredProcedureInput, dbConnectionPool);
		response = databaseAccess.executeStoredProcedure();
		return response;
	}
}
