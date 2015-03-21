package view.inventory.itemstorage;

import java.util.Iterator;

/**
 * A class implements this when it stores data for a certain <b>ItemPanelParticipant</b>
 * <br><br>
 * Similar to the concept of a builder pattern
 * @author dovahkiin5
 *
 */
public interface ItemStorage {
	/**
	 * Resets the contents of the specific <b>ItemStorage<b>
	 */
	public void resetStorage();
	/**
	 * Returns the compiled list of attributes stored in the <b>ItemStorage</b> class
	 * @return <b>Iterator</b>
	 */
	public Iterator loadList();
}
