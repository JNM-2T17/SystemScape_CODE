package view.inventory;

import com.toedter.calendar.JDateChooser;
import controller.AssignmentController;

import controller.ContractController;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import model.Employee;
import model.HardwareItem;
import model.ITAsset;
import model.ITAsset.ITAssetBuilder;
import model.InventoryItem;
import model.NonITAsset;
import model.SoftwareItem;
import controller.EmployeeController;
import controller.ITAssetController;
import controller.InventoryItemController;
import controller.WarrantyController;
import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;
import model.Assignment;

import view.inventory.itemstorage.ItemStorageContract;
import view.inventory.itemstorage.ItemStorageGenInfo;
import view.inventory.itemstorage.ItemStorageGeneral;
import view.inventory.itemstorage.ItemStorageIT;
import view.inventory.itemstorage.ItemStorageNonIT;
import view.inventory.itemstorage.ItemStorageSoftware;
import view.inventory.itemstorage.ItemStorageWarranty;
import view.inventory.itemtilefield.ItemTileContractField;
import view.inventory.itemtilefield.ItemTileITField;
import view.inventory.itemtilefield.ItemTileWarrantyField;
import view.inventory.itemtilefield.TypeItemTileField;
import view.inventory.itemtileview.ItemTileContractView;
import view.inventory.itemtileview.ItemTileWarrantyView;
import view.inventory.itemtileview.TypeItemTileView;
import model.Contract;
import model.Warranty;

public class PanelRegistry implements PanelRegistration {

	private String type;
	private boolean isAdd;
	private ArrayList<ItemPanelParticipant> participantList;
	private InventoryItemDisplayManager displayManager;
	private InventoryItem currentInventoryItem;
	private EmployeeController employeeController;
        private AssignmentController assignmentController;
        
	public PanelRegistry() {
		participantList = new ArrayList<ItemPanelParticipant>();
		type = "IT";
		isAdd = true;
                employeeController = EmployeeController.getInstance();
                assignmentController = AssignmentController.getInstance();
	}

	public void clearParticipants() {
		participantList.clear();
	}
	

	@Override
	public void registerParticipant(ItemPanelParticipant itemPanelParticipant) {
		// TODO Auto-generated method stub
		participantList.add(itemPanelParticipant);
	}

	@Override
	public void unregisterParticipant(ItemPanelParticipant itemPanelParticipant) {
		// TODO Auto-generated method stub
		participantList.remove(itemPanelParticipant);
	}
	
	public void isAdd(boolean stat)
	{
		isAdd = stat;
	}

	public InventoryItem getCurrentInventoryItem()
	{
		return currentInventoryItem;
	}
	
	public void setCurrentInventoryItem(InventoryItem ii)
	{
		currentInventoryItem = ii;
	}
	@Override
	public void retrieveInformationFromAll() {
		// TODO Auto-generated method stub
		ArrayList generalInfo = new ArrayList();
		ArrayList typeInfo = new ArrayList();
		ArrayList warrantyInfo = new ArrayList();
		ArrayList contractInfo = new ArrayList();
		System.out.println("Passed RIA");
		boolean stat = true;
		int i = 0;
		if(participantList.size() > 0)
		{
			while (i < participantList.size() && stat) {
				stat=participantList.get(i).checkInput();
				i++;
				System.out.println("Status :" + stat);
			}
		}
		else
		{
		}
		

		if (stat) {
			for (i = 0; i < participantList.size(); i++) {
				if (i == 0) {
					Iterator iter = participantList.get(i)
							.retrieveInformation();
					while (iter.hasNext()) {
						generalInfo.add(iter.next());
					}
				} else if (i == 1) {
					Iterator iter2 = participantList.get(i)
							.retrieveInformation();
					while (iter2.hasNext()) {
						typeInfo.add(iter2.next());
					}
				} else if (i == 2) {
					Iterator iter3 = participantList.get(i)
							.retrieveInformation();
					while (iter3.hasNext()) {
						warrantyInfo.add(iter3.next());
					}
				} else if (i == 3) {
					Iterator iter4 = participantList.get(i)
							.retrieveInformation();
					while (iter4.hasNext()) {
						contractInfo.add(iter4.next());
					}
				}
			}
			buildInventoryItem(type, generalInfo, typeInfo, warrantyInfo, contractInfo);
		}
		
	}
	
	/**
	 * This method creates the InventoryItem then passes it to <b>ItemInventoryController</b> to either add or edit an <b>InventoryItem</b>
	 * @param type The current <b>InventoryItem</b> type
	 * @param generalInfo <b>Compilation of all the attributes retrieved from <b>ItemTileGenInfo</b>
	 * @param typeInfo <b>Compilation of all the attributes retrieved from <b>TypeItemTile</b>
	 * @param warrantyInfo <b>
	 * @param contractInfo
	 */
	private void buildInventoryItem(String type, ArrayList generalInfo, ArrayList typeInfo, ArrayList warrantyInfo, ArrayList contractInfo)
	{       	
		InventoryItem inventoryItem = null;
		float unitPrice = Float.parseFloat(generalInfo.get(2).toString());
		System.out.println(typeInfo.get(0).getClass() + "\n");
		int ID = InventoryItemController.getInstance().getID();
                ID++;
                System.out.println(ID + "company");
		if (type.equalsIgnoreCase("IT")) {

			int assetTag = Integer.parseInt(typeInfo.get(5).toString());
			if (!warrantyInfo.isEmpty()) {
				Warranty warranty = new Warranty(0,
						(Date) warrantyInfo.get(0),
						(Date) warrantyInfo.get(1));
			}
			if (!contractInfo.isEmpty()) {
				Contract contract = new Contract(0,
						(Date) contractInfo.get(1),
						(Date) contractInfo.get(2),
						Float.parseFloat((String) contractInfo.get(0)));
			}

			ITAsset itAsset = new ITAsset.ITAssetBuilder()
                                        .addID(ID)
					.addName(generalInfo.get(0).toString())
					.addDescription(generalInfo.get(1).toString())
					.addUnitPrice(unitPrice)
					.addInvoiveNo(generalInfo.get(3).toString())
					.addLocation(generalInfo.get(4).toString())
					.addStatus(generalInfo.get(5).toString())
					.addClassification("IT")
					.addAssetTag(assetTag)
					.addServiceTag(typeInfo.get(3).toString())
					.addDeliveryDate((Date)typeInfo.get(4))
					.addContract(
							new Contract(0, (Date) contractInfo.get(1),
									(Date) contractInfo.get(2),
									Float.parseFloat((String) contractInfo
											.get(0))))
					.addWarranty(
							new Warranty(0, (Date) warrantyInfo.get(0),
									(Date) warrantyInfo.get(1))).build();
			inventoryItem = itAsset;

		} else if (type.equalsIgnoreCase("Non-IT")) {
                       
			NonITAsset nonItAsset = new NonITAsset.NonITAssetBuilder()
                                        .addID(ID)
					.addName(generalInfo.get(0).toString())
					.addDescription(generalInfo.get(1).toString())
					.addUnitPrice(unitPrice)
					.addInvoiveNo(generalInfo.get(3).toString())
					.addLocation(generalInfo.get(4).toString())
					.addStatus(generalInfo.get(5).toString()) 
					.addClassification("Non-IT").build();
					
			inventoryItem = nonItAsset;
			
		} else if (type.equalsIgnoreCase("Soft")) {
                        
			SoftwareItem software = new SoftwareItem.SoftwareBuilder()
                                        .addID(ID)
					.addName(generalInfo.get(0).toString())
					.addDescription(generalInfo.get(1).toString())
					.addUnitPrice(unitPrice)
					.addInvoiveNo(generalInfo.get(3).toString())
					.addLocation(generalInfo.get(4).toString())
					.addStatus(generalInfo.get(5).toString())
					.addClassification("Soft")
					.addLicenseKey(typeInfo.get(3).toString()).build();
			
			inventoryItem = software;
			
		} else if (type.equalsIgnoreCase("Others")) {
			InventoryItem general = new InventoryItem.InventoryItemBuilder()
                                        .addID(ID)
					.addName(generalInfo.get(0).toString())
					.addDescription(generalInfo.get(1).toString())
					.addUnitPrice(unitPrice)
					.addInvoiveNo(generalInfo.get(3).toString())
					.addLocation(generalInfo.get(4).toString())
					.addStatus(generalInfo.get(5).toString())
					.addClassification("Others").build();
			
			inventoryItem = general;
		}
		System.out.println("Here");
		System.out.println(inventoryItem.getName());
		System.out.println(inventoryItem.getDescription());
		System.out.println(inventoryItem.getUnitPrice());
		System.out.println(inventoryItem.getStatus());
		System.out.println(inventoryItem.getLocation());
		System.out.println(inventoryItem.getInvoiceNo());
		
		if(isAdd){
                    InventoryItemController.getInstance().addInventoryItem(inventoryItem);
                    Employee employee = (Employee)employeeController.getObject(typeInfo.get(0).toString());
                    
                    if(!typeInfo.get(0).toString().equals("None")){
                        System.out.println(employee.getName() + " Karen\n");
                        Assignment assignment = new Assignment(inventoryItem.getID(), employee, (Date)typeInfo.get(1), (Date)typeInfo.get(2));
                        assignmentController.add(assignment);
                    }
                }
                else{ 
                    System.out.println("EDITTING FILE!");
                    System.out.println("doobie " + generalInfo.get(6).toString());
                    InventoryItemController.getInstance().editInventoryItem(inventoryItem, generalInfo.get(6).toString());
                    Assignment assignment = null;
                    Employee employee = (Employee)employeeController.getObject(typeInfo.get(0).toString());
                    if(employee != null)
                        assignment = (Assignment)assignmentController.getObject(generalInfo.get(6).toString() + " " + employee.getID());
                    Iterator i = assignmentController.filter(generalInfo.get(6).toString());
                    
                    if(assignment == null){ // checks if item is currently not assigned to the selected employee
                        if(!typeInfo.get(0).toString().equals("None")&&!typeInfo.get(0).toString().equals("Remove Assignment")){//checks if user assigned item to new employee
                            assignment = new Assignment(Integer.parseInt(generalInfo.get(6).toString()), employee, (Date)typeInfo.get(1), (Date)typeInfo.get(2));
                            if(i.hasNext()){ //checks if item is currently assigned to an employee
                                assignmentController.update(assignment);
                                System.out.println("it should enter here\n");
                            }else{
                                if(!typeInfo.get(0).toString().equals("None")){
                                    assignmentController.add(assignment);
                                }
                            }  
                        }else{
                            if(typeInfo.get(0).toString().equals("Remove Assignment")){
                                if(i.hasNext()){
                                    assignment = (Assignment)i.next();
                                    System.out.println("brothers " + assignment.getEmployee().getID());
                                    assignmentController.delete(assignment);
                                }
                            }
                        }
                    }
                }
		
	}

//	/**
//	 * Connects the <b>Tab Inventory</b> to the <b>PanelRegistry</b>
//	 * @param tabInventory
//	 */
//	public void setTabInventory(TabInventory tabInventory) {
//		this.tabInventory = tabInventory;
//	}

	public void setCurrentType(String type) {
		System.out.println("Passed setCurrentType");
		if (type.equalsIgnoreCase("IT")) {
			this.type = "IT";
			//displayManager.displayITField();
		} else if (type.equalsIgnoreCase("Non-IT")) {
			this.type = "Non-IT";
			//displayManager.displayNonITField();
		} else if (type.equalsIgnoreCase("Soft")) {
			this.type = "Soft";
			//displayManager.displaySoftwareField();
		} else if (type.equalsIgnoreCase("Others")) {
			this.type = "Others";
			//displayManager.displayGeneralField();
		}
                System.out.println(this.type + " tips\n");
	}
	
	/**
	 * This method places the attributes of <b> InventoryItem </b> to their respective <b>ItemPanelParticipants</b> and set for editing
	 * @param ii <b>InventoryItem</b> passed from the <b>InventoryItemController</b> through the <b>TabInventory</b>
	 */
	public void setEditToCurrentSet()
	{       
		InventoryItem ii = getCurrentInventoryItem();
		System.out.println("setEditToCurrentSet -> InventoryItem");
		if(ii == null)
		{
			System.out.println("II is null");
		}
		System.out.println("IT ASSET: "+(ii instanceof ITAsset));
		System.out.println("SOFTWARE: "+(ii instanceof SoftwareItem));
		System.out.println("NON IT ASSET: "+(ii instanceof NonITAsset));
		System.out.println("OTHERS: "+(ii instanceof InventoryItem));
		System.out.println(participantList.size());
//		Object object = (Object)ii;
		setCurrentType(ii.getClassification());
//		System.out.println("TYPE: " + ((InventoryItem) object).getClassification());
		String typeInventoryItem = (ii.getClassification().toString());
		System.out.println(typeInventoryItem);
                Iterator i = assignmentController.filter(""+ii.getID());
                ArrayList<String> employees = new ArrayList<String>();
                Iterator<Employee> employeeIter = employeeController.getAll();
                
                Assignment assignment;                      // This code
                if(i.hasNext()){                             // assumes item can
                    assignment = ((Assignment)i.next());    // only be assigned to one employee
                    employees.add(assignment.getEmployee().getName());
                }
                else
                    employees.add("None");
		
		
		
		while(employeeIter.hasNext())
		{
                    String name = employeeIter.next().getName();
                    if(!employees.contains(name))
                        employees.add(name);
		}
                if(!employees.contains("None"))
                    employees.add("Remove Assignment");
		InventoryItem inventoryItem;
		
		if(typeInventoryItem.equalsIgnoreCase("IT"))
		{
			type = "IT";
			/*** 															***/
			System.out.println("IN EDIT IT ASSET");
//			System.out.println(ii instanceof ITAsset);
			inventoryItem = (ITAsset) ii;
			
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			
			((TypeItemTileField) participantList.get(1)).loadAssigneeList(employees.iterator());
			((TypeItemTileField) participantList.get(1)).setType("IT Assets");
			((ItemTileITField) participantList.get(1)).setDeliveryDate(((ITAsset) inventoryItem).getDeliveryDate());
			
			participantList.get(1).loadPresets(
					ItemStorageIT.getInstance()
					.saveAssetTag(((ITAsset) inventoryItem).getAssetTag())
					.saveServiceTag(((ITAsset) inventoryItem).getServiceTag())
					.loadList()
			);
			
			
			
			/** TEMPORARY FIX **/
			System.out.println("ASDASD" + ((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileWarrantyField)participantList.get(2)).setWarrantyStartDate(((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileWarrantyField)participantList.get(2)).setWarrantyEndDate(((ITAsset) inventoryItem).getWarrantyEndDate());
			
			
			participantList.get(3).loadPresets(
					ItemStorageContract.getInstance()
					.saveMainCost(((ITAsset) inventoryItem).getContractMaintenanceCost())
					.loadList()
			);
			
			((ItemTileContractField)participantList.get(3)).setContractStartDate(((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileContractField)participantList.get(3)).setContractEndDate(((ITAsset) inventoryItem).getWarrantyEndDate());
			System.out.println("Finished IT");
			
		}
		else if(typeInventoryItem.equalsIgnoreCase("Non-IT"))
		{
			type = "Non-IT";
			/*** 						TEMPORARY FIX									***/
			inventoryItem = (NonITAsset) ii;
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			((TypeItemTileField) participantList.get(1)).loadAssigneeList(employees.iterator());
			((TypeItemTileField) participantList.get(1)).setType("Non-IT Assets");
			
//			tabInventory.showAddPanel();
		}
		else if(typeInventoryItem.equalsIgnoreCase("Soft"))
		{
//			InventoryItem newInventoryItem = new InventoryItem();
//			ITAsset test = (ITAsset) newInventoryItem;
//			System.out.println("we got this far");
//			System.out.println(ii instanceof SoftwareItem);
			type = "Soft";
			inventoryItem = (SoftwareItem) ii;	
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			
			
			((TypeItemTileField) participantList.get(1)).loadAssigneeList(employees.iterator());
			((TypeItemTileField) participantList.get(1)).setType("Software");
			participantList.get(1).loadPresets(
					ItemStorageSoftware.getInstance()
					//.saveAssignee(((SoftwareItem) inventoryItem).getAssignee(); Assignee
					.saveLicenseKey(((SoftwareItem) inventoryItem).getLicenseKey())
					.loadList()
			);
		
			
			System.out.println(((SoftwareItem) inventoryItem).getLicenseKey());
			
			System.out.println("Passed Software");
		}
		else if(typeInventoryItem.equalsIgnoreCase("Others"))
		{
			type = "Others";
			inventoryItem = ii;
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);

			((TypeItemTileField) participantList.get(1)).loadAssigneeList(employees.iterator());
			((TypeItemTileField) participantList.get(1)).setType("Others");
			
			
//			tabInventory.showAddPanel();
		}
        isAdd(false);
        System.out.println("OGA CHAKA " + isAdd);
		resetAllStorage();
		
		
	}
	
	public void setViewToCurrentSet()
	{       
		InventoryItem ii = getCurrentInventoryItem();
		System.out.println(participantList.size());
//		Object object = (Object)ii;
//		setCurrentType(((InventoryItem) ii).getClassification());
//		System.out.println("TYPE: " + ((InventoryItem) object).getClassification());
		Iterator<Employee> employeeIter = employeeController.getAll();
		ArrayList<String> employees = new ArrayList<String>();
                Iterator i = assignmentController.filter(""+ii.getID());
                String assignee;
                if(i.hasNext()){
                    assignee = "Assignee: " + ((Assignment)i.next()).getEmployee().getName();
                }else assignee = "";
		
		while(employeeIter.hasNext())
		{
			employees.add(employeeIter.next().getName());
		}
		
		if(((InventoryItem) ii).getClassification().equalsIgnoreCase("IT"))
		{
			type = "IT";
			/*** 															***/
			ITAsset inventoryItem = (ITAsset) ii;
			
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			
			((TypeItemTileView) participantList.get(1)).setType("IT Assets");
                        ((TypeItemTileView) participantList.get(1)).loadAssignee(assignee);
			
			participantList.get(1).loadPresets(
					ItemStorageIT.getInstance()
					.saveAssetTag(((ITAsset) inventoryItem).getAssetTag())
					.saveServiceTag(((ITAsset) inventoryItem).getServiceTag())
					.loadList()
			);
			
			/** TEMPORARILY DISABLED **/
					
//			participantList.get(2).loadPresets(
//					ItemStorageWarranty.getInstance()
//					.saveStartDate(((ITAsset) inventoryItem).getWarrantyStartDate())
//					.saveEndDate(((ITAsset) inventoryItem).getWarrantyEndDate())
//					.loadList()
//			);
			
			
			/** TEMPORARY FIX **/
			System.out.println("ASDASD" + ((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileWarrantyView)participantList.get(2)).setWarrantyStartDate(((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileWarrantyView)participantList.get(2)).setWarrantyEndDate(((ITAsset) inventoryItem).getWarrantyEndDate());
			
			
			participantList.get(3).loadPresets(
					ItemStorageContract.getInstance()
					.saveMainCost(((ITAsset) inventoryItem).getContractMaintenanceCost())
//					.saveStartDate(((ITAsset) inventoryItem).getContractStartDate())
//					.saveEndDate(((ITAsset) inventoryItem).getContractEndDate())
					.loadList()
			);
			
			((ItemTileContractView)participantList.get(3)).setContractStartDate(((ITAsset) inventoryItem).getWarrantyStartDate());
			((ItemTileContractView)participantList.get(3)).setContractEndDate(((ITAsset) inventoryItem).getWarrantyEndDate());
			
			
//			tabInventory.showAddPanel();
		}
		else if(((InventoryItem) ii).getClassification().equalsIgnoreCase("Non-IT"))
		{
			type = "Non-IT";
			/*** 						TEMPORARY FIX									***/
			NonITAsset inventoryItem = (NonITAsset) ii;
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			
			((TypeItemTileField) participantList.get(1)).setType("Non-IT Assets");
                        ((TypeItemTileView) participantList.get(1)).loadAssignee(assignee);
			//PROBLEM
			
//			tabInventory.showAddPanel();
		}
		else if(((InventoryItem) ii).getClassification().equalsIgnoreCase("Soft"))
		{
			type = "Soft";
			SoftwareItem inventoryItem = (SoftwareItem) ii;
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);
			
			
		
			((TypeItemTileView) participantList.get(1)).setType("Software");
                        ((TypeItemTileView) participantList.get(1)).loadAssignee(assignee);
			//PROBLEM
			participantList.get(1).loadPresets(
					ItemStorageSoftware.getInstance()
					.saveLicenseKey(((SoftwareItem) inventoryItem).getLicenseKey())
					.loadList()
			);
		
			
			System.out.println(((SoftwareItem) inventoryItem).getLicenseKey());
		}
		else if(((InventoryItem) ii).getClassification().equalsIgnoreCase("Others"))
		{
			type = "Others";
			InventoryItem inventoryItem = ii;
			participantList.get(0).loadPresets(
					ItemStorageGenInfo.getInstance()
					.saveID(inventoryItem.getID())
					.saveName(inventoryItem.getName())
					.saveDescription(inventoryItem.getDescription())
					.saveUnitPrice(inventoryItem.getUnitPrice())
					.saveInvoiceNumber(inventoryItem.getInvoiceNo())
					.saveLocation(inventoryItem.getLocation())
					.saveStatus(inventoryItem.getStatus())
					.loadList()
			);

			((TypeItemTileView) participantList.get(1)).loadAssignee(assignee);
//			((TypeItemTileField) participantList.get(1)).setType("Others");
//			tabInventory.showAddPanel();
		}
                isAdd = false;
                System.out.println("OGA CHAKA " + isAdd);
                resetAllStorage();
		
	}
	
	/**
	 * Resets all the data stored inside the storage of each <b>ItemPanelParticipant</b>
	 */
	public void resetAllStorage()
	{
		ItemStorageContract.getInstance().resetStorage();
		ItemStorageGeneral.getInstance().resetStorage();
		ItemStorageGenInfo.getInstance().resetStorage();
		ItemStorageIT.getInstance().resetStorage();
		ItemStorageNonIT.getInstance().resetStorage();
		ItemStorageSoftware.getInstance().resetStorage();
		ItemStorageWarranty.getInstance().resetStorage();
	}
	
	/**
	 * Unregisters all the <b>ItemPanelParticipants</b>
	 */
	public void resetParticipantPanels()
	{
		for(int i = 0; i < participantList.size(); i++)
		{
			participantList.clear();
		}
	}
	
	private void createITEdit()
	{
		
	}

	public void deleteInventoryItem(InventoryItem ii) {
		// TODO Auto-generated method stub
		InventoryItemController.getInstance().deleteInventoryItem(ii);
		displayManager.setInventoryReturn();
	}

	public void disableAssignee(boolean stat) {
		// TODO Auto-generated method stub
		
		((TypeItemTileField) participantList.get(1)).setAssigneeVisible(stat);
	}



}
