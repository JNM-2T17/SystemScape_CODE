package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.UIManager;

public class TextFieldError implements FocusListener {
	private JTextField fld;
	public TextFieldError(JTextField fld){
		this.fld=fld;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		fld.setBorder(UIManager.getBorder("TextField.border"));
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
