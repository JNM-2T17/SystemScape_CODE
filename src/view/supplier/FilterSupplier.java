package view.supplier;

import controller.SupplierController;
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

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.PopUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import model.Supplier;

public class FilterSupplier extends PopUp implements ActionListener {
	private SpringLayout spring;
	private JComboBox cmbSupplier;
	private JTextField txtStreet, txtCountry, txtCity;
	private JTextField txtContact;
	private JButton btnFilter, btnRemoveFilter;
	private JComboBox cmbContact;
	private SupplierController supplierController;
        private ArrayList list;

	private boolean closed=true;

	public FilterSupplier(JFrame parent) {
		super(parent);
                list=new ArrayList();
		supplierController = SupplierController.getInstance();
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

		cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		spring.putConstraint(SpringLayout.NORTH, cmbSupplier, 0,
				SpringLayout.NORTH, lblSupplier);
		spring.putConstraint(SpringLayout.WEST, cmbSupplier, 42,
				SpringLayout.EAST, lblSupplier);
		spring.putConstraint(SpringLayout.EAST, cmbSupplier, -50,
				SpringLayout.EAST, panContent);
		panContent.add(cmbSupplier);

		JLabel lblAddress = new JLabel("Address:");
		spring.putConstraint(SpringLayout.NORTH, lblAddress, 20,
				SpringLayout.SOUTH, lblSupplier);
		spring.putConstraint(SpringLayout.WEST, lblAddress, 0,
				SpringLayout.WEST, lblSupplier);
		panContent.add(lblAddress);

		JLabel lblStreet = new JLabel("Street:");
		spring.putConstraint(SpringLayout.NORTH, lblStreet, 10,
				SpringLayout.SOUTH, lblAddress);
		spring.putConstraint(SpringLayout.WEST, lblStreet, 30,
				SpringLayout.WEST, lblAddress);
		panContent.add(lblStreet);

		JLabel lblCity = new JLabel("City: ");
		spring.putConstraint(SpringLayout.NORTH, lblCity, 10,
				SpringLayout.SOUTH, lblStreet);
		spring.putConstraint(SpringLayout.WEST, lblCity, 0, SpringLayout.WEST,
				lblStreet);
		panContent.add(lblCity);

		JLabel lblCountry = new JLabel("Country: ");
		spring.putConstraint(SpringLayout.NORTH, lblCountry, 10,
				SpringLayout.SOUTH, lblCity);
		spring.putConstraint(SpringLayout.WEST, lblCountry, 0, SpringLayout.WEST,
				lblStreet);
		panContent.add(lblCountry);

		JLabel lblContact = new JLabel("Contact #:");
		spring.putConstraint(SpringLayout.NORTH, lblContact, 21,
				SpringLayout.SOUTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, lblContact, 0,
				SpringLayout.WEST, lblSupplier);
		panContent.add(lblContact);

		txtCity = new JTextField();
		txtCity.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, txtCity, 0,
				SpringLayout.NORTH, lblCity);
		spring.putConstraint(SpringLayout.WEST, txtCity, 0, SpringLayout.WEST,
				cmbSupplier);
		spring.putConstraint(SpringLayout.EAST, txtCity, 0, SpringLayout.EAST,
				cmbSupplier);
		panContent.add(txtCity);

		txtStreet = new JTextField();
		txtStreet.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, txtStreet, 0,
				SpringLayout.NORTH, lblStreet);
		spring.putConstraint(SpringLayout.WEST, txtStreet, 0,
				SpringLayout.WEST, cmbSupplier);
		spring.putConstraint(SpringLayout.EAST, txtStreet, 0,
				SpringLayout.EAST, cmbSupplier);
		panContent.add(txtStreet);

		txtCountry = new JTextField();
		txtCountry.setBackground(Color.WHITE);
		spring.putConstraint(SpringLayout.NORTH, txtCountry, 0,
				SpringLayout.NORTH, lblCountry);
		spring.putConstraint(SpringLayout.WEST, txtCountry, 0, SpringLayout.WEST,
				cmbSupplier);
		spring.putConstraint(SpringLayout.EAST, txtCountry, 0, SpringLayout.EAST,
				cmbSupplier);
		panContent.add(txtCountry);

		txtContact = new JTextField();
		spring.putConstraint(SpringLayout.NORTH, txtContact, -1,
				SpringLayout.NORTH, lblContact);
		spring.putConstraint(SpringLayout.WEST, txtContact, 0,
				SpringLayout.WEST, cmbSupplier);
		spring.putConstraint(SpringLayout.EAST, txtContact, -169,
				SpringLayout.EAST, cmbSupplier);
		panContent.add(txtContact);
		txtContact.setColumns(10);

		String opt[] = {"","FAX", "Telephone", "Cellphone"};
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
		
		btnRemoveFilter = new JButton("Remove Filter");
		btnRemoveFilter.setForeground(new Color(255, 255, 255));
		btnRemoveFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRemoveFilter.setBackground(new Color(32, 130, 213));
		btnRemoveFilter.addActionListener(this);
		panFooter.add(btnRemoveFilter);

		getClose().addActionListener(this);
		btnFilter.addActionListener(this);
		populateSupplierNames();
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}

	public boolean isClosed(){
		return closed;
	}

	public Iterator getValues(){
            if(list!=null){
		list.add((String)cmbSupplier.getSelectedItem());
		list.add(txtStreet.getText());
		list.add(txtCity.getText());
		list.add(txtCountry.getText());
		list.add(txtContact.getText());
		list.add((String)cmbContact.getSelectedItem());
                return list.iterator();
            }
            return null;
	}

	public boolean checkFields()
	{
		boolean isEmpty = false;
		
		if(txtStreet.getText().equals("") && txtCountry.getText().equals("") && 
		   txtContact.getText().equals("") && txtCity.getText().equals("") && 
		   cmbContact.getSelectedIndex() == 0 && cmbSupplier.getSelectedIndex() == 0)
		{
			//System.out.println("EMPTY FILEDS:");
			isEmpty = true;
		}
		
		return isEmpty;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getClose()) {
			this.dispose();
		} 
		/****
		 * DEV Insert Code statements here to filter the list of suppliers 
		 * if at least one of the fields is not empty
		 *****/
		else if (checkFields() == false && e.getSource() == btnFilter) {
			closed = false;
			this.dispose();
		}
		else if (checkFields() == true && e.getSource() == btnFilter ){
			/****
			 * DEV Insert Code statements here to set the 
			 * list to the original if not one of the fields is filled up/
			 * All fields are empty
			 *****/
                        list = null;
                        closed = false;
                        this.dispose();
		}
		else if (e.getSource() == btnRemoveFilter){
			/***
			 * DEV insert code statements here to remove the filter and set the view table to the original
			 * meaning yung walang filter...
			*****/
                        list = null;
                        closed = false;
			this.dispose();
		}
	}

	public void populateSupplierNames() {
		Iterator<Supplier> iterator = supplierController.getAll();
		ArrayList<String> supplierNames = new ArrayList();
		supplierNames.add("");
		while (iterator.hasNext()) {
			supplierNames.add(iterator.next().getName());
		}
		cmbSupplier.setModel(new DefaultComboBoxModel(supplierNames.toArray()));
	}
}
