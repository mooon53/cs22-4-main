package resources;

import dao.DatabaseAccess;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import models.Alert;

import static dao.SessionHolder.INSTANCE;

public class AlertResource {
	String sessionId;
	Long alertId;

	public AlertResource(String sessionId, String alertId) {
		this.sessionId = sessionId;
		this.alertId = Long.parseLong(alertId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Alert getAlert() {
		if (INSTANCE.sessionExists(sessionId)) {
			return DatabaseAccess.getAlert(alertId);
		} else return null;
	}
}
