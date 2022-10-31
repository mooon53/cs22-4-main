package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import models.Camera;

import java.util.ArrayList;
import java.util.List;

@Path("cameras")
public class CamerasResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Camera> getCameras() {
		ArrayList<Camera> cameras = new ArrayList<>();
		cameras.add(new Camera(0L, "Office"));
		return cameras;
	}
}
