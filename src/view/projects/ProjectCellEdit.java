package view.projects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Project;
import model.Supplier;
import view.Button;
import view.CellEdit;


public class ProjectCellEdit extends CellEdit implements ActionListener {
	private Project proj;
	private TabProject tab;
	public ProjectCellEdit(Project proj, TabProject tab){
		super();
		this.tab=tab;
		this.proj=proj;
	}
	
	public Object get(){
		return proj;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getBtnEdit()){
			if(tab==null) //System.out.println("ACT TAB NUUUUL");
			tab.setEdit(proj);
		}
		else if(e.getSource()==getBtnView()){
			if(tab==null) //System.out.println("ACT TAB NUUUUL");
			tab.setView(proj);
		}
		
	}
	
}
