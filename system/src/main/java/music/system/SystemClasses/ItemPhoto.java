package music.system.SystemClasses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ItemPhoto {
	private Image image;

	//Constructor which takes a file
	public ItemPhoto (String imagePath) {
            loadImage(imagePath);
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
                //Establish connection to MySQL database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");
                
                //Define SQL query to insert ItemPhoto data into the database
                String query = "INSERT INTO item_photos (image) VALUES (?)";
                
                // Create PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                byte[] imageData = imageToByteArray(image);
                //set the byte array as a parameter for the PreparedStatement
                preparedStatement.setBytes(1, imageData);
                //Execute the query
                preparedStatement.executeUpdate();
                
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

	//generate an ID for ItemPhoto
	//return the ID for ItemPhoto
	private int generatePhotoId() {	
		return 0;
	}
        
        /*
	//get photoId
	//return photoId
	public int getPhotoId () {
		return photoId;
	}
        */

	//set the image, if the file is not valid, prompt the user for a different file
	public void setImage (File file) {
	
	}

	//get the image file
	//return the image file
	public Image getImage() {
		return image;
	}

	//check if the file is valid and an Image can be opened with it
	//return true if valid, false if not
	public boolean isValidImage(File file) {
		return false;
	}
}
