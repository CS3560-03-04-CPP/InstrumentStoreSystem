package music.system;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.InventoryAnalytics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InventoryAnalyticsScene {

    @SuppressWarnings("unchecked")
    public static void displayInventoryAnalytics() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inventory Analytics");
    
        // Label for displaying the generation time
        Label generationLabel = new Label("Generated at: " + getCurrentTime());
    
        TableView<InventoryAnalytics> tableView = new TableView<>();
    
        // Define TableColumn instances
        TableColumn<InventoryAnalytics, Integer> stockCountColumn = new TableColumn<>("Item Stock Count");
        stockCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getItemStockCount()));
        stockCountColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> salesRevenueColumn = new TableColumn<>("Sales Revenue");
        salesRevenueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalesRevenue()));
        salesRevenueColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Integer> repairsCountColumn = new TableColumn<>("Repairs Performed Count");
        repairsCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRepairsPerformedCount()));
        repairsCountColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> averageAgeColumn = new TableColumn<>("Average Age of Inventory");
        averageAgeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAverageAgeOfInventory()));
        averageAgeColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> totalValueColumn = new TableColumn<>("Total Inventory Value");
        totalValueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTotalInventoryValue()));
        totalValueColumn.setPrefWidth(150);
    
        // Add TableColumn instances to TableView
        tableView.getColumns().addAll(stockCountColumn, salesRevenueColumn, repairsCountColumn, averageAgeColumn, totalValueColumn);
    
        // Create PieChart for stock count vs. sales revenue
        PieChart stockVsSalesPieChart = createStockVsSalesPieChart(100, 2000); // Example values for demonstration
    
        // Create PieChart for age vs. total value
        PieChart ageVsValuePieChart = createAgeVsValuePieChart(2.5, 5000); // Example values for demonstration
    
        VBox vbox = new VBox();
        vbox.getChildren().addAll(generationLabel, tableView, stockVsSalesPieChart, ageVsValuePieChart);
    
        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    
        // Perform analytics and save to database
        InventoryAnalytics newAnalytics = (InventoryAnalytics) InventoryAnalytics.performAnalyitics();
        newAnalytics.saveToDatabase();
    
        // Populate TableView with the new entry
        tableView.getItems().add(newAnalytics);
    }
    
    @SuppressWarnings("unchecked")
    public static void displayInventoryAnalytics(@SuppressWarnings("exports") InventoryAnalytics selectedAnalytics) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Inventory Analytics");
    
        // Label for displaying the generation time
        Label generationLabel = new Label("Generated at: " + getCurrentTime());
    
        TableView<InventoryAnalytics> tableView = new TableView<>();
    
        // Define TableColumn instances
        TableColumn<InventoryAnalytics, Integer> stockCountColumn = new TableColumn<>("Item Stock Count");
        stockCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(selectedAnalytics.getItemStockCount()));
        stockCountColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> salesRevenueColumn = new TableColumn<>("Sales Revenue");
        salesRevenueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(selectedAnalytics.getSalesRevenue()));
        salesRevenueColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Integer> repairsCountColumn = new TableColumn<>("Repairs Performed Count");
        repairsCountColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(selectedAnalytics.getRepairsPerformedCount()));
        repairsCountColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> averageAgeColumn = new TableColumn<>("Average Age of Inventory");
        averageAgeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(selectedAnalytics.getAverageAgeOfInventory()));
        averageAgeColumn.setPrefWidth(150);
    
        TableColumn<InventoryAnalytics, Double> totalValueColumn = new TableColumn<>("Total Inventory Value");
        totalValueColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(selectedAnalytics.getTotalInventoryValue()));
        totalValueColumn.setPrefWidth(150);
    
        // Add TableColumn instances to TableView
        tableView.getColumns().addAll(stockCountColumn, salesRevenueColumn, repairsCountColumn, averageAgeColumn, totalValueColumn);
    
        // Create PieChart for stock count vs. sales revenue
        PieChart stockVsSalesPieChart = createStockVsSalesPieChart(selectedAnalytics.getItemStockCount(), selectedAnalytics.getSalesRevenue());
    
        // Create PieChart for age vs. total value
        PieChart ageVsValuePieChart = createAgeVsValuePieChart(selectedAnalytics.getAverageAgeOfInventory(), selectedAnalytics.getTotalInventoryValue());
    
        VBox vbox = new VBox();
        vbox.getChildren().addAll(generationLabel, tableView, stockVsSalesPieChart, ageVsValuePieChart);
        tableView.getItems().add(selectedAnalytics);
        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static PieChart createStockVsSalesPieChart(int stockCount, double salesRevenue) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Stock Count", stockCount),
                new PieChart.Data("Sales Revenue", salesRevenue)
        );
    
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Stock Count vs. Sales Revenue");
        return pieChart;
    }
    
    private static PieChart createAgeVsValuePieChart(double averageAge, double totalValue) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Average Age", averageAge),
                new PieChart.Data("Total Value", totalValue)
        );
    
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Average Age vs. Total Value");
        return pieChart;
    }

    /*private static void populateInventoryAnalytics(TableView<InventoryAnalytics> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
    
            // Define SQL query to select the latest record from the database
            String query = "SELECT * FROM inventory_analytics ORDER BY time DESC LIMIT 1";
    
            // Create a Statement
            Statement statement = connection.createStatement();
    
            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);
    
            // Populate TableView with the latest result
            ObservableList<InventoryAnalytics> analyticsList = FXCollections.observableArrayList();
            if (resultSet.next()) {
                // Construct InventoryAnalytics object and add it to the list
                InventoryAnalytics analytics = new InventoryAnalytics(
                        resultSet.getDate("time"),
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
    
            // Set fixed cell size to prevent extra empty rows
            tableView.setFixedCellSize(25);
    
            // Set placeholder to null to prevent the empty row
            tableView.setPlaceholder(null);

            // Set preferred height to fit only one row
            tableView.setPrefHeight(tableView.getFixedCellSize() + 2);
    
            // Close resources
            System.out.println("Connection closed to the database: InventoryAnalytics Scene");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    // Method to filter records based on time
    @SuppressWarnings("unused")
    private static void filterRecords(TableView<InventoryAnalytics> tableView, String time) {
        // Implement filtering logic here
    }

    // Method to get current time in a formatted string
    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}