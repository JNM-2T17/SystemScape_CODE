package view.employee;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import controller.EmployeeController;
import controller.ProjectController;
import model.Employee;
import model.Project;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;
import view.projects.ProjectCellEdit;
import view.projects.TabProject;

public class ViewEmployee extends ViewTemplate implements Observer{
	
	EmployeeController employeeController;
	private TabEmployees tab;
	private JFrame parent;
	
	public ViewEmployee(JFrame parent, TabEmployees tab){
		super();
		this.parent=parent;
		this.tab = tab;
		if (this.tab == null)
			System.out.println("VIEW CONST TAB NULL");
		else
			System.out.println("Gio");
		employeeController = EmployeeController.getInstance();
		employeeController.registerObserver(this);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(5);
		String headers[]={"Name", "Status", "Projects", "Project Date", ""};
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void update() {
		clearTable();
		Employee employee;
		ArrayList<Project> projectsList = new ArrayList<Project>();
		Iterator data = employeeController.getAll();
		while(data.hasNext()){
			employee = (Employee) data.next();
			System.out.println("Employee THINGY " + employee.getName());
			Iterator projectsIterator = employee.getProjectList();
			String projectsString = "";
			String projectsDate = "";
			int i = 0;
			while(projectsIterator.hasNext()){
				projectsString = projectsString + ((Project) projectsIterator.next()).toString() + ", ";
				projectsList.add((Project) projectsIterator.next());
				projectsDate = projectsDate + "StartDate: " + projectsList.get(i).getStartDate() + " EndDate: +" + projectsList.get(i).getEndDate() + ", ";
				
				i+=1;
				System.out.println(projectsString);
			}
			getModel().setRowCount(getModel().getRowCount() + 1);
            getModel().setValueAt(employee.getName(),
            getModel().getRowCount() - 1, 0);
            
   
            getModel().setValueAt(employee.getStatus(), getModel().getRowCount() - 1, 1);
           
            getModel().setValueAt(projectsString, getModel().getRowCount() - 1, 2);
            getModel().setValueAt(projectsDate, getModel().getRowCount() - 1, 3);
            getModel().setValueAt(new EmployeeCellEdit(employee, tab),
                    getModel().getRowCount() - 1, 4);
            
            
		}
		
	}
	
	public void filterPopulate(Iterator data) {
		clearTable();
		while (data.hasNext()) {
			getModel().setRowCount(getModel().getRowCount() + 1);
			getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 0);
			getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 1);
			getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 2);
			getModel().setValueAt(data.next(), getModel().getRowCount() - 1, 3);
		}
	}

}
