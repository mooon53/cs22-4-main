package dao;

import exceptions.SessionException;
import models.Session;

import java.util.HashMap;
import java.util.Random;

/**
 * Holds all current sessions and allows gives the rest of the backend controlled access to them
 */
public enum SessionHolder {
	INSTANCE;

	static final HashMap<Long, Session> sessions = new HashMap<>();

	/**
	 * Returns the Session object if it exists, otherwise throws an Exception
	 * @param sessionId The id of the session that you want
	 * @return The Session
	 * @throws SessionException If the session does not exist or is expired
	 */
	public Session getSession(Long sessionId) throws SessionException {
		if (!sessions.containsKey(sessionId) || sessions.get(sessionId) == null) {
			throw new SessionException("This session does not exist");
		}
		if (sessions.get(sessionId).expired()) throw new SessionException("This session has expired");
		return sessions.get(sessionId);
	}

	public Long addSession(Session session) {
		Random random = new Random();
		Long sessionId = null;
		while (!sessions.containsKey(sessionId)) {
			sessionId = random.nextLong();
			sessions.put(sessionId, session);
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sessionId;
	}
}
