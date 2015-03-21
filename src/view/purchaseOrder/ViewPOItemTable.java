package view.purchaseOrder;

import java.util.Iterator;

import model.PurchaseOrder;
import controller.PurchaseOrderController;
import view.CellEdit;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;

public class ViewPOItemTable extends ViewTemplate implements Observer{

	 PurchaseOrderController poController;
	    
     public ViewPOItemTable() 
     {
		 super();
	     poController = new PurchaseOrderController();
	     poController.registerObserver(this);
	}
     
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(7);
		String headers[]={"Item", "Description", "Quantity", "Unit Price", "Amount", "Delivered", ""};
		getModel().setColumnIdentifiers(headers);
		setColWidth(0, 150);
		setColWidth(1, 150);
		setColWidth(2, 15);
		setColWidth(3, 80);
		setColWidth(4, 80);
		setColWidth(5, 80);
		setColWidth(6, 15);
		setColRendEdit(new PanelCell(), new PanelCell());
//		getModel().setValueAt(new CellEdit(), 0, 6);
//		packTable();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
     
     @Override
     public void update() {
//         System.out.println("WE GO HERE BERI HARD");
//         //clearTable();
//         PurchaseOrder purchaseOrder;
//         Iterator data = poController.getAll();
//         while(data.hasNext())
//         {
//             System.out.println("WE HERE BERI BERI BERI HARD");
//             purchaseOrder = (PurchaseOrder) data.next();
//             getModel().setRowCount(getModel().getRowCount() + 1);
//			 getModel().setValueAt(purchaseOrder.getIdNo(), getModel().getRowCount() - 1, 0);
//			 getModel().setValueAt(purchaseOrder.getSupplier().getName(), getModel().getRowCount() - 1, 1);
//			 getModel().setValueAt(purchaseOrder.getDate(), getModel().getRowCount() - 1, 2);
//			 getModel().setValueAt(purchaseOrder.computeGrandTotal(), getModel().getRowCount() - 1, 3);
//             getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 4);
//         }
	}


}
