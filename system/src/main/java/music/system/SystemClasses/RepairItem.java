package music.system.SystemClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

/**
 * System Class, RepairItem: This class handles creation of a new repair entry,
 * In order to keep track of this repair a new temp item entry is generated with default attributes
 * and marked as a temporary item with the name of the client.
 * 
 * Database table repair_items, links to item table with a Item_repair_items:
 * 
 *                              FK                                              PK
 *           item_repair_items: itemID                from item table           (itemID)
 *                              repair_items_repairID from repair_items table   (Last created itemID[temp item entry] plus 1)
 * 
 */
public class RepairItem {
    
    private StringProperty nameProperty;            
    private StringProperty statusProperty;           
    private StringProperty descriptionProperty;     
    private DoubleProperty fixPriceProperty;         
    private Date date;                   
    private int repairId;
    private File file;

    //Constructor
    public RepairItem(String status, String name, String description, double fixPrice, Date date) {
        this.statusProperty = new SimpleStringProperty(status); 
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
        this.date = date;
        updateDaysLeft();
    }
    
    public RepairItem(String status, String name, String description, double fixPrice, Date date, File file) {
        this.statusProperty = new SimpleStringProperty(status); 
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
        this.date = date;
        this.file = file;
        updateDaysLeft();
    }
    
    public RepairItem(int id, String status, String name, String description, double fixPrice, Date date, File file) {
        this.repairId = id;
        this.statusProperty = new SimpleStringProperty(status); 
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
        this.date = date;
        this.file = file;
        updateDaysLeft();
    }
    
    public RepairItem(int id, String status, String name, String description, double fixPrice, Date date) {
        this.repairId = id;
        this.statusProperty = new SimpleStringProperty(status); 
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.fixPriceProperty = new SimpleDoubleProperty(fixPrice);
        this.date = date;
        this.file = file;
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
        
        String insertQuery;
        PreparedStatement preparedStatement;
        //Define SQL query to insert RepairItem data into the database
        if (file == null) {
            // Define SQL query to insert RepairItem data into the database
            insertQuery = "INSERT INTO repair_items (repairId, status, name, description, fixPrice, date) VALUES (?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, repairId);
            preparedStatement.setString(2, statusProperty.get());
            preparedStatement.setString(3, nameProperty.get());
            preparedStatement.setString(4, descriptionProperty.get());
            preparedStatement.setDouble(5, fixPriceProperty.get());
            preparedStatement.setDate(6, sqlDate);
        } else {
            // Define SQL query to insert RepairItem data into the database
            insertQuery = "INSERT INTO repair_items (repairId, status, name, description, fixPrice, date, file) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, repairId);
            preparedStatement.setString(2, statusProperty.get());
            preparedStatement.setString(3, nameProperty.get());
            preparedStatement.setString(4, descriptionProperty.get());
            preparedStatement.setDouble(5, fixPriceProperty.get());
            preparedStatement.setDate(6, sqlDate);
            byte[] fileData = readFileToByteArray(file);
            preparedStatement.setBytes(7, fileData);
        }
        

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
    public String getDaysLeft() {return statusProperty.get();}

    public Date getDate() {return date;}

    public String setStatus(String statusString) {statusProperty.set(statusString); return statusString;}

    public int getRepairId() {return repairId;}

    public void setName(String name) {this.nameProperty.set(name);}

    public String getName() {return nameProperty.get();}

    public void setDescription(String description) {this.descriptionProperty.set(description);}

    public String getDescription() {return descriptionProperty.get();}

    public void setPrice(double fixPrice) {this.fixPriceProperty.set(fixPrice);}

    public double getPrice() {return fixPriceProperty.get();}

    public String getStatus() {return statusProperty.get();}
    
    public File getFile() {return file;}
    
    private byte[] readFileToByteArray(File file) {
        byte[] fileData = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            fileData = new byte[(int) file.length()];
            inputStream.read(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileData;
    }

}
