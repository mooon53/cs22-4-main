package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import models.Session;

import static dao.SessionHolder.INSTANCE;

import java.util.Date;

@Path("sessions")
public class SessionResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Session newSession() {
		return INSTANCE.addSession();
	}


}
