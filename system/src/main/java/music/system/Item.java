public class Item {

    // Declaring Variables
    private int itemID;

    private String name;
    private String category;
    private String brand;
    private String dateManufactured;

    private int serialNumber;
    private double manufacturerPrice;
    private double retailPrice;

    private String description;

    // Constructor
    public Item(int itemID, int serialNumber, String name, String category, String brand, String dateManufactured,
            String description, double manufacturerPrice, double retailPrice) {
        this.itemID = itemID;
        this.serialNumber = serialNumber;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.dateManufactured = dateManufactured;
        this.description = description;
        this.manufacturerPrice = manufacturerPrice;
        this.retailPrice = retailPrice;

    }

}