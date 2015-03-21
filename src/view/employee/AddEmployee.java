package view.employee;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import view.Button;
import view.Button.ButtonBuilder;
import view.supplier.Contact;

public class AddEmployee extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnSubmit;
	private JComboBox cmbStatus;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirm;

	public AddEmployee() {
		this.setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(null);

		JLabel lblSupp = new JLabel("Name:");
		lblSupp.setBounds(157, 90, 93, 27);
		panContent.add(lblSupp);

		txtName = new JTextField();
		txtName.setBounds(288, 90, 372, 25);
		panContent.add(txtName);
		txtName.setColumns(10);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(158, 130, 92, 26);
		panContent.add(lblStatus);

		cmbStatus = new JComboBox();
		cmbStatus.setBackground(Color.WHITE);
		cmbStatus.setBounds(288, 130, 372, 25);
		panContent.add(cmbStatus);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(157, 206, 78, 14);
		panContent.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(157, 242, 65, 14);
		panContent.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(157, 281, 133, 14);
		panContent.add(lblConfirmPassword);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(288, 202, 372, 25);
		panContent.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(288, 238, 372, 25);
		panContent.add(txtPassword);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setColumns(10);
		txtConfirm.setBounds(288, 275, 372, 25);
		panContent.add(txtConfirm);

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		add(panFooter, BorderLayout.SOUTH);

		btnSubmit = new JButton("Submit");
		panFooter.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSubmit) {

		}
	}
}
