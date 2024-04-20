package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class archiveClass {

    private Connection connection;

    public archiveClass() {
        // Initialize database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adds a new instrument to the inventory and database
    public void addInstrument(String instrumentID, String instrumentName, String instrumentType, double price, int quantity) {
        try {
            String query = "INSERT INTO instruments (instrument_id, instrument_name, instrument_type, price, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, instrumentID);
            preparedStatement.setString(2, instrumentName);
            preparedStatement.setString(3, instrumentType);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, quantity);
            preparedStatement.executeUpdate();
            System.out.println("Instrument added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updates the details of an existing instrument in the database
    public void updateInstrument(String instrumentID, String instrumentName, String instrumentType, double price, int quantity) {
        try {
            String query = "UPDATE instruments SET instrument_name=?, instrument_type=?, price=?, quantity=? WHERE instrument_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, instrumentName);
            preparedStatement.setString(2, instrumentType);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setString(5, instrumentID);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Instrument updated.");
            } else {
                System.out.println("No instrument found with ID " + instrumentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Removes an instrument from the inventory and database
    public void removeInstrument(String instrumentID) {
        try {
            String query = "DELETE FROM instruments WHERE instrument_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, instrumentID);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Instrument removed.");
            } else {
                System.out.println("No instrument found with ID " + instrumentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves all items from the archive
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            String query = "SELECT * FROM archive"; // Corrected table name to "archive"
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getInt("itemID"), // Corrected column name
                        resultSet.getInt("serialNumber"), // Corrected column name
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("brand"),
                        resultSet.getString("dateManufactured"), // Corrected column name
                        resultSet.getString("description"),
                        resultSet.getDouble("manufacturerPrice"), // Corrected column name
                        resultSet.getDouble("retailPrice") // Corrected column name
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}