package view.inventory;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import view.Message;

public class ItemTileGenInfo extends ItemPanelDecorator implements
		ItemPanelParticipant {

	private JPanel panGeneral;
	private JLabel lblName;
	private JLabel lblDescription;

	private JTextField tfName;
	private JTextArea taDescription;
	private JScrollPane scrollPane;
	private JLabel lblUnitPrice;
	private JTextField tfUnitPrice;
	private JLabel lblInvoiceNumber;
	private JTextField tfInvoiceNumber;
	private JLabel lblLocation;
	private JComboBox cbLocation;
	private JLabel lblStatus;
	private JComboBox cbStatus;

	private JFrame parent;

	public ItemTileGenInfo(JFrame parent,
			ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);

		this.parent = parent;
		// TODO Auto-generated constructor stub
		panGeneral = new JPanel();
		panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panGeneral.setBackground(Color.WHITE);
		/* Layout */

		panGeneral.setLayout(new MigLayout("", "[][][grow]",
				"[][98.00,grow][37.00][22.00,grow][grow][]"));

		/* Labels */

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblName, "cell 0 0,alignx right");

		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panGeneral.add(tfName, "cell 2 0,growx");
		tfName.setColumns(10);

		/* Name Field */

		/* Description Field */
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblDescription, "cell 0 1,alignx right");

		scrollPane = new JScrollPane();
		panGeneral.add(scrollPane, "cell 2 1,grow");

		taDescription = new JTextArea();
		scrollPane.setViewportView(taDescription);
		taDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		taDescription.setBorder(new LineBorder(Color.LIGHT_GRAY));

		lblUnitPrice = new JLabel("Unit Price:");
		lblUnitPrice.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblUnitPrice, "cell 0 2,alignx right");

		tfUnitPrice = new JTextField();
		tfUnitPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfUnitPrice.setColumns(10);
		panGeneral.add(tfUnitPrice, "cell 2 2,growx");

		lblInvoiceNumber = new JLabel("Invoice Number:");
		lblInvoiceNumber.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblInvoiceNumber, "cell 0 3");

		tfInvoiceNumber = new JTextField();
		tfInvoiceNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfInvoiceNumber.setColumns(10);
		panGeneral.add(tfInvoiceNumber, "cell 2 3,growx");

		lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblLocation, "cell 0 4,alignx right");

		cbLocation = new JComboBox();
		cbLocation.setBackground(Color.white);
		cbLocation.setModel(new DefaultComboBoxModel(new String[] { "1WS",
				"Somewhere Else" }));
		cbLocation.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(cbLocation, "cell 2 4,growx");

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(lblStatus, "cell 0 5,alignx right");

		cbStatus = new JComboBox();
		cbStatus.setBackground(Color.white);
		cbStatus.setModel(new DefaultComboBoxModel(new String[] { "Assigned",
				"Unassigned" }));
		cbStatus.setFont(new Font("Arial", Font.BOLD, 20));
		panGeneral.add(cbStatus, "cell 2 5,growx");
	}

	@Override
	public void renderPanel() {
		renderItemTileGeneralPanel();
		super.renderPanel();
	}

	public void renderItemTileGeneralPanel() {

		addItemPanelReference.assignToQuad(panGeneral, 2);

	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList();
		infoList.add(tfName.getText());
		infoList.add(taDescription.getText());
		infoList.add(tfUnitPrice.getText());
		infoList.add(tfInvoiceNumber.getText());
		infoList.add(cbLocation.getSelectedItem().toString());
		infoList.add(cbStatus.getSelectedItem().toString());
		return infoList.iterator();
	}

	@Override
	public boolean checkInput() {
		boolean stat = true;
		if (tfName.getText().equals("")) {
			new Message(parent, Message.ERROR, "Please specify item name.");
			stat = false;
		} else if (taDescription.getText().equals("")) {
			new Message(parent, Message.ERROR,
					"Please specify item description.");
			stat = false;
		} else if (tfUnitPrice.getText().equals("")) {
			new Message(parent, Message.ERROR,
					"Please specify item unit price.");
			stat = false;
		} else if (tfInvoiceNumber.getText().equals("")) {
			new Message(parent, Message.ERROR,
					"Please specify item invoice number.");
			stat = false;
		} else {
			try {
				float f = Float.parseFloat(tfUnitPrice.getText());
			} catch (Exception e) {
				new Message(parent, Message.ERROR,
						"Invalid unit price.");
				stat = false;
			}
		}
		return stat;
	}
}
