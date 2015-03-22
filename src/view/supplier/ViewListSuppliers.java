package view.supplier;

import javax.swing.JFrame;

import model.Supplier;
import view.Observer;
import view.ViewSpecificTemplate;

public class ViewListSuppliers extends ViewSpecificTemplate implements Observer{
	
	private ViewSpecificSupplier viewSpecific;
	private JFrame frame;
	private Supplier supp;
	public ViewListSuppliers(JFrame frame, Supplier supp)
	{
		super();
		
		viewSpecific = new ViewSpecificSupplier(frame, supp);
		setContentPanel(viewSpecific);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
