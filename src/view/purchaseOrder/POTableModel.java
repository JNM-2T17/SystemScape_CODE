package view.purchaseOrder;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import controller.PurchaseOrderController;
import model.ItemData;
import model.PurchaseOrder;


public class POTableModel extends DefaultTableModel{
	private static final long	serialVersionUID	= 1L;

	public POTableModel(PurchaseOrderController poController) {
		
		PurchaseOrder po = poController.getPurchaseOrder();
		Iterator itemList = po.getItems();
		addColumn("Item");
		addColumn("Description");
		addColumn("Quantity");
		addColumn("Unit Price");
		addColumn("Amount");

		
		while(itemList.hasNext())
		{
			ItemData i = (ItemData) itemList.next();
			addRow(new Object[] { i.getName(), i.getDescription(), po.getQuantity(i), i.getUnitPrice(), poController.computeAmount(po.getQuantity(i),i.getUnitPrice()) });
		}
	}
	
	public boolean isCellEditable(int row, int column) {
        return false;
    }

}
