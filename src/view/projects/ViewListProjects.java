package view.projects;

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import controller.SupplierController;
import model.Project;
import model.Supplier;
import view.Observer;
import view.ViewSpecificTemplate;
import view.purchaseOrder.ViewSpecificPO;

public class ViewListProjects extends ViewSpecificTemplate{
	
	private ViewSpecificProject viewSpecific;
	private JFrame frame;
	private Project proj;
	private TabProject tab;
	
	private Project objNext;
	private Project objPrev;
	private Iterator it;
	public ViewListProjects(TabProject tab, JFrame frame, Iterator it, Project proj)
	{
		super();
		
		this.it=it;
		objNext=objPrev=null;
		this.tab=tab;
		this.proj=proj;
		
		viewSpecific = new ViewSpecificProject();
		viewSpecific.setProject(proj);
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
		else if(e.getSource()==getEditButton()) tab.setEdit(proj);
		else if(e.getSource()==getPrevButton()) tab.setView(objPrev);
		else if(e.getSource()==getNextButton()) tab.setView(objNext);
	}

	@Override
	public void setButtonObjects() {
		// TODO Auto-generated method stub
		Project first=null;
		boolean found=false;
		while(it.hasNext()&&!found){
			Project obj=(Project) it.next();
			if(objPrev==null) first=obj;
			if(obj.getName().equals(proj.getName())){
				found=true;
				if(objPrev==null){
					if(it.hasNext()){
						objNext=objPrev=(Project) it.next();
						while(it.hasNext()){
							objPrev=(Project) it.next();
						}
					}
					else{
						objNext=objPrev=obj;
					}
				}
				else{
					if(it.hasNext()){
						objNext=(Project) it.next();
					}
					else{
						objNext=first;
					}
				}
			}
			else{
				objPrev=obj;
			}
				
//			i++;
		}
	}

}
