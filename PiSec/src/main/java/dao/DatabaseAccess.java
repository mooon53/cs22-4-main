package dao;

import models.Account;
import models.Alert;
import utils.DBConnectionChecker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
Gets data from the database and transforms it into a format that is easier to use in the rest of the code
 */
public class DatabaseAccess {
	private static Statement statement;
	private static final String URL = "jdbc:sqlite:/Users/tice/python/PiSec.db";

	static {
		boolean connected = false;
		Connection connection = null;
		while (!connected) {
			try {
				DriverManager.registerDriver(new org.sqlite.JDBC());
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

	private DatabaseAccess() {
		throw new IllegalStateException("This class should not be instantiated");
	}

	public static void replaceStatement(Statement newStatement) {
		statement = newStatement;
	}

	public static List<Alert> getAlerts() {
		ArrayList<Alert> alerts = new ArrayList<>();
		String query = "SELECT date, recording\n" +
				"FROM alert;";
		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String date = resultSet.getString("date");
				String recording = resultSet.getString("recording");
				alerts.add(new Alert(date, recording));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerts;
	}

	public static Alert getAlert(Long alertId) {
		String query = "SELECT date, recording\n" +
				"FROM alert\n" +
				"WHERE aid =" + alertId.toString() + ";";
		Alert alert = null;
		try {
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			String date = resultSet.getString("date");
			String recording = resultSet.getString("recording");
			alert = new Alert(date, recording);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alert;
	}

	public static void addAccount(String username, String password) throws SQLException {
		String query = "INSERT INTO user(login, password)\n" +
				"VALUES ('" + username + "', '" + password + "');";
		statement.executeUpdate(query);
	}

	public static Account getAccount(String username) {
		String query = "SELECT login, password\n" +
				"FROM user\n" +
				"WHERE login = '" + username + "';";
		Account account = null;
		try {
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			account = new Account(resultSet.getString("login"), resultSet.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
}
