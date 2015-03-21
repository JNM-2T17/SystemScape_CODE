package view;

import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ErrorListenerFactory {
	public static FocusListener getListener(Object obj){
		if(obj instanceof JTextField){
			return new TextFieldError((JTextField) obj);
		}
		else if(obj instanceof JTextArea){
			return new TextAreaError((JTextArea) obj);
		}
		return null;
	}
}
