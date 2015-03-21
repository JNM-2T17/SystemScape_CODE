package view.purchaseOrder;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.PurchaseOrder;
import model.Supplier;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;
import view.supplier.EditSupplier;


public class TabPO extends JPanel implements ActionListener{
	private ArrayList<Content> list;
	private CardLayout cl;
	private Gui gui;
	public TabPO(Gui gui)
	{
		setEnabled(false);
		cl = new CardLayout();
		list = new ArrayList<Content>();
		this.gui=gui;
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Purchase Orders").add(true).content(new ViewPO(gui, this)).filter(true).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
		
		temp=new Content.ContentBuilder().caption("Add Purchase Order").back(true).content(new AddPO(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
		temp=new Content.ContentBuilder().caption("List of Purchase Order").add(true).filter(true).content(new ViewListPO()).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "listPO");
		list.add(temp);
		
		cl.show(this, "view");
	}
	
	public void setEdit(PurchaseOrder po){
		
		Content temp=new Content.ContentBuilder().caption("Edit Purchase Order").delete(true).back(true).content(new EditPO(gui, po)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);
		
		cl.show(this, "edit");
	}
     
	
    public void updateSupplierBox(){
        ((AddPO)list.get(1).getContent()).populateSupplierNames();
    }
    

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			cl.show(this, "add");
                        updateSupplierBox();
//			JOptionPane.showMessageDialog(null, "hi");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			new FilterPO(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			cl.show(this, "view");
		}
	}
	
}
