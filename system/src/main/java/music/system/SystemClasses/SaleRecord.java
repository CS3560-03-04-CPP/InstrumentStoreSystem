package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleRecord {

    // Attributes
    private IntegerProperty orderIdProperty;            // Unique order ID for the sale
    private StringProperty dateProperty;                // Date of the sale
    private StringProperty buyerNameProperty;           // Name of the buyer
    private DoubleProperty soldPriceProperty;           // Price at which the item was sold
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

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
            System.out.println("Connection closed to the database: SaleRecord"); 
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to insert sale record data into the database
            String query = "INSERT INTO item_sale_records (sale_records_id, sale_transactions_transaction_id) VALUES (?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, orderIdProperty.get());
            preparedStatement.setInt(2, itemID.get());
            

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            System.out.println("Connection closed to the database: SaleRecord");
            preparedStatement.close();
            connection.close();
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

    public String getDate() {
        return dateProperty.get().toString();
    }

    public void setDate(String date) {
        this.dateProperty.set(date);
    }

    public String getBuyerName() {
        return buyerNameProperty.get();
    }

    public void setBuyerName(String buyerName) {
        this.buyerNameProperty.set(buyerName);
    }

    public double getSoldPrice() {
        return soldPriceProperty.get();
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPriceProperty.set(soldPrice);
    }

    public String getOrderId() {
        return itemID.toString();
    }

}