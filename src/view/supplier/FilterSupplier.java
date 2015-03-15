package view.supplier;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import view.PopUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FilterSupplier extends PopUp implements ActionListener {
	private SpringLayout spring;
	private JComboBox cmbCountry, cmbCity, cmbState;
	private JTextField txtSupplier, txtContact;
	private JButton btnFilter;
	
	private boolean closed=true;

	public FilterSupplier(JFrame parent) {
		super(parent);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(750, 475));
		panel.setSize(new Dimension(750, 475));
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		setContent(panel);

		JPanel panContent = new JPanel();
		panel.add(panContent, BorderLayout.CENTER);
		panContent.setBackground(Color.WHITE);
		spring = new SpringLayout();
		panContent.setLayout(spring);

		JLabel lblSupplier = new JLabel("Supplier: ");
		lblSupplier.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, lblSupplier, 62,
				SpringLayout.NORTH, panContent);
		spring.putConstraint(SpringLayout.WEST, lblSupplier, 80,
				SpringLayout.WEST, panContent);
		panContent.add(lblSupplier);

		txtSupplier = new JTextField();
		txtSupplier.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, txtSupplier, 62,
				SpringLayout.NORTH, panContent);
		spring.putConstraint(SpringLayout.WEST, txtSupplier, 42,
				SpringLayout.EAST, lblSupplier);
		spring.putConstraint(SpringLayout.EAST, txtSupplier, 460,
				SpringLayout.EAST, lblSupplier);
		panContent.add(txtSupplier);
		txtSupplier.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, lblAddress, 20,
				SpringLayout.SOUTH, lblSupplier);
		spring.putConstraint(SpringLayout.WEST, lblAddress, 0,
				SpringLayout.WEST, lblSupplier);
		panContent.add(lblAddress);

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, lblCountry, 10,
				SpringLayout.SOUTH, lblAddress);
		spring.putConstraint(SpringLayout.WEST, lblCountry, 30,
				SpringLayout.WEST, lblAddress);
		panContent.add(lblCountry);

		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, lblState, 10,
				SpringLayout.SOUTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, lblState, 0, SpringLayout.WEST,
				lblCountry);
		panContent.add(lblState);

		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, lblCity, 10,
				SpringLayout.SOUTH, lblState);
		spring.putConstraint(SpringLayout.WEST, lblCity, 0, SpringLayout.WEST,
				lblCountry);
		panContent.add(lblCity);

		JLabel lblContact = new JLabel("Contact #:");
		spring.putConstraint(SpringLayout.NORTH, lblContact, 21,
				SpringLayout.SOUTH, lblCity);
		spring.putConstraint(SpringLayout.WEST, lblContact, 0,
				SpringLayout.WEST, lblSupplier);
		lblContact.setFont(new Font("Arial", Font.PLAIN, 24));
		panContent.add(lblContact);

		cmbState = new JComboBox();
		cmbState.setFont(new Font("Arial", Font.PLAIN, 24));
		cmbState.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbState, 0,
				SpringLayout.NORTH, lblState);
		spring.putConstraint(SpringLayout.WEST, cmbState, 0, SpringLayout.WEST,
				txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbState, 0, SpringLayout.EAST,
				txtSupplier);
		panContent.add(cmbState);

		cmbCountry = new JComboBox();
		cmbCountry.setFont(new Font("Arial", Font.PLAIN, 24));
		cmbCountry.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbCountry, 0,
				SpringLayout.NORTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, cmbCountry, 0,
				SpringLayout.WEST, txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbCountry, 0,
				SpringLayout.EAST, txtSupplier);
		panContent.add(cmbCountry);

		cmbCity = new JComboBox();
		cmbCity.setFont(new Font("Arial", Font.PLAIN, 24));
		cmbCity.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbCity, 0,
				SpringLayout.NORTH, lblCity);
		spring.putConstraint(SpringLayout.WEST, cmbCity, 0, SpringLayout.WEST,
				txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbCity, 0, SpringLayout.EAST,
				txtSupplier);
		panContent.add(cmbCity);

		txtContact = new JTextField();
		txtContact.setFont(new Font("Arial", Font.PLAIN, 24));
		spring.putConstraint(SpringLayout.NORTH, txtContact, -1,
				SpringLayout.NORTH, lblContact);
		spring.putConstraint(SpringLayout.WEST, txtContact, 0,
				SpringLayout.WEST, txtSupplier);
		spring.putConstraint(SpringLayout.EAST, txtContact, 0,
				SpringLayout.EAST, txtSupplier);
		panContent.add(txtContact);
		txtContact.setColumns(10);

		JPanel panFooter = new JPanel();
		panel.add(panFooter, BorderLayout.SOUTH);
		panFooter.setBackground(Color.WHITE);

		btnFilter = new JButton("Filter");
		btnFilter.setActionCommand("suppFilter");
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 32));

		getClose().addActionListener(this);
		btnFilter.addActionListener(this);

		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}
	
	public boolean isClosed(){
		return closed;
	}
	
	public ArrayList getValues(){
		ArrayList list=new ArrayList();
		
		list.add(txtSupplier.getText());
		list.add(cmbCountry.getSelectedItem());
		list.add(cmbState.getSelectedItem());
		list.add(cmbCity.getSelectedItem());
		
		return list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getClose()) {
			this.dispose();
		} else if (e.getSource() == btnFilter) {
			closed=false;
			this.dispose();
		}
	}
}
