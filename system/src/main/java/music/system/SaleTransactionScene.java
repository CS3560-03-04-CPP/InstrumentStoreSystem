package music.system;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.system.SystemClasses.SaleRecord;
import music.system.SystemClasses.SaleTransaction;

public class SaleTransactionScene {

    private static String buyerName;
    private static String cardNumber;
    private static LocalDate expirationDate;
    private static String CVV;
    private static String billingAddress;
    private static String billingPostalCode;
    private static double transactionAmount;
    private static int itemID;

    public static void displaySaleTransactions() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sale Transactions");

        VBox root = new VBox();
        root.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels
        grid.add(new Label("Buyer Name:"), 0, 1);
        grid.add(new Label("Card Number:"), 0, 2);
        grid.add(new Label("Expiration Date:"), 0, 3);
        grid.add(new Label("CVV:"), 0, 4);
        grid.add(new Label("Billing Address:"), 0, 5);
        grid.add(new Label("Billing Postal Code:"), 0, 6);
        grid.add(new Label("Transaction Amount:"), 0, 7);
        grid.add(new Label("Item ID:"), 0, 8);


        // Text Fields
        TextField buyerNameField = new TextField();
        grid.add(buyerNameField, 1, 1);

        TextField cardNumberField = new TextField();
        grid.add(cardNumberField, 1, 2);

        DatePicker expirationDatePicker = new DatePicker();
        grid.add(expirationDatePicker, 1, 3);

        TextField cvvField = new TextField();
        grid.add(cvvField, 1, 4);

        TextField billingAddressField = new TextField();
        grid.add(billingAddressField, 1, 5);

        TextField billingPostalCodeField = new TextField();
        grid.add(billingPostalCodeField, 1, 6);

        TextField transactionAmountField = new TextField();
        grid.add(transactionAmountField, 1, 7);

        TextField itemIDField = new TextField();
        grid.add(itemIDField, 1, 8);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                // Parse transactionAmount and itemID
                transactionAmount = Double.parseDouble(transactionAmountField.getText());
                itemID = Integer.parseInt(itemIDField.getText());

                // Check for valid transactionAmount and itemID
                if (transactionAmount <= 0 || itemID <= 0) {
                    showAlert("Invalid input", "Transaction amount and item ID must be greater than zero.");
                    return;
                }

                // Parse and validate expiration date
                LocalDate selectedExpirationDate = expirationDatePicker.getValue();
                if (selectedExpirationDate == null) {
                    showAlert("Invalid expiration date", "Please select a valid expiration date.");
                    return;
                }
                expirationDate = selectedExpirationDate;

                buyerName = buyerNameField.getText();
                cardNumber = cardNumberField.getText();
                CVV = cvvField.getText();
                billingAddress = billingAddressField.getText();
                billingPostalCode = billingPostalCodeField.getText();
                
                //If all inputs are valid, proceed with storing the data
                SaleTransaction saleTransaction = new SaleTransaction(buyerName, cardNumber, expirationDate, CVV, billingAddress, billingPostalCode, transactionAmount, itemID);
                saleTransaction.saveToMySQL();

                Integer saleRecordID = buyerName.hashCode() + itemID;

                //String orderId, Date date, String buyerName, double soldPrice
                SaleRecord salerecord = new SaleRecord(saleRecordID, LocalDate.now().toString(), buyerName, transactionAmount, cardNumber.hashCode() + itemID);                
                salerecord.saveToMySQL();
                
                primaryStage.close();
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter valid numeric values for transaction amount and item ID.");
            } catch (DateTimeParseException e) {
                showAlert("Invalid date", "Please select valid dates.");
            }
        });

        HBox buttonBox = new HBox(submitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        root.getChildren().addAll(grid, buttonBox);

        Scene scene = new Scene(root, 400, 350);
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
