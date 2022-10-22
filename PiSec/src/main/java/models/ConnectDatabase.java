package models;

import java.sql.*;


public class ConnectDatabase {
    private String fileName;
    //TODO: replace the link
    private String url = "jdbc:sqlite:~/DataBase/PiSec.db";

    public ConnectDatabase(String fileName){
        this.fileName=fileName;
    }

    public void connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("SQL connection established!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void create(){
        String sql = url + this.fileName;

        try{
            Connection connection =DriverManager.getConnection(sql);
            if (connection!=null){
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("Driver name: "+metaData.getDriverName());
                System.out.println("Database is created!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTables(){
        String sql = url + this.fileName;

        String createUser = "CREATE TABLE IF NOT EXISTS user (\n"
                + " uid bigint PRIMARY KEY,\n"
                + " login text UNIQUE NOT NULL,\n"
                + " password text NOT NULL\n"
                + ");";

        String alert = "CREATE TABLE IF NOT EXISTS alert (\n"
                + " aid bigint PRIMARY KEY,\n"
                + " date text NOT NULL,\n"
                + " time text NOT NULL,\n"
                + " recording text\n"
                + ");";

        try{
            Connection connection = DriverManager.getConnection(sql);
            Statement statement = connection.createStatement();
            statement.execute(createUser);
            statement.execute(alert);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
