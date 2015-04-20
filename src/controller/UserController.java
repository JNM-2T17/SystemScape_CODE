/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.database.DAO;
import model.database.UserDAO;
import model.User;

/**
 *
 * @author Laptop
 */
public class UserController {

    private static UserController instance;
    private DAO dao;
    private User u;
    private UserDAO userDAO;

    public UserController() {
        dao = DAO.getInstance();
        userDAO = new UserDAO();
        u = new User();
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public Object getUser(String username) {
        return dao.get("user", username);
    }
    
    public void addUser(User user){
    	u.setUsername(user.getUsername());
    	u.setPassword(user.getPassword());
    	u.setIsManager(u.isManager());
    	userDAO.add(u);
    }
    
    public boolean checkExistingUser(String user){
    	ArrayList<User> userList = new ArrayList<User>();
    	Iterator i= userDAO.getUsers();
    	while(i.hasNext()){
    		userList.add((User)i.next());
    	}
    	
    	for(int index = 0; index<userList.size(); index++){
    		if(user.equals(userList.get(index).getUsername())){
    			return true;
    		}
    	}
    	
    	return false;
    	
    }
}
