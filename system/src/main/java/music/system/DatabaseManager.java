package music.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    // Method to establish the database connection
    public static void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("New connection made.");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
        }
    }

    // Method to close the database connection
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed to the database.");
        }
    }

    // Method to get the connection
    @SuppressWarnings("exports")
    public static Connection getConnection() throws SQLException {
        connect(); // Ensure connection is open
        return connection;
    }
}