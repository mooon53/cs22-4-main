package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("recording")
public class RecordingResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


}
