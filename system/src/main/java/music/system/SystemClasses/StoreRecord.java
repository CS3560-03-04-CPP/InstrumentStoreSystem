package music.system.SystemClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

public class StoreRecord {

    // Attributes
    private String invoiceNumber;             // Unique invoice number for the store record
    private Date date;                        // Date of the store record

    // Constructor
    public StoreRecord(String invoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

    // Save to MySQL method
    public void saveToMySQL() {
        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Instrument_Store_System", "username", "password");

            // Define SQL query to insert store record data into the database
            String query = "INSERT INTO store_records (invoice_number, date) VALUES (?, ?)";

            // Create PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, invoiceNumber);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            System.out.println("Connection closed to the database: StoreRecord");
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Override toString method to provide a string representation of the StoreRecord object
    @Override
    public String toString() {
        return "StoreRecord{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
