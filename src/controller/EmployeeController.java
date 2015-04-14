/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.Employee;
import model.Project;
import model.Supplier;
import model.SupplierContact;
import model.database.DAO;
import model.database.EmployeeDAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class EmployeeController implements Subject {

    private static EmployeeController instance = null;
    private DAO dao;
    private Employee employee;
    private ArrayList<Observer> observerList;
    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
        employee = new Employee();
        employeeDAO = new EmployeeDAO();
        
    }
    
    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }
    
    public void init(){
    	employee = new Employee();
    }

    public Iterator filter(Iterator conditions) {
        return dao.filter("Employee", conditions);
    }
    
    public Iterator getDistinct(String string){
        return dao.getDistinct("Employee", string);
    }

    public void addEmployee(Employee emp) {
        // TODO Auto-generated method stub
        employee.setName(emp.getName());
        employee.setID(emp.getID());
        employee.setStatus(emp.getStatus());

        dao.add("Employee", employee);
        
        Iterator i = emp.getProjectList();
        while(i.hasNext()){
        	dao.add("Project", (Project) i.next());
        }
        notifyObserver();
    }
    
    
    public void editEmployee(Employee emp, String key) {
    	employee.setName(emp.getName());
    	employee.setID(emp.getID());
    	employee.setStatus(emp.getStatus());
    	employee.setProjectList(emp.getProjectList());
    	System.out.println("Employee name: "+employee.getName());
    	System.out.println("Employee id: "+employee.getID());
    	System.out.println("Employee status: "+employee.getStatus());
    	
    	dao.update("Employee", employee, key);
    	Iterator i = employee.getProjectList();
    	while(i.hasNext()){
    		Project pie = (Project) i.next();
    		System.out.println("Project: "+pie);
    		employeeDAO.addProjects(employee, pie);
    	}
    	
    	notifyObserver();
    }

    public Object getObject(String key) {
        return dao.get("Employee", key);
    }
    
    public Employee getEmployee(){
    	return employee;
    }
    public Iterator getAll() {
        return dao.get("Employee");
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observerList) {
            o.update();
        }
    }
    
    public int getEmployeeID(){
    	return employeeDAO.getID();
    }

}
