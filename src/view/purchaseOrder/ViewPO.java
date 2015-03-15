package view.purchaseOrder;

import controller.PurchaseOrderController;
import java.util.Iterator;
import model.ItemData;
import model.PurchaseOrder;
import view.CellEdit;
import view.Observer;
import view.ViewTemplate;

public class ViewPO extends ViewTemplate implements Observer{

        PurchaseOrderController poController;
    
        public ViewPO() {
		super();
        poController = PurchaseOrderController.getInstance();
        poController.registerObserver(this);
	}
        
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(5);
		String headers[]={"P.O. #", "Supplier", "Date", "Grand Total", ""};
		getModel().setColumnIdentifiers(headers);
		setColWidth(0, 150);
		setColWidth(1, 150);
		setColWidth(2, 50);
		setColWidth(3, 80);
		setColWidth(4, 15);
		setColRendEdit(4);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
        
        @Override
        public void update() {
            System.out.println("WE GO HERE BERI HARD");
            clearTable();
            PurchaseOrder purchaseOrder;
            Iterator data = poController.getAll();
            while(data.hasNext())
            {
                System.out.println("WE HERE BERI BERI BERI HARD");
                purchaseOrder = (PurchaseOrder) data.next();
                getModel().setRowCount(getModel().getRowCount() + 1);
                getModel().setValueAt(purchaseOrder.getIdNo(), getModel().getRowCount() - 1, 0);
                getModel().setValueAt(purchaseOrder.getSupplier().getName(), getModel().getRowCount() - 1, 1);
                getModel().setValueAt(purchaseOrder.getDate(), getModel().getRowCount() - 1, 2);
                getModel().setValueAt(purchaseOrder.computeGrandTotal(), getModel().getRowCount() - 1, 3);
                getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 4);
            }
	}

}
