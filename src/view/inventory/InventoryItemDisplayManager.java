package view.inventory;

import java.awt.CardLayout;
import java.util.Iterator;

import javax.swing.JPanel;

import model.InventoryItem;
import view.Content;
import view.Gui;
import view.inventory.itemtilefield.ItemTileContractField;
import view.inventory.itemtilefield.ItemTileGenInfoField;
import view.inventory.itemtilefield.ItemTileGeneralField;
import view.inventory.itemtilefield.ItemTileITField;
import view.inventory.itemtilefield.ItemTileNonITField;
import view.inventory.itemtilefield.ItemTileSoftwareField;
import view.inventory.itemtilefield.ItemTileWarrantyField;
import view.inventory.itemtileview.ItemTileContractView;
import view.inventory.itemtileview.ItemTileGenInfoView;
import view.inventory.itemtileview.ItemTileGeneralView;
import view.inventory.itemtileview.ItemTileITView;
import view.inventory.itemtileview.ItemTileNonITView;
import view.inventory.itemtileview.ItemTileSoftwareView;
import view.inventory.itemtileview.ItemTileWarrantyView;

public class InventoryItemDisplayManager {

	private static InventoryItemDisplayManager instance;
	private PanelRegistry panelRegistry;
	private TabInventory tab;
	private Gui gui;
	private ViewInventoryItemLinkedList viewList;
	
	private ItemTileGenInfoField itemTileGenInfoField;
	private ItemTileGeneralField itemTileGeneralField;
	private ItemTileITField itemTileITField;
	private ItemTileNonITField itemTileNonITField;
	private ItemTileWarrantyField itemTileWarrantyField;
	private ItemTileContractField itemTileContractField;
	private ItemTileSoftwareField itemTileSoftwareField;
	
	private ItemTileGenInfoView itemTileGenInfoView;
	private ItemTileGeneralView itemTileGeneralView;
	private ItemTileITView itemTileITView;
	private ItemTileNonITView itemTileNonITView;
	private ItemTileWarrantyView itemTileWarrantyView;
	private ItemTileContractView itemTileContractView;
	private ItemTileSoftwareView itemTileSoftwareView;
	
	private Content temp;
	private CardLayout cl;
	
	private InventoryItemDisplayManager()
	{
		panelRegistry = new PanelRegistry();
		viewList = new ViewInventoryItemLinkedList();
	}
	
	public void setTabInventory(TabInventory tab)
	{
		this.tab = tab;
		
		
	}
	
	public void setGui(Gui gui)
	{
		this.gui = gui;
	}
	
	public void setContent(Content temp)
	{
		this.temp = temp;
	}
	
	public void setCardLayout(CardLayout cl)
	{
		this.cl = cl;
	}
	
	public void createNewViewList()
	{
		viewList = new ViewInventoryItemLinkedList();
	}
	
	public void viewNextNode()
	{
		InventoryItem ii = null;
		ii = viewList.pNext();
		if(ii != null)
		{
			
		}
	}
	
	
	public JPanel buildContentEdit(InventoryItem ii, String type) {
		// TODO Auto-generated method stub
			System.out.println("PassEDIT");
			if(ii.getClassification().equalsIgnoreCase("IT"))
			{
				return displayITField(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Non-IT"))
			{
				return displayNonITField(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Soft"))
			{
				return displaySoftwareField(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Others"))
			{
				return displayGeneralField(ii);
			}
			
			if(ii != null)
				return displayITField(ii);
			else return displayITField();
	}
	
	public JPanel buildContentView(InventoryItem ii, String type)
	{
			System.out.println("PassVIEW");
			if(ii.getClassification().equalsIgnoreCase("IT"))
			{
				return displayITView(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Non-IT"))
			{
				return displayNonITView(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Soft"))
			{
				return displaySoftwareView(ii);
			}
			else if(ii.getClassification().equalsIgnoreCase("Others"))
			{
				return displayGeneralView(ii);
			}
			return displayITView(ii);
	}
	
	public JPanel buildContentAdd(String type) {
		// TODO Auto-generated method stub
		System.out.println("Mode IN ADD");
		if(type.equalsIgnoreCase("IT"))
		{
			return displayITField();
		}
		else if(type.equalsIgnoreCase("Non-IT"))
		{
			return displayNonITField();
		}
		else if(type.equalsIgnoreCase("Soft"))
		{
			return displaySoftwareField();
		}
		else if(type.equalsIgnoreCase("Others"))
		{
			System.out.println("buildContentAdd Others BUILD");
			return displayGeneralField();
		
		}
		
		return displayITField();
	}
	
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public JPanel displayITField(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		
		panelRegistry.clearParticipants();
		
		BasicItemField template = new BasicItemField();
		itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, itemTileContractField);
		itemTileITField = new ItemTileITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		panelRegistry.registerParticipant(itemTileContractField);
		
		panelRegistry.isAdd(false);
		System.out.println("Passes setEdit");
		panelRegistry.setCurrentInventoryItem(ii);
		panelRegistry.setEditToCurrentSet();
		
		return template;
	}
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public JPanel displayNonITField(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		//itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, template);
		itemTileNonITField = new ItemTileNonITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileNonITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileNonITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		//panelRegistry.registerParticipant(itemTileContractField);

		panelRegistry.isAdd(false);
		System.out.println("Passes setEdit");
		panelRegistry.setCurrentInventoryItem(ii);
		panelRegistry.setEditToCurrentSet();
		
		
		return template;
	}
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public JPanel displaySoftwareField(InventoryItem ii)
	{
		if(temp != null) {
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		itemTileSoftwareField = new ItemTileSoftwareField(gui, template);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileSoftwareField);
		ItemPanelDecorator dec = itemTileGenInfoField;		

		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileSoftwareField);
		
		panelRegistry.isAdd(false);
		
		System.out.println("Passes setEdit");
		panelRegistry.setCurrentInventoryItem(ii);
		panelRegistry.setEditToCurrentSet();
		
		
		
		return template;
	}
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public JPanel displayGeneralField(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		itemTileGenInfoField = new ItemTileGenInfoField(gui, template);
		itemTileGeneralField = new ItemTileGeneralField(gui, itemTileGenInfoField);
		ItemPanelDecorator dec = itemTileGeneralField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileGeneralField);
		
		panelRegistry.isAdd(false);
		
		System.out.println("Passes setEdit");
		panelRegistry.setCurrentInventoryItem(ii);
		panelRegistry.setEditToCurrentSet();
		
		return template;
	}
	
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public JPanel displayITField()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, itemTileContractField);
		itemTileITField = new ItemTileITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		panelRegistry.registerParticipant(itemTileContractField);
		
		panelRegistry.isAdd(true);
		
		return template;
	}
	
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public JPanel displayNonITField()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		//itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, template);
		itemTileNonITField = new ItemTileNonITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileNonITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileNonITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		//panelRegistry.registerParticipant(itemTileContractField);
		
		panelRegistry.isAdd(true);
		
		return template;
	}	
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public JPanel displaySoftwareField()
	{
		if(temp != null) {
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		itemTileSoftwareField = new ItemTileSoftwareField(gui, template);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileSoftwareField);
		ItemPanelDecorator dec = itemTileGenInfoField;	
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileSoftwareField);
		
		panelRegistry.isAdd(true);

		
		return template;
	}
	
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public JPanel displayGeneralField()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicItemField template = new BasicItemField();
		itemTileGenInfoField = new ItemTileGenInfoField(gui, template);
		itemTileGeneralField = new ItemTileGeneralField(gui, itemTileGenInfoField);
		ItemPanelDecorator dec = itemTileGeneralField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileGeneralField);
		
		panelRegistry.isAdd(true);
		
		System.out.println("Pass");
		if(template == null)
		{
			System.out.println("NULL template");
		}
		
		return template;
	}
	
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public JPanel displayITView(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		
		panelRegistry.clearParticipants();
		createNewViewList();
		System.out.println("VIEW LIST - COUNT: " + viewList.getCount());
		viewList.jumpToItem(ii);
		ViewSpecificInventory template = new ViewSpecificInventory(tab, viewList);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, template);
		itemTileContractView = new ItemTileContractView(gui, itemTileGenInfoView);
		itemTileWarrantyView = new ItemTileWarrantyView(gui, itemTileContractView);
		itemTileITView = new ItemTileITView(gui, itemTileWarrantyView);
		
		ItemPanelDecorator dec = itemTileITView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileITView);
		panelRegistry.registerParticipant(itemTileWarrantyView);
		panelRegistry.registerParticipant(itemTileContractView);
		
		panelRegistry.setCurrentInventoryItem(viewList.pCurr());
		panelRegistry.setViewToCurrentSet();
		
		
		return template;
	}
	
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public JPanel displayNonITView(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		createNewViewList();
		viewList.jumpToItem(ii);
		ViewSpecificInventory template = new ViewSpecificInventory(tab, viewList);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, template);
		itemTileNonITView = new ItemTileNonITView(gui, itemTileGenInfoView);
		itemTileWarrantyView = new ItemTileWarrantyView(gui, itemTileNonITView);
		//itemTileContractView = new ItemTileContractView(gui, itemTileWarrantyView);
		ItemPanelDecorator dec = itemTileWarrantyView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileNonITView);
		panelRegistry.registerParticipant(itemTileWarrantyView);
		//panelRegistry.registerParticipant(itemTileContractView);
		
		panelRegistry.setCurrentInventoryItem(viewList.pCurr());
		panelRegistry.setViewToCurrentSet();
		return template;
	}	
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public JPanel displaySoftwareView(InventoryItem ii)
	{
		if(temp != null) {
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		createNewViewList();
		viewList.jumpToItem(ii);
		ViewSpecificInventory template = new ViewSpecificInventory(tab, viewList);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, template);
		itemTileSoftwareView = new ItemTileSoftwareView(gui, itemTileGenInfoView);
		ItemPanelDecorator dec = itemTileSoftwareView;	
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileSoftwareView);
		
		panelRegistry.setCurrentInventoryItem(viewList.pCurr());
		panelRegistry.setViewToCurrentSet();
		return template;
	}
	
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public JPanel displayGeneralView(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		createNewViewList();
		viewList.jumpToItem(ii);
		ViewSpecificInventory template = new ViewSpecificInventory(tab, viewList);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, template);
		itemTileGeneralView = new ItemTileGeneralView(gui, itemTileGenInfoView);
		ItemPanelDecorator dec = itemTileGeneralView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileGeneralView);
		
		panelRegistry.setCurrentInventoryItem(viewList.pCurr());
		panelRegistry.setViewToCurrentSet();
		return template;
	}
	
	public void setAssigneeStatus(boolean stat)
	{
		panelRegistry.disableAssignee(stat);
	}
	
	public void overrideContentPanel(String type)
	{
		System.out.println("TYPE IN OVERRIDE: " + type);
		tab.setAdd(type);
                panelRegistry.setCurrentType(type);
	}
	
	public void setEditToCurrentSet(InventoryItem ii) {
		// TODO Auto-generated method stub
		panelRegistry.setCurrentInventoryItem(viewList.pCurr());
		panelRegistry.setViewToCurrentSet();
	}
	
	public void retrieveInformation() {
		// TODO Auto-generated method stub
		
		if(panelRegistry.checkInputFromAll())
		{
			System.out.println("PASSED RETRIEVE INFO AND CLOSE");
			panelRegistry.retrieveInformationFromAll();
			tab.setReturn();
		}
			
	}
	public static InventoryItemDisplayManager getInstance()
	{
		if (instance == null) {
			instance = new InventoryItemDisplayManager();
		}
		return instance;
	}

	public void setInventoryReturn() {
		// TODO Auto-generated method stub
		tab.setReturn();
	}
	
	public void callDeleteInventoryItem() {
		panelRegistry.deleteInventoryItem(viewList.pCurr());
	}

	public void requestBuildIT() {
		// TODO Auto-generated method stub
		tab.setAdd("IT");
	}
	
	public void requestBuildNonIT() {
		// TODO Auto-generated method stub
		tab.setAdd("Non-IT");
	}
	
	public void requestBuildSoftware() {
		// TODO Auto-generated method stub
		tab.setAdd("Soft");
	}

	public void requestBuildGeneral() {
		// TODO Auto-generated method stub
		tab.setAdd("Others");
	}

	

	

	
	
}
