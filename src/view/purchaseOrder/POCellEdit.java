package view.purchaseOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.PurchaseOrder;
import view.Button;


public class POCellEdit extends JPanel implements ActionListener {
	private JButton edit;
	private JButton view;
	private JButton export;
	
	private PurchaseOrder po;
	private TabPO tab;
	
	private JFrame parent;
	
	public POCellEdit(JFrame parent, PurchaseOrder po, TabPO tab){
		this.parent=parent;
		edit=new Button.ButtonBuilder().img("src/assets/Round/Note2.png", 30,
				30).build();
		view=new Button.ButtonBuilder().img("src/assets/Round/Preview.png", 30,
				30).build();
		export=new Button.ButtonBuilder().img("src/assets/Round/Export.png", 30,
				30).build();
		
		edit.addActionListener(this);
		view.addActionListener(this);
		export.addActionListener(this);
		
		this.add(view);
		this.add(edit);
		this.add(export);
		
		this.po=po;
		this.tab=tab;
		
		this.repaint();
		this.revalidate();
	}
	public JButton getBtnEdit(){
		return edit;
	}
	public JButton getBtnView(){
		return view;
	}
	public JButton getBtnExport(){
		return export;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getBtnEdit()){
			tab.setEdit(po);
		}
		else if(e.getSource()==getBtnView()){
			tab.setView(po);
		}
		else if(e.getSource()==getBtnExport()){
			new ExportPO(parent);
		}
	}
}
