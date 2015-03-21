package view.supplier;

import controller.SupplierController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.Supplier;
import view.Button;
import view.Button.ButtonBuilder;
import view.Message;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class AddSupplier extends JPanel implements ActionListener {

	private JTextField txtSupp;
	// private JComboBox cmbCountry, cmbState, cmbCity;
	private JTextField cmbCountry, cmbState, cmbCity;
	private Contact temp;
	private JPanel panContact, panClose;
	private ArrayList<Contact> list;
	private ArrayList<JButton> close;
	private JButton btnSubmit;
	private SupplierController supplierController;
	private JFrame parent;

	public AddSupplier(JFrame parent) {
		this.parent = parent;
		this.setBackground(Color.WHITE);

		list = new ArrayList<Contact>();
		close = new ArrayList<JButton>();
		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBorder(new EmptyBorder(50, 50, 0, 0));
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(new MigLayout("", "[100px][15px][400px]", "[27px][26px][26px][28px][28px][200px]"));

		JLabel lblSupp = new JLabel("Supplier: ");
		panContent.add(lblSupp, "cell 0 0,alignx left,growy");

		txtSupp = new JTextField();
		panContent.add(txtSupp, "cell 2 0,grow");
		txtSupp.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		panContent.add(lblAddress, "cell 0 1,alignx left,growy");

		JLabel lblCountry = new JLabel("Country:");
		panContent.add(lblCountry, "cell 0 2,alignx right,growy");

		JLabel lblState = new JLabel("State:");
		panContent.add(lblState, "cell 0 3,alignx right,growy");

		JLabel lblCity = new JLabel("City:");
		panContent.add(lblCity, "cell 0 4,alignx right,growy");

		cmbCountry = new JTextField();
		panContent.add(cmbCountry, "cell 2 2,grow");
		cmbCountry.setBackground(Color.white);

		cmbState = new JTextField();
		panContent.add(cmbState, "cell 2 3,grow");
		cmbState.setBackground(Color.white);

		cmbCity = new JTextField();
		panContent.add(cmbCity, "cell 2 4,grow");
		cmbCity.setBackground(Color.white);

		JLabel lblContact = new JLabel("Contact #:");
		panContent.add(lblContact, "cell 0 5 3 1,alignx left,aligny top");

		panContact = new JPanel();
		panContact.setBackground(Color.WHITE);
		panContact.setBounds(51, 478, 457, 200);
		panContact.setLayout(new MigLayout());
		

		panClose = new JPanel();
	
		panClose.setBackground(Color.ORANGE);
//		panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose, "");
		panClose.setLayout(new BoxLayout(panClose, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panContent.add(scrollPane, "cell 2 5,grow");
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);
		panClose.setVisible(false);
		

		temp = new Contact();
		FlowLayout flowLayout = (FlowLayout) temp.getLayout();
		temp.getBtn().addActionListener(this);
		panContact.add(temp, "newline,alignx left,aligny top");

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

		supplierController = SupplierController.getInstance();
		// populate();
		System.out.println("STUPID");
		
		this.repaint();
		this.revalidate();
	}

	public boolean contactError(String val) {
		boolean stat = false;
		for (int i = 0; i < val.length(); i++) {
			if (!(val.charAt(i) >= '0' && val.charAt(i) <= '9')) {
				stat = true;
			}
		}

		return stat;

	}

	public void addContact(Contact pan) {

		if (pan.getValue().equals("")) {
			new Message(parent, Message.ERROR, "Please set contact value.");
		} else if (contactError(pan.getValue())) {
			new Message(parent, Message.ERROR,
					"Contact value can only be composed of numbers.");
		} else {
			JButton close = new Button.ButtonBuilder().img(
					"src/assets/Round/Delete.png", 30, 30).build();
			close.addActionListener(this);
			this.close.add(close);

			Contact temp = new Contact();
			temp.setButton(close);
			temp.getBtn().setActionCommand("close");

			System.out.println("PAN VALUE" + pan.getValue());
			System.out.println("PAN TYPE" + pan.getType());

			temp.setValue(pan.getValue());
			temp.setType(pan.getType());

			pan.setValue("");
			pan.setType("FAX");

			panClose.add(temp);
			list.add(temp);
			
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
			
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			this.repaint();
			this.revalidate();
		}

	}

	// public void populate() {
	// Iterator iterator = supplierController.getDistinct("country");
	// ArrayList<String> data = new ArrayList();
	//
	// while (iterator.hasNext()) {
	// data.add((String) iterator.next());
	// }
	// cmbCountry.setModel(new DefaultComboBoxModel(data.toArray()));
	// data.removeAll(data);
	//
	// iterator = supplierController.getDistinct("state");
	// while (iterator.hasNext()) {
	// data.add((String) iterator.next());
	// }
	// cmbState.setModel(new DefaultComboBoxModel(data.toArray()));
	// data.removeAll(data);
	//
	// iterator = supplierController.getDistinct("city");
	// while (iterator.hasNext()) {
	// data.add((String) iterator.next());
	// }
	//
	// cmbCity.setModel(new DefaultComboBoxModel(data.toArray()));
	//
	// }

	public void clear() {
		list.clear();
		close.clear();
		txtSupp.setText("");
		cmbCountry.setText("");
		cmbState.setText("");
		cmbCity.setText("");
		panClose.removeAll();

		Contact pan = (Contact) panContact.getComponent(1);
		pan.clear();
		this.repaint();
		this.revalidate();
	}

	public boolean checkInput() {
		boolean stat = true;

		if (txtSupp.getText().equals("")) {
			new Message(parent, Message.ERROR, "Please specify the supplier.");
			stat = false;
		} else if (cmbCountry.getText().equals("")
				|| cmbState.getText().equals("")
				|| cmbCity.getText().equals("")) {
			new Message(parent, Message.ERROR,
					"Please accomplish supplier location.");
			stat = false;
		}
		return stat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("add")) {
			addContact((Contact) panContact.getComponent(1));
		} else if (e.getSource() == btnSubmit) {
			if (checkInput()) {
				Supplier checkSupplier;
				Supplier supplier = new Supplier(txtSupp.getText(),
						(String) cmbCountry.getText(),
						(String) cmbState.getText(), (String) cmbCity.getText());
				// supplier.setSupplierContactList(contacts);

				checkSupplier = (Supplier) supplierController
						.getObject(supplier.getName());

				if (checkSupplier == null) {
					for (int i = 0; i < list.size(); i++) {
						System.out.println("RISSA: "+i);
						supplier.addSupplierContact(txtSupp.getText(), list
								.get(i).getType().toString(),
								Integer.parseInt(list.get(i).getValue()));
					}

					supplierController.addSupplier(supplier);

					Message msg = new Message(parent, Message.SUCCESS,
							"Supplier added successfully.");
				} else {
					Message msg = new Message(parent, Message.ERROR,
							"Supplier already exists!");
				}
				supplierController.init();
				clear();
			}

		} else {
			int index = close.indexOf(e.getSource());
			close.remove(index);
			panClose.remove(index);
			list.remove(index);
			System.out.println(list.size());
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			this.repaint();
			this.revalidate();
		}
	}
}
