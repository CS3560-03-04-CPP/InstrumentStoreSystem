package music.system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import music.system.SystemClasses.Employee;
import music.system.SystemClasses.Item;
import music.system.SystemClasses.SaleRecord;
import music.system.SystemClasses.StoreRecord;


<<<<<<< HEAD
/**
 * Application Class, InventoryPage: This class represents the inventory root Stage of the music system application.
 * 
 * The main user GUI interaface which connects all the buttons a user can interact with to their respective classes.
 * 
 */
public class InventoryPage {
    @FXML
    private Text handle_user_greet;
    @FXML
    private Text username;
    @FXML
    private Text position;
    @FXML
    private TableView<Item> customerTransactionsTableView;
    @FXML
    private TableView<StoreRecord> recordsTableView1;
    @FXML
    private TableView<SaleRecord> recordsTableView2;
    @FXML
    private TextField storeRecordsSearchField;
    @FXML
    private TextField saleRecordsSearchField;
    @FXML
    private TextField inventorySearchField;
    
    @FXML
    public void initialize() {
        handle_user_greet.setText(Employee.getName());
        username.setText("Username: " + SignInPage.userName);
        position.setText("Position: " + Employee.position);

        itemTable();
        storeRecordsTable();
        saleRecordsTable();


    }
=======
public class InventoryPage extends Application {
>>>>>>> 4f93f740da24fb163dd53e2f812c5bd189feb614

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // Load the image
        Image image = new Image(getClass().getResourceAsStream("user.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set smaller image width
        imageView.setPreserveRatio(true); // Maintain aspect ratio

        // Create a label for the welcome message
        Label welcomeLabel = new Label("Welcome, User");
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Create buttons for methods
        Button signOutButton = new Button("Sign Out");
        Button closeButton = new Button("Close");

<<<<<<< HEAD
    public void handle_GenerateAnalytics(){

        System.out.println(Employee.position);

        if (Employee.position.equals("manager"))
            InventoryAnalyticsScene.displayInventoryAnalytics();
        else    
            showAlert("Access Denied", "Please sign in with a managers account");

    }
    public void handle_ViewPastAnalytics(){

        if (Employee.position.equals("manager"))
            ViewPastAnalyticsScene.displayPastAnalytics();
        else
            showAlert("Access Denied", "Please sign in with a managers account");

    }


    @SuppressWarnings("unchecked")
    private void itemTable(){
        
        TableColumn<Item, Integer> itemIDColumn = new TableColumn<>("Item ID");
        itemIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getitemID()).asObject());

        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

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

        customerTransactionsTableView.getColumns().addAll(itemIDColumn, nameColumn, categoryColumn, brandColumn, dateManufacturedColumn, serialNumberColumn, manufacturerPriceColumn, retailPriceColumn, descriptionColumn);
        populateCustomerTransactionsTableView(customerTransactionsTableView);
    }

    @FXML
    private void handle_storeRecords_search() {
        filterRecords1(recordsTableView1, storeRecordsSearchField.getText());
    }

    @FXML
    private void handle_saleRecords_search() {
        filterRecords2(recordsTableView2, saleRecordsSearchField.getText());
    }

    @FXML
    private void handle_inventory_search() {
        filterRecords3(customerTransactionsTableView, inventorySearchField.getText());
    }

    @SuppressWarnings("unchecked")
    private void storeRecordsTable() {
        TableColumn<StoreRecord, String> invoiceNumberColumn = new TableColumn<>("Invoice Number");
        invoiceNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInvoiceNumber()));

        TableColumn<StoreRecord, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> {
            SimpleObjectProperty<Date> property = new SimpleObjectProperty<>();
            property.setValue(new java.sql.Date(cellData.getValue().getDate().getTime()));
            return property;
        });

        recordsTableView1.getColumns().addAll(invoiceNumberColumn, dateColumn);
        populateStoreRecordsTableView(recordsTableView1);
    }

    @SuppressWarnings("unchecked")
    private void saleRecordsTable() {
        TableColumn<SaleRecord, String> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId().toString()));

        TableColumn<SaleRecord, String> DateColumn = new TableColumn<>("Date");
        DateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<SaleRecord, String> buyerNameColumn = new TableColumn<>("Buyer Name");
        buyerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBuyerName()));

        TableColumn<SaleRecord, Double> soldPriceColumn = new TableColumn<>("Sold Price");
        soldPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSoldPrice()).asObject());

        TableColumn<SaleRecord, String> itemIDColumn = new TableColumn<>("Item's ID");
        itemIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId().toString()));

        recordsTableView2.getColumns().addAll(orderIdColumn, DateColumn, buyerNameColumn, soldPriceColumn);
        populateSaleRecordsTableView(recordsTableView2);
    }

    // Method to populate Store Records TableView
    private static void populateStoreRecordsTableView(TableView<StoreRecord> tableView) {
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
=======
        // Apply drop shadow effect to buttons
        DropShadow shadow = new DropShadow();
        signOutButton.setEffect(shadow);
        closeButton.setEffect(shadow);

        // Add actions to buttons
        signOutButton.setOnAction(e -> handle_SignOut());
        closeButton.setOnAction(e -> handle_Close());

        // Add elements to a vertical layout
        VBox topPanel = new VBox(20, welcomeLabel, imageView, signOutButton, closeButton);
        topPanel.setPadding(new Insets(30));
        topPanel.setAlignment(Pos.TOP_CENTER);

        // Create a gradient background fill
        Stop[] stops = new Stop[] { new Stop(0, Color.DARKVIOLET), new Stop(1, Color.PURPLE) };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null, stops);
        topPanel.setBackground(new Background(new BackgroundFill(gradient, null, null)));

        // Set the top panel to the left region of the BorderPane
        root.setLeft(topPanel);

        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem signOutMenuItem = new MenuItem("Sign Out");
        MenuItem closeMenuItem = new MenuItem("Close");
        fileMenu.getItems().addAll(signOutMenuItem, closeMenuItem);
        signOutMenuItem.setOnAction(e -> handle_SignOut());
        closeMenuItem.setOnAction(e -> handle_Close());

        // Inventory menu
        Menu inventoryMenu = new Menu("Inventory");
        MenuItem viewInstrumentMenuItem = new MenuItem("View Instruments");
        MenuItem viewArchiveMenuItem = new MenuItem("View Archive");
        inventoryMenu.getItems().addAll(viewInstrumentMenuItem, viewArchiveMenuItem);
        viewInstrumentMenuItem.setOnAction(e -> handle_ViewInstrument());
        viewArchiveMenuItem.setOnAction(e -> handle_ViewArchive());

        // Sales menu
        Menu salesMenu = new Menu("Sales");
        MenuItem newSaleTransactionMenuItem = new MenuItem("New Sale Transaction");
        MenuItem saleRecordsMenuItem = new MenuItem("Sale Records");
        salesMenu.getItems().addAll(newSaleTransactionMenuItem, saleRecordsMenuItem);
        newSaleTransactionMenuItem.setOnAction(e -> handle_NewSaleTransaction());
        saleRecordsMenuItem.setOnAction(e -> handle_SaleRecords());

        // Store menu
        Menu storeMenu = new Menu("Store");
        MenuItem newStoreTransactionMenuItem = new MenuItem("New Store Transaction");
        MenuItem storeRecordsMenuItem = new MenuItem("Store Records");
        storeMenu.getItems().addAll(newStoreTransactionMenuItem, storeRecordsMenuItem);
        newStoreTransactionMenuItem.setOnAction(e -> handle_NewStoreTransaction());
        storeRecordsMenuItem.setOnAction(e -> handle_StoreRecords());

        // Maintenance menu
        Menu maintenanceMenu = new Menu("Maintenance");
        MenuItem scheduleRepairMenuItem = new MenuItem("Schedule Repair");
        MenuItem repairStatusMenuItem = new MenuItem("Repair Status");
        maintenanceMenu.getItems().addAll(scheduleRepairMenuItem, repairStatusMenuItem);
        scheduleRepairMenuItem.setOnAction(e -> handle_ScheduleRepair());
        repairStatusMenuItem.setOnAction(e -> handle_RepairStatus());

        // Analytics menu
        Menu analyticsMenu = new Menu("Analytics");
        MenuItem generateAnalyticsMenuItem = new MenuItem("Generate Analytics");
        MenuItem viewPastAnalyticsMenuItem = new MenuItem("View Past Analytics");
        analyticsMenu.getItems().addAll(generateAnalyticsMenuItem, viewPastAnalyticsMenuItem);
        generateAnalyticsMenuItem.setOnAction(e -> handle_GenerateAnalytics());
        viewPastAnalyticsMenuItem.setOnAction(e -> handle_ViewPastAnalytics());

        // Add menus to menu bar
        menuBar.getMenus().addAll(fileMenu, inventoryMenu, salesMenu, storeMenu, maintenanceMenu, analyticsMenu);

        // Set the menu bar at the top of the BorderPane
        root.setTop(menuBar);

        // Create a SplitPane for the middle section
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);

        // Upper section
        VBox upperSection = new VBox();
        upperSection.setStyle("-fx-background-color: lightgray;");
        upperSection.setPrefHeight(300); // Set preferred height
        // Add content to the upper section if needed

        // Lower section
        VBox lowerSection = new VBox();
        lowerSection.setStyle("-fx-background-color: white;");
        lowerSection.setPrefHeight(300); // Set preferred height
        // Add content to the lower section if needed

        // Divider line
        splitPane.setDividerPositions(0.5f); // Set divider position
        splitPane.getItems().addAll(upperSection, lowerSection);

        // Set the SplitPane to the center of the BorderPane
        root.setCenter(splitPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Music System Inventory");
        primaryStage.show();
    }

    // Other methods here...

    public void handle_SignOut() {
        try {
            App.setRoot("SignInPage");
        } catch (IOException e) {
>>>>>>> 4f93f740da24fb163dd53e2f812c5bd189feb614
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    // Method to populate Sale Records TableView
    private static void populateSaleRecordsTableView(TableView<SaleRecord> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

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
                        resultSet.getInt("item_ID")
                );
                saleRecords.add(saleRecord);
            }

            // Set the items in TableView
            tableView.setItems(saleRecords);

            // Close resources
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to populate customerTransactionsTableView with data from the database
    private static void populateCustomerTransactionsTableView(TableView<Item> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to select customer transactions from the database
            String query = "SELECT * FROM item WHERE itemID NOT IN (SELECT itemID FROM item_archive) AND itemID NOT IN (SELECT item_itemID FROM sale_transactions)";

            // Create a Statement
            Statement statement = connection.createStatement();

            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Populate TableView with the results
            ObservableList<Item> transactionsList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                // Assuming InventoryManagementScene has appropriate constructor and setters
                Item transaction = new Item(
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
                transactionsList.add(transaction);
            }

            // Set the items in TableView
            tableView.setItems(transactionsList);

            // Close resources
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to filter records by invoice
    private static void filterRecords1(TableView<StoreRecord> tableView, String searchText) {
        if (searchText.isEmpty()) {
            populateStoreRecordsTableView(tableView);
        } else {
            ObservableList<StoreRecord> filteredData = FXCollections.observableArrayList();
            for (StoreRecord record : tableView.getItems()) {
                if (record.getInvoiceNumber().contains(searchText.toLowerCase())){
                    filteredData.add(record);
                }
            }
            tableView.setItems(filteredData);
        }
    }

    // Method to filter records by name
    private static void filterRecords2(TableView<SaleRecord> tableView, String searchText) {
        if (searchText.isEmpty()) {
            populateSaleRecordsTableView(tableView);
        } else {
            ObservableList<SaleRecord> filteredData = FXCollections.observableArrayList();
            for (SaleRecord record : tableView.getItems()) {
                if (record.getBuyerName().toLowerCase().contains(searchText.toLowerCase())){
                    filteredData.add(record);
                }
            }
            tableView.setItems(filteredData);
        }
    }

     // Method to filter items
     private static void filterRecords3(TableView<Item> tableView, String searchText) {
        if (searchText.isEmpty()) {
            populateCustomerTransactionsTableView(tableView);
        } else {
            ObservableList<Item> filteredData = FXCollections.observableArrayList();
            for (Item record : tableView.getItems()) {
                if (record.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    record.getBrand().toLowerCase().contains(searchText.toLowerCase()) ||
                    record.getCategory().toLowerCase().contains(searchText.toLowerCase()) ||
                    record.getdateManufactured().toLowerCase().contains(searchText.toLowerCase())){
                    filteredData.add(record);
                }
            }
            tableView.setItems(filteredData);
        }
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
=======
    public void handle_Close() {
        try {
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void handle_ViewInstrument() {
        InventoryManagementScene.displayInstrumentManagement();
    }

    public void handle_ViewArchive() {
        ArchiveScene.displayArchive();
    }

    public void handle_NewSaleTransaction() {
        SaleTransactionScene.displaySaleTransactions();
    }

    public void handle_SaleRecords() {
        SaleRecordScene.displaySaleRecords();
    }

    public void handle_NewStoreTransaction() {
        StoreTransactionScene.displayStoreTransactions();
    }

    public void handle_StoreRecords() {
        StoreRecordScene.displayStoreRecords();
    }

    public void handle_ScheduleRepair() {
        ScheduleRepairScene.displayScheduleRepair();
    }

    public void handle_RepairStatus() {
        RepairStatusScene.displayRepairs();
    }

    public void handle_GenerateAnalytics() {
        InventoryAnalyticsScene.displayInventoryAnalytics();
    }

    public void handle_ViewPastAnalytics() {
        ViewPastAnalyticsScene.displayPastAnalytics();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
>>>>>>> 4f93f740da24fb163dd53e2f812c5bd189feb614
