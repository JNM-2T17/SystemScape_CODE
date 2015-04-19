/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

/**
 *
 * @author Christian Gabriel
 */
public class Assignment {
    private int ID;
    private Employee employee;
    private Date startDate;
    private Date endDate;
    
    public Assignment (int ID, Employee employee, Date startDate, Date endDate){
        this.ID = ID;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
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
    
    public void setStartDate(Date startDate){
    	this.startDate = startDate;
    }
    
    public void setEndDate(Date endDate){
    	this.endDate = endDate;
    }
    
    public Date getStartDate(){
    	return startDate;
    }
    
    public Date getEndDate(){
    	return endDate;
    }
}
