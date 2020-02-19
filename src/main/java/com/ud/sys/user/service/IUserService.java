package com.ud.sys.user.service;

public interface IUserService {

	public String listAllUsers(String StoredProcedureInput) throws Exception;

	public String findUserByID(String StoredProcedureInput) throws Exception;
}
