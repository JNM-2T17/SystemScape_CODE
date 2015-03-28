package view.inventory;

import java.awt.CardLayout;

import javax.swing.JPanel;

import model.InventoryItem;
import view.Content;
import view.Gui;
import view.inventory.itemtile.ItemTileContract;
import view.inventory.itemtile.ItemTileGenInfo;
import view.inventory.itemtile.ItemTileGeneral;
import view.inventory.itemtile.ItemTileIT;
import view.inventory.itemtile.ItemTileNonIT;
import view.inventory.itemtile.ItemTileSoftware;
import view.inventory.itemtile.ItemTileWarranty;

public class InventoryItemDisplayManager {

	private static InventoryItemDisplayManager instance;
	private PanelRegistry panelRegistry;
	private TabInventory tab;
	private Gui gui;
	
	private ItemTileGenInfo itemTileGenInfo;
	private ItemTileGeneral itemTileGeneral;
	private ItemTileIT itemTileIT;
	private ItemTileNonIT itemTileNonIT;
	private ItemTileWarranty itemTileWarranty;
	private ItemTileContract itemTileContract;
	private ItemTileSoftware itemTileSoftware;
	
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
	
	public JPanel buildContent(InventoryItem ii, String mode) {
		// TODO Auto-generated method stub
		if(ii.getClassification().equals("IT"))
		{
			return displayIT(ii);
		}
		else if(ii.getClassification().equals("Non-IT"))
		{
			return displayNonIT(ii);
		}
		else if(ii.getClassification().equals("Software"))
		{
			return displaySoftware(ii);
		}
		else if(ii.getClassification().equals("General"))
		{
			return displayGeneral(ii);
		}
		
		return displayIT(ii);
	}
	
	public JPanel buildContent(String type, String mode) {
		// TODO Auto-generated method stub
		
		if(type.equals("IT"))
		{
			return displayIT();
		}
		else if(type.equals("Non-IT"))
		{
			return displayNonIT();
		}
		else if(type.equals("Software"))
		{
			return displaySoftware();
		}
		else if(type.equals("General"))
		{
			return displayGeneral();
		}
		
		return displayIT();
	}
	
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public JPanel displayIT(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		
		panelRegistry.clearParticipants();
		
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		panelRegistry.setEditToCurrentSet(ii);
		
		return template;
		
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Add Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");
//		tab.revalidate();
//		tab.repaint();
	}
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public JPanel displayNonIT(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileNonIT = new ItemTileNonIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileNonIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileNonIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);

		panelRegistry.setEditToCurrentSet(ii);
		
		return template;
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Add Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");
//		tab.revalidate();
//		tab.repaint();
	}
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public JPanel displaySoftware(InventoryItem ii)
	{
		if(temp != null) {
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileSoftware = new ItemTileSoftware(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileSoftware);
		ItemPanelDecorator dec = itemTileGenInfo;		

		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileSoftware);
		
		panelRegistry.setEditToCurrentSet(ii);
		
		return template;
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Edit Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");	
//		tab.revalidate();
//		tab.repaint();
	}
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public JPanel displayGeneral(InventoryItem ii)
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileWarranty = new ItemTileWarranty(gui, template);
		itemTileGeneral = new ItemTileGeneral(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileGeneral);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileGeneral);
		
		return template;
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Edit Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");	
//		tab.revalidate();
//		tab.repaint();
		
		
	}
	
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public JPanel displayIT()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		
		return template;
		
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Add Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");
//		tab.revalidate();
//		tab.repaint();
	}
	
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public JPanel displayNonIT()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileNonIT = new ItemTileNonIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileNonIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		
		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileNonIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		
		return template;
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Add Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");
//		tab.revalidate();
//		tab.repaint();
	}	
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public JPanel displaySoftware()
	{
		if(temp != null) {
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileSoftware = new ItemTileSoftware(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileSoftware);
		ItemPanelDecorator dec = itemTileGenInfo;	
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileSoftware);
		
		
		return template;
		
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Edit Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");	
//		tab.revalidate();
//		tab.repaint();
	}
	
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public JPanel displayGeneral()
	{
		if(temp != null)
		{
			tab.remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileWarranty = new ItemTileWarranty(gui, template);
		itemTileGeneral = new ItemTileGeneral(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileGeneral);
		ItemPanelDecorator dec = itemTileGenInfo;
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileGeneral);
		
		
		return template;
//		dec.renderPanel();
//		dec.repaint();
//		dec.revalidate();
//		temp=new Content.ContentBuilder().caption("Edit Item").back(true).content(template).build();
//		temp.getBtnBack().addActionListener(tab);
//		
//		tab.add(temp, "add");
//		cl.show(tab, "add");	
//		tab.revalidate();
//		tab.repaint();
		
		
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

	

	

	
	
}
