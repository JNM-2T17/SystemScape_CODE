package view.inventory.itemtileview;

import java.util.Date;
import java.util.Iterator;

public interface TypeItemTileView {
	/**
	 * Sets the content to be loaded into the model of the assignee combo box
	 * @param iter
	 */
public void loadAssignee(String assignee);
	/**
	 * Sets the type for the current inventoryItem template
	 * @param type
	 */
	public void setType(String type);
	/**
	 * Sets the delivery date in the JDateChooser
	 * @param date
	 */
}
