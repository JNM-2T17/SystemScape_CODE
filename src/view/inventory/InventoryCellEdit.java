package view.inventory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.InventoryItem;
import model.Supplier;
import view.Button;
import view.CellEdit;


public class InventoryCellEdit extends CellEdit implements ActionListener {
	private InventoryItem ii;
	private TabInventory tab;
	public InventoryCellEdit(InventoryItem ii, TabInventory tab){
		super();
		this.tab=tab;
		this.ii=ii;
	}
	
	public Object get(){
		return ii;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getBtnEdit()){
			if(tab==null) //System.out.println("ACT TAB NUUUUL");
			//System.out.println("EDIT-TO");
			tab.setEdit(ii);
		}
		else if(e.getSource()==getBtnView()){
			if(tab==null) //System.out.println("ACT TAB NUUUUL");
			//System.out.println("Pasok PLEASE VIEW-TO");
			tab.setView(ii);
		}
	}
	
}
