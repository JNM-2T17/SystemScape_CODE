package view.projects;

import controller.ProjectController;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import model.Project;
import model.Supplier;
import model.SupplierContact;
import view.CellEdit;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;
import view.supplier.SupplierCellEdit;

public class ViewProjects extends ViewTemplate implements Observer {

//	SupplierController supplierController;
	ProjectController projectController;
	private TabProject tab;
	private JFrame parent;

	public ViewProjects(JFrame parent, TabProject tab) {
		super();
		this.parent=parent;
		this.tab = tab;
		projectController = ProjectController.getInstance();
		projectController.registerObserver(this);
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(4);
		String headers[] = { "Project", "Start Date", "End Date", "" };
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {

		clearTable();
		Project project;
		Iterator data = projectController.getAll();
		while (data.hasNext()) {

            project = (Project) data.next();
            getModel().setRowCount(getModel().getRowCount() + 1);
            getModel().setValueAt(project.getName(),
            getModel().getRowCount() - 1, 0);
            
            SimpleDateFormat df=new SimpleDateFormat("MMMM dd, yyyy");
            
            getModel().setValueAt(df.format(project.getStartDate()), getModel().getRowCount() - 1, 1);
            
            getModel().setValueAt(df.format(project.getEndDate()), getModel().getRowCount() - 1, 2);
            getModel().setValueAt(new ProjectCellEdit(project, tab),
    				getModel().getRowCount() - 1, 3);

        }
        if (this.tab == null) {
            System.out.println("VIEW TAB NULL");
        } else {
            System.out.println("Gio");
        }
        
	}

	public void filterPopulate(Iterator data) {
		clearTable();
		while (data.hasNext()) {
			SimpleDateFormat df=new SimpleDateFormat("MMM dd, yyyy");
                        Project project = (Project)data.next();
			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(project.getName(), getModel().getRowCount() - 1, 0);
			getModel().setValueAt(df.format(project.getStartDate()), getModel().getRowCount() - 1, 1);
			getModel().setValueAt(df.format(project.getEndDate()), getModel().getRowCount() - 1, 2);
                        getModel().setValueAt(new ProjectCellEdit(project, tab),
    				getModel().getRowCount() - 1, 3);

			
		}
	}
}
