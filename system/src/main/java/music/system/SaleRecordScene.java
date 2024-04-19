package music.system;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
    
        TableColumn<SaleRecord, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> {
            SimpleObjectProperty<Date> property = new SimpleObjectProperty<>();
            property.setValue(new java.sql.Date(cellData.getValue().getDate().getTime()));
            return property;
        });
    
        TableColumn<SaleRecord, String> buyerNameColumn = new TableColumn<>("Buyer Name");
        buyerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBuyerName()));
    
        TableColumn<SaleRecord, String> buyerPhoneNumberColumn = new TableColumn<>("Buyer Phone Number");
        buyerPhoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBuyerPhoneNumber()));
    
        TableColumn<SaleRecord, Double> soldPriceColumn = new TableColumn<>("Sold Price");
        soldPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSoldPrice()).asObject());
    
        tableView.getColumns().addAll(orderIdColumn, dateColumn, buyerNameColumn, buyerPhoneNumberColumn, soldPriceColumn);

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
            while (resultSet.next()) {
                SaleRecord saleRecord = new SaleRecord(
                        resultSet.getString("order_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("buyer_name"),
                        resultSet.getString("buyer_phone_number"),
                        resultSet.getDouble("sold_price")
                );
                tableView.getItems().add(saleRecord);
            }

            // Close resources
            System.out.println("Connection closed to the database: Record Scene");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
