package com.ud.sys.user.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ud.sys.user.service.IUserService;
import com.ud.sys.user.service.UserServiceImpl;
import com.ud.sys.utils.AppUtils;

@Path("/")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	AppUtils appUtils = new AppUtils();
	IUserService userService = new UserServiceImpl();

	@GET
	@Path("/users")
	public Response listAllUsers() {
		String response = null;
		String StoredProcedureInput = null;
		try {
			response = userService.listAllUsers(StoredProcedureInput);
			logger.info(response);
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(), "listAllItems");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@GET
	@Path("/user/{id}")
	public Response findUserByID(@PathParam("id") String userID) {
		String response = null;
		String StoredProcedureInput = userID;
		try {
			response = userService.findUserByID(StoredProcedureInput);
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(), "findItemByID");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}
}