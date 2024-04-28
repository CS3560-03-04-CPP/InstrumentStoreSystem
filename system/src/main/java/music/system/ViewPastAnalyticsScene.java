package music.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.InventoryAnalytics;

public class ViewPastAnalyticsScene {

    @SuppressWarnings("unchecked")
    public static void displayPastAnalytics() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inventory Analytics");

        TableView<InventoryAnalytics> tableView = new TableView<>();

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Define TableColumn instances
        TableColumn<InventoryAnalytics, Date> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTime()));

        TableColumn<InventoryAnalytics, Integer> stockCountColumn = new TableColumn<>("Item Stock Count");
        stockCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getItemStockCount()));

        TableColumn<InventoryAnalytics, Double> salesRevenueColumn = new TableColumn<>("Sales Revenue");
        salesRevenueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalesRevenue()));

        TableColumn<InventoryAnalytics, Integer> repairsCountColumn = new TableColumn<>("Repairs Performed Count");
        repairsCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRepairsPerformedCount()));

        TableColumn<InventoryAnalytics, Double> averageAgeColumn = new TableColumn<>("Average Age of Inventory");
        averageAgeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAverageAgeOfInventory()));

        TableColumn<InventoryAnalytics, Double> totalValueColumn = new TableColumn<>("Total Inventory Value");
        totalValueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalInventoryValue()));

        // Add TableColumn instances to TableView
        tableView.getColumns().addAll(timeColumn, stockCountColumn, salesRevenueColumn, repairsCountColumn, averageAgeColumn, totalValueColumn);


        TableColumn<InventoryAnalytics, Void> deleteButtonColumn = new TableColumn<>("Delete");
        deleteButtonColumn.setCellFactory(param -> new TableCell<InventoryAnalytics, Void>() {
            private final Button deleteButton = new Button("Delete");
            
            {
                deleteButton.setOnAction(event -> {
                    InventoryAnalytics selectedAnalytics = getTableView().getItems().get(getIndex());
                    if (selectedAnalytics != null) {
                        deleteAnalytics(selectedAnalytics);
                        getTableView().getItems().remove(selectedAnalytics);
                    } else {
                        System.out.println("No item selected.");
                    }
                });
            }
        
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        
        
        Button openButton = new Button("Open");
        openButton.setOnAction(event -> {
            InventoryAnalytics selectedAnalytics = tableView.getSelectionModel().getSelectedItem();
            if (selectedAnalytics != null) {
                InventoryAnalyticsScene.displayInventoryAnalytics(selectedAnalytics);
            } else {
                System.out.println("No item selected.");
            }
        });

        tableView.getColumns().add(deleteButtonColumn);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView, openButton);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        populatePastAnalytics(tableView);
    }

    private static void populatePastAnalytics(TableView<InventoryAnalytics> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to select all records from the database
            String query = "SELECT * FROM inventory_analytics";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<InventoryAnalytics> analyticsList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                // Construct InventoryAnalytics object and add it to the list
                InventoryAnalytics analytics = new InventoryAnalytics(
                        resultSet.getTimestamp("time"),
                        resultSet.getInt("item_stock_count"),
                        resultSet.getDouble("sales_revenue"),
                        resultSet.getInt("repairs_performed_count"),
                        resultSet.getDouble("average_age_of_inventory"),
                        resultSet.getDouble("total_inventory_value")
                );
                analyticsList.add(analytics);
            }

            // Set the items in TableView
            tableView.setItems(analyticsList);

            // Close resources
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteAnalytics(InventoryAnalytics selectedAnalytics) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();
    
            // Define SQL query to delete the selected record from the database
            String query = "DELETE FROM inventory_analytics WHERE time = ?";
    
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            // Convert Java Date to SQL Timestamp
            Timestamp timestamp = new Timestamp(selectedAnalytics.getTime().getTime());
            
            // Set parameters for the PreparedStatement
            preparedStatement.setTimestamp(1, timestamp);
    
            // Execute the deletion
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Failed to delete item.");
            }
    
            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}