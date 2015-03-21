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
import javax.swing.UIManager;
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
import view.ErrorListenerFactory;
import view.Message;
import view.PopUp;
import view.JTextFieldFilter;
import view.TextFieldError;
import controller.PurchaseOrderController;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class AddPOItem extends PopUp implements ActionListener, FocusListener {
	private JPanel panHeader, panCenter, panClose, panContent, panFooter,
			panWest, panEast, panSubmit;
	private JLabel lblItem, lblAmount, lblAmountValue, lblQuantity,
			lblDescription, lblPrice;
	private JTextArea txtDescription;
	private JTextField txtQuantity, txtPrice, txtItem;
	private JButton btnSubmit;
	private JScrollPane scrollPane;

	private PurchaseOrderController poController;
	private JLabel lblType;
	private JComboBox cmbType;
	private JFrame parent;
	private JLabel lblVat;
	private JCheckBox checkBox;

	public AddPOItem(JFrame parent, String type,
			PurchaseOrderController poController) {

		super(parent);
		this.parent = parent;
		this.poController = poController;
		this.addFocusListener(this);
		this.setUndecorated(true);

		panCenter = new JPanel();
		getContentPane().add(panCenter, BorderLayout.CENTER);

		panCenter.setBackground(Color.white);
		panCenter.setLayout(new BorderLayout(0, 0));
		panCenter.setSize(new Dimension(500, 400));
		panCenter.setPreferredSize(new Dimension(500, 400));

		panContent = new JPanel();
		panContent.setBackground(Color.white);
		panCenter.add(panContent, BorderLayout.CENTER);
		panContent.setLayout(new MigLayout("", "[grow][188.00,grow][][][]",
				"[][][][grow][][][][][][][][]"));

		lblItem = new JLabel("Item :");
		panContent.add(lblItem, "cell 0 1,alignx left");

		txtItem = new JTextField("");
		txtItem.addFocusListener(ErrorListenerFactory.getListener(txtItem));
		panContent.add(txtItem, "cell 1 1,growx");
		txtItem.setColumns(10);

		lblDescription = new JLabel("Item Description :");
		panContent.add(lblDescription, "cell 0 2,alignx left");

		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		panContent.add(scrollPane, "cell 1 3,growy");

		txtDescription = new JTextArea("");
		txtDescription.addFocusListener(ErrorListenerFactory
				.getListener(txtDescription));
		txtDescription.setLineWrap(true);
		scrollPane.setViewportView(txtDescription);

		lblType = new JLabel("Type:");
		panContent.add(lblType, "cell 0 5,alignx left");

		String[] types = { "IT Asset", "Non-IT Asset" };
		cmbType = new JComboBox(types);
		cmbType.setPreferredSize(new Dimension(185, 32));
		cmbType.setBackground(Color.WHITE);
		panContent.add(cmbType, "cell 1 5,alignx left");

		lblQuantity = new JLabel("Quantity :");
		panContent.add(lblQuantity, "cell 0 6,alignx left");

		txtQuantity = new JTextField();
		txtQuantity.addFocusListener(ErrorListenerFactory
				.getListener(txtQuantity));
		txtQuantity.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
		txtQuantity.setPreferredSize(new Dimension(10, 25));
		panContent.add(txtQuantity, "cell 1 6");
		txtQuantity.setColumns(10);

		lblPrice = new JLabel("Unit Price :");
		panContent.add(lblPrice, "cell 0 7");

		txtPrice = new JTextField("");
		txtPrice.addFocusListener(ErrorListenerFactory.getListener(txtPrice));
		txtPrice.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
		txtPrice.setPreferredSize(new Dimension(10, 25));
		panContent.add(txtPrice, "cell 1 7");
		txtPrice.addActionListener(new TextAmountActionListener());
		txtPrice.setColumns(10);

		lblAmount = new JLabel("Amount :");
		panContent.add(lblAmount, "cell 0 8");

		lblAmountValue = new JLabel("0.00");
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
		btnSubmit.addActionListener(this);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.setBackground(new Color(32, 130, 213));
		panSubmit.add(btnSubmit);

		setContent(panCenter);
		getClose().addActionListener(this);

		if (!type.equals("Hard")) {
			lblType.setVisible(false);
			cmbType.setVisible(false);
		}

		this.setVisible(true);

		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == getClose()) {
			this.setVisible(false); // you can't see me! - LOL
			this.dispose();
		} else if (e.getSource() == btnSubmit) {

			String error = checkFields();
			if (error.equals("") == true) {
				String item = txtItem.getText();
				String description = txtDescription.getText();
				int quantity = parseStringInt(txtQuantity.getText());
				float price = (float) parseStringFloat(txtPrice.getText());

				poController.addItem(new ItemData(item, description, price),
						quantity);
				this.setVisible(false); // you can't see me!
				this.dispose();

			} else if (error.equals("") == false) {
				Message msg = new Message(parent, Message.ERROR, error);
			} else {
				clearFields();
			}

		}
		//
	}

	public void clearFields() {
		txtDescription.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
		txtItem.setText("");
	}

	/**** parse string to integer ******/
	public int parseStringInt(String quantity) {
		if (quantity.equals("") == false)
			return Integer.parseInt(quantity);
		return 0;
	}

	/**** parse string to float ****/
	public double parseStringFloat(String price) {
		if (price.equals("") == false)
			return Float.parseFloat(price);
		return 0.0;
	}

	public String checkFields() {
		String error = "";
		Border border = BorderFactory.createLineBorder(Color.RED, 1);

		if (txtItem.getText().equals("")) {
			error += "Item Name Field is empty \n";
			txtItem.setBorder(border);
		}
		if (txtDescription.getText().equals("")) {
			error += "Item Description Area is empty \n";
			txtDescription.setBorder(border);
		}
		if (txtQuantity.getText().equals("")) {
			error += "Quantity Field is empty \n";
			txtQuantity.setBorder(border);
		}
		if (txtPrice.getText().equals("")) {
			error += "Price Field is empty";
			txtPrice.setBorder(border);
		}

		return error;
	}

	class TextAmountActionListener implements ActionListener {

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
		JFrame f = (JFrame) e.getSource();
		f.toFront();
	}
}
