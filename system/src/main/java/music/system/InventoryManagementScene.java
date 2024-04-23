package music.system;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle.Control;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import music.system.SystemClasses.Item;

public class InventoryManagementScene {
    @SuppressWarnings("unchecked")
    public static void displayInstrumentManagement() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Instrument Management");

        TableView<Item> tableView = new TableView<>();

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Define TableColumn instances
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

        tableView.getColumns().addAll(itemIDColumn, nameColumn, categoryColumn, brandColumn, dateManufacturedColumn, serialNumberColumn, manufacturerPriceColumn, retailPriceColumn, descriptionColumn);

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            // Create an alert dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Add New Item");
            alert.setContentText("Fill out the form to add a new item.");
            
            // Create input fields
            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            TextField categoryField = new TextField();
            categoryField.setPromptText("Category");
            TextField brandField = new TextField();
            brandField.setPromptText("Brand");
            
            // Use DatePicker for date input
            DatePicker dateManufacturedPicker = new DatePicker();
            dateManufacturedPicker.setPromptText("Date Manufactured");
            TextField serialNumberField = new TextField();
            serialNumberField.setPromptText("Serial Number");
            TextField manufacturerPriceField = new TextField();
            manufacturerPriceField.setPromptText("Manufacturer Price");
            TextField retailPriceField = new TextField();
            retailPriceField.setPromptText("Retail Price");
            TextField descriptionField = new TextField();
            descriptionField.setPromptText("Description");
            
            // Create a grid pane to layout the fields
            GridPane grid = new GridPane();
            grid.addRow(0, new Label("Name:"), nameField);
            grid.addRow(1, new Label("Category:"), categoryField);
            grid.addRow(2, new Label("Brand:"), brandField);
            grid.addRow(3, new Label("Date Manufactured:"), dateManufacturedPicker);
            grid.addRow(4, new Label("Serial Number:"), serialNumberField);
            grid.addRow(5, new Label("Manufacturer Price:"), manufacturerPriceField);
            grid.addRow(6, new Label("Retail Price:"), retailPriceField);
            grid.addRow(7, new Label("Description:"), descriptionField);
            
            alert.getDialogPane().setContent(grid);
            
            // Add save and cancel buttons
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(saveButtonType, ButtonType.CANCEL);

            
            // Handle save button action
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == saveButtonType) {

                 // Format date.
                 LocalDate date = dateManufacturedPicker.getValue();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                 String formattedDate = date.format(formatter);

                // Validate input fields
                if (validateFields(nameField.getText(), categoryField.getText(), formattedDate, Integer.parseInt(serialNumberField.getText()), Double.parseDouble(manufacturerPriceField.getText()), Double.parseDouble(retailPriceField.getText()), descriptionField.getText())) {
                    // Retrieve the entered values and save the new item
                    String name = nameField.getText();
                    String category = categoryField.getText();
                    String brand = brandField.getText();
                    String dateManufactured = formattedDate;           
                    int serialNumber = 0;
                    double manufacturerPrice = 0.0;
                    double retailPrice = 0.0;

                    try {
                        serialNumber = Integer.parseInt(serialNumberField.getText());
                    } catch (NumberFormatException e) {
                        System.out.println("Serial number must be a valid integer.");
                        return;
                    }

                    try {
                        manufacturerPrice = Double.parseDouble(manufacturerPriceField.getText());
                    } catch (NumberFormatException e) {
                        System.out.println("Manufacturer price must be a valid number.");
                        return; 
                    }

                    try {
                        retailPrice = Double.parseDouble(retailPriceField.getText());
                    } catch (NumberFormatException e) {
                        System.out.println("Retail price must be a valid number.");
                        return; 
                    }
                    String description = descriptionField.getText();
                    
                    saveNewItemToDatabase(name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description);
                    populateInstrumentData(tableView);
                } else {
                     // Validation failed, display error message
                     Alert validationAlert = new Alert(Alert.AlertType.ERROR);
                     validationAlert.setTitle("Validation Error");
                     validationAlert.setHeaderText(null);
                     validationAlert.setContentText("Please fill out all fields with valid values.");
                     validationAlert.showAndWait();
                }               
                } else {
                    // User clicked cancel or closed the dialog
                    System.out.println("Add new item operation canceled.");
                }
            });

            Button editButton = new Button("Edit");
            editButton.setOnAction(event -> {
                // Get the selected item from the table
                Item selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Create an alert dialog for editing
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Edit Item");
                    alert.setHeaderText("Edit the item details.");
            
                    // Create input fields and set their initial values to the selected item's properties
                    TextField nameField = new TextField(selectedItem.getName());
                    TextField categoryField = new TextField(selectedItem.getCategory());
                    TextField brandField = new TextField(selectedItem.getBrand());
                    DatePicker dateManufacturedPicker = new DatePicker(LocalDate.parse(selectedItem.getdateManufactured()));
                    TextField serialNumberField = new TextField(String.valueOf(selectedItem.getserialNumber()));
                    TextField manufacturerPriceField = new TextField(String.valueOf(selectedItem.getmanufacturerPrice()));
                    TextField retailPriceField = new TextField(String.valueOf(selectedItem.getretailPrice()));
                    TextField descriptionField = new TextField(selectedItem.getDescription());
                    
                    // Create a grid pane to layout the fields
                    GridPane grid = new GridPane();
                    grid.addRow(0, new Label("Name:"), nameField);
                    grid.addRow(1, new Label("Category:"), categoryField);
                    grid.addRow(2, new Label("Brand:"), brandField);
                    grid.addRow(3, new Label("Date Manufactured:"), dateManufacturedPicker);
                    grid.addRow(4, new Label("Serial Number:"), serialNumberField);
                    grid.addRow(5, new Label("Manufacturer Price:"), manufacturerPriceField);
                    grid.addRow(6, new Label("Retail Price:"), retailPriceField);
                    grid.addRow(7, new Label("Description:"), descriptionField);
            
                    alert.getDialogPane().setContent(grid);
            
                    // Add save and cancel buttons
                    ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(saveButtonType, ButtonType.CANCEL);
            
                    // Handle save button action
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == saveButtonType) {

                        // Format date.
                        LocalDate date = dateManufacturedPicker.getValue();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                        String formattedDate = date.format(formatter);

                        // Validate input fields
                        if (validateFields(nameField.getText(), categoryField.getText(), formattedDate, Integer.parseInt(serialNumberField.getText()), Double.parseDouble(manufacturerPriceField.getText()), Double.parseDouble(retailPriceField.getText()), descriptionField.getText())) {
                            // Retrieve the entered values and update the item
                            String name = nameField.getText();
                            String category = categoryField.getText();
                            String brand = brandField.getText();
                            String dateManufactured = formattedDate;
                            int serialNumber = Integer.parseInt(serialNumberField.getText());
                            double manufacturerPrice = Double.parseDouble(manufacturerPriceField.getText());
                            double retailPrice = Double.parseDouble(retailPriceField.getText());
                            String description = descriptionField.getText();
            
                            // Update the selected item
                            selectedItem.setName(name);
                            selectedItem.setCategory(category);
                            selectedItem.setBrand(brand);
                            selectedItem.setdateManufactured(dateManufactured);
                            selectedItem.setSerialNumber(serialNumber);
                            selectedItem.setmanufacturerPrice(manufacturerPrice);
                            selectedItem.setretailPrice(retailPrice);
                            selectedItem.setDescription(description);
            
                            // Update the item in the database
                            updateItemInDatabase(selectedItem.getitemID(), name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description);
                            // Refresh the table view
                            populateInstrumentData(tableView);
                        } else {
                            // Validation failed, display error message
                            Alert validationAlert = new Alert(Alert.AlertType.ERROR);
                            validationAlert.setTitle("Validation Error");
                            validationAlert.setHeaderText(null);
                            validationAlert.setContentText("Please fill out all fields with valid values.");
                            validationAlert.showAndWait();
                        }
                    } else {
                        // User clicked cancel or closed the dialog
                        System.out.println("Edit item operation canceled.");
                    }
                } else {
                    // No item selected, display error message
                    Alert noItemSelectedAlert = new Alert(Alert.AlertType.WARNING);
                    noItemSelectedAlert.setTitle("No Item Selected");
                    noItemSelectedAlert.setHeaderText(null);
                    noItemSelectedAlert.setContentText("Please select an item to edit.");
                    noItemSelectedAlert.showAndWait();
                }
            });

            Button archiveButton = new Button("Archive Instrument");
            archiveButton.setOnAction(event -> {
                Item selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Confirmation");
                    confirmation.setHeaderText("Archive Confirmation");
                    confirmation.setContentText("Are you sure you want to archive this item?");
                    
                    // Adding button types
                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");

                    confirmation.getButtonTypes().setAll(yesButton, noButton);

                    // Handling user's choice
                    confirmation.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yesButton) {
                            archiveItem(selectedItem.getitemID());
                            deleteItem(selectedItem);
                            System.out.println("Item archived!"); // Placeholder for actual archive operation
                            // Refresh the table view
                            populateInstrumentData(tableView);
                        }
                    });
                }
            });

            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> {
                Item selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setTitle("Confirmation");
                    confirmation.setHeaderText("Delete Confirmation");
                    confirmation.setContentText("Are you sure you want to delete this item?");
                    
                    // Adding button types
                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");

                    confirmation.getButtonTypes().setAll(yesButton, noButton);

                    // Handling user's choice
                    confirmation.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yesButton) {
                            // Logic to delete selected item
                            deleteItem(selectedItem);
                            tableView.getItems().remove(selectedItem);
                        }
                    });
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select an item to delete.");
                    alert.showAndWait();
                }
            });

            // Close button
            Button closeButton = new Button("Close");
            closeButton.setOnAction(event -> {
            primaryStage.close(); // Close the application
        });

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        vbox.getChildren().addAll(tableView);
        hbox.getChildren().addAll(addButton, editButton, archiveButton, deleteButton, closeButton);
        vbox.getChildren().add(hbox);
        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        populateInstrumentData(tableView);
        primaryStage.show();

    }


    private static boolean validateFields(String name, String category, String formattedDate, int serialNumber, double manufacturerPrice, double retailPrice, String description) {
        // Check if any of the fields are empty or invalid
        if (name.isEmpty() || category.isEmpty() || formattedDate == null || serialNumber <= 0 || manufacturerPrice <= 0 || retailPrice <= 0 || description.isEmpty()
        || !formattedDate.toString().matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            return false;
        }
        return true;
    }

    private static void saveNewItemToDatabase(String name, String category, String brand, String dateManufactured, int serialNumber, double manufacturerPrice, double retailPrice, String description) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Parse the date string into a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date manufacturedDate = dateFormat.parse(dateManufactured);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDateManufactured = new java.sql.Date(manufacturedDate.getTime());
    
            // Retrieve the largest ID from the database and increment it by one
            String getMaxIDQuery = "SELECT MAX(itemID) FROM item";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getMaxIDQuery);
            int maxID = 0;
            if (resultSet.next()) {
                maxID = resultSet.getInt(1);
            }
            int newItemID = maxID + 1;
    
            // Check if the generated ID is already taken
            String checkIDQuery = "SELECT * FROM item WHERE itemID = ?";
            PreparedStatement checkIDStatement = connection.prepareStatement(checkIDQuery);
            checkIDStatement.setInt(1, newItemID);
            ResultSet checkIDResult = checkIDStatement.executeQuery();
            if (checkIDResult.next()) {
                // If ID is taken, generate a new ID
                newItemID++;
            }
    
            // Prepare and execute the INSERT query
            String insertQuery = "INSERT INTO item (itemID, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, newItemID); // Use the incremented ID
            preparedStatement.setString(2, name.toString());
            preparedStatement.setString(3, category.toString());
            preparedStatement.setString(4, brand.toString());
            preparedStatement.setDate(5, sqlDateManufactured);
            preparedStatement.setInt(6, serialNumber);
            preparedStatement.setDouble(7, (double) manufacturerPrice);
            preparedStatement.setDouble(8, (double) retailPrice);
            preparedStatement.setString(9, description.toString());
            preparedStatement.executeUpdate();
    
            // Close resources
            preparedStatement.close();
            checkIDResult.close();
            checkIDStatement.close();
            statement.close();
            connection.close();
    
            System.out.println("New item added to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateItemInDatabase(int itemID, String name, String category, String brand, String dateManufactured, int serialNumber, double manufacturerPrice, double retailPrice, String description) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
    
           // Parse the date string into a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date manufacturedDate = dateFormat.parse(dateManufactured);

            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDateManufactured = new java.sql.Date(manufacturedDate.getTime());
    
            // Prepare and execute the UPDATE query
            String updateQuery = "UPDATE item SET name=?, category=?, brand=?, dateManufactured=?, serialNumber=?, manufacturerPrice=?, retailPrice=?, description=? WHERE itemID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, brand);
            preparedStatement.setDate(4, sqlDateManufactured);
            preparedStatement.setInt(5, serialNumber);
            preparedStatement.setDouble(6, manufacturerPrice);
            preparedStatement.setDouble(7, retailPrice);
            preparedStatement.setString(8, description);
            preparedStatement.setInt(9, itemID);
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Item with ID " + itemID + " updated successfully.");
            } else {
                System.out.println("No item found with ID " + itemID + ".");
            }
    
            // Close resources
            preparedStatement.close();
            connection.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void archiveItem(int itemID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password")) {
            String insertQuery = "INSERT INTO archive SELECT * FROM item WHERE itemID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, itemID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateInstrumentData(TableView<Item> tableView) {
        try {
        // Establish connection to MySQL database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

        // Define SQL query to select all records from the database
        String query = "SELECT * FROM item";

        // Create a Statement
        Statement statement = connection.createStatement();

        // Execute the query and get the ResultSet
        ResultSet resultSet = statement.executeQuery(query);

        // Populate TableView with the results
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            // Construct Item object and add it to the list
            Item item = new Item(
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
            itemList.add(item);
        }

        // Set the items in TableView
        tableView.setItems(itemList);

        // Close resources
        System.out.println("Connection closed to the database: Instrument Management Scene");
        resultSet.close();
        statement.close();
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    private static void deleteItem(Item selectedItem) {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
    
            // Define SQL query to delete the selected record from the database
            String query = "DELETE FROM item WHERE itemID = ?";
    
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
    
            // Set parameters for the PreparedStatement
            preparedStatement.setInt(1, selectedItem.getitemID());
    
            // Execute the deletion
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Item deleted successfully.");
            } else {
                System.out.println("Failed to delete item.");
            }
    
            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
