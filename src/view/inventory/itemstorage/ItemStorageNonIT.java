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

	private ItemStorageNonIT()
	{
		assignee = "";
		deliveryDate =  new Date();
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
