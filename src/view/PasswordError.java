package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PasswordError implements FocusListener {
	private JPasswordField fld;
	public PasswordError(JPasswordField fld){
		this.fld=fld;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		fld.setBorder(UIManager.getBorder("PasswordField.border"));
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
