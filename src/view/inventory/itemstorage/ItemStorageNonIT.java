package view.inventory.itemstorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import model.Employee;
import controller.EmployeeController;

public class ItemStorageNonIT implements ItemStorage{
	
	private static ItemStorageNonIT instance = null;
	
	private String assignee;
	private Date deliveryDate;
        private Date startDate;
        private Date endDate;
        
	private ItemStorageNonIT()
	{
		assignee = "";
		deliveryDate =  new Date();
                startDate = new Date();
                endDate = new Date();
                
	}
	
	public ItemStorageNonIT saveAssignee(String assignee)
	{
		this.assignee = assignee;
		return this;
	}
	
	public ItemStorageNonIT saveDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
		return this;
	}
	public ItemStorageNonIT saveStartDate(Date startDate)
        {
            this.startDate = startDate;
            return this;
        }
	 public ItemStorageNonIT saveEndDate(Date endDate)
        {
            this.endDate = endDate;
            return this;
        }
         
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(assignee);
		compiledList.add(deliveryDate);
		
		return compiledList.iterator();
	}
	
	public void resetStorage()
	{
		instance = new ItemStorageNonIT();
	}
	
	public static ItemStorageNonIT getInstance() {
		if (instance == null) {
			instance = new ItemStorageNonIT();
		}
		return instance;
	}
}
