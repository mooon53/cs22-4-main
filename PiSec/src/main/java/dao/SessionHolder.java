package dao;

import exceptions.SessionException;
import models.Session;

import java.util.HashMap;

/**
 * Holds all current sessions and allows gives the rest of the backend controlled access to them
 */
public class SessionHolder {
	static HashMap<String, Session> sessions = new HashMap<>();

	/**
	 * Returns the Session object if it exists, otherwise throws an Exception
	 * @param sessionId The id of the session that you want
	 * @return The Session
	 * @throws SessionException If the session does not exist or is expired
	 */
	public static Session getSession(String sessionId) throws SessionException {
		if (!sessions.containsKey(sessionId) || sessions.get(sessionId) == null) {
			throw new SessionException("This session does not exist");
		}
		if (sessions.get(sessionId).expired()) throw new SessionException("This session has expired");
		return sessions.get(sessionId);
	}
}
