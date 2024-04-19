package music.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JavaFX App: This Handles the initialization of javaFX and the properties of the windows created through-out the system. 
 */
public class App extends Application {

    // Static fields for storing scene and primary stage
    private static Scene scene;
    private static Stage primaryStage;
    private static int currentRootPage;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {

        primaryStage = stage;

        // At any point either the sign in page or the inventory pages will be open,
        // these are the programs "root" Stages and this integer keeps track of which
        // page is currently open.
        currentRootPage = 0;

        // Loading the SignInPage FXML file and creating a scene
        scene = new Scene(loadFXML("SignInPage"), 600, 300);

         // Setting stage properties
        setSizeSignIn();
        stage.setTitle("StoreInventorySystem");
        stage.setScene(scene);
        stage.show();

        centerStageOnScreen(stage);
    }

    // Method to set the root of the scene
    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
    
        if (root instanceof Pane) {
            Pane pane = (Pane) root;

            if (currentRootPage % 2 == 0)
                setSizeInventory(pane);
            else
                setSizeSignIn();
        
        }

        currentRootPage++;
        centerStageOnScreen(primaryStage);
        
    }


    // Method to load FXML file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Method to Set size of Signin Window
    public static void setSizeSignIn()
    {
        primaryStage.setMaxHeight(400);
        primaryStage.setMaxWidth(660);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(660);

    }

    // Method to Set size of Inventory Window
    public static void setSizeInventory(@SuppressWarnings("exports") Pane pane)
    {
        double prefWidth = Math.min(pane.getPrefWidth(), Screen.getPrimary().getVisualBounds().getWidth());
        double prefHeight = Math.min(pane.getPrefHeight(), Screen.getPrimary().getVisualBounds().getHeight());

        primaryStage.setWidth(prefWidth);
        primaryStage.setHeight(prefHeight);
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(660);

    }

     // Method to center the stage on the screen
     private static void centerStageOnScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    // Main method to launch the application
    public static void main(String[] args) {

        // JDBC URL, username, and password
        String url = "jdbc:mysql://localhost:3306/Instrument_Store_System";
        String user = "username";
        String password = "password";

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            // Launch the Application
            launch();
            
            // Close the connection when done
            System.out.println("Connection closed to the database: App");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }

       
    }

}