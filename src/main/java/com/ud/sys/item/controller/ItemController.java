package com.ud.sys.item.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.ud.sys.config.AppConfig;
import com.ud.sys.db.DBConnectionPooling;
import com.ud.sys.db.DatabaseAccess;
import com.ud.sys.item.service.IItemService;
import com.ud.sys.item.service.ItemServiceImpl;
import com.ud.sys.utils.AppUtils;

@Path("/")
public class ItemController {

	private static final Logger logger = Logger.getLogger(ItemController.class);

	AppUtils appUtils = new AppUtils();
	IItemService itemservice = new ItemServiceImpl();

	@GET
	@Path("/status")
	public Response verifyAppStatus2() {
		String response = null;
		try {
			response = appUtils.getSuccessJSON("App is Up and Running", "verifyAppStatus");
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(),"verifyAppStatus2");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@GET
	@Path("/db/status")
	public Response verifyDBStatus() {
		String response = null;
		String StoredProcedureInput = null;
		try {
			response = itemservice.verifyDBStatus(StoredProcedureInput);
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(), "verifyDBStatus");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@GET
	@Path("/items")
	public Response listAllItems() {
		String response = null;
		String StoredProcedureInput = null;
		try {
			response = itemservice.listAllItems(StoredProcedureInput);
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(), "listAllItems");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	@GET
	@Path("/item/{id}")
	public Response findItemByID(@PathParam("id") String itemID) {
		String response = null;
		String StoredProcedureInput = itemID;
		try {
			response = itemservice.findItemByID(StoredProcedureInput);
		} catch (Exception e) {
			e.printStackTrace();
			response = appUtils.getErrorJSON(e.getClass().getCanonicalName() + ": " + e.getMessage(), "findItemByID");
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}
}