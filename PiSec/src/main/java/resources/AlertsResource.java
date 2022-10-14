package resources;

import exceptions.SessionException;
import dao.Database;
import models.*;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

import static dao.SessionHolder.*;

@Path("/alerts")
public class AlertsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alert> getAlerts(@HeaderParam("sessionId") String sessionId) {
		try {
			Session session = getSession(sessionId);
		} catch (SessionException e) {
			// return error 401, unauthorized with message to please try log in again
			return new ArrayList<>();
		}

		return Database.getAlerts();
	}
}
