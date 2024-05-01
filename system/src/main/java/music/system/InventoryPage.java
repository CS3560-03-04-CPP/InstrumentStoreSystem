package music.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import music.system.SystemClasses.RepairItem;
import music.system.SystemClasses.SaleRecord;
import music.system.SystemClasses.StoreRecord;


/**
 * Application Class, InventoryPage: This class represents the inventory root Stage of the music system application.
 * 
 * The main user GUI interface which connects all the buttons a user can interact with to their respective classes.
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
    private TableView<RepairItem> repairsTableView;
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
    private TextField repairSearchField;
    
    public void initialize() {
        handle_user_greet.setText(Employee.getName());
        username.setText("Username: " + SignInPage.userName);
        position.setText("Position: " + SignInPage.position);

        itemTable();
        storeRecordsTable();
        saleRecordsTable();
        repairTable();


    }

    public void handle_SignOut() throws IOException{App.setRoot("SignInPage");}
    public void handle_Close(){ try {DatabaseManager.closeConnection();} catch (SQLException e) {}; System.exit(0);}

    public void handle_ViewInstrument(){InventoryManagementScene.displayInstrumentManagement();}
    public void handle_ViewArchive(){ArchiveScene.displayArchive();}

    public void handle_NewSaleTransaction(){SaleTransactionScene.displaySaleTransactions();}
    public void handle_SaleRecords(){SaleRecordScene.displaySaleRecords();}
    
    public void handle_NewStoreTransaction(){StoreTransactionScene.displayStoreTransactions();}
    public void handle_StoreRecords(){StoreRecordScene.displayStoreRecords();}

    public void handle_ScheduleRepair(){ScheduleRepairScene.displayScheduleRepair();}
    public void handle_RepairStatus(){RepairStatusScene.displayRepairs();}

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

    @FXML
    private void handle_repair_search() {
        filterRecords4(repairsTableView, repairSearchField.getText());
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
    
    //Create repairTable for Repairs Tab
    @SuppressWarnings("unchecked")
    private void repairTable() {
       TableColumn<RepairItem, Integer> repairIDColumn = new TableColumn<>("Item ID");
       repairIDColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRepairId()).asObject());
       repairIDColumn.setPrefWidth(50);
       
       TableColumn<RepairItem, String> statusColumn = new TableColumn<>("Status");
       statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
       statusColumn.setPrefWidth(100);

       TableColumn<RepairItem, String> nameColumn = new TableColumn<>("Name");
       nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
       nameColumn.setPrefWidth(200);
        
       TableColumn<RepairItem, String> descriptionColumn = new TableColumn<>("Description");
       descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
       descriptionColumn.setPrefWidth(350);
       
       TableColumn<RepairItem, Double> fixPriceColumn = new TableColumn<>("Fix Price");
       fixPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
       fixPriceColumn.setPrefWidth(200);

       TableColumn<RepairItem, String> dateColumn = new TableColumn<>("Date Initiated");
       dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
       dateColumn.setPrefWidth(350);
       
       repairsTableView.getColumns().addAll(repairIDColumn, statusColumn, nameColumn, descriptionColumn, fixPriceColumn, dateColumn);
       populateRepairItems(repairsTableView);
       
       //on a double click, a new stage will appear to show the image and details of a repair item
       repairsTableView.setOnMouseClicked(event -> {
           if (event.getClickCount() == 2) {
               //Retrieve selected row
               RepairItem selectedRepair = repairsTableView.getSelectionModel().getSelectedItem();
               if (selectedRepair != null) {
                   ViewRepairItem view = new ViewRepairItem();
                   view.displayRepairStage(selectedRepair);
               }
           }
           
           
       });
       
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
            e.printStackTrace();
        }
    }

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
    
    private static void populateRepairItems(TableView<RepairItem> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();
    
            // Define SQL query to select all records from the database
            String query = "SELECT * FROM repair_items";
    
            // Create a Statement
            Statement statement = connection.createStatement();
    
            // Execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery(query);
    
            // Initialize the PreparedStatement for updating the status
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE repair_items SET status = ? WHERE repairId = ?");
    
            // Populate TableView with the results
            ObservableList<RepairItem> repairRecords = FXCollections.observableArrayList();
            while (resultSet.next()) {
                RepairItem repairRecord = new RepairItem(
                        resultSet.getInt("repairId"),
                        resultSet.getString("status"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("fixPrice"),
                        resultSet.getDate("date")
                );
    
                repairRecord.updateDaysLeft();
                repairRecord.setStatus(repairRecord.getDaysLeft());
    
                // Update the status in the database if updateStatement is not null
                if (updateStatement != null) {
                    updateStatement.setString(1, repairRecord.getStatus());
                    updateStatement.setInt(2, repairRecord.getRepairId());
                    updateStatement.executeUpdate();
                } else {
                    System.out.println("Error: updateStatement is null.");
                }
    
                repairRecords.add(repairRecord);
            }
    
            // Set the items in TableView
            tableView.setItems(repairRecords);
    
            // Close resources
            resultSet.close();
            statement.close();

    
        } catch (Exception e) {
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

    // Method to filter records by name
    private static void filterRecords4(TableView<RepairItem> tableView, String searchText) {
        if (searchText.isEmpty()) {
            populateRepairItems(tableView);
        } else {
            ObservableList<RepairItem> filteredData = FXCollections.observableArrayList();
            for (RepairItem record : tableView.getItems()) {
                if (record.getName().toLowerCase().contains(searchText.toLowerCase())){
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
