package music.system;

import java.io.IOException;

/**
 * InventoryPage: This class represents the inventory page of the music system application.
 */
public class InventoryPage {

    public void handleSignOut() throws IOException{App.setRoot("SignInPage");}
    public void handleClose(){System.exit(0);}
    public void handleAddInstrument(){}
    public void handleRemoveInstrument(){}
    public void handleInstrumentDetails(){}
    public void handleArchiveInstrument(){}
    public void handleViewArchive(){}
    public void handleDeleteArchiveInstrument(){}
    public void handleNewSaleTransaction(){}
    public void handleSaleRecords(){}
    public void handleNewStoreTransaction(){}
    public void handleStoreRecords(){}
    public void handleRepairStatus(){}
    public void handleScheduleRepair(){}
    public void handleGenerateAnalytics(){}
    public void handleViewPast(){}



}