package music.system;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import music.system.SystemClasses.Item;
import music.system.SystemClasses.ItemPhoto;
import music.system.SystemClasses.archiveItem;

/**
 * JavaFX Scene, InventoryManagementScene: This scene opens the main item
 * management form.
 *
 * Functionality: Add an instrument --> opens new form to fillout Edit an
 * instruemnt --> opens new form to fillout for the highlighted item Archive an
 * instrument --> archives highlighted item Close --> closes the form
 *
 * Methods for add, edit, and archive are public and can be called from other
 * GUI interactions.
 *
 */
public class InventoryManagementScene {
    private static ArrayList<File> files = new ArrayList<>(); //will contain photos if user chooses to select one
    private static Stage primaryStage;
    
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
        
        //on a double click, a new stage will appear to show the image and details of a repair item
       tableView.setOnMouseClicked(event -> {
           if (event.getClickCount() == 2) {
               //Retrieve selected row
               Item selectedItem = tableView.getSelectionModel().getSelectedItem();
               if (selectedItem != null) {
                   ViewItem view = new ViewItem();
                    view.displayItemStage(selectedItem);                
               }
           }
           
           
       });

        // Connecting buttons to their methods.
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            addItem(tableView);
        });

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> {
            editItem(tableView);
        });

        Button archiveButton = new Button("Archive Instrument");
        archiveButton.setOnAction(event -> {
            archiveItem(tableView);
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            primaryStage.close();
        });

        // Finishing scene elemenets
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        vbox.getChildren().addAll(tableView);
        hbox.getChildren().addAll(addButton, editButton, archiveButton, closeButton);
        vbox.getChildren().add(hbox);
        Scene scene = new Scene(vbox, 1000, 400);
        primaryStage.setScene(scene);
        populateInstrumentData(tableView);
        primaryStage.show();

    }

    //Button methods
    @SuppressWarnings("unchecked")
    public static void addItem(@SuppressWarnings({"exports", "rawtypes"}) TableView tableView) {
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
        TextField attributesField = new TextField();
        attributesField.setPromptText("Attributes (comma-separated)");
        
        //Button to add file
        Button fileButton = new Button("Add Photo");
        fileButton.setOnAction(event -> {
            //Create a FileChooser object from JavaFX
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Photo");
            
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            
            if (selectedFile != null) {
                files.add(selectedFile);
            }      
        });

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
        grid.addRow(8, new Label("Attributes:"), attributesField);
        grid.addRow(9, fileButton);
        
        alert.getDialogPane().setContent(grid);

        // Add save and cancel buttons
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(saveButtonType, ButtonType.CANCEL);

        // Handle save button action
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == saveButtonType) {

            String attributesString = attributesField.getText();
            String[] attributes = attributesString.split(",");

            if (attributes.length >= 3) {
                saveNewItemToDatabase(attributes[0], attributes[1], attributes[2], attributes[3], Integer.parseInt(attributes[4]), Double.parseDouble(attributes[5]), Double.parseDouble(attributes[6]), attributes[7]);
                populateInstrumentData(tableView);
            } else {

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
                    
                    //create and save item images to database using foreign key from tableView
                    ObservableList <Item> items = tableView.getItems();
                    //Get the item ID of the last added row in the table
                    int itemID = items.get(items.size() - 1).getitemID();
                    
                    //Create Database Connection
                    for (File file: files) {
                        ItemPhoto photo = new ItemPhoto(file, itemID);
                        photo.saveToMySQL();
                    }
                    
                    // delete Files from list after adding to database
                    files = null;
                                
                } else {
                    // Validation failed, display error message
                    Alert validationAlert = new Alert(Alert.AlertType.ERROR);
                    validationAlert.setTitle("Validation Error");
                    validationAlert.setHeaderText(null);
                    validationAlert.setContentText("Please fill out all fields with valid values.");
                    validationAlert.showAndWait();
                }
            }
        } else {
            // User clicked cancel or closed the dialog
            System.out.println("Add new item operation canceled.");
        }

    }

    @SuppressWarnings("unchecked")
    public static void editItem(@SuppressWarnings({"exports", "rawtypes"}) TableView tableView) {
        // Get the selected item from the table
        Item selectedItem = (Item) tableView.getSelectionModel().getSelectedItem();
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
    }

    public static void archiveItem(@SuppressWarnings("exports") TableView<Item> tableView) {
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
                    new archiveItem(selectedItem.getserialNumber(), selectedItem.getitemID());
                    System.out.println("Item archived!");

                    // Refresh the table view
                    populateInstrumentData(tableView);
                }
            });
        }
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
            Connection connection = DatabaseManager.getConnection();

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

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            checkIDResult.close();
            checkIDStatement.close();
            statement.close();

            System.out.println("New item added to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateItemInDatabase(int itemID, String name, String category, String brand, String dateManufactured, int serialNumber, double manufacturerPrice, double retailPrice, String description) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateInstrumentData(TableView<Item> tableView) {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to select all non-archived records from the database
            String query = "SELECT * FROM item WHERE itemID NOT IN (SELECT itemID FROM item_archive) AND itemID NOT IN (SELECT item_itemID FROM sale_transactions)";

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
            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
