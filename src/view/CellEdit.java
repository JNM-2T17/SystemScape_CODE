package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class CellEdit extends JPanel implements ActionListener {
	private JButton edit;
	private JButton view;
	public CellEdit(){
		edit=new Button.ButtonBuilder().img("src/assets/Round/Note2.png", 30,
				30).build();
		view=new Button.ButtonBuilder().img("src/assets/Round/Preview.png", 30,
				30).build();
		
		edit.addActionListener(this);
		view.addActionListener(this);
		
		this.add(view);
		this.add(edit);
		
		this.repaint();
		this.revalidate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==edit){
			System.out.println("edit");
		}
		else if(e.getSource()==view){
			System.out.println("view");
		}
	}
	
}
