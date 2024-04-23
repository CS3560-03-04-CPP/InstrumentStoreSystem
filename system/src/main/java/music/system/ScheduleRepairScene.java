package music.system;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.RepairItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScheduleRepairScene {
    
    @SuppressWarnings("unchecked")
    public static void displayScheduleRepair() {
       Stage primaryStage = new Stage();
       primaryStage.setTitle("Current Repairs");

       TableView<RepairItem> tableView = new TableView<>();

        primaryStage.show();

        populateSqeduleRepair(tableView);
        
    }
    
    private static void populateSqeduleRepair(TableView<RepairItem> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM repair_items";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);
            
            // Populate TableView with the results
            ObservableList<RepairItem> repairRecords = FXCollections.observableArrayList();
            while (resultSet.next()) {
                RepairItem repairRecord = new RepairItem(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("fixPrice")
                );
                repairRecords.add(repairRecord);
            }
            
            tableView.setItems(repairRecords);

            // Close resources
            System.out.println("Connection closed to the database: RepairItem Scene");
            resultSet.close();
            statement.close();
            connection.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
