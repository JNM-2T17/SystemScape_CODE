package view.inventory;

import java.awt.Component;

import javax.swing.JPanel;

import view.inventory.itemtilefield.ItemTileContractField;
import view.inventory.itemtilefield.ItemTileGenInfoField;
import view.inventory.itemtilefield.ItemTileITField;
import view.inventory.itemtilefield.ItemTileWarrantyField;

/**
 * A <b>Decorator</b> is created by first setting a <b>BasicAddItem</b> panel as the base case then instantiating <b>ItemPanelTemplate</b> as another parameter of another <b>ItemPanelTemplate</b>
 * ending with assigning the whole set into a <b>ItemPanelDecorator</b>
 * <br><br>
 * (e.g)  <b>ItemPanelDecorator</b> = new <b>ItemTileGenInfo</b>(new <b>ItemTileIT</b>(new <b>ItemTileWarranty</b>(new <b>ItemTileContract</b>(new <b>BasicAddItem</b>()))));
 * <br>
 * <br>
 * \*recursive in nature
 * @author dovahkiin5
 *
 */
@SuppressWarnings("serial")
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
