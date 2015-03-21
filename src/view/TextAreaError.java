package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TextAreaError implements FocusListener {
	private JTextArea fld;
	public TextAreaError(JTextArea fld){
		this.fld=fld;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		fld.setBorder(UIManager.getBorder("TextArea.border"));
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
