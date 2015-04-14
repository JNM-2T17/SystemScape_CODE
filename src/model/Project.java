package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Project {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private ArrayList<Employee> employees;
	
	public Project(String name, Date startDate, Date endDate){
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		employees = new ArrayList<Employee>();
		
	}
	
	public Project(){
		setName("");
		setStartDate(null);
		setEndDate(null);
		employees = new ArrayList<Employee>();
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public String toString(){
		return name;
	}
	
	public void addEmployee(Employee emp){
		employees.add(emp);
	}
	
	public Iterator getEmployeeList(){
		return employees.iterator();
	}
	
	public void setEmployeeList(Iterator employee) {
		while(employee.hasNext()){
			this.employees.add(((Employee)employee.next()));
		}
	}
	
	
}
