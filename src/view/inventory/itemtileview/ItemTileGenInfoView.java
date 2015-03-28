package view.inventory.itemtileview;

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

import view.ErrorListenerFactory;
import view.Message;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;

/**
 * @author dovahkiin5
 *
 */
public class ItemTileGenInfoView extends ItemPanelDecorator implements ItemPanelParticipant {

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
	
	private String currentID;

	public ItemTileGenInfoView(JFrame parent,
			ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		this.parent = parent;
		currentID = "";
		// TODO Auto-generated constructor stub
		panGeneral = new JPanel();
		panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panGeneral.setBackground(Color.WHITE);
		/* Layout */

		panGeneral.setLayout(new MigLayout("", "[][][grow]",
				"[][98.00,grow][37.00][22.00,grow][grow][]"));

		/* Labels */

		lblName = new JLabel("Name:");
		panGeneral.add(lblName, "cell 0 0,alignx right");

		tfName = new JTextField();
		tfName.addFocusListener(ErrorListenerFactory.getListener(tfName));
		panGeneral.add(tfName, "cell 2 0,growx");
		tfName.setColumns(10);

		/* Name Field */

		/* Description Field */
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		lblDescription = new JLabel("Description:");
		panGeneral.add(lblDescription, "cell 0 1,alignx right");

		scrollPane = new JScrollPane();
		panGeneral.add(scrollPane, "cell 2 1,grow");

		taDescription = new JTextArea();
		taDescription.addFocusListener(ErrorListenerFactory.getListener(taDescription));
		scrollPane.setViewportView(taDescription);
		taDescription.setBorder(new LineBorder(Color.LIGHT_GRAY));

		lblUnitPrice = new JLabel("Unit Price:");
		panGeneral.add(lblUnitPrice, "cell 0 2,alignx right");

		tfUnitPrice = new JTextField();
		tfUnitPrice.addFocusListener(ErrorListenerFactory.getListener(tfUnitPrice));
		tfUnitPrice.setColumns(10);
		panGeneral.add(tfUnitPrice, "cell 2 2,growx");

		lblInvoiceNumber = new JLabel("Invoice Number:");
		panGeneral.add(lblInvoiceNumber, "cell 0 3");

		tfInvoiceNumber = new JTextField();
		tfInvoiceNumber.addFocusListener(ErrorListenerFactory.getListener(tfInvoiceNumber));
		tfInvoiceNumber.setColumns(10);
		panGeneral.add(tfInvoiceNumber, "cell 2 3,growx");

		lblLocation = new JLabel("Location:");
		panGeneral.add(lblLocation, "cell 0 4,alignx right");

		cbLocation = new JComboBox();
		cbLocation.setBackground(Color.white);
		cbLocation.setModel(new DefaultComboBoxModel(new String[] { "1WS",
				"Somewhere Else" }));
		panGeneral.add(cbLocation, "cell 2 4,growx");

		lblStatus = new JLabel("Status:");
		panGeneral.add(lblStatus, "cell 0 5,alignx right");

		cbStatus = new JComboBox();
		cbStatus.setBackground(Color.white);
		cbStatus.setModel(new DefaultComboBoxModel(new String[] { "Assigned",
				"Unassigned" }));
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
		
		System.out.println("PASS" + currentID);
		
		ArrayList infoList = new ArrayList();
		infoList.add(tfName.getText());
		infoList.add(taDescription.getText());
		infoList.add(tfUnitPrice.getText());
		infoList.add(tfInvoiceNumber.getText());
		infoList.add(cbLocation.getSelectedItem().toString());
		infoList.add(cbStatus.getSelectedItem().toString());
		infoList.add(currentID);
		return infoList.iterator();
	}

	@Override
	public String checkInput() {
		String stat = "";
		if (tfName.getText().equals("")) {
			stat += "Please specify item name.\n";
			tfName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		if (taDescription.getText().equals("")) {
			stat += "Please specify item description.\n";
			taDescription.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		if (tfUnitPrice.getText().equals("")) {
			stat += "Please specify item unit price.\n";
			tfUnitPrice.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		if (tfInvoiceNumber.getText().equals("")) {
			stat += "Please specify item invoice number.\n";
			tfInvoiceNumber.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}

		try {
			float f = Float.parseFloat(tfUnitPrice.getText());
		} catch (Exception e) {
			stat += "Invalid unit price.\n";
			tfUnitPrice.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}

		return stat;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		
		currentID = iter.next().toString();
		tfName.setText(iter.next().toString());
		taDescription.setText(iter.next().toString());
		tfUnitPrice.setText(iter.next().toString());
		tfInvoiceNumber.setText(iter.next().toString());
		cbLocation.setSelectedItem(iter.next().toString());
		cbStatus.setSelectedItem(iter.next().toString());
	}
	
	/**
	 * Sets the content to be loaded into the model of the location combo box
	 * @param iter
	 */
	public void loadLocationList(Iterator iter) {
		 ArrayList<String> locationList = new ArrayList();
	     while(iter.hasNext()){
	    	 locationList.add(iter.next().toString());
	     }
	     cbLocation.setModel(new DefaultComboBoxModel(locationList.toArray()));
	}
	
	/**
	 * Sets the content to be loaded into the model of the status combo box
	 * @param iter
	 */
	public void loadStatusList(Iterator iter) {
		 ArrayList<String> statusList = new ArrayList();
	     while(iter.hasNext()){
	    	 statusList.add(iter.next().toString());
	     }
	     cbStatus.setModel(new DefaultComboBoxModel(statusList.toArray()));
	}
}
