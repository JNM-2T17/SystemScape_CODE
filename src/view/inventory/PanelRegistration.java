package view.inventory;

import view.Observer;

public interface PanelRegistration {
	public void registerParticipant(ItemPanelParticipant itemPanelParticipant);
	public void unregisterParticipant(ItemPanelParticipant itemPanelParticipant);
	public void retrieveInformationFromAll();
}
