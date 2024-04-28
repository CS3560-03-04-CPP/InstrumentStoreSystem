package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import music.system.DatabaseManager;

public class RepairItem {
    
    //Attributes
    private StringProperty nameProperty;             // Name of RepairItem
    private StringProperty statusProperty;           // Status feild
    private StringProperty descriptionProperty;      // Description of RepairItem
    private DoubleProperty fixPriceProperty;         // Price at which the item is to be repaired
    private Date date;                               // Date when the repair was initiated
    private int repairId;

    //Constructor
    public RepairItem(String status, String name, String description, double fixPrice, Date date) {
        this.statusProperty = new SimpleStringProperty(status); 
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
        this.date = date;
        updateDaysLeft();
    }
    
    // Save to MySQL method
    public void saveToMySQL() {
    try {
        // Establish connection to MySQL database
        Connection connection = DatabaseManager.getConnection();

        // Define SQL query to fetch the last primary key value
        String getLastIdQuery = "SELECT MAX(itemID) FROM item";

        // Create a Statement
        Statement statement = connection.createStatement();

        // Execute the query to fetch the last primary key value
        ResultSet resultSet = statement.executeQuery(getLastIdQuery);

        int lastIdItem = 0;
        if (resultSet.next()) {
            lastIdItem = resultSet.getInt(1);
        }
        // Increment the last primary key value by one
        int itemId = lastIdItem + 1;

        // Get the current date
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        
        // Convert the date into a SQL DATE object
        Date sqlDate = new Date(currentDate.getTime());

        Item tempItemForRepair = new Item(itemId, 0, nameProperty.get().toString(), "Repair", "Unknown", sqlDate.toString(), "Temporary repair item", 0.0, 0.0);
        tempItemForRepair.saveToDatabase(1.0, (double) fixPriceProperty.get(), 1, 0.0, 0.0, 0);
        
        // Define SQL query to fetch the last primary key value
        getLastIdQuery = "SELECT MAX(repairId) FROM repair_items";

        // Create a Statement
        statement = connection.createStatement();

        // Execute the query to fetch the last primary key value
        resultSet = statement.executeQuery(getLastIdQuery);

        int lastId = 0;
        if (resultSet.next()) {
            lastId = resultSet.getInt(1);
        }

        // Increment the last primary key value by one
        repairId = lastId + 1;
        
        // Define SQL query to insert RepairItem data into the database
        String insertQuery = "INSERT INTO repair_items (repairId, status, name, description, fixPrice, date) VALUES (?, ?, ?, ?, ?, ?)";

        // Create PreparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, repairId);
        preparedStatement.setString(2, statusProperty.get());
        preparedStatement.setString(3, nameProperty.get());
        preparedStatement.setString(4, descriptionProperty.get());
        preparedStatement.setDouble(5, fixPriceProperty.get());
        preparedStatement.setDate(6, sqlDate);

        // Execute the insert query
        preparedStatement.executeUpdate();

        // Insert into item_repair_items table
        String insertItemRepairQuery = "INSERT INTO item_repair_items (itemID, repair_items_repairId) VALUES (?, ?)";
        PreparedStatement itemRepairStatement = connection.prepareStatement(insertItemRepairQuery);
        itemRepairStatement.setInt(1, itemId);
        itemRepairStatement.setInt(2, repairId);

        // Execute the insert query
        itemRepairStatement.executeUpdate();
        

        // Close resources
        resultSet.close();
        statement.close();
        preparedStatement.close();
        itemRepairStatement.close();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Method to calculate days left
    public void updateDaysLeft() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.add(Calendar.DAY_OF_MONTH, 3);

        Calendar now = Calendar.getInstance();
        long millisLeft = currentDate.getTimeInMillis() - now.getTimeInMillis();
        int daysLeft = (int) (millisLeft / (1000 * 60 * 60 * 24));

        if (daysLeft <= 0) {
            statusProperty.set("Expired");
        } else {
            statusProperty.set(daysLeft + " days left");
        }
    }
    
    //Getters and Setters
        
    //get days left
    public String getDaysLeft() {
        return statusProperty.get();
    }

    //get days left
    public Date getDate() {
        return date;
    }

    //set new Status
    public String setStatus(String statusString) {
        statusProperty.set(statusString);
        return statusString;
    }

    //get the repairId
    public int getRepairId() {
        return repairId;
    }

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

    public String getStatus() {
        return statusProperty.get();
    }

}
