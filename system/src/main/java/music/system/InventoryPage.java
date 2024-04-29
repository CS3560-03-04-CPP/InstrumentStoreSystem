package music.system;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Application Class, InventoryPage: This class represents the inventory root Stage of the music system application.
 * 
 * The main user GUI interaface which connects all the buttons a user can interact with to their respective classes.
 * 
 */
public class InventoryPage {

    public void handle_SignOut() throws IOException{App.setRoot("SignInPage");}
    public void handle_Close(){ try {DatabaseManager.closeConnection();} catch (SQLException e) {}; System.exit(0);}

    public void handle_ViewInstrument(){InventoryManagementScene.displayInstrumentManagement();}
    public void handle_ViewArchive(){ArchiveScene.displayArchive();}

    public void handle_NewSaleTransaction(){SaleTransactionScene.displaySaleTransactions();}
    public void handle_SaleRecords(){SaleRecordScene.displaySaleRecords();}
    
    public void handle_NewStoreTransaction(){StoreTransactionScene.displayStoreTransactions();}
    public void handle_StoreRecords(){StoreRecordScene.displayStoreRecords();}

    public void handle_ScheduleRepair(){ScheduleRepairScene.displayScheduleRepair();}
    public void handle_RepairStatus(){RepairStatusScene.displayRepairs();}

    public void handle_GenerateAnalytics(){InventoryAnalyticsScene.displayInventoryAnalytics();}
    public void handle_ViewPastAnalytics(){ViewPastAnalyticsScene.displayPastAnalytics();}
    
}
