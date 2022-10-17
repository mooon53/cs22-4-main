package dao;

import models.Alert;

import java.util.ArrayList;
import java.util.List;

/*
Gets data from the database and transforms it into a format that is easier to use in the rest of the code
 */
public class DatabaseAccess {
	static {
	/*
	This block is run on startup of the server, will be used to make connection to the database, we'll also start a thread
	that checks the connection every minute (or whatever interval we want)
	 */
	}

	public static List<Alert> getAlerts() {
		return new ArrayList<>();
	}
}
