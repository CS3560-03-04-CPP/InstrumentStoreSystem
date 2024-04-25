package music.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.SaleTransaction;

public class SaleTransactionScene {

    @SuppressWarnings("unchecked")
    public static void displaySaleTransactions() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sale Transactions");

        TableView<SaleTransaction> tableView = new TableView<>();

        TableColumn<SaleTransaction, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<SaleTransaction, Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        TableColumn<SaleTransaction, String> customerNameColumn = new TableColumn<>("Customer Name");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        tableView.getColumns().addAll(itemNameColumn, itemPriceColumn, customerNameColumn);

        // TextField for search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search by Item Name");

        // Add listener to react to user input in the search bar
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterRecords(tableView, newValue);
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(searchField, tableView);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populateSaleTransactions(tableView);
    }

    private static void populateSaleTransactions(TableView<SaleTransaction> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instrument_store_system", "username", "password");

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM sale_records";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<SaleTransaction> saleTransactions = FXCollections.observableArrayList();
            while (resultSet.next()) {
                SaleTransaction saleTransaction = new SaleTransaction(
                        resultSet.getString("itemName"),
                        resultSet.getDouble("itemPrice"),
                        resultSet.getString("customerName")
                );
                saleTransactions.add(saleTransaction);
            }

            tableView.setItems(saleTransactions);

            // Close resources
            System.out.println("Connection closed to the database: SaleTransactionScene");
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to filter records
    private static void filterRecords(TableView<SaleTransaction> tableView, String itemName) {
        ObservableList<SaleTransaction> filteredData = FXCollections.observableArrayList();
        for (SaleTransaction transaction : tableView.getItems()) {
            if (transaction.getBuyerName().contains(itemName)) {
                filteredData.add(transaction);
            }
        }
        tableView.setItems(filteredData);
    }
}
