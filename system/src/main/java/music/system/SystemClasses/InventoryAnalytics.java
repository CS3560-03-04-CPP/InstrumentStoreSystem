package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

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

    // Method to save inventory analytics data to the database
    public void saveToDatabase() {
        try {
            // Establish connection to the database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert inventory analytics data into the database
            String query = "INSERT INTO inventory_analytics (time, item_stock_count, sales_revenue, repairs_performed_count, average_age_of_inventory, total_inventory_value, users_employeeID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, currentTime);
            preparedStatement.setInt(2, itemStockCount);
            preparedStatement.setDouble(3, salesRevenue);
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

    public static InventoryAnalytics performAnalyitics() {
    // Generate random values for each attribute <-Temporary!
    Random random = new Random();
    int itemStockCount = random.nextInt(10); 
    double salesRevenue = random.nextDouble() * 100; 
    int repairsPerformedCount = random.nextInt(10);
    double averageAgeOfInventory = random.nextDouble() * 100; 
    double totalInventoryValue = random.nextDouble() * 50; 

    // Create an instance of InventoryAnalytics with the generated random values
    currentTime = new java.sql.Timestamp(System.currentTimeMillis()); // Current time
    InventoryAnalytics newAnalytics = new InventoryAnalytics(currentTime, itemStockCount, salesRevenue, repairsPerformedCount, averageAgeOfInventory, totalInventoryValue);

    return newAnalytics;
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