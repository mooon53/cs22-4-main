package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import models.Camera;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.SessionHolder;

@Path("cameras")
public class CamerasResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Camera> getCameras(@HeaderParam("sessionId") String sessionId) {
		if (SessionHolder.INSTANCE.sessionLoggedIn(sessionId)) {
			ArrayList<Camera> cameras = new ArrayList<>();
			cameras.add(new Camera(0L, "Office"));
			return cameras;
		}
		return Collections.emptyList();
	}
}
