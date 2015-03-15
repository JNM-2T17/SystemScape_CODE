package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

public class Extra extends JPanel{
	public Extra() {
		setBackground(Color.PINK);
		
		JLabel lblNewLabel = new JLabel("Hello Dude");
		add(lblNewLabel);
	}

}
