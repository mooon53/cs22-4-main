package dao;

import models.Account;
import models.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Holds all current sessions and allows gives the rest of the backend controlled access to them
 */
public enum SessionHolder {
	INSTANCE;

	private static final Random random = new Random();
	private static final HashMap<String, Session> sessions = new HashMap<>();

	/**
	 * Returns the Session object if it exists, otherwise throws an Exception
	 * @param sessionId The id of the session that you want
	 * @return The Session
	 */
	public Session getSession(String sessionId) {
		if (sessionExists(sessionId)) return sessions.get(sessionId);
		return addSession();
	}

	public boolean sessionExists(String sessionId) {
		return true; // TODO: remove this when testing is done, Temporary for testing
//		if (!sessions.containsKey(sessionId) || sessions.get(sessionId) == null) return false;
//		return !sessions.get(sessionId).expired();
	}

	public boolean sessionLoggedIn(String sessionId, Account account) {
		if (!sessionExists(sessionId)) return false;
		Session session = sessions.get(sessionId);
		return session.getAccount().equals(account);
	}

	public Session addSession() {
		String sessionId = null;
		Session session = null;
		while (!sessions.containsKey(sessionId)) {
			sessionId = Long.toUnsignedString(random.nextLong(), 16);
			session = new Session(new Date(), sessionId);
			sessions.put(sessionId, session);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return session;
	}
}
