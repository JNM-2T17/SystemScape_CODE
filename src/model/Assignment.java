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
    private String employee;
    private String project;
    
    public Assignment (int ID, String employee, String project){
        this.ID = ID;
        this.employee = employee;
        this.project = project;
    }

    public int getID() {
        return ID;
    }

    public String getEmployee() {
        return employee;
    }

    public String getProject() {
        return project;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
