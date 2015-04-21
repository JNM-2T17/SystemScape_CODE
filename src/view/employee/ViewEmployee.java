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
import model.ProjectAssignment;
import view.Observer;
import view.PanelCell;
import view.ViewTemplate;
import view.projects.ProjectCellEdit;
import view.projects.TabProject;

public class ViewEmployee extends ViewTemplate implements Observer{
	
	EmployeeController employeeController;
	ProjectController projectController;
	private TabEmployees tab;
	private JFrame parent;
	private SimpleDateFormat dateFormat;
	
	public ViewEmployee(JFrame parent, TabEmployees tab){
		super();
		this.parent=parent;
		this.tab = tab;
		dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		employeeController = EmployeeController.getInstance();
		projectController = ProjectController.getInstance();
		employeeController.registerObserver(this);
		projectController.registerObserver(this);
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(6);
		String headers[]={"Name", "Status", "Projects", "Project Start Date", "Project End Date", ""};
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void update() {
		SimpleDateFormat df=new SimpleDateFormat("MMMM dd, yyyy");
		clearTable();
		Employee employee;
		Iterator data = employeeController.getAll();
		while(data.hasNext()){
			employee = (Employee) data.next();
			System.out.println("Employee THINGY " + employee.getName());
			
			//added
			ArrayList<String> projectsList = new ArrayList<String>();
			Iterator projectAssignmentIT = employeeController.getProjectsFromAssignment(Integer.toString(employee.getID()));
			while(projectAssignmentIT.hasNext()){
				ProjectAssignment pa = (ProjectAssignment) projectAssignmentIT.next();
				System.out.println("Employee id: "+pa.getEmployeeID());
				projectsList.add(pa.getProject());
			}
			
			ArrayList<Project> projectArrayList = new ArrayList<Project>();
			for(int i = 0; i<projectsList.size(); i++){
				Project projTemp = (Project) projectController.getObject(projectsList.get(i));
				projectArrayList.add(projTemp);
			}
			
			Iterator it = projectArrayList.iterator();
			//
			
			Iterator projectsIterator = employee.getProjectList();
			Date sDate = null;
			Date eDate = null;
			boolean start=true;
			
			if(!projectsIterator.hasNext()){
				getModel().setRowCount(getModel().getRowCount() + 1);
				getModel().setValueAt(employee.getName(), getModel().getRowCount() - 1, 0);
				getModel().setValueAt(employee.getStatus(), getModel().getRowCount() - 1, 1);
				getModel().setValueAt("", getModel().getRowCount() - 1, 2);
				getModel().setValueAt("", getModel().getRowCount() - 1, 3);
	            getModel().setValueAt("", getModel().getRowCount() - 1, 4);
	            getModel().setValueAt(new EmployeeCellEdit(employee, tab),
	                    getModel().getRowCount() - 1, 5);
	            packTable();
			}
			
			while(projectsIterator.hasNext()){
				getModel().setRowCount(getModel().getRowCount() + 1);
				if(start){
					getModel().setValueAt(employee.getName(), getModel().getRowCount() - 1, 0);
					getModel().setValueAt(employee.getStatus(), getModel().getRowCount() - 1, 1);
				}
				else{
					getModel().setValueAt("", getModel().getRowCount() - 1, 0);
					getModel().setValueAt("", getModel().getRowCount() - 1, 1);
				}
                
				Project project = (Project)projectsIterator.next();
                
				getModel().setValueAt(project.getName(), getModel().getRowCount() - 1, 2);
				getModel().setValueAt(df.format(project.getStartDate()), getModel().getRowCount() - 1, 3);
	            getModel().setValueAt(df.format(project.getEndDate()), getModel().getRowCount() - 1, 4);
	            if(start) {
	            	getModel().setValueAt(new EmployeeCellEdit(employee, tab), getModel().getRowCount() - 1, 5);
	            }
	            else{
	            	getModel().setValueAt(new EmployeeCellEdit(null, tab), getModel().getRowCount() - 1, 5);
		        }
	            
                start=false;
                
                packTable();
			}
		}
		
	}
	
	public void filterPopulate(Iterator data) {
		SimpleDateFormat df=new SimpleDateFormat("MMMM dd, yyyy");
		clearTable();
		Employee employee = null;
		Employee prevEmployee = null;
		while(data.hasNext()){
			if(prevEmployee!=null){
				while(prevEmployee == employee)
					prevEmployee = (Employee) data.next();
				prevEmployee = (Employee) data.next();
				employee = prevEmployee;
			}
			else{
				employee = (Employee) data.next();
			}
			
			/////////////////////////////////////////
			ArrayList<String> projectsList = new ArrayList<String>();
			Iterator projectAssignmentIT = employeeController.getProjectsFromAssignment(Integer.toString(employee.getID()));
			while(projectAssignmentIT.hasNext()){
				ProjectAssignment pa = (ProjectAssignment) projectAssignmentIT.next();
				System.out.println("Employee id: "+pa.getEmployeeID());
				projectsList.add(pa.getProject());
			}
			
			ArrayList<Project> projectArrayList = new ArrayList<Project>();
			for(int i = 0; i<projectsList.size(); i++){
				Project projTemp = (Project) projectController.getObject(projectsList.get(i));
				projectArrayList.add(projTemp);
			}
			
			///////////////////////////////////////////
			System.out.println("Employee THINGY " + employee.getName());
			Iterator projectsIterator = projectArrayList.iterator();
			Date sDate = null;
			Date eDate = null;
			boolean start=true;
			
			if(!projectsIterator.hasNext()){
				getModel().setRowCount(getModel().getRowCount() + 1);
				getModel().setValueAt(employee.getName(), getModel().getRowCount() - 1, 0);
				getModel().setValueAt(employee.getStatus(), getModel().getRowCount() - 1, 1);
				getModel().setValueAt("", getModel().getRowCount() - 1, 2);
				getModel().setValueAt("", getModel().getRowCount() - 1, 3);
	            getModel().setValueAt("", getModel().getRowCount() - 1, 4);
	            getModel().setValueAt(new EmployeeCellEdit(employee, tab),
	                    getModel().getRowCount() - 1, 5);
	            packTable();
			}
			
			while(projectsIterator.hasNext()){
				getModel().setRowCount(getModel().getRowCount() + 1);
				if(start){
					getModel().setValueAt(employee.getName(), getModel().getRowCount() - 1, 0);
					getModel().setValueAt(employee.getStatus(), getModel().getRowCount() - 1, 1);
				}
				else{
					getModel().setValueAt("", getModel().getRowCount() - 1, 0);
					getModel().setValueAt("", getModel().getRowCount() - 1, 1);
				}
                
				Project project = (Project)projectsIterator.next();
                
				getModel().setValueAt(project.getName(), getModel().getRowCount() - 1, 2);
				getModel().setValueAt(df.format(project.getStartDate()), getModel().getRowCount() - 1, 3);
	            getModel().setValueAt(df.format(project.getEndDate()), getModel().getRowCount() - 1, 4);
	            if(start) {
	            	getModel().setValueAt(new EmployeeCellEdit(employee, tab), getModel().getRowCount() - 1, 5);
	            }
	            else{
	            	getModel().setValueAt(new EmployeeCellEdit(null, tab), getModel().getRowCount() - 1, 5);
		        }
	                    
	            
                start=false;
                
                packTable();
			}
			
			prevEmployee = employee;
		}
	}

}
