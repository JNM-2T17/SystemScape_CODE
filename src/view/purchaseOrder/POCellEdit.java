package view.purchaseOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.PurchaseOrder;
import model.Supplier;
import view.Button;
import view.CellEdit;


public class POCellEdit extends CellEdit implements ActionListener {
	private PurchaseOrder po;
	private TabPO tab;
	public POCellEdit(PurchaseOrder po, TabPO tab){
		super();
		this.tab=tab;
		this.po=po;
	}
	
	public Object get(){
		return po;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getBtnEdit()){
			tab.setEdit(po);
		}
		else if(e.getSource()==getBtnView()){
			System.out.println("view");
		}
	}
	
}
