package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import music.system.DatabaseManager;

/**
 * System Class, StoreRecord: This class handles the creation of a new store record entry.
 * 
 * Database table store_records, links to store_transactions table with a item_store_records:
 * 
 *                               FK                                                                  PK
 *           item_store_records: store_transacrions_transaction_id from store_transacions table     (transaction_id)
 *                               store_records_invoice_numbner     from store_records table         (invoice_number)
 * 
 */
public class StoreRecord {

    private String invoiceNumber;             
    private Date date;                        
    private int transactionID;

    // Constructor
    public StoreRecord(String invoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

    // Save to MySQL method
    public void saveToMySQL(Integer id) {


        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert store record data into the database
            String query = "INSERT INTO store_records (invoice_number, date) VALUES (?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, invoiceNumber);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Establish connection to MySQL database
            Connection connection = DatabaseManager.getConnection();

            // Define SQL query to insert store record data into the database
            String query = "INSERT INTO item_store_records (store_transactions_transaction_id, store_records_invoice_number) VALUES (?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, invoiceNumber);

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public String getInvoiceNumber() {return invoiceNumber;}

    public void setInvoiceNumber(String invoiceNumber) {this.invoiceNumber = invoiceNumber;}

    public Date getDate() {return date;}

    public Integer getID(){Integer id = transactionID; return id;}

    public void setDate(Date date) {this.date = date;}

}
