package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import music.system.DatabaseManager;

public class archiveItem {

    private Item selectedItem;
    private int archiveID;

    public archiveItem(Item selectedItem){
        this.selectedItem = selectedItem;
        this.archiveID = selectedItem.getserialNumber() + selectedItem.getitemID();
        saveToDatabase();
    }

    public void saveToDatabase() {
        try {
            Connection connection = DatabaseManager.getConnection();
            // Define the columns explicitly, excluding the first key attribute.
            // Update query for main archive table.
            String insertQuery = "INSERT INTO archive (itemID, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description) " +
                                 "SELECT ?, name, category, brand, dateManufactured, serialNumber, manufacturerPrice, retailPrice, description FROM item WHERE itemID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, archiveID);
            preparedStatement.setInt(2, selectedItem.getitemID());

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
            Connection connection = DatabaseManager.getConnection();
            // Update query for the linked table.
            String insertItemArchiveQuery = "INSERT INTO item_archive (itemID, archive_itemID) VALUES (?, ?)";
            PreparedStatement itemArchiveStatement = connection.prepareStatement(insertItemArchiveQuery);
            itemArchiveStatement.setInt(1, selectedItem.getitemID());
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
}