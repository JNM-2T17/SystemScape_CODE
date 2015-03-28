package view;

import java.awt.event.FocusListener;

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
		return new TextFieldError((JTextField) obj);
	}
}
