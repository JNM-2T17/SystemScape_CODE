package view.inventory;

import javax.swing.JPanel;

public interface ItemPanelTemplate {
	public void renderPanel();
	public void assignToQuad(JPanel panel, int quadNo);
}
