package resources;

import exceptions.SessionException;
import dao.DatabaseAccess;
import jakarta.servlet.annotation.HttpMethodConstraint;
import models.*;
import models.Session;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static dao.SessionHolder.INSTANCE;

@Path("/alerts")
public class AlertsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAlerts(@HeaderParam("sessionId") String sessionId) {
//		try {
//			Session session = INSTANCE.getSession(sessionId);
//		} catch (SessionException e) {
//			// return error 401, unauthorized with message to please try log in again
//			return null;
//		}
		return DatabaseAccess.getAlerts();
	}
}
