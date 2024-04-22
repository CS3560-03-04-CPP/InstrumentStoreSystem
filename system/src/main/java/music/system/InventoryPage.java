package music.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * InventoryPage: This class represents the inventory Stage of the music system application.
 */
public class InventoryPage {

    public void handle_SignOut() throws IOException{App.setRoot("SignInPage");}
    public void handle_Close(){System.exit(0);}

    /*
     * Please complete your assigned classes in the files you created, this is
     * where those methods will be called when a button is pressed in the GUI. 
     */
    public void handle_ViewInstrument(){InventoryManagementScene.displayInstrumentManagement();}

    public void handle_ViewArchive(){ArchiveScene.displayArchive();}

    public void handle_NewSaleTransaction(){}
    public void handle_SaleRecords(){SaleRecordScene.displaySaleRecords();}
    
    public void handle_NewStoreTransaction(){}
    public void handle_StoreRecords(){StoreRecordScene.displayStoreRecords();}

    public void handle_GenerateAnalytics(){InventoryAnalyticsScene.displayInventoryAnalytics();}
    public void handle_ViewPastAnalytics(){ViewPastAnalyticsScene.displayPastAnalytics();}

    public void handle_RepairStatus(){RepairItemScene.displayRepairs();}
    public void handle_ScheduleRepair(){}
    
    /*public VBox createInventoryPage() {
        TableView<InventoryRecord> tableView = new TableView<>();

        TableColumn<InventoryRecord, String> itemIdColumn = new TableColumn<>("Item ID");
        itemIdColumn.setCellValueFactory(cellData -> cellData.getValue().itemIdProperty());

        TableColumn<InventoryRecord, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());

        // Define other columns as needed

        tableView.getColumns().addAll(itemIdColumn, itemNameColumn);

        // TextField for search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Item Name");

        // Add listener to react to user input in the search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterRecords(tableView, newValue);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchField, tableView);

        // Populate TableView
        populateInventoryRecords(tableView);

        return vbox;
    }

    private void populateInventoryRecords(TableView<InventoryRecord> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/YourDatabase", "username", "password");

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM inventory_records";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<InventoryRecord> inventoryRecords = FXCollections.observableArrayList();
            while (resultSet.next()) {
                InventoryRecord inventoryRecord = new InventoryRecord(
                        resultSet.getString("item_id"),
                        resultSet.getString("item_name")
                        // Populate other fields as needed
                );
                inventoryRecords.add(inventoryRecord);
            }

            tableView.setItems(inventoryRecords);

            // Close resources
            System.out.println("Connection closed to the database: Inventory Page");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to filter records based on item name
    private void filterRecords(TableView<InventoryRecord> tableView, String itemName) {
        ObservableList<InventoryRecord> filteredData = FXCollections.observableArrayList();
        for (InventoryRecord record : tableView.getItems()) {
            if (record.getItemName().toLowerCase().contains(itemName.toLowerCase())) {
                filteredData.add(record);
            }
        }
        tableView.setItems(filteredData);
    }*/
}
