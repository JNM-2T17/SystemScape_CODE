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
import java.util.Iterator;

public class FilterSupplier extends PopUp implements ActionListener {
	private SpringLayout spring;
	private JTextField cmbCountry, cmbCity, cmbState;
	private JTextField txtSupplier, txtContact;
	private JButton btnFilter;
        private JComboBox cmbContact;
	
	private boolean closed=true;

	public FilterSupplier(JFrame parent) {
		super(parent);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setSize(new Dimension(500, 350));
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		setContent(panel);
		
		JPanel panHeader = new JPanel();
		panHeader.setBackground(Color.WHITE);
		panel.add(panHeader, BorderLayout.NORTH);

		JLabel lblFilterSupp = new JLabel("Filter Suppliers");
		lblFilterSupp.setFont(new Font("Arial", Font.PLAIN, 16));
		panHeader.add(lblFilterSupp);

		JPanel panContent = new JPanel();
		panel.add(panContent, BorderLayout.CENTER);
		panContent.setBackground(Color.WHITE);
		spring = new SpringLayout();
		panContent.setLayout(spring);

		JLabel lblSupplier = new JLabel("Supplier: ");
		spring.putConstraint(SpringLayout.NORTH, lblSupplier, 50,
				SpringLayout.NORTH, panContent);
		spring.putConstraint(SpringLayout.WEST, lblSupplier, 50,
				SpringLayout.WEST, panContent);
		panContent.add(lblSupplier);

		txtSupplier = new JTextField();
		spring.putConstraint(SpringLayout.NORTH, txtSupplier, 0,
				SpringLayout.NORTH, lblSupplier);
		spring.putConstraint(SpringLayout.WEST, txtSupplier, 42,
				SpringLayout.EAST, lblSupplier);
		spring.putConstraint(SpringLayout.EAST, txtSupplier, -50,
				SpringLayout.EAST, panContent);
		panContent.add(txtSupplier);
		txtSupplier.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		spring.putConstraint(SpringLayout.NORTH, lblAddress, 20,
				SpringLayout.SOUTH, lblSupplier);
		spring.putConstraint(SpringLayout.WEST, lblAddress, 0,
				SpringLayout.WEST, lblSupplier);
		panContent.add(lblAddress);

		JLabel lblCountry = new JLabel("Country:");
		spring.putConstraint(SpringLayout.NORTH, lblCountry, 10,
				SpringLayout.SOUTH, lblAddress);
		spring.putConstraint(SpringLayout.WEST, lblCountry, 30,
				SpringLayout.WEST, lblAddress);
		panContent.add(lblCountry);

		JLabel lblState = new JLabel("State:");
		spring.putConstraint(SpringLayout.NORTH, lblState, 10,
				SpringLayout.SOUTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, lblState, 0, SpringLayout.WEST,
				lblCountry);
		panContent.add(lblState);

		JLabel lblCity = new JLabel("City:");
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
		panContent.add(lblContact);

		cmbState = new JTextField();
		cmbState.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbState, 0,
				SpringLayout.NORTH, lblState);
		spring.putConstraint(SpringLayout.WEST, cmbState, 0, SpringLayout.WEST,
				txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbState, 0, SpringLayout.EAST,
				txtSupplier);
		panContent.add(cmbState);

		cmbCountry = new JTextField();
		cmbCountry.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbCountry, 0,
				SpringLayout.NORTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, cmbCountry, 0,
				SpringLayout.WEST, txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbCountry, 0,
				SpringLayout.EAST, txtSupplier);
		panContent.add(cmbCountry);

		cmbCity = new JTextField();
		cmbCity.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, cmbCity, 0,
				SpringLayout.NORTH, lblCity);
		spring.putConstraint(SpringLayout.WEST, cmbCity, 0, SpringLayout.WEST,
				txtSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbCity, 0, SpringLayout.EAST,
				txtSupplier);
		panContent.add(cmbCity);

		txtContact = new JTextField();
		spring.putConstraint(SpringLayout.NORTH, txtContact, -1,
				SpringLayout.NORTH, lblContact);
		spring.putConstraint(SpringLayout.WEST, txtContact, 0,
				SpringLayout.WEST, txtSupplier);
		spring.putConstraint(SpringLayout.EAST, txtContact, -169,
				SpringLayout.EAST, txtSupplier);
		panContent.add(txtContact);
		txtContact.setColumns(10);
		
                String opt[]={"FAX", "Telephone", "Cellphone"};
		cmbContact = new JComboBox(opt);
		cmbContact.setForeground(Color.BLACK);
		spring.putConstraint(SpringLayout.WEST, cmbContact, 6, SpringLayout.EAST, txtContact);
		spring.putConstraint(SpringLayout.SOUTH, cmbContact, 0, SpringLayout.SOUTH, txtContact);
		spring.putConstraint(SpringLayout.EAST, cmbContact, 170, SpringLayout.EAST, txtContact);
		panContent.add(cmbContact);

		JPanel panFooter = new JPanel();
		panel.add(panFooter, BorderLayout.SOUTH);
		panFooter.setBackground(Color.WHITE);

		btnFilter = new JButton("Filter");
		btnFilter.setActionCommand("suppFilter");
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));

		getClose().addActionListener(this);
		btnFilter.addActionListener(this);

		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}
	
	public boolean isClosed(){
		return closed;
	}
	
	public Iterator getValues(){
		ArrayList list=new ArrayList();
		
		list.add(txtSupplier.getText());
		list.add(cmbCountry.getText());
		list.add(cmbState.getText());
		list.add(cmbCity.getText());
                list.add(txtContact.getText());
                list.add((String)cmbContact.getSelectedItem());
		
		return list.iterator();
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
