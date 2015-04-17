package view.purchaseOrder;

import controller.PurchaseOrderController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

import model.ItemData;
import model.PurchaseOrder;
import view.CellEdit;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;

public class ViewPO extends ViewTemplate implements Observer {

	PurchaseOrderController poController;
	private TabPO tab;
	private JFrame parent;
	private DecimalFormat df;
	private SimpleDateFormat dateFormat; 
	private String sDate;

	public ViewPO(JFrame parent, TabPO tab) {
		super();
		this.parent=parent;
		this.tab = tab;
		df = new DecimalFormat("#,###,###,###,##0.00");
		df.setMaximumFractionDigits(2);
		dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		poController = PurchaseOrderController.getInstance();
		poController.registerObserver(this);

	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
		setColCount(6);
		String headers[] = { "P.O. #", "Supplier", "Invoice#", "Date",
				"Grand Total", "" };
		getModel().setColumnIdentifiers(headers);
		setColWidth(0, 50);
		setColWidth(1, 150);
		setColWidth(2, 80);
		setColWidth(3, 100);
		setColWidth(4, 150);
	    setRightCellRenderer(4);
		setColWidth(5, 15);
		setColRendEdit(new POCell(), new POCell());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// System.out.println("WE GO HERE BERI HARD");
		clearTable();
		PurchaseOrder purchaseOrder;
		Iterator data = poController.getAll();
		while (data.hasNext()) {
			// System.out.println("WE HERE BERI BERI BERI HARD");
			purchaseOrder = (PurchaseOrder) data.next();
			sDate = dateFormat.format(purchaseOrder.getDate());
			
			
			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(purchaseOrder.getIdNo(),
					getModel().getRowCount() - 1, 0);
			getModel().setValueAt(purchaseOrder.getSupplier().getName(),
					getModel().getRowCount() - 1, 1);
			getModel().setValueAt(purchaseOrder.getInvoiceNo(),
					getModel().getRowCount() - 1, 2);
			getModel().setValueAt(sDate,
					getModel().getRowCount() - 1, 3);
			getModel().setValueAt(df.format(purchaseOrder.computeGrandTotal()),
					getModel().getRowCount() - 1, 4);
			getModel().setValueAt(new POCellEdit(parent, purchaseOrder, tab),
					getModel().getRowCount() - 1, 5);
		}
		packTable();
	}
        
        public void filterPopulate(Iterator data) {
        clearTable();
		PurchaseOrder purchaseOrder;
		while (data.hasNext()) {
			purchaseOrder = (PurchaseOrder) data.next();
			sDate = dateFormat.format(purchaseOrder.getDate());
			
			
			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(purchaseOrder.getType()+"-"+purchaseOrder.getIdNo(),
					getModel().getRowCount() - 1, 0);
			getModel().setValueAt(purchaseOrder.getSupplier().getName(),
					getModel().getRowCount() - 1, 1);
			getModel().setValueAt(purchaseOrder.getInvoiceNo(),
					getModel().getRowCount() - 1, 2);
			getModel().setValueAt(sDate,
					getModel().getRowCount() - 1, 3);
			getModel().setValueAt(df.format(purchaseOrder.computeGrandTotal()),
					getModel().getRowCount() - 1, 4);
			getModel().setValueAt(new POCellEdit(parent, purchaseOrder, tab),
					getModel().getRowCount() - 1, 5);
		}
		packTable();
    }

}
