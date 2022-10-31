package dao;

import models.Account;
import models.Alert;
import utils.DBConnectionChecker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Gets data from the database and transforms it into a format that is easier to use in the rest of the code
 */
public class DatabaseAccess {
	private static Statement statement;
	private static final String HOME = System.getProperty("user.home");
	private static final String URL = HOME + "/python/PiSec.db";
	private static Connection connection = null;

	static {
		boolean connected = false;
		while (!connected) {
			try {
				DriverManager.registerDriver(new org.sqlite.JDBC());
				connection = DriverManager.getConnection("jdbc:sqlite:" + URL);
				statement = connection.createStatement();
				System.out.println("SQL connection established!");
				connected = true;
			} catch (SQLException e) {
				System.err.println("Error connecting, StackTrace:");
				e.printStackTrace();
			}
		}
		DBConnectionChecker dbCheck = new DBConnectionChecker(connection, URL);
		dbCheck.start();
	}

	private DatabaseAccess() {
		throw new IllegalStateException("This class should not be instantiated");
	}

	public static void replaceStatement(Statement newStatement) {
		statement = newStatement;
	}

	public static List<Alert> getAlerts() {
		ArrayList<Alert> alerts = new ArrayList<>();
		String query = "SELECT aid, date_time\n" +
				"FROM alert;";
		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("aid");
				Date dateTime = new java.util.Date(resultSet.getLong("date_time"));
				alerts.add(new Alert(id, dateTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alerts;
	}

	public static Alert getAlert(Long alertId) {
		String query = "SELECT aid, date_time, recording\n" +
				"FROM alert\n" +
				"WHERE aid = ?;";
		Alert alert = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, alertId.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("aid");
			Date dateTime = new Date(resultSet.getLong("date_time"));
			String recording = resultSet.getString("recording");
			alert = new Alert(id, dateTime, recording);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alert;
	}

	public static void addAccount(Account account, String password, String salt) throws SQLException {
		String query = "INSERT INTO user(login, password, salt)\n" +
				"VALUES (?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, account.getUsername());
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, salt);
		preparedStatement.executeUpdate();
	}

	public static Account getAccount(String username) {
		String query = "SELECT login, password, salt\n" +
				"FROM user\n" +
				"WHERE login = ?;";
		Account account = new Account();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String user = resultSet.getString("login");
			String password = resultSet.getString("password");
			String salt = resultSet.getString("salt");
			account = new Account(user, password, salt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
}
