package models;

import java.util.Date;

/**
 * Stores a session
 */
public class Session {
	private final Date expiry;
	String account;

	public Session(Date date) {
		this(date, "");
	}

	public Session(Date date, String account) {
		this.expiry = new Date(date.getTime() + 3600000);
		this.account = account;
	}
	/**
	 * Checks if this session is expired
	 * @return Boolean value True if the session has expired, False if it has not
	 */
	public boolean expired() {return expiry.getTime() < new Date().getTime();}


}
