/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.system;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import music.system.SystemClasses.Item;

public class ViewItem {
    @FXML
    private FlowPane flowPane;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField retailField;
    @FXML
    private TextField serialField;
    
    public void displayItemStage(@SuppressWarnings("exports") Item item) {
        try {
            // Load FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Item.fxml"));
            Parent root = fxmlLoader.load();
            ViewItem controller = fxmlLoader.getController();

            // Populate fields with repair item data
            controller.populateFields(item);

            // Create and show stage
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Item");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle FXML loading error
        }
    }
    
    private void populateFields(Item item) {
        if (item != null) {
            try {
                //get the photo from the database
                Connection connection = DatabaseManager.getConnection();
                int item_itemID = item.getitemID();
               
                String query = "SELECT image_data FROM item_photo WHERE item_itemID = ?";
                
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, item_itemID);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        byte[] blobData = resultSet.getBytes("image_data");
                        
                        //Convert Blob into an image
                        Image image = new Image(new ByteArrayInputStream(blobData));
                        
                        // Set the image to the FlowPane using ImageView objects
                        ImageView imageView = new ImageView(image);
                        imageView.setFitWidth(200); // Set the width to 200 pixels
                        imageView.setFitHeight(150); // Set the height to 150 pixels
                        flowPane.getChildren().add(imageView);
                        
                       
                        
                        
                    }
            } catch (SQLException e) {
                e.printStackTrace();
                //System.out.println("Connection failed");
            }
            
            // Set text fields with item data
            manufacturerField.setText("$" + item.getmanufacturerPrice());
            descriptionField.setText(item.getDescription());
            nameField.setText(item.getName());
            categoryField.setText(item.getCategory());
            brandField.setText(item.getBrand());
            retailField.setText("$" + item.getretailPrice());
            serialField.setText(String.valueOf(item.getserialNumber()));
            dateField.setText(item.getdateManufactured());
        }
    }
    

}
