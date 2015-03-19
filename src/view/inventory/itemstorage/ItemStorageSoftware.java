package view.inventory.itemstorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import model.Employee;
import controller.EmployeeController;

public class ItemStorageSoftware implements ItemStorage, TypeItemStorage{
	
	private static ItemStorageSoftware instance = null;
	
	private String assignee;
	private ArrayList<String> assigneeList;
	private Date deliveryDate;
	private String licenseKey;
	private ItemStorageSoftware()
	{
		assignee = "";
		deliveryDate = new Date();
		licenseKey = "";
	}
	
	
	public ItemStorageSoftware saveLicenseKey(String licenseKey)
	{
		this.licenseKey = licenseKey;
		return this;
	}
	public ItemStorageSoftware saveAssignee(String assignee)
	{
		this.assignee = assignee;
		return this;
	}
	
	public ItemStorageSoftware saveDeliveryDate(Date deliveryDate)
	{
		this.deliveryDate = deliveryDate;
		return this;
	}
	
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(assignee);
		compiledList.add(deliveryDate);
		compiledList.add(licenseKey);
		return compiledList.iterator();
	}
	
	public void resetStorage()
	{
		instance = new ItemStorageSoftware();
	}
	
	public static ItemStorageSoftware getInstance() {
		if (instance == null) {
			instance = new ItemStorageSoftware();
		}
		return instance;
	}

	@Override
	public Iterator retrieveAssigneeList() {
		// TODO Auto-generated method stub
		return assigneeList.iterator();
	}
}
