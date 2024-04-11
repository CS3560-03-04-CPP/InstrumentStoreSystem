package music.system.SystemClasses;

import java.util.Date;

public class StoreRecord {
    
    private String invoiceNumber; // Unique identifier for the purchase record
    private Date date; // Date of the purchase

    public StoreRecord(String invoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

     // Getters and Setters

    /**
     * Gets the invoice number of the purchase record.
     *
     * @return The invoice number
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the invoice number of the purchase record.
     *
     * @param invoiceNumber The invoice number to set
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * Gets the date of the purchase record.
     *
     * @return The date of the purchase
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the purchase record.
     *
     * @param date The date of the purchase to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    //Extra methods For Debugging.

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreRecord that = (StoreRecord) o;
        return invoiceNumber.equals(that.invoiceNumber) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = invoiceNumber.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}