package view.inventory.itemstorage;

import java.util.Iterator;

/**
 * A class implements this if it is a <b>ItemStorage</b> of a <b>TypeItemTile</b>
 * @author dovahkiin5
 *
 */
public interface TypeItemStorage {
	/**
	 * Retrieves the list of possible assignees for the <b>InventoryItem</b> to be
	 *  loaded into the assignee combo box found in <b>TypeItemTile</b>s
	 * @return
	 */
	public Iterator retrieveAssigneeList();
}
