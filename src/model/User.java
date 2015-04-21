/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Laptop
 */
public class User {

	private String username;
	private String password;
	boolean isManager;
	private int employeeID;
	
	public User(String username, String password, boolean isManager, int employeeID) {
		setUsername(username);
		setPassword(password);
		setIsManager(isManager);
		setEmployeeID(employeeID);

	}
	
	public User() {
		setUsername("");
		setPassword("");
		setIsManager(false);
		
	}


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public int getEmployeeID(){
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID){
		this.employeeID = employeeID;
	}

	public boolean isManager(){
		return isManager;
	}
	
	public void setUsername(String username) {
		this.username = username;
		
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}
}
