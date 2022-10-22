package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import models.Session;

import static dao.SessionHolder.INSTANCE;

@Path("sessions")
public class SessionResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Session getSession(@HeaderParam("sessionId") String sessionId) {
		return INSTANCE.getSession(sessionId);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Session newSession() {
		return INSTANCE.addSession();
	}
}
