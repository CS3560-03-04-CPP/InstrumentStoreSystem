/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.system;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import music.system.SystemClasses.RepairItem;

/**
 *
 * @author rickm
 */
public class ViewRepairItem {
    @FXML
    private ImageView photo;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField dateField;
    
    public void displayRepairStage(RepairItem repair) {
        try {
            // Load FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RepairItem.fxml"));
            Parent root = fxmlLoader.load();
            ViewRepairItem controller = fxmlLoader.getController();

            // Populate fields with repair item data
            controller.populateFields(repair);

            // Create and show stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Repair Item");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle FXML loading error
        }
    }

    private void populateFields(RepairItem repair) {
        if (repair != null) {
            try {
                //get the photo from the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instrument_store_system", "username", "password");
                int repairItemId = repair.getRepairId();
                
                String query = "SELECT file FROM repair_items WHERE repairId= ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, repairItemId);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        byte[] blobData = resultSet.getBytes("file");
                        
                        //Convert Blob into an image
                        Image image = new Image(new ByteArrayInputStream(blobData));
                        
                        // Set the image to the imageView
                        photo.setImage(image);
                        
                        
                    }
            } catch (SQLException e) {
                e.printStackTrace();
                //System.out.println("Connection failed");
            }
            
            // Set text fields with repair item data
            priceField.setText("$" + repair.getPrice());
            descriptionField.setText(repair.getDescription());
            nameField.setText(repair.getName());
            statusField.setText(repair.getStatus());

            // Format the Date into a string
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String dateString = sdf.format(repair.getDate());
            dateField.setText(dateString);
        }
    }
    
    
    
    /*
    public void displayRepairStage(RepairItem repair) throws IOException {
        //Create a new Stage to display ViewRepairItem
        Scene scene = new Scene(loadFXML("RepairItem"), 600, 300);
        
        Stage stage = new Stage();        
        //set the scene
        //photo.setImage(new Image(repair.getFile().toURI().toString()));
        priceField.setText("$" + repair.getPrice());
        descriptionField.setText(repair.getDescription());
        nameField.setText(repair.getName());
        statusField.setText(repair.getStatus());
        
        
        //Format the Date into a string
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(repair.getDate());
        dateField.setText(dateString);
        
        
        
        stage.setScene(scene);
        stage.setTitle("Repair Item");
        stage.show();
        
    }
    
     // Method to load a FXML file.
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    */
}
