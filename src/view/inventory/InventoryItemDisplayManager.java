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
	
	
	public JPanel buildContent(InventoryItem ii, String mode) {
		// TODO Auto-generated method stub
		if(mode.equals("add") || mode.equals("edit"))
		{
			if(ii.getClassification().equals("IT"))
			{
				return displayITField(ii);
			}
			else if(ii.getClassification().equals("Non-IT"))
			{
				return displayNonITField(ii);
			}
			else if(ii.getClassification().equals("Software"))
			{
				return displaySoftwareField(ii);
			}
			else if(ii.getClassification().equals("General"))
			{
				return displayGeneralField(ii);
			}
			
			if(ii != null)
				return displayITField(ii);
			else return displayITField();
		}
		else if(mode.equals("viewSpec"))
		{
			if(ii.getClassification().equals("IT"))
			{
				return displayITView(ii);
			}
			else if(ii.getClassification().equals("Non-IT"))
			{
				return displayNonITView(ii);
			}
			else if(ii.getClassification().equals("Software"))
			{
				return displaySoftwareView(ii);
			}
			else if(ii.getClassification().equals("General"))
			{
				return displayGeneralView(ii);
			}
			return displayITView(ii);
		}
		
		return null;
		
		
	}
	
	public JPanel buildContent(String type, String mode) {
		// TODO Auto-generated method stub
		
		if(type.equals("IT"))
		{
			return displayITField();
		}
		else if(type.equals("Non-IT"))
		{
			return displayNonITField();
		}
		else if(type.equals("Software"))
		{
			return displaySoftwareField();
		}
		else if(type.equals("General"))
		{
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
		panelRegistry.setEditToCurrentSet(ii);
		
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
		itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, itemTileContractField);
		itemTileNonITField = new ItemTileNonITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileNonITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileNonITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		panelRegistry.registerParticipant(itemTileContractField);

		panelRegistry.setEditToCurrentSet(ii);
		
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
		
		panelRegistry.setEditToCurrentSet(ii);
		
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
		itemTileWarrantyField = new ItemTileWarrantyField(gui, template);
		itemTileGeneralField = new ItemTileGeneralField(gui, template);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileGeneralField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileGeneralField);
		
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
		itemTileContractField = new ItemTileContractField(gui, template);
		itemTileWarrantyField = new ItemTileWarrantyField(gui, itemTileContractField);
		itemTileNonITField = new ItemTileNonITField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileNonITField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileNonITField);
		panelRegistry.registerParticipant(itemTileWarrantyField);
		panelRegistry.registerParticipant(itemTileContractField);
		
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
		itemTileWarrantyField = new ItemTileWarrantyField(gui, template);
		itemTileGeneralField = new ItemTileGeneralField(gui, itemTileWarrantyField);
		itemTileGenInfoField = new ItemTileGenInfoField(gui, itemTileGeneralField);
		ItemPanelDecorator dec = itemTileGenInfoField;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoField);
		panelRegistry.registerParticipant(itemTileGeneralField);
		
		
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
		viewList.jumpToItem(ii);
		ViewSpecificInventory template = new ViewSpecificInventory(tab, viewList);
		itemTileContractView = new ItemTileContractView(gui, template.getBasicViewSpecificItem());
		itemTileWarrantyView = new ItemTileWarrantyView(gui, itemTileContractField);
		itemTileITView = new ItemTileITView(gui, itemTileWarrantyField);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, itemTileITField);
		ItemPanelDecorator dec = itemTileGenInfoView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileITView);
		panelRegistry.registerParticipant(itemTileWarrantyView);
		panelRegistry.registerParticipant(itemTileContractView);
		
		panelRegistry.setViewToCurrentSet(ii);
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
		itemTileContractView = new ItemTileContractView(gui, template.getBasicViewSpecificItem());
		itemTileWarrantyView = new ItemTileWarrantyView(gui, itemTileContractView);
		itemTileNonITView = new ItemTileNonITView(gui, itemTileWarrantyView);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, itemTileNonITView);
		ItemPanelDecorator dec = itemTileGenInfoView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileNonITView);
		panelRegistry.registerParticipant(itemTileWarrantyView);
		panelRegistry.registerParticipant(itemTileContractView);
		
		panelRegistry.setViewToCurrentSet(ii);
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
		itemTileSoftwareView = new ItemTileSoftwareView(gui, template.getBasicViewSpecificItem());
		itemTileGenInfoView = new ItemTileGenInfoView(gui, itemTileSoftwareView);
		ItemPanelDecorator dec = itemTileGenInfoView;	
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileSoftwareView);
		
		panelRegistry.setViewToCurrentSet(ii);
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
		itemTileWarrantyView = new ItemTileWarrantyView(gui, template.getBasicViewSpecificItem());
		itemTileGeneralView = new ItemTileGeneralView(gui, itemTileWarrantyView);
		itemTileGenInfoView = new ItemTileGenInfoView(gui, itemTileGeneralView);
		ItemPanelDecorator dec = itemTileGenInfoView;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfoView);
		panelRegistry.registerParticipant(itemTileGeneralView);
		
		panelRegistry.setViewToCurrentSet(ii);
		return template;
	}
	
	public void overrideContentPanel(String type)
	{
		tab.setAdd(type);
	}
	
	public void setEditToCurrentSet(InventoryItem ii) {
		// TODO Auto-generated method stub
		panelRegistry.setEditToCurrentSet(ii);
	}
	
	public void retrieveInformation() {
		// TODO Auto-generated method stub
		panelRegistry.retrieveInformationFromAll();
		tab.setReturn();
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
//		panelRegistry.deleteInventoryItem();
	}

	public void requestBuildIT() {
		// TODO Auto-generated method stub
		tab.setAdd("IT");
	}
	
	public void requestBuildNonIT() {
		// TODO Auto-generated method stub
		tab.setAdd("NonIT");
	}
	
	public void requestBuildSoftware() {
		// TODO Auto-generated method stub
		tab.setAdd("Software");
	}

	public void requestBuildGeneral() {
		// TODO Auto-generated method stub
		tab.setAdd("General");
	}

	

	

	
	
}
