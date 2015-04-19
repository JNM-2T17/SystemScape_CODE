package view;

import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ErrorListenerFactory {
	public static FocusListener getListener(Object obj){
		if(obj instanceof JTextField){
			return new TextFieldError((JTextField) obj);
		}
		else if(obj instanceof JTextArea){
			return new TextAreaError((JTextArea) obj);
		}
		else if(obj instanceof JDateChooser){
			return new DateChooserError((JDateChooser) obj);
		}
		else if(obj instanceof JPasswordField){
			return new PasswordError((JPasswordField) obj);
		}
		else if(obj instanceof JComboBox){
			return new ComboBoxError((JComboBox) obj);
		}
		return new TextFieldError((JTextField) obj);
	}
}
