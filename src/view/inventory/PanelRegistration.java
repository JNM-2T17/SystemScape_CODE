package view.inventory;


/**
 * This interface manages the <b>ItemPanelParticipants</b> including their functionalities.
 * This is used to communicate between the <b>TabInventory</b> and the <b>ItemPanelParticipants</b>
 * . It is used for both adding and editing an <b>InventoryItem</b>.
 * <br><br>
 * Acts like an <b>Observable/Subject</b>
 *
 * @author dovahkiin5
 *
 */
public interface PanelRegistration {
	/**
	 * Add the <b>ItemPanelParticipant</b> among the panels currently being viewed in the screen
	 * @param itemPanelParticipant
	 */
	public void registerParticipant(ItemPanelParticipant itemPanelParticipant);
	/**
	 * Remove the <b>ItemPanelParticipant</b> among the panels currently being viewed in the screen
	 * @param itemPanelParticipant
	 */
	public void unregisterParticipant(ItemPanelParticipant itemPanelParticipant);
	/**
	 * Compiles all the information from all the registered <b>ItemPanelParticipant</b>s and then passes it to the 
	 * <b>buildInventoryItem</b> method to be processed
	 */
	public void retrieveInformationFromAll();
}
