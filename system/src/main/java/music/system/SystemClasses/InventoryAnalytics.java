package music.system.SystemClasses;

import java.util.Date;

public class InventoryAnalytics {

    private Date time; //Time key for the inventory analytics data
    private int itemStockCount; //Number of items in stock
    private double salesRevenue; //Total sales revenue
    private int repairsPerformedCount; //Number of repairs performed
    private double averageAgeOfInventory; //Average age of items in inventory
    private double totalInventoryValue; //Total value of the inventory


    public InventoryAnalytics(Date time, int itemStockCount, double salesRevenue, int repairsPerformedCount,
                              double averageAgeOfInventory, double totalInventoryValue) {
        this.time = time;
        this.itemStockCount = itemStockCount;
        this.salesRevenue = salesRevenue;
        this.repairsPerformedCount = repairsPerformedCount;
        this.averageAgeOfInventory = averageAgeOfInventory;
        this.totalInventoryValue = totalInventoryValue;
    }

    public void performAnalyitics() {
        //implement code here.
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