package view.inventory;
import controller.InventoryItemController;
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
import view.inventory.itemtilefield.ItemTileContractField;
import view.inventory.itemtilefield.ItemTileGenInfoField;
import view.inventory.itemtilefield.ItemTileGeneralField;
import view.inventory.itemtilefield.ItemTileITField;
import view.inventory.itemtilefield.ItemTileNonITField;
import view.inventory.itemtilefield.ItemTileSoftwareField;
import view.inventory.itemtilefield.ItemTileWarrantyField;
import view.supplier.EditSupplier;
import view.supplier.ViewListSuppliers;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import view.Message;
import view.projects.ViewProjects;


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
        private InventoryItemController inventoryItemController;
	
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
		
		
		temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory(this)).build();
		
		displayManager = InventoryItemDisplayManager.getInstance();
		displayManager.setTabInventory(this);
		displayManager.setGui(gui);
		displayManager.setContent(temp);
		displayManager.setCardLayout(cl);
	
		inventoryItemController = InventoryItemController.getInstance();
                
                if(!inventoryItemController.getAll().hasNext()){
                    new Message(gui, Message.WARNING, "Inventory database is empty");
                }
		cl.show(this, "view");
		
		//cl.show(this, "add);
	}
	
	
	/**
	 * This methods activates the editing of an <b>InventoryItem</b>
	 * @param ii
	 */
	public void setEdit(InventoryItem ii){
		//System.out.println("Edit");
		Content temp=new Content.ContentBuilder().caption("Edit Item").back(true).delete(true).content(displayManager.buildContentEdit(ii,ii.getClassification())).build();
		temp.getBtnBack().addActionListener(this);
		temp.getBtnDelete().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);
		
		cl.show(this, "edit");
		repaint();
		revalidate();
		
		//System.out.println("SET EDIT PASS CHK1");
	}
	
	public void setView(InventoryItem ii){
		//System.out.println("View");
		displayManager.createNewViewList();
		Content temp=new Content.ContentBuilder().caption("View Specific Item").back(true).content(displayManager.buildContentView(ii,ii.getClassification())).build();
		
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "viewspec");
		list.add(temp);
		
		cl.show(this, "viewspec");
		repaint();
		revalidate();
		
		//System.out.println("SET VIEW PASS CHK1");
	}
	
	public void setAdd(String type)
	{	
		//System.out.println("Add");
		//System.out.println("STRING TYPE IN SET ADD: " + type);
		Content temp=new Content.ContentBuilder().caption("Add Item").back(true).content(displayManager.buildContentAdd(type)).build();
		
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
		cl.show(this, "add");
		repaint();
		revalidate();
		
		//System.out.println("SET ADD PASS CHK1");
	}
	
	public void setDelete()
	{
		//System.out.println("Delete");
		Message msg = new Message(gui, Message.WARNING, "Items cannot be recovered once deleted. Are you sure you want to delete this item?");
		int n=msg.getValue();
		if(n==msg.YES) displayManager.callDeleteInventoryItem();
		//System.out.println("Deleting... ");
	}


	public void setReturn(){
//		Content temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory(this)).build();
//		temp.getBtnAdd().addActionListener(this);
//		temp.getBtnFilter().addActionListener(this);
//		temp.getBtnExport().addActionListener(this);
//		this.add(temp, "view");
//		list.add(temp);

		cl.show(this,"view");
		repaint();
		revalidate();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			setAdd("IT");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			FilterInventory fi = new FilterInventory(gui);
                        if(!fi.isClosed()){
				Iterator values = inventoryItemController.filter(fi.getValues());
                                if(values.hasNext())
                                    ((ViewInventory)list.get(0).getContent()).filterPopulate(values);
                                else new Message(gui, Message.WARNING, "Your search did not match any entries");
			}
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("export")){
			
			ArrayList<String> data = new ArrayList();
			DefaultTableModel model = ((ViewInventory)list.get(0).getContent()).getModel();
                        String current = "";
                        String entry[];
			//getHeaders
			ArrayList<String> headers=new ArrayList<String>();
			
			for (int j = 0; j < model.getColumnCount(); j++) {
				headers.add(model.getColumnName(j));
			}
			
			//print Headers
			for (int j = 0; j < model.getColumnCount(); j++) {
				if(((ViewInventory)list.get(0).getContent()).isColVisible(headers.get(j))) 
                                    current = current + model.getColumnName(j)+ ", ";
			}
			current = current.substring(0,current.length()-4) + "\n";
                        data.add(current);
			
			//printData
			for (int i = 0; i < model.getRowCount(); i++) {
                            current = "";
				if(((ViewInventory)list.get(0).getContent()).isToggle()){
					for (int j = 0; j < model.getColumnCount(); j++) {
						 if(((ViewInventory)list.get(0).getContent()).isColVisible(headers.get(j))){
                                                    if(model.getValueAt(i, j)!=null){
                                                        entry = model.getValueAt(i, j).toString().split(",");
                                                        if(entry.length>1)
                                                            current = current + entry[0] + entry [1]+", ";
                                                        else current = current + model.getValueAt(i, j)+", ";
                                                    }
                                                    else current = current + ", ";
                                                 }
					}
				}
				else{
					for (int j = 0; j+1 < model.getColumnCount(); j++) {
						 if(((ViewInventory)list.get(0).getContent()).isColVisible(headers.get(j))){
                                                    if(model.getValueAt(i, j)!=null){
                                                        entry = model.getValueAt(i, j).toString().split(",");
                                                        if(entry.length>1)
                                                            current = current + entry[0] + entry [1]+", ";
                                                        else current = current + model.getValueAt(i, j)+", ";
                                                    }
                                                    else current = current + ", ";
                                                    
                                                 }
					}
				}
				current = current.substring(0,current.length()-2) + "\n";
                                data.add(current);
			}
                         for(Iterator i = data.iterator();i.hasNext();){
                            //System.out.print(i.next());
                        }
                        new ExportInventory(gui, data.iterator());
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			//System.out.println("HEEERE");
			setReturn();
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("delete")){
			setDelete();
		}
	}
	
}
