package view.inventory;

import controller.AssignmentController;
import controller.ContractController;
import controller.EmployeeController;
import controller.ITAssetController;
import controller.InventoryItemController;
import controller.WarrantyController;

import java.util.Iterator;
import model.Contract;
import model.Employee;
import model.ITAsset;

import model.InventoryItem;
import model.Warranty;
import view.CellEdit;
import view.Observer;
import view.ViewTemplate;

public class ViewInventory extends ViewTemplate implements Observer{
    
        InventoryItemController iiController;
        ITAssetController itAssetController;
        AssignmentController assignmentController;
        EmployeeController employeeController;
        ContractController contractController;
        WarrantyController warrantyController;
        
	public ViewInventory(){
		super();
                iiController = InventoryItemController.getInstance();
                itAssetController = ITAssetController.getInstance();
                assignmentController = AssignmentController.getInstance();
                employeeController = EmployeeController.getInstance();
                contractController = ContractController.getInstance();
                warrantyController = WarrantyController.getInstance();
                
                iiController.registerObserver(this);
	}
        
	@Override
	public void initialize() {
		setColCount(12);
                String headers[] = { "Item", "Description", "Type", "Location", "Asset Tag", "Service Tag", "Assignee", "Invoice#", "Delivery Date", "End of Contract", "End of Warranty", "" };

		//String headers[] = { "Item", "Description", "Type", "Quantity", "Location", "Asset Tag", "Service Tag", "Assignee", "Invoice#", "Delivery Date", "End of Contract", "End of Warranty", "" };
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(11);
		
		
		packTable();
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void update() {
        clearTable();
        InventoryItem inventoryItem;
        ITAsset itAsset;
        Contract contract;
        Warranty warranty;
        Employee employee;
        Iterator data = iiController.getAll();
        int employeeid;
        
        
        while(data.hasNext()){
            Object object = data.next();
            inventoryItem = (InventoryItem)object;
            employeeid=0;
            warranty = (Warranty) warrantyController.getObject(String.valueOf(inventoryItem.getID()));
            contract = (Contract) contractController.getObject(String.valueOf(inventoryItem.getID()));
            Object idObject = assignmentController.getObject(String.valueOf(inventoryItem.getID()));
            if(idObject != null){
                employeeid = (int) idObject;
            }
            
            getModel().setRowCount(getModel().getRowCount() + 1);
            getModel().setValueAt(inventoryItem.getName(), getModel().getRowCount() - 1, 0);
            getModel().setValueAt(inventoryItem.getDescription(), getModel().getRowCount() - 1, 1);
            getModel().setValueAt(inventoryItem.getClassification(), getModel().getRowCount() - 1, 2);
            //getModel().setValueAt(inventoryItem.getStatus(), getModel().getRowCount() - 1, 3);
            getModel().setValueAt(inventoryItem.getLocation(), getModel().getRowCount() - 1, 3);
            getModel().setValueAt(inventoryItem.getInvoiceNo(), getModel().getRowCount() - 1, 7);
             
            if(object instanceof ITAsset){
                inventoryItem = (ITAsset) object;
                
                getModel().setValueAt(((ITAsset)inventoryItem).getAssetTag(), getModel().getRowCount() - 1, 4);
                getModel().setValueAt(((ITAsset)inventoryItem).getServiceTag(), getModel().getRowCount() - 1, 5);
                getModel().setValueAt(((ITAsset)inventoryItem).getDeliveryDate(), getModel().getRowCount() - 1, 8);
            }
            
            if(employeeid!=0){
                employee = (Employee) employeeController.getObject(String.valueOf(employeeid));
                getModel().setValueAt(employee.getName(), getModel().getRowCount() - 1, 6);
            }
            if(contract!=null && warranty!=null){
                getModel().setValueAt(contract.getEndDate(), getModel().getRowCount() - 1, 9);
                getModel().setValueAt(warranty.getEndDate(), getModel().getRowCount() - 1, 10);
            }
            getModel().setValueAt(new CellEdit(), getModel().getRowCount() - 1, 11);
        }
    }
}
