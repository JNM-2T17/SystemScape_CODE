package view.supplier;
import javax.swing.BorderFactory;
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
import view.ErrorListenerFactory;
import view.Button.ButtonBuilder;


public class Contact extends JPanel {
	private JLabel value;
	private JLabel type;
	private JButton btn;
	
	public Contact(){
		setMaximumSize(new Dimension(400, 37));
		setPreferredSize(new Dimension(365, 37));
		this.setBackground(Color.WHITE);
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		
		
		value=new JLabel();
		value.setPreferredSize(new Dimension(175, 25));
		
		type=new JLabel();
		type.setBorder(new EmptyBorder(0, 0, 0, 0));
		type.setBackground(Color.white);
		type.setPreferredSize(new Dimension(120, 25));
		
		btn= new Button.ButtonBuilder().img("/assets/Round/Add.png", 30,
				30).build();
		btn.setActionCommand("add");
		
		this.add(value);
		this.add(type);
		this.add(btn);
	}
	
	public void setButton(JButton btn){
		this.remove(this.btn);
		this.btn=btn;
		this.add(btn);
		this.repaint();
	}
	

	public void setValue(String value) {
		this.value.setText(value);;
	}

	public void setType(String type) {
		this.type.setText(type);
	}

	public String getValue() {
		return value.getText();
	}

	public String getType() {
		return type.getText();
	}

	public JButton getBtn() {
		return btn;
	}
	
}
