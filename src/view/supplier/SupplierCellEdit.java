package view.supplier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Supplier;
import view.Button;
import view.CellEdit;


public class SupplierCellEdit extends CellEdit implements ActionListener {
	private Supplier supp;
	private TabSupplier tab;
	public SupplierCellEdit(Supplier supp, TabSupplier tab){
		super();
		this.tab=tab;
		this.supp=supp;
		
		if(tab==null) System.out.println("TAB NUUUUL");
		else System.out.println("WUUUUUUUUUUUUUT");
		
		if(supp==null) System.out.println("NUOOOO");
		else{
			System.out.println("YEEEEEY");
		}
	}
	
	public Object get(){
		return supp;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getBtnEdit()){
			if(tab==null) System.out.println("ACT TAB NUUUUL");
			tab.setEdit(supp);
		}
		else if(e.getSource()==getBtnView()){
			if(tab==null) System.out.println("ACT TAB NUUUUL");
			tab.setView(supp);
		}
		
	}
	
}
