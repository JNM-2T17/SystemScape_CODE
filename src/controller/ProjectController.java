package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Employee;
import model.Project;
import model.Supplier;
import model.database.DAO;
import model.database.ProjectDAO;
import view.Observer;
import view.Subject;

public class ProjectController implements Subject {
	
	private static ProjectController instance;
    private DAO dao;
    private ProjectDAO projectDAO;
    private Project project;
    private ArrayList<Observer> observerList;

    public ProjectController() {
        dao = DAO.getInstance();
        projectDAO = new ProjectDAO();
        project = new Project();
        observerList = new ArrayList();
    }
    
    public static ProjectController getInstance() {
        if (instance == null) {
            instance = new ProjectController();
        }
        return instance;
    }
    
    public void init() {
        project = new Project();
    }
    
    public Iterator search(String searchStr){
        return dao.search("project", searchStr);
    }
     
     public Iterator filter(Iterator conds){
         return dao.filter("project", conds);
     }
    

    public Project getProject() {
        return project;
    }
    
    public Object getObject(String key){
        return dao.get("project", key);
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        // TODO Auto-generated method stub
        observerList.remove(o);
    }

    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        for (Observer o : observerList) {
            o.update();
        }
    }
    
    public Iterator getAll(){
        return dao.get("project");
    }
    
    public void addProject(Project project) {
		// TODO Auto-generated method stub
        // Supplier supplier = (Supplier) dao.get("Supplier", supplierName);
        this.project.setName(project.getName());
        this.project.setStartDate(project.getStartDate());
        this.project.setEndDate(project.getEndDate());
        
        dao.add("project", this.project);
        notifyObserver();
    }
    
    public void editProject(Project project, String key){
    	this.project.setName(project.getName());
    	this.project.setStartDate(project.getStartDate());
        this.project.setEndDate(project.getEndDate());
        this.project.setEmployeeList(project.getEmployeeList());
        projectDAO.deleteAssignment(project);
        dao.update("Project", this.project, key);
        Iterator i = project.getEmployeeList();
        while(i.hasNext()){
        	Employee ofthemonth = (Employee) i.next();
        	System.out.println("Controller employee: "+ofthemonth.getName());
        	projectDAO.addEmployees(ofthemonth, this.project);
        }
        
        notifyObserver();
    	
    }
    
    public Iterator getEmployees(){
    	return project.getEmployeeList();
    }
    
    public void setEmployees(Iterator employees){
    	this.project.setEmployeeList(employees);
    }

    

}
