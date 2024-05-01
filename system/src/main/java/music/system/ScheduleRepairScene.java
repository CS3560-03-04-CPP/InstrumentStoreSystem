package music.system;

import java.io.File;
import java.util.Calendar;
import java.sql.Date;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import music.system.SystemClasses.RepairItem;

/**
 * JavaFX Scene, ScheduleRepairScene: This scene opens the Schedule Repair window.
 * 
 * Functionality: Allows for input for a new repair tast, name, description, and price for repair.
 * 
 */
public class ScheduleRepairScene {
    
    private static File file; //will contain the photo if user chooses to select one
    
    public static void displayScheduleRepair() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Schedule Repair");

        // Create labels and text fields for attributes
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();

        Label fixPriceLabel = new Label("Fix Price:");
        TextField fixPriceField = new TextField();
        
        //Button to add file
        Button fileButton = new Button("Add Photo");
        fileButton.setOnAction(event -> {
            //Create a FileChooser object from JavaFX
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Photo");
            
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            
            if (selectedFile != null) {
                file = selectedFile;
            }      
        });
        
           
        // Button to send data to the database
        Button addButton = new Button("Add Repair");
        addButton.setOnAction(event -> {
            // Get data from text fields
            String name = nameField.getText();
            String description = descriptionField.getText();
            double fixPrice = 0.0; // Default value in case of invalid input

            // Parse fix price, if valid
            try {
                fixPrice = Double.parseDouble(fixPriceField.getText());
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.err.println("Invalid fix price. Please enter a valid number.");
                return; // Exit the method if fix price is invalid
            }

            // Get the current date
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            
            // Convert the date into a SQL DATE object
            Date sqlDate = new Date(currentDate.getTime());

            String status = "ongoing";
            // Create RepairItem object
            RepairItem repairItem = new RepairItem(status, name, description, fixPrice, sqlDate, file);

            // Call method to insert data into the database
            repairItem.saveToMySQL();
            
            // delete File from variable after adding to database
            file = null;
            // Clear fields after adding repair
            nameField.clear();
            descriptionField.clear();
            fixPriceField.clear();
        });

        // Create layout and add components
        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, fixPriceLabel, fixPriceField, fileButton, addButton);

        // Set scene
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}