package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {

    // Declaring Variables
    private int itemID;

    private String name;
    private String category;
    private String brand;
    private String dateManufactured;

    private int serialNumber;
    private double manufacturerPrice;
    private double retailPrice;

    private String description;

    // Constructor
    public Item(int itemID, int serialNumber, String name, String category, String brand, String dateManufactured,
            String description, double manufacturerPrice, double retailPrice) {
        this.itemID = itemID;
        this.serialNumber = serialNumber;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.dateManufactured = dateManufactured;
        this.description = description;
        this.manufacturerPrice = manufacturerPrice;
        this.retailPrice = retailPrice;

    }

        // Method to save inventory analytics data to the database
        public void saveToDatabase(double itemStockCount, double salesRevenue, int repairsPerformedCount,
        double averageAgeOfInventory, double totalInventoryValue, int currentUserID) {
            try {
                // Establish connection to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
    
                // Define SQL query to insert item data into the item table
                String query = "INSERT INTO item (itemID, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
                // Create PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, this.itemID);
                preparedStatement.setString(2, this.name);
                preparedStatement.setString(3, this.category);
                preparedStatement.setString(4, this.brand);
                preparedStatement.setString(5, this.dateManufactured);
                preparedStatement.setInt(6, this.serialNumber);
                preparedStatement.setDouble(7, this.manufacturerPrice);
                preparedStatement.setDouble(8, this.retailPrice);
                preparedStatement.setString(9, this.description);
    
                // Execute the insert query
                preparedStatement.executeUpdate();
    
                // Close resources
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // Getters and Setters
    public int getitemID() {
        return itemID;
    }

    public void setitemID(int id) {
        this.itemID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getdateManufactured() {
        return dateManufactured;
    }

    public void setdateManufactured(String dateManufactured2) {
        this.dateManufactured = dateManufactured2;
    }

    public int getserialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serial) {
        this.serialNumber = serial;
    }

    public double getmanufacturerPrice() {
        return manufacturerPrice;
    }

    public void setmanufacturerPrice(double manufacturerPrice) {
        this.manufacturerPrice = manufacturerPrice;
    }

    public double getretailPrice() {
        return retailPrice;
    }

    public void setretailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}