package view.employee;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TabEmployees extends JPanel implements ActionListener{
	private CardLayout cl;
	private ArrayList<Content> list;
	private Gui gui;
	private EmployeeController employeeController;
	public TabEmployees(Gui gui) {
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Employees").add(true).filter(true).content(new ViewEmployee(gui, this)).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
		temp=new Content.ContentBuilder().caption("Add Employee").back(true).content(new AddEmployee(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
		temp=new Content.ContentBuilder().caption("Edit Employee").back(true).delete(true).content(new EditEmployee(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "edit");
		list.add(temp);
		
		cl.show(this, "view");
		employeeController.getInstance();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			cl.show(this, "add");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			new FilterEmployee(gui);
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			cl.show(this, "view");
		}
	}

}
