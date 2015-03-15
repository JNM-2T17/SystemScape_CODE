package view.inventory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.Content;
import view.Gui;
import view.Content.ContentBuilder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
	
	public TabInventory(Gui gui) {
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		PanelRegistry.getInstance().setTabInventory(this);
		this.setBackground(Color.WHITE);
		setLayout(cl);
		Content temp=new Content.ContentBuilder().caption("View Inventory").add(true).filter(true).export(true).content(new ViewInventory()).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		temp.getBtnExport().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
	
		displayIT();
		
		
		cl.show(this, "view");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
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
	
	public void revertToMain()
	{
	if(temp != null)
		{
			remove(temp);
		}
		PanelRegistry.getInstance().clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		PanelRegistry.getInstance().registerParticipant(itemTileGenInfo);
		PanelRegistry.getInstance().registerParticipant(itemTileIT);
		PanelRegistry.getInstance().registerParticipant(itemTileWarranty);
		PanelRegistry.getInstance().registerParticipant(itemTileContract);
		
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("View Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "view");
		revalidate();
		repaint();	
            
            
                
	}
        
	public void displayIT()
	{
		if(temp != null)
		{
			remove(temp);
		}
		PanelRegistry.getInstance().clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileIT = new ItemTileIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		PanelRegistry.getInstance().registerParticipant(itemTileGenInfo);
		PanelRegistry.getInstance().registerParticipant(itemTileIT);
		PanelRegistry.getInstance().registerParticipant(itemTileWarranty);
		PanelRegistry.getInstance().registerParticipant(itemTileContract);
		
		
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
	
	public void displayNonIT()
	{
		if(temp != null)
		{
			remove(temp);
		}
		PanelRegistry.getInstance().clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileContract = new ItemTileContract(gui, template);
		itemTileWarranty = new ItemTileWarranty(gui, itemTileContract);
		itemTileNonIT = new ItemTileNonIT(gui, itemTileWarranty);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileNonIT);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		PanelRegistry.getInstance().registerParticipant(itemTileGenInfo);
		PanelRegistry.getInstance().registerParticipant(itemTileNonIT);
		PanelRegistry.getInstance().registerParticipant(itemTileWarranty);
		PanelRegistry.getInstance().registerParticipant(itemTileContract);
		
		
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
	
	public void displaySoftware()
	{
		if(temp != null) {
			remove(temp);
		}
		PanelRegistry.getInstance().clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileSoftware = new ItemTileSoftware(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileSoftware);
		ItemPanelDecorator dec = itemTileGenInfo;		

		PanelRegistry.getInstance().registerParticipant(itemTileGenInfo);
		PanelRegistry.getInstance().registerParticipant(itemTileSoftware);
		
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("View Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");	
		revalidate();
		repaint();
	}
	
	public void displayGeneral()
	{
		if(temp != null)
		{
			remove(temp);
		}
		PanelRegistry.getInstance().clearParticipants();
		BasicAddItem template = new BasicAddItem();
		itemTileWarranty = new ItemTileWarranty(gui, template);
		itemTileGeneral = new ItemTileGeneral(gui, template);
		itemTileGenInfo = new ItemTileGenInfo(gui, itemTileGeneral);
		ItemPanelDecorator dec = itemTileGenInfo;
		

		PanelRegistry.getInstance().registerParticipant(itemTileGenInfo);
		PanelRegistry.getInstance().registerParticipant(itemTileGeneral);
		dec.renderPanel();
		dec.repaint();
		dec.revalidate();
		temp=new Content.ContentBuilder().caption("View Inventory").back(true).content(template).build();
		temp.getBtnBack().addActionListener(this);
		
		this.add(temp, "add");
		cl.show(this, "add");	
		revalidate();
		repaint();
		
		
	}

}
