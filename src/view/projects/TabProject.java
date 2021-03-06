package view.projects;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ProjectController;
import controller.SupplierController;
import model.Employee;
import model.Project;
import model.Supplier;
import view.Content;
import view.Gui;
import view.Content.ContentBuilder;
import view.employee.EditEmployee;
import view.purchaseOrder.AddPO;
import view.supplier.FilterSupplier;
import view.supplier.ViewListSuppliers;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;


public class TabProject extends JPanel implements ActionListener{
	private CardLayout cl;
	private ArrayList<Content> list;
	private Gui gui;
	private ProjectController projectController;
	
	public TabProject(Gui gui) {
		cl=new CardLayout();
		list=new ArrayList<Content>();
		this.gui=gui;
		
		this.setBackground(Color.WHITE);
		setLayout(cl);
		
		Content temp=new Content.ContentBuilder().caption("View Projects").add(true).filter(true).content(new ViewProjects(gui, this)).build();
		temp.getBtnAdd().addActionListener(this);
		temp.getBtnFilter().addActionListener(this);
		this.add(temp, "view");
		list.add(temp);
		
		temp=new Content.ContentBuilder().caption("Add Project").back(true).content(new AddProject(gui)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "add");
		list.add(temp);
		
		cl.show(this, "view");
		projectController = ProjectController.getInstance();
		
	}
	
	public void setEdit(Project txt){
		Content temp=new Content.ContentBuilder().caption("Edit Project").back(true).delete(true).content(new EditProject(gui, txt)).build();
		temp.getBtnBack().addActionListener(this);
		temp.getBtnDelete().addActionListener(this);
		
		this.add(temp, "edit");
		list.add(temp);
		
		cl.show(this, "edit");
	}
	
	public void setView(Project project){
		
		Content temp=new Content.ContentBuilder().caption("View Specific Project").back(true).content(new ViewListProjects(this, gui, ProjectController.getInstance().getAll(), project)).build();
		temp.getBtnBack().addActionListener(this);
		this.add(temp, "preview");
		list.add(temp);
		
		cl.show(this, "preview");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(((JButton) e.getSource()).getActionCommand().equals("add")){
			cl.show(this, "add");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("filter")){
			FilterProject fp=new FilterProject(gui);
			if(!fp.isClosed()){
				Iterator values = projectController.filter(fp.getValues());
				((ViewProjects)list.get(0).getContent()).filterPopulate(values);
			}
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("back")){
			((AddProject) list.get(1).getContent()).clear();
			cl.show(this, "view");
		}
		else if(((JButton) e.getSource()).getActionCommand().equals("delete")){
			Project del = null;
			int i=list.size()-1;
			while(i>=0){
				if(list.get(i).getContent() instanceof EditProject){
					 del=((EditProject)list.get(i).getContent()).getProject();
				}
				i--;
			}
			//System.out.println(del);
		}
	}

	public void setReturn() {
		cl.show(this, "view");
	}
	


}
