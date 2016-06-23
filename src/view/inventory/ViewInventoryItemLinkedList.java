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
	
	private void populateList()
	{
		currIndex = 0;
		
		Iterator<InventoryItem> iter = InventoryItemController.getInstance().getAll();
		while(iter.hasNext()){
			nodeMap.put(currIndex, iter.next());
			currIndex++;
		}
	}
	
	public InventoryItem pCurr()
	{
		return nodeMap.get(currIndex);
	}
	
	public InventoryItem pNext()
	{
		currIndex++;
		//System.out.println("Current Index: " + currIndex);
		if(nodeMap.get(currIndex) != null && currIndex < nodeMap.size())
		{
			return nodeMap.get(currIndex);
		}
                else if(currIndex > nodeMap.size()-1){
                    currIndex = 0;
                    return nodeMap.get(currIndex);
                }
		currIndex--;
		
		return null;
	}
	
	public InventoryItem pPrev()
	{
		currIndex--;
		//System.out.println("Current Index: " + currIndex);
		if(nodeMap.get(currIndex) != null && currIndex >= 0)
		{
			return nodeMap.get(currIndex);
		}
                else if(currIndex<0){
                    currIndex = nodeMap.size()-1;
                    return nodeMap.get(currIndex);
                }
		currIndex++;
		
		return null;
	}
	
	public void jumpToItem(InventoryItem ii)
	{
		for(int i = 0 ; i < nodeMap.size(); i++)
		{
			if(nodeMap.get(i).getID() == ii.getID())
			{
				currIndex = i;
				//System.out.println("EUREKA");
				break;
			}
		}
	}
	
	public int getCount()
	{
		return nodeMap.size();
	}

}
