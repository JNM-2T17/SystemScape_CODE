package view.inventory.itemstorage;

import java.util.Iterator;

public interface ItemStorage {
	public void resetStorage();
	public Iterator loadList();
}
