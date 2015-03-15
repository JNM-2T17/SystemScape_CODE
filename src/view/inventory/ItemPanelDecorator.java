package view.inventory;

import java.awt.Component;

import javax.swing.JPanel;

public abstract class ItemPanelDecorator extends JPanel implements ItemPanelTemplate{

	protected ItemPanelTemplate addItemPanelReference;
	
	public ItemPanelDecorator(ItemPanelTemplate addItemPanelReference)
	{
		this.addItemPanelReference = addItemPanelReference;
	}
	
	@Override
	public void renderPanel() {
		// TODO Auto-generated method stub
		addItemPanelReference.renderPanel();
	}

	@Override
	public void assignToQuad(JPanel panel, int quadNo) {
		addItemPanelReference.assignToQuad(panel, quadNo);
	}
}
