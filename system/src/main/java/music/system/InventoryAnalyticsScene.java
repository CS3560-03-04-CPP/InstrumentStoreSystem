package music.system;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    // Method to get current time in a formatted string
    private static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}