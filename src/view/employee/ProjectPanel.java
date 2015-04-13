package view.employee;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;

import view.Button;
import view.Button.ButtonBuilder;


public class ProjectPanel extends JPanel {
	private JLabel value;
	private JButton btn;
	
	public ProjectPanel(){
		setMaximumSize(new Dimension(400, 37));
		setPreferredSize(new Dimension(365, 37));
		this.setBackground(Color.WHITE);
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		value=new JLabel();
		value.setBorder(new EmptyBorder(0, 0, 0, 0));
		value.setBackground(Color.white);
		value.setPreferredSize(new Dimension(200, 25));
		
		btn= new Button.ButtonBuilder().img("src/assets/Round/Add.png", 30,
				30).build();
		btn.setActionCommand("add");
		
		this.add(value);
		this.add(btn);
	}
	
	public void setButton(JButton btn){
		if(btn!=null){
			this.remove(this.btn);
			this.btn=btn;
			this.add(btn);
		}
		else{
			this.btn.setVisible(false);
		}
		this.repaint();
	}

	public void setValue(String value) {
		this.value.setText(value);
	}

	public String getValue() {
		return (String) value.getText();
	}

	public JButton getBtn() {
		return btn;
	}

	public void clear() {
		value.setText("");
	}
	
}
