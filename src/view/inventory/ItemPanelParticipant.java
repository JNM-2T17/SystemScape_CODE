package view.inventory;

import java.util.Iterator;

public interface ItemPanelParticipant {
	public Iterator retrieveInformation();
	public void loadPresets(Iterator iter);
	public boolean checkInput();
}
