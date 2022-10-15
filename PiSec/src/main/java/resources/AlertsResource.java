package resources;

import exceptions.SessionException;
import dao.Database;
import models.*;
import models.Session;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
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
			return null;
		}

		return Database.getAlerts();
	}
}
