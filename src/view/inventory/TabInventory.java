package view.inventory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.SupplierController;
import model.InventoryItem;
import model.Supplier;
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
import view.supplier.EditSupplier;
import view.supplier.ViewListSuppliers;

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
	private ViewInventory viewInventory;
	private InventoryItemDisplayManager displayManager;
	
	public TabInventory(Gui gui) {
		cl = new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory(this)).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		temp.getBtnExport().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
		//cl.show(this, "view");
		
		
		
		displayManager = InventoryItemDisplayManager.getInstance();
		displayManager.setTabInventory(this);
		displayManager.setGui(gui);
		displayManager.setContent(temp);
		displayManager.setCardLayout(cl);
	
		
		cl.show(this, "view");
		
		//cl.show(this, "add);
	}
	
	
	/**
	 * This methods activates the editing of an <b>InventoryItem</b>
	 * @param ii
	 */
	public void setEdit(InventoryItem ii){
		Content temp=new Content.ContentBuilder().caption("Edit Item").back(true).delete(true).content(displayManager.buildContent(ii,"edit")).build();
		temp.getBtnBack().addActionListener(this);
		temp.getBtnDelete().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);
		
		cl.show(this, "edit");
		repaint();
		revalidate();
	}
	
	public void setView(InventoryItem ii){
		
//		Content temp=new Content.ContentBuilder().caption("View Specific Supplier").back(true).content(new ViewListSuppliers(this, gui, SupplierController.getInstance().getAll(), supp)).build();
//		temp.getBtnBack().addActionListener(this);
//		this.add(temp, "preview");
//		list.add(temp);
//		
//		cl.show(this, "preview");
	}
	
	public void setAdd(String type)
	{
		Content temp=new Content.ContentBuilder().caption("Add Item").back(true).content(displayManager.buildContent(type,"add")).build();
		
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
		cl.show(this, "add");
		repaint();
		revalidate();
	}


	public void setReturn(){
//		Content temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory(this)).build();
//		temp.getBtnAdd().addActionListener(this);
//		temp.getBtnFilter().addActionListener(this);
//		temp.getBtnExport().addActionListener(this);
//		this.add(temp, "view");
//		list.add(temp);
		//System.out.println(cl.);
		cl.show(this,"view");
		repaint();
		revalidate();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			System.out.println("PESS");
			setAdd("IT");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			new FilterInventory(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("export")){
			new ExportInventory(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			
			System.out.println("BACK");
			cl.show(this,"view");
			repaint();
			revalidate();
			
		}
	}
	
}
