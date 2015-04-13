package view.employee;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import controller.SupplierController;
import model.Employee;
import model.Supplier;
import view.Observer;
import view.ViewSpecificTemplate;

public class ViewListEmployee extends ViewSpecificTemplate{
	
	private ViewSpecEmployee viewSpecific;
	private JFrame frame;
	private Employee emp;
	private TabEmployees tab;
	
	private Employee objNext;
	private Employee objPrev;
	private Iterator it;
	public ViewListEmployee(TabEmployees tab, JFrame frame, Iterator it, Employee emp)
	{
		super();
		
		this.it=it;
		objNext=objPrev=null;
		this.tab=tab;
		this.emp=emp;
		
		viewSpecific = new ViewSpecEmployee();
		viewSpecific.setEmployee(emp);
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
		else if(e.getSource()==getEditButton()) tab.setEdit(emp);
		else if(e.getSource()==getPrevButton()) tab.setView(objPrev);
		else if(e.getSource()==getNextButton()) tab.setView(objNext);
	}

	@Override
	public void setButtonObjects() {
		// TODO Auto-generated method stub
		Employee first = null;
		boolean found = false;
		while (it.hasNext() && !found) {
			Employee obj = (Employee) it.next();
			if (objPrev == null)
				first = obj;
			if (obj.getID()==emp.getID()) {
				found = true;
				if (objPrev == null) {
					if (it.hasNext()) {
						objNext = objPrev = (Employee) it.next();
						while (it.hasNext()) {
							objPrev = (Employee) it.next();
						}
					} else {
						objNext = objPrev = obj;
					}
				} else {
					if (it.hasNext()) {
						objNext = (Employee) it.next();
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
