package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleRecord {

    // Attributes
    private StringProperty orderIdProperty;             // Unique order ID for the sale
    private ObjectProperty<Date> dateProperty;          // Date of the sale
    private StringProperty buyerNameProperty;           // Name of the buyer
    private StringProperty buyerPhoneNumberProperty;    // Phone number of the buyer
    private DoubleProperty soldPriceProperty;           // Price at which the item was sold

    // Constructor
    public SaleRecord(String orderId, Date date, String buyerName, String buyerPhoneNumber, double soldPrice) {
        this.orderIdProperty = new SimpleStringProperty(orderId);
        this.dateProperty = new SimpleObjectProperty<>(date);
        this.buyerNameProperty = new SimpleStringProperty(buyerName);
        this.buyerPhoneNumberProperty = new SimpleStringProperty(buyerPhoneNumber);
        this.soldPriceProperty = new SimpleDoubleProperty(soldPrice);
    }

     // Save to MySQL method
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to insert sale record data into the database
            String query = "INSERT INTO sale_records (order_id, date, buyer_name, buyer_phone_number, sold_price) VALUES (?, ?, ?, ?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, orderIdProperty.get());
            preparedStatement.setDate(2, new java.sql.Date(dateProperty.get().getTime()));
            preparedStatement.setString(3, buyerNameProperty.get());
            preparedStatement.setString(4, buyerPhoneNumberProperty.get());
            preparedStatement.setDouble(5, soldPriceProperty.get());

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

    // Getter and Setter methods 
    public String getOrderId() {
        return orderIdProperty.get();
    }

    public void setOrderId(String orderId) {
        this.orderIdProperty.set(orderId);
    }

    public Date getDate() {
        return dateProperty.get();
    }

    public void setDate(Date date) {
        this.dateProperty.set(date);
    }

    public String getBuyerName() {
        return buyerNameProperty.get();
    }

    public void setBuyerName(String buyerName) {
        this.buyerNameProperty.set(buyerName);
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumberProperty.get();
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumberProperty.set(buyerPhoneNumber);
    }

    public double getSoldPrice() {
        return soldPriceProperty.get();
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPriceProperty.set(soldPrice);
    }

    // Override toString method to provide a string representation of the SaleRecord object
    @Override
    public String toString() {
        return "SaleRecord{" +
                "orderId='" + getOrderId() + '\'' +
                ", date=" + getDate() +
                ", buyerName='" + getBuyerName() + '\'' +
                ", buyerPhoneNumber='" + getBuyerPhoneNumber() + '\'' +
                ", soldPrice=" + getSoldPrice() +
                '}';
    }
}