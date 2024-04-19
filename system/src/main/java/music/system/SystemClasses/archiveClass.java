package music.system.SystemClasses;

import java.util.HashMap;
import java.util.Map;

public class archiveClass {
    private Map<String, Instrument> inventory;

    public archiveClass() {
        this.inventory = new HashMap<>();
    }

    // Adds a new instrument to the inventory
    public void addInstrument(String instrumentID, String instrumentName, String instrumentType, double price, int quantity) {
        if (inventory.containsKey(instrumentID)) {
            System.out.println("Instrument with ID " + instrumentID + " already exists.");
        } else {
            Instrument newInstrument = new Instrument(instrumentID, instrumentName, instrumentType, price, quantity);
            inventory.put(instrumentID, newInstrument);
            System.out.println("Instrument added: " + newInstrument);
        }
    }

    // Updates the details of an existing instrument
    public void updateInstrument(String instrumentID, String instrumentName, String instrumentType, double price, int quantity) {
        if (inventory.containsKey(instrumentID)) {
            Instrument updatedInstrument = new Instrument(instrumentID, instrumentName, instrumentType, price, quantity);
            inventory.put(instrumentID, updatedInstrument);
            System.out.println("Instrument updated: " + updatedInstrument);
        } else {
            System.out.println("No instrument found with ID " + instrumentID);
        }
    }

    // Removes an instrument from the inventory
    public void removeInstrument(String instrumentID) {
        if (inventory.containsKey(instrumentID)) {
            inventory.remove(instrumentID);
            System.out.println("Instrument with ID " + instrumentID + " has been removed.");
        } else {
            System.out.println("No instrument found with ID " + instrumentID);
        }
    }

    // Lists all instruments in the inventory
    public void listInstruments() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Listing all instruments:");
            for (Instrument instrument : inventory.values()) {
                System.out.println(instrument);
            }
        }
    }

    // Nested Instrument class
    private static class Instrument {
        private String instrumentID;
        private String instrumentName;
        private String instrumentType;
        private double price;
        private int quantity;

        public Instrument(String instrumentID, String instrumentName, String instrumentType, double price, int quantity) {
            this.instrumentID = instrumentID;
            this.instrumentName = instrumentName;
            this.instrumentType = instrumentType;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "Instrument{" +
                   "ID='" + instrumentID + '\'' +
                   ", Name='" + instrumentName + '\'' +
                   ", Type='" + instrumentType + '\'' +
                   ", Price=" + price +
                   ", Quantity=" + quantity +
                   '}';
        }
    }
}
