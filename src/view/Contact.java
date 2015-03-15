package view;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.FlowLayout;


public class Contact extends JPanel {
	private JTextField value;
	private JComboBox type;
	private JButton btn;
	
	public Contact(){
		this.setBackground(Color.white);
		
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		String opt[]={"FAX", "Telephone", "Cellphone"};
		
		value=new JTextField();
		value.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		value.setPreferredSize(new Dimension(200, 35));
		
		type=new JComboBox(opt);
		type.setBackground(Color.white);
		type.setFont(new Font("Tahoma", Font.PLAIN, 21));
		type.setPreferredSize(new Dimension(134, 35));
		
		btn= new Button.ButtonBuilder().img("src/assets/Round/Add.png", 30,
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
		this.type.setSelectedItem(type);
	}

	public String getValue() {
		return value.getText();
	}

	public String getType() {
		return (String) type.getSelectedItem();
	}

	public JButton getBtn() {
		return btn;
	}
	
}
