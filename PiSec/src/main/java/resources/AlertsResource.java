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
import java.util.ArrayList;
import java.util.List;

import static dao.SessionHolder.INSTANCE;

@Path("/alerts")
public class AlertsResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alert> getAlerts(@HeaderParam("sessionId") String sessionId) {
		if (INSTANCE.sessionIsValid(sessionId)) return DatabaseAccess.getAlerts();
		else return new ArrayList<>();
	}

	@Path("{id}")
	public AlertResource getAlert(@HeaderParam("sessionId") String sessionId, @PathParam("id") String alertId) {
		return new AlertResource(sessionId, alertId);
	}
}
