package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.io.IOException;

import dao.SessionHolder;
import dao.PortHolder;

@Path("live")
public class LiveResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getLive(@HeaderParam("sessionId") String sessionId) throws IOException {
		if (SessionHolder.INSTANCE.sessionLoggedIn(sessionId)) {
			int port = PortHolder.INSTANCE.firstUnusedPort();
			String[] args = new String[] {"/bin/libcamera-vid", "-t", "0", "--width", "1920", "--height", "1080", "--codec", "h264", "--listen", "--inline", "-o", "tcp://0.0.0.0:" + port};
			Process proc = new ProcessBuilder(args).start();
			PortHolder.INSTANCE.addPort(port, proc);
			return Integer.toString(port);
			//libcamera-vid -t 0 --width 1920 --height 1080 --codec h264 --listen --inline -o tcp://0.0.0.0:8888
		}
		return null;
	}
}
