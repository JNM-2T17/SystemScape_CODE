package model;

import java.util.Date;

public class Project {
	
	private String name;
	private Date startDate;
	private Date endDate;
	
	public Project(String name, Date startDate, Date endDate){
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		
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
	
	public String toString(){
		return name;
	}
}
