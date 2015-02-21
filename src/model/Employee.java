/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caista.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class Employee {
    private int ID;
    private String name;

    public Employee(int ID, String name) {
        setID(ID);
        setName(name);
    }
    
    public Employee() {
        setID(0);
        setName("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
