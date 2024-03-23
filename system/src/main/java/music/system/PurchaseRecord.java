package music.system;

import java.util.Date;

public class PurchaseRecord {
    
    private String invoiceNumber;
    private Date date;

    public PurchaseRecord(String invoiceNumber, Date date) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
    }

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
        PurchaseRecord that = (PurchaseRecord) o;
        return invoiceNumber.equals(that.invoiceNumber) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = invoiceNumber.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}