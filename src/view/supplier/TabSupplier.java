package view.supplier;
import controller.SupplierController;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.SupplierController;
import model.Supplier;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TabSupplier extends JPanel implements ActionListener{
	private CardLayout cl;
	private ArrayList<Content> list;
	private Gui gui;
	private SupplierController supplierController;

	public TabSupplier(Gui gui) {
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;

		this.setBackground(Color.WHITE);
		setLayout(cl);

		Content temp=new Content.ContentBuilder().caption("View Suppliers").add(true).filter(true).content(new ViewSuppliers(this)).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);

		temp=new Content.ContentBuilder().caption("Add Supplier").back(true).content(new AddSupplier(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);

		cl.show(this, "view");
		supplierController = SupplierController.getInstance();
	}

	public void setEdit(Supplier supp){
		Content temp=new Content.ContentBuilder().caption("Edit Supplier").back(true).delete(true).content(new EditSupplier(gui, supp)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);

		cl.show(this, "edit");
	}

	public void setView(Supplier supp){

		Content temp=new Content.ContentBuilder().caption("View Specific Supplier").back(true).content(new ViewListSuppliers(this, gui, SupplierController.getInstance().getAll(), supp)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "preview");
		list.add(temp);

		cl.show(this, "preview");
	}

	public void setReturn(){
		cl.show(this, "view");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			cl.show(this, "add");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			FilterSupplier fs=new FilterSupplier(gui);
			if(!fs.isClosed()){
				Iterator values = supplierController.filter(fs.getValues());
				((ViewSuppliers)list.get(0).getContent()).filterPopulate(values);
			}
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			((AddSupplier) list.get(1).getContent()).clear();
			cl.show(this, "view");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("suppFilter")){
			System.out.println("filtered");
		}
	}

}
