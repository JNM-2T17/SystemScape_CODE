package view.supplier;

import controller.PurchaseOrderController;
import view.Observer;
import view.ViewTemplate;

public class ViewContactsTable extends ViewTemplate implements Observer{
//	 PurchaseOrderController poController;
	    
     public ViewContactsTable() 
     {
		 super();
//	     poController = new PurchaseOrderController();
//	     poController.registerObserver(this);
	}
     
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(2);
		String headers[]={"Type", "Value"};
		getModel().setColumnIdentifiers(headers);
		setColWidth(0, 80);
		setColWidth(1, 150);
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
