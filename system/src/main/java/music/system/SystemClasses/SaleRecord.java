package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import music.system.DatabaseManager;

/**
 * System Class, SaleRecord: This class handles the creation of a new sale record entry.
 * 
 * Database table sale_records, links to sale_transactions table with a item_sale_records:
 * 
 *                              FK                                                          PK
 *           item_sale_records: sale_record_id                  from sale_records table     (id)
 *                              sale_transaction_transaction_id from sale_transacions table (transaction_id)
 * 
 */
public class SaleRecord {

    private IntegerProperty orderIdProperty;            
    private StringProperty dateProperty;                
    private StringProperty buyerNameProperty;           
    private DoubleProperty soldPriceProperty;          
    private IntegerProperty itemID;

    // Constructor
    public SaleRecord(Integer orderId, String date, String buyerName, double soldPrice, int itemID) {
        this.orderIdProperty = new SimpleIntegerProperty(orderId);
        this.dateProperty = new SimpleStringProperty(date);
        this.buyerNameProperty = new SimpleStringProperty(buyerName);
        this.soldPriceProperty = new SimpleDoubleProperty(soldPrice);
        this.itemID = new SimpleIntegerProperty(itemID);
    }

    // Save to MySQL method
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert sale record data into the database
            String query = "INSERT INTO sale_records (id, date, buyer_name, sold_price, item_id) VALUES (?, ?, ?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderIdProperty.get());
            preparedStatement.setDate(2, convertStringToDate(dateProperty.get()));
            preparedStatement.setString(3, buyerNameProperty.get());
            preparedStatement.setDouble(4, soldPriceProperty.get());
            preparedStatement.setInt(5, itemID.get());

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert sale record data into the database
            String query = "INSERT INTO item_sale_records (sale_records_id, sale_transactions_transaction_id) VALUES (?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderIdProperty.get());
            preparedStatement.setInt(2, itemID.get());
            

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static java.sql.Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(dateString);
            return new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }

    // Setters and Getters
    public String getDate() {return dateProperty.get().toString();}

    public void setDate(String date) {this.dateProperty.set(date);}

    public String getBuyerName() {return buyerNameProperty.get();}

    public void setBuyerName(String buyerName) {this.buyerNameProperty.set(buyerName);}

    public double getSoldPrice() {return soldPriceProperty.get();}

    public void setSoldPrice(double soldPrice) {this.soldPriceProperty.set(soldPrice);}

    public String getOrderId() {return itemID.toString();}

}