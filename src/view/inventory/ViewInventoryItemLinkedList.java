package view.inventory;

import java.util.HashMap;
import java.util.Iterator;

import controller.InventoryItemController;
import model.InventoryItem;

public class ViewInventoryItemLinkedList {
	
	private HashMap<Integer,InventoryItem> nodeMap;
	private int currIndex;
	
	public ViewInventoryItemLinkedList()
	{
		nodeMap = new HashMap<Integer,InventoryItem>();
		populateList();
	}
	
	public void populateList()
	{
		currIndex = 0;
		
		Iterator<InventoryItem> iter = InventoryItemController.getInstance().getAll();
		while(iter.hasNext()){
			nodeMap.put(currIndex, iter.next());
			currIndex++;
		}
	}
	
	public InventoryItem pNext()
	{
		currIndex++;
		if(nodeMap.get(currIndex) != null)
		{
			return nodeMap.get(currIndex);
		}
		
		return null;
	}
	
	public InventoryItem pPrev()
	{
		currIndex--;
		if(nodeMap.get(currIndex) != null || currIndex >= 0)
		{
			return nodeMap.get(currIndex);
		}
		
		return null;
	}
	
	public InventoryItem jumpToItem(InventoryItem ii)
	{
		for(int i = 0 ; i < nodeMap.size(); i++)
		{
			if(nodeMap.get(i) == ii)
			{
				currIndex = i;
				return ii;
			}
		}
		return null;
	}

}
