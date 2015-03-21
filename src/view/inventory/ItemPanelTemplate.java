package view.inventory;

import javax.swing.JPanel;

/**
 * A class can implement this if it wants to appear as an <b>Item Tile</b> which is included among the <b>ItemPanelParticipant</b> classes
 * @author dovahkiin5
 *
 */
public interface ItemPanelTemplate {
	/**
	 * Renders the current panel to the screen
	 */
	public void renderPanel();
	
	/**
	 * Assigns the current panel to a specific location in the quadrant of the <b>BasicAddItem</b> panel
	 * @param panel The container of the <b>ItemPanelParticipants</b>
	 * @param quadNo A panel can be assigned to either quadrants 1,2,3,4
	 * 
	 */
	public void assignToQuad(JPanel panel, int quadNo);
}
