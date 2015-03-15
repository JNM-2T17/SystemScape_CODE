package view.supplier;
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


public class TabSupplier extends JPanel implements ActionListener{
	private CardLayout cl;
	private ArrayList<Content> list;
	private Gui gui;
	
	public TabSupplier(Gui gui) {
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Suppliers").add(true).filter(true).content(new ViewSuppliers()).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
		temp=new Content.ContentBuilder().caption("Add Supplier").back(true).content(new AddSupplier(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
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
				System.out.println(fs.getValues());
			}
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			cl.show(this, "view");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("suppFilter")){
			System.out.println("filtered");
		}
	}

}
