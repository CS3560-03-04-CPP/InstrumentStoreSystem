package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Random;

public class InventoryAnalytics {

    private Date time;
    private int itemStockCount;
    private double salesRevenue;
    private int repairsPerformedCount;
    private double averageAgeOfInventory;
    private double totalInventoryValue;

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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to insert inventory analytics data into the database
            String query = "INSERT INTO inventory_analytics (time, item_stock_count, sales_revenue, repairs_performed_count, average_age_of_inventory, total_inventory_value) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(time.getTime()));
            preparedStatement.setInt(2, itemStockCount);
            preparedStatement.setDouble(3, salesRevenue);
            preparedStatement.setInt(4, repairsPerformedCount);
            preparedStatement.setDouble(5, averageAgeOfInventory);
            preparedStatement.setDouble(6, totalInventoryValue);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InventoryAnalytics performAnalyitics() {
    // Generate random values for each attribute
    Random random = new Random();
    int itemStockCount = random.nextInt(10); 
    double salesRevenue = random.nextDouble() * 100; 
    int repairsPerformedCount = random.nextInt(10);
    double averageAgeOfInventory = random.nextDouble() * 100; 
    double totalInventoryValue = random.nextDouble() * 50; 

    // Create an instance of InventoryAnalytics with the generated random values
    Date currentTime = new Date(); // Current time
    InventoryAnalytics newAnalytics = new InventoryAnalytics(currentTime, itemStockCount, salesRevenue, repairsPerformedCount, averageAgeOfInventory, totalInventoryValue);

    return newAnalytics;
}


    // Getters and Setters
    /**
     * Gets the time key for the inventory analytics data.
     *
     * @return The time key
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets the time key for the inventory analytics data.
     *
     * @param time The time key to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Gets the number of items in stock.
     *
     * @return The number of items in stock
     */
    public int getItemStockCount() {
        return itemStockCount;
    }

    /**
     * Sets the number of items in stock.
     *
     * @param itemStockCount The number of items in stock to set
     */
    public void setItemStockCount(int itemStockCount) {
        this.itemStockCount = itemStockCount;
    }

    /**
     * Gets the total sales revenue.
     *
     * @return The total sales revenue
     */
    public double getSalesRevenue() {
        return salesRevenue;
    }

    /**
     * Sets the total sales revenue.
     *
     * @param salesRevenue The total sales revenue to set
     */
    public void setSalesRevenue(double salesRevenue) {
        this.salesRevenue = salesRevenue;
    }

    /**
     * Gets the number of repairs performed.
     *
     * @return The number of repairs performed
     */
    public int getRepairsPerformedCount() {
        return repairsPerformedCount;
    }

    /**
     * Sets the number of repairs performed.
     *
     * @param repairsPerformedCount The number of repairs performed to set
     */
    public void setRepairsPerformedCount(int repairsPerformedCount) {
        this.repairsPerformedCount = repairsPerformedCount;
    }

    /**
     * Gets the average age of items in inventory.
     *
     * @return The average age of items in inventory
     */
    public double getAverageAgeOfInventory() {
        return averageAgeOfInventory;
    }

    /**
     * Sets the average age of items in inventory.
     *
     * @param averageAgeOfInventory The average age of items in inventory to set
     */
    public void setAverageAgeOfInventory(double averageAgeOfInventory) {
        this.averageAgeOfInventory = averageAgeOfInventory;
    }

    /**
     * Gets the total value of the inventory.
     *
     * @return The total value of the inventory
     */
    public double getTotalInventoryValue() {
        return totalInventoryValue;
    }

    /**
     * Sets the total value of the inventory.
     *
     * @param totalInventoryValue The total value of the inventory to set
     */
    public void setTotalInventoryValue(double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }

    

}