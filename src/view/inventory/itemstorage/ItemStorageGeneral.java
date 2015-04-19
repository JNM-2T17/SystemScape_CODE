package view.inventory.itemstorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import model.Employee;
import controller.EmployeeController;

public class ItemStorageGeneral implements ItemStorage, TypeItemStorage{
	
	private static ItemStorageGeneral instance = null;
	
	private String assignee;
	private ArrayList<String> assigneeList;
	private Date deliveryDate;
        private Date startDate;
        private Date endDate;
        
	private ItemStorageGeneral()
	{
		assignee = "";
		deliveryDate =  new Date();
		assigneeList = new ArrayList<String>();
                startDate = new Date();
                endDate = new Date();
		saveAssigneeList();
	}
	
	private void saveAssigneeList()
	{
		Iterator<Employee> iter = EmployeeController.getInstance().getAll();
		while(iter.hasNext())
		{
			assigneeList.add(iter.next().getName());
		}
	}
	
	public void saveSet(String assignee, Date deliveryDate)
	{
		this.assignee = assignee;
		this.deliveryDate = deliveryDate;
	}
	
	public void saveAssignee(String assignee)
	{
		this.assignee = assignee;
	}
	
	public void saveDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}
	
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(assignee);
		compiledList.add(deliveryDate);
                
		return compiledList.iterator();
	}
	public ItemStorageGeneral saveStartDate(Date startDate)
        {
            this.startDate = startDate;
            return this;
        }
	 public ItemStorageGeneral saveEndDate(Date endDate)
        {
            this.endDate = endDate;
            return this;
        }
         
	public void resetStorage()
	{
		instance = new ItemStorageGeneral();
	}
	
	public static ItemStorageGeneral getInstance() {
		if (instance == null) {
			instance = new ItemStorageGeneral();
		}
		return instance;
	}

	@Override
	public Iterator retrieveAssigneeList() {
		// TODO Auto-generated method stub
		return assigneeList.iterator();
	}
}
