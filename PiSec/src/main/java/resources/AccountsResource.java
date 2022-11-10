package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Account;
import models.Session;
import org.json.JSONObject;

import java.sql.SQLException;

import static utils.PasswordUtils.*;
import static dao.DatabaseAccess.*;
import static dao.SessionHolder.INSTANCE;

@Path("accounts")
public class AccountsResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccountInfo(@HeaderParam("sessionId") String sessionId) {
		if (INSTANCE.sessionExists(sessionId)) {
			Session session = INSTANCE.getSession(sessionId);
			return session.getAccount();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAccount(String input, @HeaderParam("sessionId") String sessionId) throws SQLException {
		JSONObject accountData = new JSONObject(input);
		String username = accountData.getString("username");
		String salt = generateSalt();
		String password = preparePassword(accountData.getString("password"), salt);
		Account account = new Account(username, password, salt);
		addAccount(account, password, salt);
		INSTANCE.getSession(sessionId).login(account);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logIn(String input, @HeaderParam("sessionId") String sessionId) {
		JSONObject response = new JSONObject();
		if (INSTANCE.sessionExists(sessionId) && !INSTANCE.sessionLoggedIn(sessionId)) {
			JSONObject accountData = new JSONObject(input);
			String username = accountData.getString("username");
			Account account = getAccount(username);
			String salt = account.getSalt();
			String password = preparePassword(accountData.getString("password"), salt);
			if (account.checkPassword(password)) {
				Session session = INSTANCE.getSession(sessionId);  // TODO: check if session exists and isn't logged in
				session.login(account);
				response.put("success", true);
				return response.toString();
			} else {
				response.put("success", false);
				response.put("info", "incorrect login details");
				return response.toString();
			}
		}
		response.put("succes", false);
		response.put("info", "session does not exist");
		return response.toString();
	}
}