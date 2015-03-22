package view.purchaseOrder;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import model.PurchaseOrder;
import model.Supplier;
import view.Observer;
import view.ViewSpecificTemplate;

public class ViewListPO extends ViewSpecificTemplate {
	private TabPO tab;
	private PurchaseOrder po;
	private ViewSpecificPO viewSpecific;

	private PurchaseOrder objNext;
	private PurchaseOrder objPrev;
	private Iterator it;

	public ViewListPO(TabPO tab, JFrame parent, Iterator it, PurchaseOrder po) {
		super();

		objNext = objPrev = null;
		this.tab = tab;
		this.po = po;
		this.it = it;

		viewSpecific = new ViewSpecificPO(parent);
		viewSpecific.setPO(po);
		
		this.getCloseButton().addActionListener(this);
		this.getEditButton().addActionListener(this);
		this.getPrevButton().addActionListener(this);
		this.getNextButton().addActionListener(this);
		
		setContentPanel(viewSpecific);
		
		setButtonObjects();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == getCloseButton())
			tab.setReturn();
		else if (e.getSource() == getEditButton())
			tab.setEdit(po);
		else if (e.getSource() == getPrevButton())
			tab.setView(objPrev);
		else if (e.getSource() == getNextButton())
			tab.setView(objNext);
	}

	@Override
	public void setButtonObjects() {
		// TODO Auto-generated method stub
		PurchaseOrder first = null;
		boolean found = false;
		while (it.hasNext() && !found) {
			PurchaseOrder obj = (PurchaseOrder) it.next();
			if (objPrev == null)
				first = obj;
			if (obj.getIdNo() == po.getIdNo()) {
				found = true;
				if (objPrev == null) {
					if (it.hasNext()) {
						objNext = objPrev = (PurchaseOrder) it.next();
						while (it.hasNext()) {
							objPrev = (PurchaseOrder) it.next();
						}
					} else {
						objNext = objPrev = obj;
					}
				} else {
					if (it.hasNext()) {
						objNext = (PurchaseOrder) it.next();
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
