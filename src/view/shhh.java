package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class shhh extends JPanel{
	private JTextField textField;
	public shhh() {
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
	}

}
