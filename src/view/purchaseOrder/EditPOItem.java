package view.purchaseOrder;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;

import model.ItemData;
import model.PurchaseOrder;
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollBar;

import view.Button;
import view.Button.ButtonBuilder;
import view.Message;
import view.PopUp;
import view.JTextFieldFilter;
import controller.PurchaseOrderController;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class EditPOItem extends PopUp implements ActionListener, FocusListener{
	private JPanel panHeader, panCenter, panClose, panContent,panFooter,panWest,panEast, panSubmit;
	private JLabel lblItem,lblAmount,lblAmountValue,lblQuantity,lblDescription,lblPrice;
	private JTextArea txtDescription;
	private JTextField txtQuantity,txtPrice, txtItem;
	private JButton btnSubmit;
	private JScrollPane scrollPane;

	private PurchaseOrderController poController;
	private JLabel lblType;
	private JComboBox cmbType;
	private JFrame parent;
	private String type;
	private JLabel lblVat;
	private JCheckBox checkBox;
        private PurchaseOrderController purchaseOrderController;
        private ItemData itemData;
        private PurchaseOrder po;
	
	public EditPOItem(JFrame parent,ItemData i, PurchaseOrder po,  PurchaseOrderController poController) 
	{
	
		super(parent);
		this.parent = parent;
		this.poController = poController;
		this.type = po.getType();
		itemData = i;
                this.po = po;
		this.addFocusListener(this);
//		this.setUndecorated(true);
		purchaseOrderController = poController;
		panCenter = new JPanel();
		panCenter.setBackground(Color.white);
		panCenter.setLayout(new BorderLayout(0, 0));
		panCenter.setSize(new Dimension(600,400));
		panCenter.setPreferredSize(new Dimension(500,500));
		
		panContent = new JPanel();
		panContent.setBackground(Color.white);
		panCenter.add(panContent, BorderLayout.CENTER);
		panContent.setLayout(new MigLayout("", "[grow][188.00,grow][][][]", "[][][][grow][][][][][][]"));
		
		lblItem = new JLabel("Item :");
		panContent.add(lblItem, "cell 0 1,alignx left");
		
		/***set selected item name to edit****/
		txtItem = new JTextField(i.getName());
		txtItem.setColumns(10);
		panContent.add(txtItem, "cell 1 1,growx");
		txtItem.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
			
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				/***set the border of the textfield to black***/
				txtItem.setBorder(border);
			}
		});

		
		lblDescription = new JLabel("Item Description :");
		panContent.add(lblDescription, "cell 0 2,alignx left");
		
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		panContent.add(scrollPane, "cell 1 3,growy");
		
		/***set selected item description to edit****/
		txtDescription = new JTextArea(i.getDescription());
		txtDescription.setLineWrap(true);
		scrollPane.setViewportView(txtDescription);
		txtDescription.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtDescription.setBorder(border);
			}
		});

		
		lblType = new JLabel("Type:");
		panContent.add(lblType, "cell 0 5,alignx left");
		
		/**have a query to set the classification of an item given the item data and the purchase order
		  the  item belongs to**/
		String[] types={"IT Asset", "Non-IT Asset"};
		cmbType = new JComboBox(types);
		cmbType.setPreferredSize(new Dimension(185, 32));
		cmbType.setBackground(Color.WHITE);
		panContent.add(cmbType, "cell 1 5,alignx left");
		
		lblQuantity = new JLabel("Quantity :");
		panContent.add(lblQuantity, "cell 0 6,alignx left");
		
		/***set the item quantity***/
		txtQuantity = new JTextField(String.valueOf(po.getQuantity(i)));
		txtQuantity.setPreferredSize(new Dimension(10, 25));
		panContent.add(txtQuantity, "cell 1 6");
		txtQuantity.setColumns(10);
		txtQuantity.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtQuantity.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
				txtQuantity.setBorder(border);
			}
		});
		
		
		lblPrice = new JLabel("Unit Price :");
		panContent.add(lblPrice, "cell 0 7");
	
		/***set the unit price of the item****/
		txtPrice = new JTextField(String.valueOf(i.getUnitPrice()));
		
		txtPrice.setPreferredSize(new Dimension(10, 25));
		txtPrice.addActionListener(new TextAmountActionListener());
		txtPrice.setColumns(10);
		panContent.add(txtPrice, "cell 1 7");
		txtPrice.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtPrice.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
				txtPrice.setBorder(border);
			}
		});

		
		lblAmount = new JLabel("Amount :");
		panContent.add(lblAmount, "cell 0 8");
		
		/***set the total amount of the item****/
		lblAmountValue = new JLabel(String.valueOf(po.computeTotal(i)));
		panContent.add(lblAmountValue, "cell 1 8");
		
		lblVat = new JLabel("VAT:");
		panContent.add(lblVat, "cell 0 9");
		
		checkBox = new JCheckBox("");
		checkBox.setBackground(Color.WHITE);
		panContent.add(checkBox, "cell 1 9");
		
		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		panCenter.add(panSubmit, BorderLayout.SOUTH);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		panSubmit.add(btnSubmit);
		
		setContent(panCenter);
		getClose().addActionListener(this);
		
		if(!type.equals("Hard")){
			lblType.setVisible(false);
			cmbType.setVisible(false);
		}
		
		this.setVisible(true);
                
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == getClose())
		{
			this.setVisible(false); 
			this.dispose();
		}
		else if(e.getSource() == btnSubmit)
		{
			String error = checkFields();
			if(error.equals("") == true)
			{
                                String item = txtItem.getText();
				String description = txtDescription.getText();
				int quantity = parseStringInt(txtQuantity.getText());
				float price = (float) parseStringFloat(txtPrice.getText());
				
				/***insert code statements here to edit the PO item***/
                                System.out.println("Item: "+item+" "+description+" "+price);
                                ItemData id = new ItemData(item, description, price);
                                System.out.println("ITEMZATA :"+itemData.getName());
                                purchaseOrderController.editItem(id, quantity, itemData);
				
				this.setVisible(false); 
				this.dispose();	
			}
			else if(error.equals("") == false)
			{
				Message msg = new Message(parent, Message.ERROR,  error);
			}
			else
			{
				clearFields();
			}
			

		}
		
	}
	
	/***clear the fields*****/
	public void clearFields()
	{
		txtDescription.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
		txtItem.setText("");
	}

	/****parse string to integer******/
	public int parseStringInt(String quantity)
	{
		if(quantity.equals("") == false)
			return Integer.parseInt(quantity);
		return 0;
	}
	
	/****parse string to float****/
	public double parseStringFloat(String  price)
	{
		if(price.equals("") == false)
			return  Float.parseFloat(price);
		return 0.0;
	}
	
	/****check the for blank fields and return the error message to display****/
	public String checkFields()
	{
		String error = "";
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
	
		if(txtItem.getText().equals("")){
			error+= "Item Name Field is empty \n";
			txtItem.setBorder(border);
		}
		if(txtDescription.getText().equals("")){
			error+= "Item Description Area is empty \n";
			txtDescription.setBorder(border);
		}
		if(txtQuantity.getText().equals("")){
			error+= "Quantity Field is empty \n";
			txtQuantity.setBorder(border);
		}
		if(txtPrice.getText().equals("")){
			error+= "Price Field is empty";
			txtPrice.setBorder(border);
		}

		return error;
	}
	
	
	/***use to auto compute the amount of an item once the user press the enter key.***/
	class TextAmountActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int quantity = parseStringInt(txtQuantity.getText());
			float price = (float) parseStringFloat(txtPrice.getText());
			float result = quantity * price;
			 lblAmountValue.setText(String.valueOf(result));
		}
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		JFrame f=(JFrame) e.getSource();
		f.toFront();
	}
}
