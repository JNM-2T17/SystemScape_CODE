/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Christian Gabriel
 */
public class Assignment {
    private int ID;
    private Employee employee;
    
    public Assignment (int ID, Employee employee ){
        this.ID = ID;
        this.employee = employee;
    }

    public int getID() {
        return ID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
