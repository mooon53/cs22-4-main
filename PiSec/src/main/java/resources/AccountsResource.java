package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import models.Account;
import models.Session;
import org.json.JSONObject;

import java.sql.SQLException;

import static utils.PasswordUtils.preparePassword;
import static dao.DatabaseAccess.*;
import static dao.SessionHolder.INSTANCE;

@Path("accounts")
public class AccountsResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccountInfo(@HeaderParam("sessionId") String sessionId) {
		Session session = INSTANCE.getSession(sessionId);
		return session.getAccount();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAccount(String input, @HeaderParam("sessionId") String sessionId) throws SQLException {
		JSONObject accountData = new JSONObject(input);
		String username = accountData.getString("username");
		String password = preparePassword(accountData.getString("password"));
		Account account = new Account(username, password);
		addAccount(account, password);
		INSTANCE.getSession(sessionId).login(account);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logIn(String input, @HeaderParam("sessionId") String sessionId) {
		JSONObject accountData = new JSONObject(input);
		String username = accountData.getString("username");
		String password = preparePassword(accountData.getString("password"));
		JSONObject response = new JSONObject();
		Account account = getAccount(username);
		if (account != null && account.checkPassword(password)) {
			Session session = INSTANCE.getSession(sessionId);
			session.login(account);
			response.put("success", true);
			return response.toString();
		}
		response.put("success", false);
		response.put("info", "incorrect login details");
		return response.toString();
	}
}