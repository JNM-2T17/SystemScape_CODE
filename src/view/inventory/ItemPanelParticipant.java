package view.inventory;

import java.util.Iterator;
/**
 * A class can implement the <b> ItemPanelParticipant </b> when it wants to be 
 * included in the current group of displayed panels in adding an <b>InventoryItem</b>.
 * <br>
 * This class is managed by <b>ItemPanelRegistration</b> 
 * @author dovahkiin5
 *
 */
public interface ItemPanelParticipant {
	
	/**
	 * Retrieves the current information stored in the specific registered <b>ItemPanelParticipant</b>
	 * @return
	 */
	public Iterator retrieveInformation();
	/**
	 * Loads the information from the <b>ItemStorage</b> classes
	 * @param iter
	 */
	public void loadPresets(Iterator iter);
	/**
	 * Checks the input for the specific registered <b>ItemPanelParticipant</b>
	 * @return
	 */
	public String checkInput();
}
