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

	String username;
	String password;
	boolean isManager;
	
	public User(String username, String password, boolean isManager) {
		this.username = username;
		this.password = password;
		this.isManager = isManager;

	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isManager(){
		return isManager;
	}
}
