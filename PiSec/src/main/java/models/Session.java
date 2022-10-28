package models;

import java.util.Date;

/**
 * Stores a session
 */
public class Session {
	private final String sessionId;
	private final Date expiry;
	private Account account;

	public Session(Date date, String sessionId) {
		this(date, sessionId, null);
	}

	public Session(Date date, String sessionId, Account account) {
		this.expiry = new Date(date.getTime() + 3600000);
		this.sessionId = sessionId;
		this.account = account;
	}

	/**
	 * Checks if this session is expired
	 * @return Boolean value True if the session has expired, False if it has not
	 */
	public boolean expired() {return expiry.getTime() < new Date().getTime();}

	public void login(Account account) {this.account = account;}

	public String getSessionId() {return sessionId;}
	public long getExpiration() {return expiry.getTime();}
	public boolean getLoggedIn() {return account != null;}
	public Account getAccount() {return account;}
}
