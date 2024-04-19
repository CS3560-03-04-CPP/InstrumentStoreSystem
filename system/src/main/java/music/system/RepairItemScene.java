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

public class RepairItemScene {
    
    @SuppressWarnings("unchecked")
    public static void displayRepairs() {
       Stage primaryStage = new Stage();
       primaryStage.setTitle("Current Repairs");

       TableView<RepairItem> tableView = new TableView<>();

       TableColumn<RepairItem, String> nameColumn = new TableColumn<>("Name");
       nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
       nameColumn.setPrefWidth(200);
        
       TableColumn<RepairItem, String> descriptionColumn = new TableColumn<>("Description");
       descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
       descriptionColumn.setPrefWidth(350);
       
       TableColumn<RepairItem, Double> fixPriceColumn = new TableColumn<>("Fix Price");
       fixPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
       
       tableView.getColumns().addAll(nameColumn, descriptionColumn, fixPriceColumn);
       
       // TextField for search bar
       TextField searchField = new TextField();
       searchField.setPromptText("Search by Name");
       
       // Add listener to react to user input in the search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterRecords(tableView, newValue);
        });
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchField, tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populateRepairItems(tableView);
        
    }
    
    private static void populateRepairItems(TableView<RepairItem> tableView) {
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
    
    // Method to filter records based on phone number
    private static void filterRecords(TableView<RepairItem> tableView, String name) {
        ObservableList<RepairItem> filteredData = FXCollections.observableArrayList();
        for (RepairItem record : tableView.getItems()) {
            if (record.getName().contains(name)) {
                filteredData.add(record);
            }
        }
        tableView.setItems(filteredData);
    }
}
