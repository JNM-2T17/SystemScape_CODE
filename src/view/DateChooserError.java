package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

public class DateChooserError implements FocusListener {
	private JDateChooser fld;
	public DateChooserError(JDateChooser fld){
		this.fld=fld;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		fld.setBorder((new JDateChooser()).getBorder());
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
