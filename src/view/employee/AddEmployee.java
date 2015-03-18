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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import view.Button;
import view.Contact;
import view.Button.ButtonBuilder;

public class AddEmployee extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnSubmit;
	private JComboBox cmbStatus;

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
		txtName.setBounds(251, 89, 372, 25);
		panContent.add(txtName);
		txtName.setColumns(10);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(158, 130, 92, 26);
		panContent.add(lblStatus);

		cmbStatus = new JComboBox();
		cmbStatus.setBackground(Color.WHITE);
		cmbStatus.setBounds(251, 130, 372, 25);
		panContent.add(cmbStatus);

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
