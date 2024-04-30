package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import music.system.DatabaseManager;

/**
 * System Class, StoreTransaction: This class handles the creation of a new store transaction entry.
 * 
 * Database table store_transaction, links to item table with a item_itemID attribute.
 * 
 */
public class StoreTransaction {

    private Date transactionDate;
    private String purchaseDescription;
    private double unitPrice;
    private int itemID;

    // Constructor
    public StoreTransaction(Date transactionDate, String purchaseDescription, double unitPrice) {
        this.transactionDate = transactionDate;
        this.purchaseDescription = purchaseDescription;
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

            // Split the string by spaces
            String[] words = purchaseDescription.split(" ");
            
            // Get the last word
            String lastWord = words[words.length - 1];

            // Define SQL query to insert store transaction data into the database
            query = "INSERT INTO store_transactions (transaction_id, transaction_date, purchase_description, price, item_itemID) VALUES (?, ?, ?, ?, ?)";
    
            // Create PreparedStatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, lastWord.hashCode()+itemID);
            preparedStatement.setDate(2, new java.sql.Date(transactionDate.getTime()));
            preparedStatement.setString(3, purchaseDescription);
            preparedStatement.setDouble(4, unitPrice);
            preparedStatement.setInt(5, itemID);

            // Execute the insert query
            preparedStatement.executeUpdate();
    
            // Close resources
            preparedStatement.close();

    
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    // Getters and Setters
    public int getId() {return itemID;}

    public Date getTransactionDate() {return transactionDate;}

    public void setTransactionDate(Date transactionDate) {this.transactionDate = transactionDate;}

    public String getPurchaseDescription() {return purchaseDescription;}

    public void setPurchaseDescription(String purchaseDescription) {this.purchaseDescription = purchaseDescription;}

    public double getUnitPrice() {return unitPrice;}

    public void setUnitPrice(double unitPrice) {this.unitPrice = unitPrice;}

}