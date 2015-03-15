/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.database.DAO;
import model.User;

/**
 *
 * @author Laptop
 */
public class UserController {

    private static UserController instance;
    private DAO dao;
    private User u;

    public UserController() {
        dao = DAO.getInstance();
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
}
