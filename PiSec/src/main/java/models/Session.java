package models;

import java.util.Date;

/**
 * Stores a session
 */
public class Session {
	private final long sessionId;
	private final Date expiry;
	String account;

	public Session(Date date, long sessionId) {
		this(date, sessionId, null);
	}

	public Session(Date date, long sessionId, String account) {
		this.expiry = new Date(date.getTime() + 3600000);
		this.sessionId = sessionId;
		this.account = account;
	}

	/**
	 * Checks if this session is expired
	 * @return Boolean value True if the session has expired, False if it has not
	 */
	public boolean expired() {return expiry.getTime() < new Date().getTime();}

	public long getSessionId() {
		return sessionId;
	}

	public long getExpiration() {
		return expiry.getTime();
	}

	public boolean getLoggedIn() {
		return account != null;
	}

	public String getAccount() {
		return account;
	}
}
