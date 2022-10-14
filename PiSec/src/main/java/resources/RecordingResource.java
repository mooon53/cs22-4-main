package resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("recording")
public class RecordingResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


}
