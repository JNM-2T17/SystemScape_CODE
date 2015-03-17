package view.supplier;

import controller.SupplierController;

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
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.Supplier;
import model.SupplierContact;
import view.Button;
import view.Contact;
import view.Button.ButtonBuilder;
import view.Message;

public class EditSupplier extends JPanel implements ActionListener {

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

	public EditSupplier(JFrame parent, Supplier supp) {
		this.parent = parent;
		this.setBackground(Color.WHITE);

		list = new ArrayList<Contact>();
		close = new ArrayList<JButton>();
		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(null);

		JLabel lblSupp = new JLabel("Supplier: ");
		lblSupp.setBounds(157, 90, 93, 27);
		panContent.add(lblSupp);
		lblSupp.setFont(new Font("Tahoma", Font.PLAIN, 22));

		txtSupp = new JTextField(supp.getName());
		txtSupp.setBounds(289, 89, 372, 32);
		panContent.add(txtSupp);
		txtSupp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSupp.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(158, 138, 92, 26);
		panContent.add(lblAddress);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(182, 185, 92, 26);
		panContent.add(lblCountry);
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblState = new JLabel("State:");
		lblState.setBounds(182, 232, 92, 26);
		panContent.add(lblState);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(182, 279, 92, 26);
		panContent.add(lblCity);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 22));

		cmbCountry = new JTextField(supp.getCountry());
		cmbCountry.setBounds(289, 185, 372, 32);
		panContent.add(cmbCountry);
		cmbCountry.setBackground(Color.white);

		cmbState = new JTextField(supp.getState());
		cmbState.setBounds(289, 230, 372, 32);
		panContent.add(cmbState);
		cmbState.setBackground(Color.white);

		cmbCity = new JTextField(supp.getCity());
		cmbCity.setBounds(289, 277, 372, 32);
		panContent.add(cmbCity);
		cmbCity.setBackground(Color.white);

		JLabel lblContact = new JLabel("Contact #:");
		lblContact.setBounds(157, 334, 139, 26);
		panContent.add(lblContact);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 22));

		panContact = new JPanel();
		panContact.setBackground(Color.WHITE);
		panContact.setBounds(51, 478, 457, 200);
		panContact.setLayout(new MigLayout());

		panClose = new JPanel();
		panClose.setBackground(Color.WHITE);
		panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose);

		panClose.setLayout(new MigLayout("", "[]", "[]"));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 334, 500, 200);
		panContent.add(scrollPane);
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);
		panClose.setVisible(false);

		temp = new Contact();
		temp.getBtn().addActionListener(this);
		panContact.add(temp, "newline");

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		add(panFooter, BorderLayout.SOUTH);

		btnSubmit = new JButton("Submit");
		panFooter.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSubmit.addActionListener(this);

		supplierController = SupplierController.getInstance();
		// populate();
		System.out.println("STUPID");
		
		init(supp.getSupplierContactList());
	}
	
	private void init(Iterator it){
		while(it.hasNext()){
			SupplierContact sc=(SupplierContact) it.next();
			JButton close = new Button.ButtonBuilder().img(
					"src/assets/Round/Delete.png", 30, 30).build();
			close.addActionListener(this);
			this.close.add(close);

			Contact temp = new Contact();
			temp.setButton(close);
			temp.getBtn().setActionCommand("close");
			temp.setValue(sc.getValue()+"");
			temp.setType(sc.getType());

//			pan.setValue("");
//			pan.setType("FAX");

			panClose.add(temp, "newline");
			list.add(temp);
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
			
		}
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

			panClose.add(temp, "newline");
			list.add(temp);
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
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
			this.repaint();
			this.revalidate();
		}
	}
}
