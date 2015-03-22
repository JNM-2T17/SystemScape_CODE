package view.inventory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.InventoryItem;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;
import view.inventory.itemtile.ItemTileContract;
import view.inventory.itemtile.ItemTileGenInfo;
import view.inventory.itemtile.ItemTileGeneral;
import view.inventory.itemtile.ItemTileIT;
import view.inventory.itemtile.ItemTileNonIT;
import view.inventory.itemtile.ItemTileSoftware;
import view.inventory.itemtile.ItemTileWarranty;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * @author dovahkiin5
 *
 */
public class TabInventory extends JPanel implements ActionListener{
	private CardLayout cl;
	private ArrayList<Content> list;
	private Gui gui;
	private Content temp;
	private ItemTileGenInfo itemTileGenInfo;
	private ItemTileGeneral itemTileGeneral;
	private ItemTileIT itemTileIT;
	private ItemTileNonIT itemTileNonIT;
	private ItemTileWarranty itemTileWarranty;
	private ItemTileContract itemTileContract;
	private ItemTileSoftware itemTileSoftware;
	private PanelRegistry panelRegistry;
	private ViewInventory viewInventory;
	
	public TabInventory(Gui gui) {
		panelRegistry = PanelRegistry.getInstance();
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		panelRegistry.setTabInventory(this);
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory(this)).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		temp.getBtnExport().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
	
		displayIT();
		
		
		cl.show(this, "view");
	}
	
	
	/**
	 * This methods activates the editing of an <b>InventoryItem</b>
	 * @param ii
	 */
	public void setEdit(InventoryItem ii){
		panelRegistry.setToEditMode();
		panelRegistry.setEditToCurrentSet(ii);
	}
	
	public void updateView()
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			panelRegistry.setToAddMode();
			cl.show(this, "add");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			new FilterInventory(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("export")){
			new ExportInventory(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			cl.show(this, "view");
		}
	}
	
	
	/**
	 * Reverts the display back to the <b>ViewInventory</b> panel
	 */
	public void revertToMain()
	{
	if(temp != null)
		{
			remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("Add Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "view");
		revalidate();
		repaint();	
            
            
                
	}
	/**
	 * Displays the Template for an ITAsset <b>InventoryItem</b>
	 */
	public void displayIT()
	{
		if(temp != null)
		{
			remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("Add Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");
		revalidate();
		repaint();
	}
	/**
	 * Displays the Template for a NonITAsset <b>InventoryItem</b>
	 */
	public void displayNonIT()
	{
		if(temp != null)
		{
			remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileNonIT = new ItemTileNonIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileNonIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileNonIT);
		panelRegistry.registerParticipant(itemTileWarranty);
		panelRegistry.registerParticipant(itemTileContract);
		
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("Add Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");
		revalidate();
		repaint();
	}
	
	/**
	 * /**
	 * Displays the template for a Software <b>InventoryItem</b>
	 */
	public void displaySoftware()
	{
		if(temp != null) {
			remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileSoftware = new ItemTileSoftware(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileSoftware);
		ItemPanelDecorator dec = itemTileGenInfo;		

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileSoftware);
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("Add Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");	
		revalidate();
		repaint();
	}
	
	/**
	 * Displays the template for a General <b>InventoryItem</b>
	 */
	public void displayGeneral()
	{
		if(temp != null)
		{
			remove(temp);
		}
		panelRegistry.clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileWarranty = new ItemTileWarranty(gui, template);
		itemTileGeneral = new ItemTileGeneral(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileGeneral);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		panelRegistry.registerParticipant(itemTileGenInfo);
		panelRegistry.registerParticipant(itemTileGeneral);
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("Add Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");	
		revalidate();
		repaint();
		
		
	}
	
	/**
	 * Displays the panel in adding or editing the <b>InventoryItem</b>
	 */
	public void showAddPanel()
	{
		cl.show(this, "add");
	}
}
