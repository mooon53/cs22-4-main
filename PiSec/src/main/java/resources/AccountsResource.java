package resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONObject;

import java.sql.SQLException;

import static utils.PasswordUtils.preparePassword;
import static dao.DatabaseAccess.addAccount;
import static dao.SessionHolder.INSTANCE;

@Path("accounts")
public class AccountsResource {
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAccount(String input, @HeaderParam("sessionId") String sessionId) throws SQLException {
		JSONObject accountData = new JSONObject(input);
		String username = accountData.getString("username");
		String password = preparePassword(accountData.getString("password"));
		addAccount(username, password);
		INSTANCE.getSession(sessionId).login(username);  // TODO: fix error
	}
}