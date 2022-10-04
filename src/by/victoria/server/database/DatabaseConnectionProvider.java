package by.victoria.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionProvider {
    private static final String url = "jdbc:mysql://localhost:3306/lw_psp";
    private static final String user = "root";
    private static final String password = "Karolina";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("can't provide connection", e);
        }
    }
}
