package dao;

import models.Alert;

import java.util.ArrayList;
import java.util.List;

public class Dbao {
	// This class will get data from the database and transform it into a format that is easier to use in java

//	This block is run on startup of the server, will be used to make connection to the database, we'll also start a thread
//	that checks the connection every minute (or whatever interval we want)
	static {

	}

	public static List<Alert> getAlerts() {
		return new ArrayList<>();
	}
}
