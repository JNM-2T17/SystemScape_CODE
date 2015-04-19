package view.inventory.itemstorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import model.Employee;
import controller.EmployeeController;

public class ItemStorageIT implements ItemStorage{
	
	private static ItemStorageIT instance = null;
	
	private String assignee;
	private Date deliveryDate;
	private int assetTag;
	private String serviceTag;
        private Date startDate;
        private Date endDate;
        
	private ItemStorageIT()
	{
		assignee = "";
		Date date = Calendar.getInstance().getTime();
		deliveryDate = date;
		assetTag = 0;
		serviceTag = "";
                startDate = date;
                endDate = date;
                
	}
	
	public ItemStorageIT saveAssignee(String assignee)
	{
		this.assignee = assignee;
		return this;
	}
	
	public ItemStorageIT saveDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
		return this;
	}
	
	public ItemStorageIT saveAssetTag(int assetTag)
	{
		this.assetTag = assetTag;
		return this;
	}
	
	public ItemStorageIT saveServiceTag(String serviceTag)
	{
		this.serviceTag = serviceTag;
		return this;
	}
        public ItemStorageIT saveStartDate(Date startDate)
        {
            this.startDate = startDate;
            return this;
        }
	 public ItemStorageIT saveEndDate(Date endDate)
        {
            this.endDate = endDate;
            return this;
        }
         
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		//compiledList.add(assignee);*/
		compiledList.add(assetTag);
		compiledList.add(serviceTag);
		compiledList.add(deliveryDate);
                
		return compiledList.iterator();
	}
	
	public void resetStorage()
	{
		instance = new ItemStorageIT();
	}
	
	public static ItemStorageIT getInstance() {
		if (instance == null) {
			instance = new ItemStorageIT();
		}
		return instance;
	}
}
