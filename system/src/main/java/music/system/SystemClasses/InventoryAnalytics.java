package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import music.system.DatabaseManager;
import music.system.SignInPage;

/**
 * System Class, InventoryAnalytics: This class handles the generation of system wide analyitics.
 * 
 * Database table inventory_analyitics, links to users table with a users_employeeID attribute:
 * 
 * 
 * If the account requesting access to both generate or show analyitics in of manager type,
 * the database actions will be accepted.
 * 
 */
public class InventoryAnalytics {

    private Date time;
    private int itemStockCount;
    private double salesRevenue;
    private int repairsPerformedCount;
    private double averageAgeOfInventory;
    private double totalInventoryValue;
    
    public static Timestamp currentTime;

    // Constructor
    public InventoryAnalytics(Date time, int itemStockCount, double salesRevenue, int repairsPerformedCount,
                              double averageAgeOfInventory, double totalInventoryValue) {
        this.time = time;
        this.itemStockCount = itemStockCount;
        this.salesRevenue = salesRevenue;
        this.repairsPerformedCount = repairsPerformedCount;
        this.averageAgeOfInventory = averageAgeOfInventory;
        this.totalInventoryValue = totalInventoryValue;
    }

    // Method to save analytics data to the database
    public void saveToDatabase() {
        try {
            // Establish connection to the database
            Connection connection = DatabaseManager.getConnection();

            // Get current timestamp
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            // Count the entries in the item table
            int itemStockCount = countItems(connection);

            // Calculate the total retail value of all items
            double totalInventoryValue = calculateTotalInventoryValue(connection);

            // Count the amount of entries in the repair table
            int repairsPerformedCount = countRepairs(connection);

            // Average out all the items' dates against all the items inside of the archive tables' dates
            double averageAgeOfInventory = calculateAverageAgeOfInventory(connection);

            // Calculate the total amount of manufacturer price in the item table
            double totalManufacturerPrice = calculateTotalManufacturerPrice(connection);

            // Define SQL query to insert inventory analytics data into the database
            String query = "INSERT INTO inventory_analytics (time, item_stock_count, sales_revenue, repairs_performed_count, average_age_of_inventory, total_inventory_value, users_employeeID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, currentTime);
            preparedStatement.setInt(2, itemStockCount);
            preparedStatement.setDouble(3, totalManufacturerPrice); // Assuming no sales revenue for now
            preparedStatement.setInt(4, repairsPerformedCount);
            preparedStatement.setDouble(5, averageAgeOfInventory);
            preparedStatement.setDouble(6, totalInventoryValue);
            preparedStatement.setInt(7, SignInPage.currentUserID);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to count the entries in the item table
    private int countItems(Connection connection) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM item";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        }
        return count;
    }

    // Method to calculate the total retail value of all items
    private double calculateTotalInventoryValue(Connection connection) throws SQLException {
        double totalValue = 0;
        String query = "SELECT SUM(retailPrice) FROM item";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                totalValue = resultSet.getDouble(1);
            }
        }
        return totalValue;
    }

    // Method to count the amount of entries in the repair table
    private int countRepairs(Connection connection) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM repair_items";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        }
        return count;
    }

    private double calculateAverageAgeOfInventory(Connection connection) {
        double averageAge = 10;

        // TODO; Implement.
    
        return averageAge;
    }

    // Method to calculate the total amount of manufacturer price in the item table
    private double calculateTotalManufacturerPrice(Connection connection) throws SQLException {
        double totalManufacturerPrice = 0;
        String query = "SELECT SUM(manufacturerPrice) FROM item";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                totalManufacturerPrice = resultSet.getDouble(1);
            }
        }
        return totalManufacturerPrice;
    }

    // Getters and Setters
    public Date getTime() {return time;}

    public void setTime(Date time) {this.time = time;}

    public int getItemStockCount() {return itemStockCount;}

    public void setItemStockCount(int itemStockCount) {this.itemStockCount = itemStockCount;}

    public double getSalesRevenue() {return salesRevenue;}

    public void setSalesRevenue(double salesRevenue) {this.salesRevenue = salesRevenue;}

    public int getRepairsPerformedCount() {return repairsPerformedCount;}

    public void setRepairsPerformedCount(int repairsPerformedCount) {this.repairsPerformedCount = repairsPerformedCount;}

    public double getAverageAgeOfInventory() {return averageAgeOfInventory;}

    public void setAverageAgeOfInventory(double averageAgeOfInventory) {this.averageAgeOfInventory = averageAgeOfInventory;}

    public double getTotalInventoryValue() {return totalInventoryValue;}

    public void setTotalInventoryValue(double totalInventoryValue) {this.totalInventoryValue = totalInventoryValue;}

}