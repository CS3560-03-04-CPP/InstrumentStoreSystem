package music.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.StoreRecord;

public class StoreRecordScene {

    @SuppressWarnings("unchecked")
    public static void displayStoreRecords() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Store Records");

        TableView<StoreRecord> tableView = new TableView<>();

        TableColumn<StoreRecord, String> invoiceNumberColumn = new TableColumn<>("Invoice Number");
        invoiceNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInvoiceNumber()));

        TableColumn<StoreRecord, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> {
            SimpleObjectProperty<Date> property = new SimpleObjectProperty<>();
            property.setValue(new java.sql.Date(cellData.getValue().getDate().getTime()));
            return property;
        });

        tableView.getColumns().addAll(invoiceNumberColumn, dateColumn);

        // TextField for search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Invoice Number");

        // Add listener to react to user input in the search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterRecords(tableView, newValue);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchField, tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populateStoreRecords(tableView);
    }

    private static void populateStoreRecords(TableView<StoreRecord> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM store_records";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<StoreRecord> storeRecords = FXCollections.observableArrayList();
            while (resultSet.next()) {
                StoreRecord storeRecord = new StoreRecord(
                        resultSet.getString("invoice_number"),
                        resultSet.getDate("date")
                        
                );
                storeRecords.add(storeRecord);
            }

            // Set the items in TableView
            tableView.setItems(storeRecords);

            // Close resources
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to filter records based on invoice number
    private static void filterRecords(TableView<StoreRecord> tableView, String invoiceNumber) {
        ObservableList<StoreRecord> filteredData = FXCollections.observableArrayList();
        for (StoreRecord record : tableView.getItems()) {
            if (record.getInvoiceNumber().contains(invoiceNumber)) {
                filteredData.add(record);
            }
        }
        tableView.setItems(filteredData);
    }
}
