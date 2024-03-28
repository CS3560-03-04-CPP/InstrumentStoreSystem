package music.system.SystemClasses;
//import javafx.scene.image.Image;
import java.io.File;

public class ItemPhoto {
	private int photoId;
	private File file;

	//Constructor which takes a file
	public ItemPhoto (File file) {
		this.photoId = generatePhotoId();
		setImage(file);
	}

	//generate an ID for ItemPhoto
	//return the ID for ItemPhoto
	private int generatePhotoId() {	
		return 0;
	}

	//get photoId
	//return photoId
	public int getPhotoId () {
		return photoId;
	}

	//set the image, if the file is not valid, prompt the user for a different file
	public void setImage (File file) {
	
	}

	//get the image file
	//return the image file
	public File getImage() {
		return file;
	}

	//check if the file is valid and an Image can be opened with it
	//return true if valid, false if not
	public boolean isValidImage(File file) {
		return false;
	}
}
