package music.system;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.Item;
import music.system.SystemClasses.StoreRecord;
import music.system.SystemClasses.StoreTransaction;

public class StoreTransactionScene {

    private static Date transactionDate;
    private static double totalAmount;
    private static String purchaseDescription;
    private static int quantity;
    private static double unitPrice;

    @SuppressWarnings("unchecked")
    public static void displayStoreTransactions() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Store Transactions");
        VBox root = new VBox(); // Change HBox to VBox
        root.setSpacing(10); // Add spacing between children

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels
        grid.add(new Label("Transaction Date:"), 0, 0);
        grid.add(new Label("Total Amount:"), 0, 1);
        grid.add(new Label("Purchase Description:"), 0, 2);
        grid.add(new Label("Quantity:"), 0, 3);
        grid.add(new Label("Unit Price:"), 0, 4);

        // Date Picker
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(event -> {});
        grid.add(datePicker, 1, 0);

        // Text Fields
        TextField totalAmountField = new TextField();
        grid.add(totalAmountField, 1, 1);

        TextField purchaseDescriptionField = new TextField();
        grid.add(purchaseDescriptionField, 1, 2);

        TextField quantityField = new TextField();
        grid.add(quantityField, 1, 3);

        TextField unitPriceField = new TextField();
        grid.add(unitPriceField, 1, 4);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                // Parse totalAmount, quantity, and unitPrice
                totalAmount = Double.parseDouble(totalAmountField.getText());
                quantity = Integer.parseInt(quantityField.getText());
                unitPrice = Double.parseDouble(unitPriceField.getText());
                
                // Check for valid totalAmount, quantity, and unitPrice
                if (totalAmount <= 0 || quantity <= 0 || unitPrice <= 0) {
                    showAlert("Invalid input", "Total amount, quantity, and unit price must be greater than zero.");
                    
                }
                
                // Parse and validate transaction date
                LocalDate selectedDate = datePicker.getValue();
                try {
                    if (selectedDate == null || !datePicker.getEditor().getText().matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                        showAlert("Invalid date", "Please select a valid date in the format MM-dd-YYYY.");                   
                    }
                    @SuppressWarnings("rawtypes")
                    TableView tempTable = new TableView();
                    InventoryManagementScene.addItem(tempTable);
                    transactionDate = Date.valueOf(selectedDate);
                    primaryStage.close();
                } catch (DateTimeParseException e) {
                    showAlert("Invalid date", "Please select a valid date in the format MM-dd-YYYY.");                   
                }
                transactionDate = Date.valueOf(selectedDate);
                purchaseDescription = purchaseDescriptionField.getText();

                // If all inputs are valid, proceed with storing the data                     
                Integer purchaseDescriptionToInvoice = purchaseDescription.hashCode();

                StoreTransaction StoreTransaction = new StoreTransaction(transactionDate, totalAmount, purchaseDescription, quantity, unitPrice);
                StoreTransaction.saveToMySQL();

                StoreRecord storerecord = new StoreRecord(purchaseDescriptionToInvoice.toString(), transactionDate);
                storerecord.saveToMySQL(purchaseDescription.hashCode(), StoreTransaction.getId());


            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter valid numeric values for total amount, quantity, and unit price.");
            }
        });

        HBox buttonBox = new HBox(submitButton);
        buttonBox.setAlignment(Pos.CENTER); // Align the button to the center
        buttonBox.setSpacing(10);

        root.getChildren().addAll(grid, buttonBox);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


