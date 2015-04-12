package model;

import java.util.Date;

public class Project {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private String employee;
	
	public Project(String name, Date startDate, Date endDate, String employee){
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		setEmployee(employee);
	}
	
	public Project(){
		setName("");
		setStartDate(null);
		setEndDate(null);
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
	
	public void setEmployee(String employee){
		this.employee = employee;
	}
	
	public String getEmployee(){
		return employee;
	}
	
	public String toString(){
		return name;
	}
}
