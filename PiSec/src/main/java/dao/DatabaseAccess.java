package dao;

import models.Alert;
import utils.DBConnectionChecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
Gets data from the database and transforms it into a format that is easier to use in the rest of the code
 */
public class DatabaseAccess {
	private static Statement statement;
	private static final String URL = "jdbc:sqlite:~/DataBase/PiSec.db";

	static {
		boolean connected = false;
		Connection connection = null;
		while (!connected) {
			try {
				connection = DriverManager.getConnection(URL);
				statement = connection.createStatement();
				System.out.println("SQL connection established!");
				connected = true;
			} catch (SQLException e) {
				System.err.println("Error connecting, StackTrace:");
				e.printStackTrace();
			}
		}
		DBConnectionChecker dbCheck = new DBConnectionChecker(connection, URL);
		Thread thread = new Thread(dbCheck, "Connection checker");
		thread.start();
	}

	private DatabaseAccess() {throw new IllegalStateException("This class should not be instantiated");}

	public static void replaceStatement(Statement newStatement) {
		statement = newStatement;
	}

	public static List<Alert> getAlerts() {
		return new ArrayList<>();
	}
}
