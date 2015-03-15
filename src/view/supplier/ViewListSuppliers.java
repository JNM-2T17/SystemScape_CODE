package view.supplier;

import view.Observer;
import view.ViewSpecificTemplate;
import view.purchaseOrder.ViewSpecificPO;

public class ViewListSuppliers extends ViewSpecificTemplate implements Observer{
	
	private ViewSpecificSupplier viewSpecific;
	public ViewListSuppliers()
	{
		super();
		viewSpecific = new ViewSpecificSupplier();
		setContentPanel(viewSpecific);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
