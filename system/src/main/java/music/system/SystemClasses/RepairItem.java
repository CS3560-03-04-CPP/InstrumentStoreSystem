/*
Need to create an enumerated type called Status:
    Have it contain in progress and completed

*/
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

public class RepairItem {
    
    //Attributes
    private StringProperty nameProperty;             // Name of RepairItem
    private StringProperty descriptionProperty;      // Description of RepairItem
    private DoubleProperty fixPriceProperty;         // Price at which the item is to be repaired

    private int repairId;
    private String name;
    private String description;
    private double fixPrice;

    //Constructor
    public RepairItem(String name, String description, double fixPrice) {
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
    }
    
    // Save to MySQL method
    public void saveToMySQL() {
        try {
            //Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
            
            //Define SQL query to insert RepairItem data into the database
            String query = "INSERT INTO repair_items (name, description, fixPrice) VALUES (?, ?, ?)";
            
            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nameProperty.get());
            preparedStatement.setString(2, descriptionProperty.get());
            preparedStatement.setDouble(3, fixPriceProperty.get());

            // Execute the insert query
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //generate a RepairId when technician enters item into system
    //return repairId
    private int generateRepairId() {
        return 0;
    }
    
    //get the repairId
    public int getRepairId() {
        return repairId;
    }
    
    //Getters and Setters
    
    //set the name of the repair
    public void setName(String name) {
        this.nameProperty.set(name);
    }

    //get the name of the repair
    public String getName() {
        return nameProperty.get();
    }

    //set the description
    public void setDescription(String description) {
        this.descriptionProperty.set(description);
    }

    //get the description
    public String getDescription() {
        return descriptionProperty.get();
    }

    //set the price of the fix
    public void setPrice(double fixPrice) {
        //check if the price is below zero, if it is then reprompt user
        this.fixPriceProperty.set(fixPrice);
    }

    //get the price
    public double getPrice() {
        return fixPriceProperty.get();
    }

}
