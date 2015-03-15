/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.database.DAO;
import model.User;
import model.database.InputCheckDAO;

/**
 *
 * @author Laptop
 */
public class InputCheckController {
    private InputCheckDAO inputCheckDAO= new InputCheckDAO();
    
    public boolean checkIfExists(String table, String column, String key) {
        return inputCheckDAO.checkIfExists(table, column, key);
    }
}
