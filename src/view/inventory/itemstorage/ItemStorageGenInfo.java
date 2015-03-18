package view.inventory.itemstorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ItemStorageGenInfo implements ItemStorage{
	
	private static ItemStorageGenInfo instance = null;
	
	private String name;
	private String description;
	private float unitPrice;
	private String invoiceNumber;
	private String location;
	private String status;
	
	private ItemStorageGenInfo()
	{
		name = "";
		description = "";
		unitPrice = 0;
		invoiceNumber = "";
		location = "";
		status = "";
	}
	
	public ItemStorageGenInfo(String name, String description, float unitPrice, String invoiceNumber, String location, String status)
	{
		this.name = name;
		this.description = description;
		this.unitPrice = unitPrice;
		this.invoiceNumber = invoiceNumber;
		this.location = location;
		this.status = status;
	}
	
	
	public ItemStorageGenInfo saveName(String name)
	{
		this.name = name;
		return this;
	}
	public ItemStorageGenInfo saveDescription(String description)
	{
		this.description = description;
		return this;
	}
	public ItemStorageGenInfo saveUnitPrice(float unitPrice)
	{
		this.unitPrice = unitPrice;
		return this;
	}
	public ItemStorageGenInfo saveInvoiceNumber(String invoiceNumber)
	{
		this.invoiceNumber = invoiceNumber;
		return this;
	}
	public ItemStorageGenInfo saveLocation(String location)
	{
		this.location = location;
		return this;
	}
	public ItemStorageGenInfo saveStatus(String status)
	{
		this.status = status;
		return this;
	}
	
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(name);
		compiledList.add(description);
		compiledList.add(unitPrice);
		compiledList.add(invoiceNumber);
		compiledList.add(location);
		compiledList.add(status);
		
		return compiledList.iterator();
	}
	
	public void resetStorage()
	{
		instance = new ItemStorageGenInfo();
	}
	
	public static ItemStorageGenInfo getInstance() {
		if (instance == null) {
			instance = new ItemStorageGenInfo();
		}
		return instance;
	}
}
