package view.inventory;

import controller.AssignmentController;
import controller.ContractController;
import controller.EmployeeController;
import controller.ITAssetController;
import controller.InventoryItemController;
import controller.WarrantyController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import model.Assignment;
import model.Contract;
import model.Employee;
import model.ITAsset;
import model.InventoryItem;
import model.Warranty;
import view.CellEdit;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;

public class ViewInventory extends ViewTemplate implements Observer,
		ActionListener {

	InventoryItemController iiController;
	ITAssetController itAssetController;
	AssignmentController assignmentController;
	EmployeeController employeeController;
	ContractController contractController;
	WarrantyController warrantyController;

	private DefaultTableModel tglModel;
	private TabInventory tab;
	private SimpleDateFormat dateFormat;

	public ViewInventory(TabInventory tab) {
		super();
		this.tab = tab;
		iiController = InventoryItemController.getInstance();
		itAssetController = ITAssetController.getInstance();
		assignmentController = AssignmentController.getInstance();
		employeeController = EmployeeController.getInstance();
		contractController = ContractController.getInstance();
		warrantyController = WarrantyController.getInstance();
		dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		iiController.registerObserver(this);
		assignmentController.registerObserver(this);
	}

	@Override
	public void initialize() {
		setColCount(12);
		String headers[] = { "Item", "Description", "Type", "Status",
				"Assignee", "Location", "Asset Tag", "Service Tag", "Invoice#",
				"Delivery Date", "End of Contract", "End of Warranty", "" };

		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());

		String headers2[] = { "Item", "Description", "Type", "Quantity" };
		tglModel = new DefaultTableModel(headers2, 4) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}

			public boolean isFocusable(int rowIndex, int mColIndex) {
				return true;
			}

			public boolean isCellSelectable(int rowIndex, int mColIndex) {
				return true;
			}
		};
		tglModel.setRowCount(0);
		activateToggle(tglModel);
		getToggle().addActionListener(this);

		packTable();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
	
	public DefaultTableModel getModel(){
		if(getToggle().isSelected()) return tglModel;
		else return super.getModel();
	}
	
	public boolean isToggle(){
		return getToggle().isSelected();
	}

	@Override
	public void update() {
		clearTable();
		InventoryItem inventoryItem;
		ITAsset itAsset;
		Contract contract;
		Warranty warranty;
		Iterator data = iiController.getAll();
		String employee;
		Assignment assignment;

		while (data.hasNext()) {
			Object object = data.next();
			inventoryItem = (InventoryItem) object;
			employee = null;
			warranty = (Warranty) warrantyController.getObject(String
					.valueOf(inventoryItem.getID()));
			contract = (Contract) contractController.getObject(String
					.valueOf(inventoryItem.getID()));
			assignment = (Assignment) assignmentController.getObject(""
					+ inventoryItem.getID());
			if (assignment != null) {
				System.out.println("update "
						+ assignment.getEmployee().getName());
				employee = assignment.getEmployee().getName();
			}

			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(inventoryItem.getName(),
					getModel().getRowCount() - 1, 0);
			getModel().setValueAt(inventoryItem.getDescription(),
					getModel().getRowCount() - 1, 1);
			getModel().setValueAt(inventoryItem.getClassification(),
					getModel().getRowCount() - 1, 2);
			getModel().setValueAt(inventoryItem.getStatus(),
					getModel().getRowCount() - 1, 3);
			getModel().setValueAt(inventoryItem.getLocation(),
					getModel().getRowCount() - 1, 5);
			getModel().setValueAt(inventoryItem.getInvoiceNo(),
					getModel().getRowCount() - 1, 8);

			if (object instanceof ITAsset) {
				inventoryItem = (ITAsset) object;

				getModel().setValueAt(((ITAsset) inventoryItem).getAssetTag(),
						getModel().getRowCount() - 1, 6);
				getModel().setValueAt(
						((ITAsset) inventoryItem).getServiceTag(),
						getModel().getRowCount() - 1, 7);

				Date deliveryDate = ((ITAsset) inventoryItem).getDeliveryDate();
				if (deliveryDate != null) {
					getModel().setValueAt(dateFormat.format(deliveryDate),
							getModel().getRowCount() - 1, 9);
				} else {
					getModel().setValueAt(deliveryDate,
							getModel().getRowCount() - 1, 9);
				}
			}
			if (employee != null) {

				getModel()
						.setValueAt(employee, getModel().getRowCount() - 1, 4);
			}
			if (contract != null) {
				getModel().setValueAt(dateFormat.format(contract.getEndDate()),
						getModel().getRowCount() - 1, 10);
			}
			if (warranty != null) {
				getModel().setValueAt(dateFormat.format(warranty.getEndDate()),
						getModel().getRowCount() - 1, 11);
			}
			getModel().setValueAt(new InventoryCellEdit(inventoryItem, tab),
					getModel().getRowCount() - 1, 12);
		}

		data = iiController.getAllQuantity();

		for (int i = 0; i < tglModel.getRowCount(); i++) {
			tglModel.removeRow(i);
		}
		tglModel.setRowCount(0);
		while (data.hasNext()) {
			inventoryItem = (InventoryItem) data.next();
			tglModel.setRowCount(tglModel.getRowCount() + 1);
			tglModel.setValueAt(inventoryItem.getName(),
					tglModel.getRowCount() - 1, 0);
			tglModel.setValueAt(inventoryItem.getDescription(),
					tglModel.getRowCount() - 1, 1);
			tglModel.setValueAt(inventoryItem.getClassification(),
					tglModel.getRowCount() - 1, 2);
			tglModel.setValueAt(inventoryItem.getQuantity(),
					tglModel.getRowCount() - 1, 3);
		}

		packTable();
	}

	public void filterPopulate(Iterator data) {
		clearTable();
		InventoryItem inventoryItem;
		ITAsset itAsset;
		Contract contract;
		Warranty warranty;
		String employee;
		Assignment assignment;

		while (data.hasNext()) {
			Object object = data.next();
			inventoryItem = (InventoryItem) object;
			employee = null;
			warranty = (Warranty) warrantyController.getObject(String
					.valueOf(inventoryItem.getID()));
			contract = (Contract) contractController.getObject(String
					.valueOf(inventoryItem.getID()));
			assignment = (Assignment) assignmentController.getObject(""
					+ inventoryItem.getID());
			if (assignment != null) {
				System.out.println("update "
						+ assignment.getEmployee().getName());
				employee = assignment.getEmployee().getName();
			}

			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(inventoryItem.getName(),
					getModel().getRowCount() - 1, 0);
			getModel().setValueAt(inventoryItem.getDescription(),
					getModel().getRowCount() - 1, 1);
			getModel().setValueAt(inventoryItem.getClassification(),
					getModel().getRowCount() - 1, 2);
			getModel().setValueAt(inventoryItem.getStatus(),
					getModel().getRowCount() - 1, 3);
			getModel().setValueAt(inventoryItem.getLocation(),
					getModel().getRowCount() - 1, 5);
			getModel().setValueAt(inventoryItem.getInvoiceNo(),
					getModel().getRowCount() - 1, 8);

			if (object instanceof ITAsset) {
				inventoryItem = (ITAsset) object;

				getModel().setValueAt(((ITAsset) inventoryItem).getAssetTag(),
						getModel().getRowCount() - 1, 6);
				getModel().setValueAt(
						((ITAsset) inventoryItem).getServiceTag(),
						getModel().getRowCount() - 1, 7);
				getModel().setValueAt(
						((ITAsset) inventoryItem).getDeliveryDate(),
						getModel().getRowCount() - 1, 9);
			}
			if (employee != null) {

				getModel()
						.setValueAt(employee, getModel().getRowCount() - 1, 4);
			}
			if (contract != null && warranty != null) {
				getModel().setValueAt(contract.getEndDate(),
						getModel().getRowCount() - 1, 10);
				getModel().setValueAt(warranty.getEndDate(),
						getModel().getRowCount() - 1, 11);
			}
			getModel().setValueAt(new InventoryCellEdit(inventoryItem, tab),
					getModel().getRowCount() - 1, 12);
		}

		data = iiController.getAllQuantity();

		for (int i = 0; i < tglModel.getRowCount(); i++) {
			tglModel.removeRow(i);
		}
		tglModel.setRowCount(0);
		while (data.hasNext()) {
			inventoryItem = (InventoryItem) data.next();
			tglModel.setRowCount(tglModel.getRowCount() + 1);
			tglModel.setValueAt(inventoryItem.getName(),
					tglModel.getRowCount() - 1, 0);
			tglModel.setValueAt(inventoryItem.getDescription(),
					tglModel.getRowCount() - 1, 1);
			tglModel.setValueAt(inventoryItem.getClassification(),
					tglModel.getRowCount() - 1, 2);
			tglModel.setValueAt(inventoryItem.getQuantity(),
					tglModel.getRowCount() - 1, 3);
		}

		packTable();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		toggle(new PanelCell(), new PanelCell());
	}
}
