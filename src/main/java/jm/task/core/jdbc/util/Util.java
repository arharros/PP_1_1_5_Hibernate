package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public final static String URL = "jdbc:postgresql://localhost:5432/mytestdb";
    public final static String USER = "postgres";
    public final static String PASSWORD = "Ruletka";
    // реализуйте настройку соединения с БД
    public static Connection connection() {
        Connection connectionDb = null;
        try {
            connectionDb = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectionDb;
    }
}
