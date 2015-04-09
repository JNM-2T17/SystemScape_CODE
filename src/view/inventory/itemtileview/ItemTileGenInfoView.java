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
	private JScrollPane scrollPane;
	private JLabel lblUnitPrice;
	private JLabel lblInvoiceNumber;
	private JLabel lblLocation;
	private JLabel lblStatus;

	private JFrame parent;
	
	private String currentID;
	private JLabel lblNameText;
	private JLabel lblDescriptionText;
	private JLabel lblUnitPriceText;
	private JLabel lblInvoiceNumberText;
	private JLabel lblLocationText;
	private JLabel lblStatusText;

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

		panGeneral.setLayout(new MigLayout("", "[][][grow]", "[][98.00,grow][37.00][22.00,grow][grow][grow]"));

		/* Labels */

		lblName = new JLabel("Name:");
		panGeneral.add(lblName, "cell 0 0,alignx right");

		/* Name Field */

		/* Description Field */
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		lblNameText = new JLabel("");
		panGeneral.add(lblNameText, "cell 2 0");

		lblDescription = new JLabel("Description:");
		panGeneral.add(lblDescription, "cell 0 1,alignx right");

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		panGeneral.add(scrollPane, "cell 2 1,grow");
		
		lblDescriptionText = new JLabel("");
		lblDescriptionText.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(lblDescriptionText);

		lblUnitPrice = new JLabel("Unit Price:");
		panGeneral.add(lblUnitPrice, "cell 0 2,alignx right");
		
		lblUnitPriceText = new JLabel("");
		panGeneral.add(lblUnitPriceText, "flowx,cell 2 2");

		lblInvoiceNumber = new JLabel("Invoice Number:");
		panGeneral.add(lblInvoiceNumber, "cell 0 3");
		
		lblInvoiceNumberText = new JLabel("");
		panGeneral.add(lblInvoiceNumberText, "cell 2 3");

		lblLocation = new JLabel("Location:");
		panGeneral.add(lblLocation, "cell 0 4,alignx right");
		
		lblLocationText = new JLabel("");
		panGeneral.add(lblLocationText, "cell 2 4");

		lblStatus = new JLabel("Status:");
		panGeneral.add(lblStatus, "cell 0 5,alignx right");
		
		lblStatusText = new JLabel("");
		panGeneral.add(lblStatusText, "cell 2 5,alignx left,aligny baseline");
	}

	@Override
	public void renderPanel() {
		renderItemTileGeneralPanel();
		super.renderPanel();
	}

	public void renderItemTileGeneralPanel() {
		
		panGeneral = new JPanel();
		panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panGeneral.setBackground(Color.WHITE);
		/* Layout */

		panGeneral.setLayout(new MigLayout("", "[][][grow]", "[][98.00,grow][37.00][22.00,grow][grow][grow]"));

		/* Labels */

		lblName = new JLabel("Name:");
		panGeneral.add(lblName, "cell 0 0,alignx right");

		/* Name Field */

		/* Description Field */
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		lblNameText = new JLabel("");
		panGeneral.add(lblNameText, "cell 2 0");

		lblDescription = new JLabel("Description:");
		panGeneral.add(lblDescription, "cell 0 1,alignx right");

		scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		panGeneral.add(scrollPane, "cell 2 1,grow");
		
		lblDescriptionText = new JLabel("");
		lblDescriptionText.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(lblDescriptionText);

		lblUnitPrice = new JLabel("Unit Price:");
		panGeneral.add(lblUnitPrice, "cell 0 2,alignx right");
		
		lblUnitPriceText = new JLabel("");
		panGeneral.add(lblUnitPriceText, "flowx,cell 2 2");

		lblInvoiceNumber = new JLabel("Invoice Number:");
		panGeneral.add(lblInvoiceNumber, "cell 0 3");
		
		lblInvoiceNumberText = new JLabel("");
		panGeneral.add(lblInvoiceNumberText, "cell 2 3");

		lblLocation = new JLabel("Location:");
		panGeneral.add(lblLocation, "cell 0 4,alignx right");
		
		lblLocationText = new JLabel("");
		panGeneral.add(lblLocationText, "cell 2 4");

		lblStatus = new JLabel("Status:");
		panGeneral.add(lblStatus, "cell 0 5,alignx right");
		
		lblStatusText = new JLabel("");
		panGeneral.add(lblStatusText, "cell 2 5,alignx left,aligny baseline");

		addItemPanelReference.assignToQuad(panGeneral, 2);

	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean checkInput() {
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		
		if(iter.hasNext()) currentID = iter.next().toString();
		if(iter.hasNext()) lblNameText.setText(iter.next().toString());
		if(iter.hasNext()) lblDescriptionText.setText(iter.next().toString());
		if(iter.hasNext()) lblUnitPriceText.setText(iter.next().toString());
		if(iter.hasNext()) lblInvoiceNumberText.setText(iter.next().toString());
		if(iter.hasNext()) lblLocationText.setText(iter.next().toString());
		if(iter.hasNext()) lblStatusText.setText(iter.next().toString());
	}
	
	/**
	 * Sets the content to be loaded into the model of the location combo box
	 * @param iter
	 */
	public void loadLocationList(Iterator iter) {
//		 ArrayList<String> locationList = new ArrayList();
//	     while(iter.hasNext()){
//	    	 locationList.add(iter.next().toString());
//	     }
//	     cbLocation.setModel(new DefaultComboBoxModel(locationList.toArray()));
	}
	
	/**
	 * Sets the content to be loaded into the model of the status combo box
	 * @param iter
	 */
	public void loadStatusList(Iterator iter) {
//		 ArrayList<String> statusList = new ArrayList();
//	     while(iter.hasNext()){
//	    	 statusList.add(iter.next().toString());
//	     }
//	     cbStatus.setModel(new DefaultComboBoxModel(statusList.toArray()));
	}
}
