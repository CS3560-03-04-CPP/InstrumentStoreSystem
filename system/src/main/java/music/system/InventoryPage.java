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
    public void handle_ViewInstrument(){InventoryManagementScene.displayInstrumentManagement();}

    public void handle_ViewArchive(){ArchiveScene.displayArchive();}

    public void handle_NewSaleTransaction(){SaleTransactionScene.displaySaleTransactions();}
    public void handle_SaleRecords(){SaleRecordScene.displaySaleRecords();}
    
    public void handle_NewStoreTransaction(){StoreTransactionScene.displayStoreTransactions();}
    public void handle_StoreRecords(){StoreRecordScene.displayStoreRecords();}

    public void handle_GenerateAnalytics(){InventoryAnalyticsScene.displayInventoryAnalytics();}
    public void handle_ViewPastAnalytics(){ViewPastAnalyticsScene.displayPastAnalytics();}

    public void handle_ScheduleRepair(){ScheduleRepairScene.displayScheduleRepair();}
    public void handle_RepairStatus(){RepairStatusScene.displayRepairs();}
    
}
