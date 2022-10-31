package utils;


import dao.DatabaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionChecker extends Thread {
    private final String url;
    private static final long WAIT = 60000;

    private Connection connection;

    public DBConnectionChecker(Connection connection, String url) {
        this.connection = connection;
        this.url = url;
        this.setName("Database connection checker");
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                Thread.sleep(WAIT);
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(url);
                    DatabaseAccess.replaceStatement(connection.createStatement());
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
