package music.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.Item;

/**
 * JavaFX Scene, ArchiveScene: This scene opens the archive form.
 * 
 * Functionality: Opens a javaFX window that shows a query result for all archived item.
 * 
 */
public class ArchiveScene {

    @SuppressWarnings("unchecked")
    public static void displayArchive() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Archive");

        TableView<Item> tableView = new TableView<>();

        TableColumn<Item, Integer> itemIdColumn = new TableColumn<>("Item ID");
        itemIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getitemID()).asObject());

        TableColumn<Item, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Item, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));

        TableColumn<Item, String> dateManufacturedColumn = new TableColumn<>("Date Manufactured");
        dateManufacturedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdateManufactured()));
        
        TableColumn<Item, Integer> serialNumberColumn = new TableColumn<>("Serial Number");
        serialNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getserialNumber()).asObject());

        TableColumn<Item, Double> manufacturerPriceColumn = new TableColumn<>("Manufacturer Price");
        manufacturerPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getmanufacturerPrice()).asObject());

        TableColumn<Item, Double> retailPriceColumn = new TableColumn<>("Retail Price");
        retailPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getretailPrice()).asObject());

        TableColumn<Item, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        tableView.getColumns().addAll(itemIdColumn, itemNameColumn, categoryColumn, brandColumn,
                dateManufacturedColumn, serialNumberColumn, manufacturerPriceColumn, retailPriceColumn, descriptionColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populateArchiveItems(tableView);
    }

    private static void populateArchiveItems(TableView<Item> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();
    
            // Define SQL query to select all records from the database
            String query = "SELECT * FROM archive";
    
            // Create a Statement
            Statement statement = connection.createStatement();
    
            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);
    
            // Populate TableView with the results
            ObservableList<Item> archiveItems = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Item archiveItem = new Item(
                    resultSet.getInt("itemID"),
                    resultSet.getInt("serialNumber"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getString("brand"),
                    resultSet.getString("dateManufactured"),
                    resultSet.getString("description"),
                    resultSet.getDouble("manufacturerPrice"),
                    resultSet.getDouble("retailPrice")
                );
                archiveItems.add(archiveItem);
            }
    
            // Set the items in TableView
            tableView.setItems(archiveItems);
    
            // Close resources
            resultSet.close();
            statement.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}