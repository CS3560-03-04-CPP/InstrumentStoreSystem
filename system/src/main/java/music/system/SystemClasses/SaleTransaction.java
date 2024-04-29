package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import music.system.DatabaseManager;

/**
 * System Class, SaleTransaction: This class handles the creation of a new sale transaction entry.
 * 
 * Database table sale_transaction, links to item table with a item_itemID attribute.
 * 
 */
public class SaleTransaction {

    private Integer orderIdProperty;                     
    private StringProperty buyerNameProperty;            
    private StringProperty cardNumberProperty;           
    private ObjectProperty<Date> expirationDateProperty; 
    private StringProperty CVVProperty;                  
    private StringProperty billingAddressProperty;       
    private StringProperty billingPostalCodeProperty;    
    private DoubleProperty transactionAmountProperty;    
    private Integer itemID;                             

    // Constructor
    public SaleTransaction( String buyerName, String cardNumber, LocalDate expirationDate, String CVV,
                           String billingAddress, String billingPostalCode, double transactionAmount, int itemID) {
        this.orderIdProperty = cardNumber.hashCode() + itemID;
        this.buyerNameProperty = new SimpleStringProperty(buyerName);
        this.cardNumberProperty = new SimpleStringProperty(cardNumber);
        this.expirationDateProperty = new SimpleObjectProperty<>(java.sql.Date.valueOf(expirationDate));
        this.CVVProperty = new SimpleStringProperty(CVV);
        this.billingAddressProperty = new SimpleStringProperty(billingAddress);
        this.billingPostalCodeProperty = new SimpleStringProperty(billingPostalCode);
        this.transactionAmountProperty = new SimpleDoubleProperty(transactionAmount);
        this.itemID = itemID;
    }

    // Save to MySQL method
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert sale record data into the database
            String query = "INSERT INTO sale_transactions (transaction_id, buyer_name, card_number, expiration_date, CVV, billing_address, billing_postal_code, transaction_amount, item_itemID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderIdProperty);
            preparedStatement.setString(2, buyerNameProperty.get());
            preparedStatement.setString(3, cardNumberProperty.get());
            preparedStatement.setDate(4, new java.sql.Date(expirationDateProperty.get().getTime()));
            preparedStatement.setString(5, CVVProperty.get());
            preparedStatement.setString(6, billingAddressProperty.get());
            preparedStatement.setString(7, billingPostalCodeProperty.get());
            preparedStatement.setDouble(8, transactionAmountProperty.get());
            preparedStatement.setInt(9, itemID);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getter and Setter methods 
    public Integer getOrderId() {return orderIdProperty;}
    
    public void setOrderId(int orderId) {this.orderIdProperty = orderId;}
    
    public String getBuyerName() {return buyerNameProperty.get();}
    
    public void setBuyerName(String buyerName) {this.buyerNameProperty.set(buyerName);}
    
}