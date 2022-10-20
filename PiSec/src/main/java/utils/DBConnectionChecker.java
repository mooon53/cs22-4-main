package utils;


import dao.DatabaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionChecker implements Runnable {
    private Connection connection;
    private final String URL;
    private final long WAIT = 60000;

    public DBConnectionChecker(Connection connection, String url) {
        this.connection = connection;
        this.URL = url;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(WAIT);
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(URL);
                    DatabaseAccess.replaceStatement(connection.createStatement());
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
