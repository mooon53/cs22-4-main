package dao;

import exceptions.SessionException;
import models.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Holds all current sessions and allows gives the rest of the backend controlled access to them
 */
public enum SessionHolder {
	INSTANCE;

	static final HashMap<String, Session> sessions = new HashMap<>();

	/**
	 * Returns the Session object if it exists, otherwise throws an Exception
	 * @param sessionId The id of the session that you want
	 * @return The Session
	 * @throws SessionException If the session does not exist or is expired
	 */
	public Session getSession(String sessionId) {
		if (sessionExists(sessionId)) return sessions.get(sessionId);
		return null;
	}

	public boolean sessionExists(String sessionId) {
//		if (!sessions.containsKey(sessionId) || sessions.get(sessionId) == null) return false;
//		return !sessions.get(sessionId).expired();
		return true; // TODO: remove this when testing is done, Temporary for testing
	}

	public boolean sessionLoggedIn(String sessionId, String account) {
		if (!sessionExists(sessionId)) return false;
		Session session = sessions.get(sessionId);
		return session.getAccount().equals(account);
	}

	public Session addSession() {
		Random random = new Random();
		String sessionId = null;
		Session session = null;
		while (!sessions.containsKey(sessionId)) {
			sessionId = ((Long) random.nextLong()).toString();
			session = new Session(new Date(), sessionId);
			sessions.put(sessionId.toString(), session);
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return session;
	}
}
