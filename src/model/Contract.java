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
public class Contract {
    private int hardware;
    private Date startDate;
    private Date endDate;
    private float maintenanceCost;
    
    public Contract(int hardware, Date startDate, Date endDate, float maintenanceCost) {
		setHardware(hardware);
		setStartDate(startDate);
		setEndDate(endDate);
		setMaintenanceCost(maintenanceCost);
	}
    
    public Contract() {
		setHardware(0);
		setStartDate(null);
		setEndDate(null);
		setMaintenanceCost(0);
	}
    
    public int getHardware(){
        return hardware;
    }
    
    public void setHardware(int hardware){
        this.hardware = hardware;
    }
    
    public Date getStartDate(){
        return startDate;
    }
    
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public float getMaintenanceCost(){
        return maintenanceCost;
    }
    
    public void setMaintenanceCost(float maintenanceCost){
        this.maintenanceCost = maintenanceCost;
    }
}
