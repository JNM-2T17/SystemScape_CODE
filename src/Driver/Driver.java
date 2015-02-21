/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Driver;

import controller.InventoryItemController;
import controller.PurchaseOrderController;
import controller.SupplierController;
import java.util.Date;

/**
 *
 * @author Christian Gabriel
 */
public class Driver {
    public static void main(String[] args) {
        SupplierController sc = new SupplierController();
        PurchaseOrderController poc = new PurchaseOrderController();
        InventoryItemController iic = new InventoryItemController();
        
        sc.addSupplierContact("supplier", "type1", 1);
        sc.addSupplierContact("supplier", "type2", 2);
        sc.addSupplier("supplier", "this,the,address,format");
        
        poc.addItem("name", "desc", 12.3f, 1);
        poc.addItem("name2", "desc2", 23.4f, 2);
        poc.addPurchaseOrder(new Date(), 11111111, "type", "supplier");
        
        iic.addInventoryItem("name", "description", 12.3f, "status", "classification", 11111111);
        iic.addInventoryItem("name", "description2", 23.4f, "status2", "classification2", 22222222);
        iic.addInventoryItem("name2", "description", 12.3f, "status", "classification", 33333333);
        iic.addInventoryItem("name2", "description2", 23.4f, "status2", "classification2", 44444444);
        
        
    }
    
}
