package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import music.system.DatabaseManager;

public class StoreTransaction {

    // Attributes
    private int transactionID;
    private Date transactionDate;
    private double totalAmount;
    private String purchaseDescription;
    private int quantity;
    private double unitPrice;
    private int itemID;

    // Constructor
    public StoreTransaction(Date transactionDate, double totalAmount, String purchaseDescription, int quantity, double unitPrice) {
        this.transactionDate = transactionDate;
        this.totalAmount = totalAmount;
        this.purchaseDescription = purchaseDescription;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Save to MySQL method
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();
    
            String query = "SELECT MAX(itemID) AS last_item_id FROM item";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            
            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();
    
            // Check if there is any result
            if (resultSet.next()) {
                itemID = resultSet.getInt("last_item_id");
            }

            // Define SQL query to insert store transaction data into the database
            query = "INSERT INTO store_transactions (transaction_id, transaction_date, total_amount, purchase_description, quantity, unit_price, item_itemID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
            // Create PreparedStatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, purchaseDescription.hashCode()+itemID);
            preparedStatement.setDate(2, new java.sql.Date(transactionDate.getTime()));
            preparedStatement.setDouble(3, totalAmount);
            preparedStatement.setString(4, purchaseDescription);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setDouble(6, unitPrice);
            preparedStatement.setInt(7, itemID);

            // Execute the insert query
            preparedStatement.executeUpdate();
    
            // Close resources
            preparedStatement.close();

    
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    // Getters and Setters
    public int getId() {
        return itemID;
    }

    public void setId(int id) {
        this.transactionID = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Override toString method to provide a string representation of the StoreTransaction object
    @Override
    public String toString() {
        return "StoreTransaction{" +
                "id=" + transactionID +
                ", transactionDate=" + transactionDate +
                ", totalAmount=" + totalAmount +
                ", purchaseDescription='" + purchaseDescription + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}