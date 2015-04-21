/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class Employee {
	private int ID;
	private String name;
	private String status;
	private String type;
	private boolean isAdmin;
	private ArrayList<Project> projects;
	
	public Employee(int ID, String name, String status, String type) {
		setID(ID);
		setName(name);
		setStatus(status);
		setType(type);
		projects = new ArrayList<Project>();
		isAdmin = false;
	}

	public Employee() {
		setID(0);
		setName("");
		setStatus("");
		projects = new ArrayList<Project>();
		isAdmin = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}
	
	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public Project getProject(int index) {
    	if (index < 0 || index >= projects.size()) {
        	return null;
    	}
    	return projects.get(index);
	}

	public Iterator getProjectList() {
		return projects.iterator();
	}
	
	public void delete(){
		for(int i =0; i<projects.size(); i++)
			projects.remove(i);
	}
	
	public void addProject(String name, Date startDate, Date endDate) {
		projects.add(new Project(name, startDate, endDate));
	}

	public void setProjectList(Iterator project) {
		while(project.hasNext()){
			this.projects.add(((Project)project.next()));
		}
	}
	
	public String toString(){
		return name;
	}
}
