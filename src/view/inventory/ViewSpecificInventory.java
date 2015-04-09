package view.inventory;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import model.InventoryItem;
import view.ViewSpecificTemplate;

public class ViewSpecificInventory extends ViewSpecificTemplate implements ItemPanelTemplate {
	private TabInventory tab;
	private ViewInventoryItemLinkedList viewList;
	private BasicViewSpecificItem template; 
	
	public ViewSpecificInventory(TabInventory tab, ViewInventoryItemLinkedList viewList)
	{
		this.tab = tab;
		this.viewList = viewList;
		
		this.getCloseButton().addActionListener(this);
		this.getEditButton().addActionListener(this);
		this.getPrevButton().addActionListener(this);
		this.getNextButton().addActionListener(this);
		
		template = new BasicViewSpecificItem();
		add(template);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getCloseButton()) tab.setReturn();
		else if(e.getSource()==getEditButton()) setEdit();
		else if(e.getSource()==getPrevButton()) setViewPrevious();
		else if(e.getSource()==getNextButton()) setViewNext();
	}
	
	private void setEdit()	
	{
		tab.setEdit(viewList.pCurr());
	}
	
	private void setViewPrevious()
	{
		System.out.println("Previous");
		InventoryItem previousItem = viewList.pPrev();
		if(previousItem != null) tab.setView(previousItem);
	}
	private void setViewNext()
	{
		System.out.println("Next");
		InventoryItem nextItem = viewList.pNext();
		if(nextItem != null) tab.setView(nextItem);
	}

	@Override
	public void setButtonObjects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderPanel() {
		// TODO Auto-generated method stub
		template.renderPanel();
		revalidate();
		repaint();
		
	}

	@Override
	public void assignToQuad(JPanel panel, int quadNo) {
		// TODO Auto-generated method stub
		template.assignToQuad(panel, quadNo);
		
	}

}
