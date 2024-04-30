package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import music.system.DatabaseManager;

/**
 * System Class, archiveItem: This class handles the INSERT command into the database for a new archive item.
 * 
 * Database table Archive, links to Item table with item_archive table: 
 * 
 *                         FK                                  PK
 *           item_archive: itemID         from item table.    (itemID)
 *                         archive_itemID from archive table. (serialNumber + itemID)
 * 
 * The user is not given the ability to delete entries, this archive class should be used to filter out
 * items from showing in available stock.
 */
public class archiveItem {

    private int archiveID;
    private int itemID;

    //Constructor
    public archiveItem(int serialNumber, int itemID){
        this.archiveID = serialNumber + itemID;
        this.itemID = itemID;
        saveToDatabase();
    }

    // Method to save an archived item to the database
    public void saveToDatabase() {
        try {
            // Establish connection to the database
            Connection connection = DatabaseManager.getConnection();

            // Define the columns explicitly, excluding the first key attribute.
            // Update query for main archive table.
            String insertQuery = "INSERT INTO archive (itemID, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description) " +
                                 "SELECT ?, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description FROM item WHERE itemID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, archiveID);
            preparedStatement.setInt(2, itemID);

            // Execute the insert query
            preparedStatement.executeUpdate();
            
            // Close resources
            preparedStatement.close();

            System.out.println("Data saved successfully!");
        } catch (SQLException e) {
            System.err.println("SQLException occurred:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }

        try {
            // Establish connection to the database
            Connection connection = DatabaseManager.getConnection();

            // Update query for the linked table.
            String insertItemArchiveQuery = "INSERT INTO item_archive (itemID, archive_itemID) VALUES (?, ?)";
            PreparedStatement itemArchiveStatement = connection.prepareStatement(insertItemArchiveQuery);
            itemArchiveStatement.setInt(1, itemID);
            itemArchiveStatement.setInt(2, archiveID);

            // Execute the insert query
            itemArchiveStatement.executeUpdate();

            // Close resources
            itemArchiveStatement.close();
            

            System.out.println("Data saved successfully!");
        } catch (SQLException e) {
            System.err.println("SQLException occurred:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }

    // Getters and Setters.
    public int getArchiveID() {return archiveID;}

    public void setArchiveID(int archiveID) {this.archiveID = archiveID;}

}