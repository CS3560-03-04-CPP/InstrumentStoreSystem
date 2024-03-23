package music.system;

import java.util.Date;

public class SaleRecord {

    // Attributes
    private String orderId;             // Unique order ID for the sale
    private Date date;                  // Date of the sale
    private String buyerName;           // Name of the buyer
    private String buyerPhoneNumber;    // Phone number of the buyer
    private double soldPrice;           // Price at which the item was sold

    // Constructor
    public SaleRecord(String orderId, Date date, String buyerName, String buyerPhoneNumber, double soldPrice) {
        this.orderId = orderId;
        this.date = date;
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.soldPrice = soldPrice;
    }

    // Getter and Setter methods 
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    // Override toString method to provide a string representation of the SaleRecord object
    @Override
    public String toString() {
        return "SaleRecord{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhoneNumber='" + buyerPhoneNumber + '\'' +
                ", soldPrice=" + soldPrice +
                '}';
    }
}
