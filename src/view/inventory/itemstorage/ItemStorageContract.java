package view.inventory.itemstorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class ItemStorageContract implements ItemStorage{
	
	private static ItemStorageContract instance = null;
	
	private float mainCost;
	private Date startDate;
	private Date endDate;
	
	private ItemStorageContract()
	{
		mainCost = 0;
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
		startDate = date;
		endDate = date;
	}
	
	
	public ItemStorageContract saveMainCost(float mainCost)
	{
		this.mainCost = mainCost;
		return this;
	}
	
	public ItemStorageContract saveStartDate(Date startDate)
	{
		this.startDate = startDate;
		return this;
	}
	
	public ItemStorageContract saveEndDate(Date endDate)
	{
		this.endDate = endDate;
		return this;
	}
	
	public Iterator loadList()
	{
		ArrayList compiledList = new ArrayList();
		compiledList.add(mainCost);
		compiledList.add(startDate);
		compiledList.add(endDate);
		
		return compiledList.iterator();
	}
	
	public static ItemStorageContract getInstance() {
		if (instance == null) {
			instance = new ItemStorageContract();
		}
		return instance;
	}


	@Override
	public void resetStorage() {
		// TODO Auto-generated method stub
		instance = new ItemStorageContract();
	}
}
