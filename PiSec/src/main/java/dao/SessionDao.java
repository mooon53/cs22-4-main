package dao;

import Exceptions.SessionException;
import models.Session;

import java.util.HashMap;

public class SessionDao {
	static HashMap<String, Session> sessions = new HashMap<>();

	public static Session getSession(String sessionId) throws SessionException {
		if (sessions.get(sessionId) != null) return sessions.get(sessionId);
		else throw new SessionException("This session does not exist");
	}
}
