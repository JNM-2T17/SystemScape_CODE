package view.inventory.itemstorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ItemStorageWarranty implements ItemStorage{
	
	private static ItemStorageWarranty instance = null;
	
	private Date startDate;
	private Date endDate;
	
	private ItemStorageWarranty()
	{
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
		startDate = date;
		endDate =  date;
	}
	
	public ItemStorageWarranty saveStartDate(Date startDate)
	{
		this.startDate = startDate;
		return this;
	}
	
	public ItemStorageWarranty saveEndDate(Date endDate)
	{
		this.endDate = endDate;
		return this;
	}
	
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(startDate);
		compiledList.add(endDate);
		
		return compiledList.iterator();
	}
	
	public static ItemStorageWarranty getInstance() {
		if (instance == null) {
			instance = new ItemStorageWarranty();
		}
		return instance;
	}


	@Override
	public void resetStorage() {
		// TODO Auto-generated method stub
		instance = new ItemStorageWarranty();
	}
}
