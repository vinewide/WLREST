package com.ud.sys.item.service;

public interface IItemService {

	public String verifyDBStatus(String StoredProcedureInput) throws Exception;

	public String listAllItems(String StoredProcedureInput) throws Exception;

	public String findItemByID(String StoredProcedureInput) throws Exception;
}
