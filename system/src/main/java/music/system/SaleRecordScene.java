package music.system;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.SaleRecord;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SaleRecordScene {

    @SuppressWarnings("unchecked")
    public static void displaySaleRecords() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sale Records");

        TableView<SaleRecord> tableView = new TableView<>();

        TableColumn<SaleRecord, String> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId()));

        TableColumn<SaleRecord, String> DateColumn = new TableColumn<>("Date");
        DateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<SaleRecord, String> buyerNameColumn = new TableColumn<>("Buyer Name");
        buyerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBuyerName()));

        TableColumn<SaleRecord, Double> soldPriceColumn = new TableColumn<>("Sold Price");
        soldPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSoldPrice()).asObject());

        TableColumn<SaleRecord, String> itemIDColumn = new TableColumn<>("Item's ID");
        itemIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId().toString()));

        tableView.getColumns().addAll(orderIdColumn, DateColumn, buyerNameColumn, soldPriceColumn);

        // TextField for search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Phone Number");

        // Add listener to react to user input in the search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterRecords(tableView, newValue);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchField, tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populateSaleRecords(tableView);
    }

    private static void populateSaleRecords(TableView<SaleRecord> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM sale_records";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<SaleRecord> saleRecords = FXCollections.observableArrayList();
            while (resultSet.next()) {
                SaleRecord saleRecord = new SaleRecord(
                        resultSet.getInt("id"),
                        resultSet.getString("date"),
                        resultSet.getString("buyer_name"),
                        resultSet.getDouble("sold_price"),
                        resultSet.getInt("itemID")
                );
                saleRecords.add(saleRecord);
            }

            tableView.setItems(saleRecords);

            // Close resources
            System.out.println("Connection closed to the database: Record Scene");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to filter records based on phone number
    private static void filterRecords(TableView<SaleRecord> tableView, String name) {
        ObservableList<SaleRecord> filteredData = FXCollections.observableArrayList();
        for (SaleRecord record : tableView.getItems()) {
            if (record.getBuyerName().contains(name)) {
                filteredData.add(record);
            }
        }
        tableView.setItems(filteredData);
    }
}