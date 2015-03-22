package view.supplier;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import controller.SupplierController;
import model.Supplier;
import view.Observer;
import view.ViewSpecificTemplate;
import view.purchaseOrder.ViewSpecificPO;

public class ViewListSuppliers extends ViewSpecificTemplate{
	
	private ViewSpecificSupplier viewSpecific;
	private JFrame frame;
	private Supplier supp;
	private TabSupplier tab;
	
	private Supplier objNext;
	private Supplier objPrev;
	private Iterator it;
	public ViewListSuppliers(TabSupplier tab, JFrame frame, Iterator it, Supplier supp)
	{
		super();
		
		this.it=it;
		objNext=objPrev=null;
		this.tab=tab;
		this.supp=supp;
		
		viewSpecific = new ViewSpecificSupplier();
		viewSpecific.setSupplier(supp);
		setContentPanel(viewSpecific);
		
		this.getCloseButton().addActionListener(this);
		this.getEditButton().addActionListener(this);
		this.getPrevButton().addActionListener(this);
		this.getNextButton().addActionListener(this);
		
		setButtonObjects();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getCloseButton()) tab.setReturn();
		else if(e.getSource()==getEditButton()) tab.setEdit(supp);
		else if(e.getSource()==getPrevButton()) tab.setView(objPrev);
		else if(e.getSource()==getNextButton()) tab.setView(objNext);
	}

	@Override
	public void setButtonObjects() {
		// TODO Auto-generated method stub
		Supplier first = null;
		boolean found = false;
		while (it.hasNext() && !found) {
			Supplier obj = (Supplier) it.next();
			if (objPrev == null)
				first = obj;
			if (obj.getName().equals(supp.getName())) {
				found = true;
				if (objPrev == null) {
					if (it.hasNext()) {
						objNext = objPrev = (Supplier) it.next();
						while (it.hasNext()) {
							objPrev = (Supplier) it.next();
						}
					} else {
						objNext = objPrev = obj;
					}
				} else {
					if (it.hasNext()) {
						objNext = (Supplier) it.next();
					} else {
						objNext = first;
					}
				}
			} else {
				objPrev = obj;
			}

			// i++;
		}
	}

}
