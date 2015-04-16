package view.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private SimpleDateFormat dateFormat;
	
	public ViewEmployee(JFrame parent, TabEmployees tab){
		super();
		this.parent=parent;
		this.tab = tab;
		dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
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
			Date sDate = null;
			Date eDate = null;
			int i = 0;
			while(projectsIterator.hasNext()){
                                Project project = (Project)projectsIterator.next();
				projectsString = projectsString + project.toString() + ", ";
				projectsList.add(project);
				sDate = projectsList.get(i).getStartDate();
				eDate = projectsList.get(i).getEndDate();
				if(sDate != null && eDate != null)
				{
					projectsDate = projectsDate + "StartDate: " + dateFormat.format(sDate) 
							 + "\n EndDate: " + dateFormat.format(eDate) + ", ";
				}
				else
				{
					projectsDate = projectsDate + "StartDate: " + sDate 
							 + "\n EndDate: " + eDate + ", ";
				}
		
				
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
            packTable();
            
		}
		
	}
	
	public void filterPopulate(Iterator data) {
		clearTable();
		Employee employee;
		ArrayList<Project> projectsList = new ArrayList<Project>();
		while(data.hasNext()){
			employee = (Employee) data.next();
			System.out.println("Employee THINGY " + employee.getName());
			Iterator projectsIterator = employee.getProjectList();
			String projectsString = "";
			String projectsDate = "";
			Date sDate = null;
			Date eDate = null;
			int i = 0;
			while(projectsIterator.hasNext()){
                                Project project = (Project)projectsIterator.next();
				projectsString = projectsString + project.toString() + ", ";
				projectsList.add(project);
				sDate = projectsList.get(i).getStartDate();
				eDate = projectsList.get(i).getEndDate();
				if(sDate != null && eDate != null)
				{
					projectsDate = projectsDate + "StartDate: " + dateFormat.format(sDate) 
							 + "\n EndDate: " + dateFormat.format(eDate) + ", ";
				}
				else
				{
					projectsDate = projectsDate + "StartDate: " + sDate 
							 + "\n EndDate: " + eDate + ", ";
				}
		
				
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
            packTable();
            
		}
	}

}
