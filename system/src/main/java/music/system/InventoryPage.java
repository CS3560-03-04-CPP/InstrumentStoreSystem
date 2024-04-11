package music.system;

import java.io.IOException;

/**
 * InventoryPage: This class represents the inventory Stage of the music system application.
 */
public class InventoryPage {

    public void handle_SignOut() throws IOException{App.setRoot("SignInPage");}
    public void handle_Close(){System.exit(0);}


    /*
     * Please complete your assigned classes in the files you created, this is
     * where those methods will be called when a button is pressed in the GUI. 
     */
    public void handle_AddInstrument(){}
    public void handle_RemoveInstrument(){}
    public void handle_InstrumentDetails(){}

    public void handle_ArchiveInstrument(){}
    public void handle_ViewArchive(){}
    public void handle_DeleteArchiveInstrument(){}

    public void handle_NewSaleTransaction(){}
    public void handle_SaleRecords(){}
    
    public void handle_NewStoreTransaction(){}
    public void handle_StoreRecords(){}

    public void handle_GenerateAnalytics(){}
    public void handle_ViewPastAnalytics(){}

    public void handle_RepairStatus(){}
    public void handle_ScheduleRepair(){}
    



}