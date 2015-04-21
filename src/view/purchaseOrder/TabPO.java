package view.purchaseOrder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.PurchaseOrderController;
import controller.SupplierController;

import java.util.Iterator;

import model.PurchaseOrder;
import model.Supplier;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;
import view.supplier.EditSupplier;
import view.supplier.ViewListSuppliers;

public class TabPO extends JPanel implements ActionListener {
	private ArrayList<Content> list;
	private CardLayout cl;
	private Gui gui;
	private PurchaseOrderController purchaseOrderController;

	// private Content temp;
	public TabPO(Gui gui) {
		setEnabled(false);
		cl = new CardLayout();
		purchaseOrderController = PurchaseOrderController.getInstance();
		list = new ArrayList<Content>();
		this.gui = gui;
		this.setBackground(Color.WHITE);
		setLayout(cl);

		Content temp = new Content.ContentBuilder()
				.caption("View Purchase Orders").add(true)
				.content(new ViewPO(gui, this)).filter(true).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);

		temp = new Content.ContentBuilder().caption("Add Purchase Order")
				.back(true).content(new AddPO(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);

		cl.show(this, "view");
	}

	public void setEdit(PurchaseOrder po) {

		Content temp = new Content.ContentBuilder()
				.caption("Edit Purchase Order").delete(true).back(true)
				.content(new EditPO(gui, po)).build();
		temp.getBtnBack().addActionListener(this);
		temp.getBtnDelete().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);

		cl.show(this, "edit");
	}

	public void updateSupplierBox() {
		((AddPO) list.get(1).getContent()).populateSupplierNames();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (((JButton) e.getSource()).getActionCommand().equals("add")) {
			cl.show(this, "add");
			updateSupplierBox();
			// JOptionPane.showMessageDialog(null, "hi");
		} else if (((JButton) e.getSource()).getActionCommand()
				.equals("filter")) {
			FilterPO fs = new FilterPO(gui);
			if (!fs.isClosed()) {
				Iterator values = purchaseOrderController
						.filter(fs.getValues());
				((ViewPO) list.get(0).getContent()).filterPopulate(values);
			}
		} else if (((JButton) e.getSource()).getActionCommand().equals("back")) {

			((AddPO) list.get(1).getContent()).clear();
			cl.show(this, "view");
		} else if (((JButton) e.getSource()).getActionCommand().equals("delete")) {
			PurchaseOrder del = null;
			int i=list.size()-1;
			while(i>=0){
				if(list.get(i).getContent() instanceof EditPO){
					 del=((EditPO)list.get(i).getContent()).getPO();
				}
				i--;
			}
			System.out.println(del);
		}
	}

	public void setReturn() {
		// TODO Auto-generated method stub
		cl.show(this, "view");
	}

	public void setView(PurchaseOrder po) {
		// TODO Auto-generated method stub
		Content temp = new Content.ContentBuilder()
				.caption("View Specific Supplier")
				.back(true)
				.content(
						new ViewListPO(this, gui, PurchaseOrderController
								.getInstance().getAll(), po)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "preview");
		list.add(temp);

		cl.show(this, "preview");
	}

}
