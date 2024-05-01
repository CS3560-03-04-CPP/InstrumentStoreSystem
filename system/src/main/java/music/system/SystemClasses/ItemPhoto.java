package music.system.SystemClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.scene.image.Image;
import music.system.DatabaseManager;

/**
 * System Class, ItemPhoto: This class handles the accociation of Photo's to item entires.
 */
public class ItemPhoto {
	private Image image;
        private File file;
        private int itemID;

	//Constructor which takes a file
	public ItemPhoto (String imagePath) {
            loadImage(imagePath);
	}
        
        public ItemPhoto (File file, int itemID) {
            this.file = file;
            this.itemID = itemID;
        }
        
    private void loadImage(String imagePath) {
        
        try {
            InputStream inputStream = new FileInputStream(imagePath);
            this.image = new Image(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();
                
            // Define SQL query to insert ItemPhoto data into the database
            String query = "INSERT INTO item_photo (image_data, item_itemID) VALUES (?, ?)";
                
            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            byte[] imageData = readFileToByteArray(file);

            // set the byte array as a parameter for the PreparedStatement
            preparedStatement.setBytes(1, imageData);
            preparedStatement.setInt(2, itemID);
            // Execute the query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    private byte[] imageToByteArray(Image image) throws IOException {
        // Save the image to a temporary file
        Path tempFile = Files.createTempFile("temp", ".png");
        
        try (InputStream inputStream = Files.newInputStream(tempFile)) {
            // Copy the image data to the temporary file
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            // Read the temporary file as a byte array
            return Files.readAllBytes(tempFile);
        } finally {
            // Delete the temporary file
            Files.deleteIfExists(tempFile);
        }
    }
    
    private byte[] readFileToByteArray(File file) {
        byte[] fileData = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            fileData = new byte[(int) file.length()];
            inputStream.read(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileData;
    }

	// Setters and Getters
	public void setImage (File file) {}
	
	public Image getImage() {return image;}

	public boolean isValidImage(File file) {return false;}
    
}
