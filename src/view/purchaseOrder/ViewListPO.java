package view.purchaseOrder;

import view.Observer;
import view.ViewSpecificTemplate;

public class ViewListPO extends ViewSpecificTemplate implements Observer{
	
	private ViewSpecificPO viewSpecific;
	public ViewListPO()
	{
		super();
		viewSpecific = new ViewSpecificPO();
		setContentPanel(viewSpecific);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
