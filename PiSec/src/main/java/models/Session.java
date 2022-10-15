package models;

import java.util.Date;

/**
 * Stores a session
 */
public class Session {
	private Date expiry;

	/**
	 * Checks if this session is expired
	 * @return Boolean value True if the session has expired, False if it has not
	 */
	public boolean expired() {return expiry.getTime() < new Date().getTime();}


}
