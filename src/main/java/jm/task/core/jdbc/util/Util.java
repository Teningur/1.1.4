package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "4260";

    private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
