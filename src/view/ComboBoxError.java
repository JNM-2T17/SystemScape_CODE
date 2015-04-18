package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ComboBoxError implements FocusListener {
	private JComboBox cmb;
	public ComboBoxError(JComboBox cmb){
		this.cmb=cmb;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		cmb.setBorder(UIManager.getBorder("ComboBox.border"));
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

}
