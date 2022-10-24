package resources;

import dao.DatabaseAccess;
import models.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.ArrayList;
import java.util.List;

import static dao.SessionHolder.INSTANCE;

@Path("alerts")
public class AlertsResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alert> getAlerts(@HeaderParam("sessionId") String sessionId) {
		if (INSTANCE.sessionExists(sessionId)) return DatabaseAccess.getAlerts();
		else return new ArrayList<>();
	}

	@Path("{id}")
	public AlertResource getAlert(@HeaderParam("sessionId") String sessionId, @PathParam("id") String alertId) {
		return new AlertResource(sessionId, alertId);
	}
}
