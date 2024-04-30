package music.system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InventoryPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // Load the image
        Image image = new Image(getClass().getResourceAsStream("user.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set smaller image width
        imageView.setPreserveRatio(true); // Maintain aspect ratio

        // Create a label for the welcome message
        Label welcomeLabel = new Label("Welcome, User");
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Create buttons for methods
        Button signOutButton = new Button("Sign Out");
        Button closeButton = new Button("Close");

        // Apply drop shadow effect to buttons
        DropShadow shadow = new DropShadow();
        signOutButton.setEffect(shadow);
        closeButton.setEffect(shadow);

        // Add actions to buttons
        signOutButton.setOnAction(e -> handle_SignOut());
        closeButton.setOnAction(e -> handle_Close());

        // Add elements to a vertical layout
        VBox topPanel = new VBox(20, welcomeLabel, imageView, signOutButton, closeButton);
        topPanel.setPadding(new Insets(30));
        topPanel.setAlignment(Pos.TOP_CENTER);

        // Create a gradient background fill
        Stop[] stops = new Stop[] { new Stop(0, Color.DARKVIOLET), new Stop(1, Color.PURPLE) };
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, null, stops);
        topPanel.setBackground(new Background(new BackgroundFill(gradient, null, null)));

        // Set the top panel to the left region of the BorderPane
        root.setLeft(topPanel);

        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem signOutMenuItem = new MenuItem("Sign Out");
        MenuItem closeMenuItem = new MenuItem("Close");
        fileMenu.getItems().addAll(signOutMenuItem, closeMenuItem);
        signOutMenuItem.setOnAction(e -> handle_SignOut());
        closeMenuItem.setOnAction(e -> handle_Close());

        // Inventory menu
        Menu inventoryMenu = new Menu("Inventory");
        MenuItem viewInstrumentMenuItem = new MenuItem("View Instruments");
        MenuItem viewArchiveMenuItem = new MenuItem("View Archive");
        inventoryMenu.getItems().addAll(viewInstrumentMenuItem, viewArchiveMenuItem);
        viewInstrumentMenuItem.setOnAction(e -> handle_ViewInstrument());
        viewArchiveMenuItem.setOnAction(e -> handle_ViewArchive());

        // Sales menu
        Menu salesMenu = new Menu("Sales");
        MenuItem newSaleTransactionMenuItem = new MenuItem("New Sale Transaction");
        MenuItem saleRecordsMenuItem = new MenuItem("Sale Records");
        salesMenu.getItems().addAll(newSaleTransactionMenuItem, saleRecordsMenuItem);
        newSaleTransactionMenuItem.setOnAction(e -> handle_NewSaleTransaction());
        saleRecordsMenuItem.setOnAction(e -> handle_SaleRecords());

        // Store menu
        Menu storeMenu = new Menu("Store");
        MenuItem newStoreTransactionMenuItem = new MenuItem("New Store Transaction");
        MenuItem storeRecordsMenuItem = new MenuItem("Store Records");
        storeMenu.getItems().addAll(newStoreTransactionMenuItem, storeRecordsMenuItem);
        newStoreTransactionMenuItem.setOnAction(e -> handle_NewStoreTransaction());
        storeRecordsMenuItem.setOnAction(e -> handle_StoreRecords());

        // Maintenance menu
        Menu maintenanceMenu = new Menu("Maintenance");
        MenuItem scheduleRepairMenuItem = new MenuItem("Schedule Repair");
        MenuItem repairStatusMenuItem = new MenuItem("Repair Status");
        maintenanceMenu.getItems().addAll(scheduleRepairMenuItem, repairStatusMenuItem);
        scheduleRepairMenuItem.setOnAction(e -> handle_ScheduleRepair());
        repairStatusMenuItem.setOnAction(e -> handle_RepairStatus());

        // Analytics menu
        Menu analyticsMenu = new Menu("Analytics");
        MenuItem generateAnalyticsMenuItem = new MenuItem("Generate Analytics");
        MenuItem viewPastAnalyticsMenuItem = new MenuItem("View Past Analytics");
        analyticsMenu.getItems().addAll(generateAnalyticsMenuItem, viewPastAnalyticsMenuItem);
        generateAnalyticsMenuItem.setOnAction(e -> handle_GenerateAnalytics());
        viewPastAnalyticsMenuItem.setOnAction(e -> handle_ViewPastAnalytics());

        // Add menus to menu bar
        menuBar.getMenus().addAll(fileMenu, inventoryMenu, salesMenu, storeMenu, maintenanceMenu, analyticsMenu);

        // Set the menu bar at the top of the BorderPane
        root.setTop(menuBar);

        // Create a SplitPane for the middle section
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);

        // Upper section
        VBox upperSection = new VBox();
        upperSection.setStyle("-fx-background-color: lightgray;");
        upperSection.setPrefHeight(300); // Set preferred height
        // Add content to the upper section if needed

        // Lower section
        VBox lowerSection = new VBox();
        lowerSection.setStyle("-fx-background-color: white;");
        lowerSection.setPrefHeight(300); // Set preferred height
        // Add content to the lower section if needed

        // Divider line
        splitPane.setDividerPositions(0.5f); // Set divider position
        splitPane.getItems().addAll(upperSection, lowerSection);

        // Set the SplitPane to the center of the BorderPane
        root.setCenter(splitPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Music System Inventory");
        primaryStage.show();
    }

    // Other methods here...

    public void handle_SignOut() {
        try {
            App.setRoot("SignInPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle_Close() {
        try {
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void handle_ViewInstrument() {
        InventoryManagementScene.displayInstrumentManagement();
    }

    public void handle_ViewArchive() {
        ArchiveScene.displayArchive();
    }

    public void handle_NewSaleTransaction() {
        SaleTransactionScene.displaySaleTransactions();
    }

    public void handle_SaleRecords() {
        SaleRecordScene.displaySaleRecords();
    }

    public void handle_NewStoreTransaction() {
        StoreTransactionScene.displayStoreTransactions();
    }

    public void handle_StoreRecords() {
        StoreRecordScene.displayStoreRecords();
    }

    public void handle_ScheduleRepair() {
        ScheduleRepairScene.displayScheduleRepair();
    }

    public void handle_RepairStatus() {
        RepairStatusScene.displayRepairs();
    }

    public void handle_GenerateAnalytics() {
        InventoryAnalyticsScene.displayInventoryAnalytics();
    }

    public void handle_ViewPastAnalytics() {
        ViewPastAnalyticsScene.displayPastAnalytics();
    }

    public static void main(String[] args) {
        launch(args);
    }
}