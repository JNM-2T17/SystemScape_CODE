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
import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollBar;

import view.Button;
import view.Button.ButtonBuilder;
import view.Message;
import view.PopUp;
import view.JTextFieldFilter;
import controller.PurchaseOrderController;

import javax.swing.JComboBox;

import java.awt.FlowLayout;

public class EditPOItemGen extends PopUp implements ActionListener, FocusListener{
	private JPanel panHeader, panCenter, panClose, panContent,panFooter,panWest,panEast, panSubmit;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JButton btnSubmit;

	private PurchaseOrderController poController;
	private JFrame parent;
	
	public EditPOItemGen(JFrame parent, PurchaseOrderController poController) 
	{
	
		super(parent);
		this.parent = parent;
		this.poController = poController;
		this.addFocusListener(this);
		this.setUndecorated(true);
		
		panCenter = new JPanel();
		getContentPane().add(panCenter, BorderLayout.CENTER);

		panCenter.setBackground(Color.white);
		panCenter.setLayout(new BorderLayout(0, 0));
		panCenter.setSize(new Dimension(600,400));
		panCenter.setPreferredSize(new Dimension(500, 100));
		
		panContent = new JPanel();
		panContent.setBackground(Color.white);
		panCenter.add(panContent, BorderLayout.CENTER);
		panContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblQuantity = new JLabel("Quantity :");
		panContent.add(lblQuantity);
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 18));
		
		txtQuantity = new JTextField();
		txtQuantity.setPreferredSize(new Dimension(10, 25));
		txtQuantity.setColumns(10);
		panContent.add(txtQuantity);
		txtQuantity.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		txtQuantity.addFocusListener( new FocusListener() {
			Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
			@Override
			public void focusLost(FocusEvent e) {
		
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				txtQuantity.setBorder(border);
			}
		});
		
		
		String[] types={"IT Asset", "Non-IT Asset"};
		
		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		panCenter.add(panSubmit, BorderLayout.SOUTH);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		panSubmit.add(btnSubmit);
	
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
				/***insert code statements here to add the information of a general item***/
				
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
	
	public void clearFields()
	{
		txtQuantity.setText("");
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
	public String checkFields()
	{
		String error = "";
		Border border = BorderFactory.createLineBorder(Color.RED, 2);
		if(txtQuantity.getText().equals("")){
			error+= "Quantity Field is empty \n";
			txtQuantity.setBorder(border);
		}
		
		return error;
	}



	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
