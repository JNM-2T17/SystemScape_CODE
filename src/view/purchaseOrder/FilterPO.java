package view.purchaseOrder;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import view.PopUp;

import com.toedter.calendar.JDateChooser;

import controller.PurchaseOrderController;
import controller.SupplierController;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import model.Supplier;

public class FilterPO extends PopUp implements ActionListener {
	private JButton btnFilter, btnRemoveFilter;
	private JDateChooser dateChooser;
	private JComboBox cmbItem, cmbSupplier, cmbTotal, cmbUnit;
	private JTextField txtInvoice, txtTotal;
	SupplierController supplierController;
	PurchaseOrderController purchaseOrderController;
        private boolean closed=true;
        private ArrayList list;
	public FilterPO(JFrame parent) {

		super(parent);
                list = new ArrayList();
		JPanel panMain = new JPanel();
		panMain.setBackground(Color.white);
		panMain.setSize(new Dimension(500, 350));
		panMain.setPreferredSize(new Dimension(500, 350));
		getContentPane().add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.white);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblItem = new JLabel("Item Classification:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblItem, 50,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblItem, 50,
				SpringLayout.WEST, panContent);
		panContent.add(lblItem);

		JLabel lblSupplier = new JLabel("Supplier:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblSupplier, 20,
				SpringLayout.SOUTH, lblItem);
		sl_panContent.putConstraint(SpringLayout.WEST, lblSupplier, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblSupplier);

		JLabel lblDate = new JLabel("Date:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblDate, 20,
				SpringLayout.SOUTH, lblSupplier);
		sl_panContent.putConstraint(SpringLayout.WEST, lblDate, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblDate);

		JLabel lblGrandTotal = new JLabel("Grand Total:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblGrandTotal, 20,
				SpringLayout.SOUTH, lblDate);
		sl_panContent.putConstraint(SpringLayout.WEST, lblGrandTotal, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblGrandTotal);

		cmbItem = new JComboBox();
		AutoCompleteDecorator.decorate(cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbItem, -50,
				SpringLayout.EAST, panContent);
		cmbItem.setBackground(Color.white);
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbItem, 50,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbItem, 26,
				SpringLayout.EAST, lblItem);
		panContent.add(cmbItem);

		cmbSupplier = new JComboBox();
		AutoCompleteDecorator.decorate(cmbSupplier);
		cmbSupplier.setBackground(Color.white);
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbSupplier, 0,
				SpringLayout.NORTH, lblSupplier);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbSupplier, 0,
				SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbSupplier, 0,
				SpringLayout.EAST, cmbItem);
		panContent.add(cmbSupplier);

		cmbTotal = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbTotal, 0,
				SpringLayout.NORTH, lblGrandTotal);
		cmbTotal.setBackground(Color.white);
		panContent.add(cmbTotal);

		dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.white);
		dateChooser.setOpaque(false);
		dateChooser.setBorder(null);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBackground(Color.WHITE);
		// dateChooser.setPreferredSize(new Dimension(150, 30));
		sl_panContent.putConstraint(SpringLayout.NORTH, dateChooser, 0,
				SpringLayout.NORTH, lblDate);
		sl_panContent.putConstraint(SpringLayout.WEST, dateChooser, 0,
				SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, dateChooser, 0,
				SpringLayout.EAST, cmbItem);
		panContent.add(dateChooser);

		txtTotal = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtTotal, 0,
				SpringLayout.NORTH, cmbTotal);
		sl_panContent.putConstraint(SpringLayout.WEST, txtTotal, 6,
				SpringLayout.EAST, cmbTotal);
		sl_panContent.putConstraint(SpringLayout.EAST, txtTotal, 0,
				SpringLayout.EAST, cmbItem);
		panContent.add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblInvoice = new JLabel("Invoice #:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblInvoice, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblInvoice);

		txtInvoice = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, lblInvoice, 0,
				SpringLayout.NORTH, txtInvoice);
		sl_panContent.putConstraint(SpringLayout.NORTH, txtInvoice, 14,
				SpringLayout.SOUTH, cmbTotal);
		sl_panContent.putConstraint(SpringLayout.WEST, txtInvoice, 0,
				SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, txtInvoice, 0,
				SpringLayout.EAST, cmbItem);
		txtInvoice.setColumns(10);
		panContent.add(txtInvoice);

                
		cmbUnit = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.EAST, cmbTotal, 75,
				SpringLayout.EAST, cmbUnit);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbUnit, 50,
				SpringLayout.WEST, dateChooser);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbUnit, 0,
				SpringLayout.WEST, dateChooser);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbTotal, 6,
				SpringLayout.EAST, cmbUnit);
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbUnit, 0,
				SpringLayout.NORTH, lblGrandTotal);
		cmbUnit.setBackground(Color.WHITE);
		panContent.add(cmbUnit);

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.white);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		panFooter.add(btnFilter);
		
		btnRemoveFilter = new JButton("Remove Filter");
		btnRemoveFilter.setForeground(new Color(255, 255, 255));
		btnRemoveFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		btnRemoveFilter.setBackground(new Color(32, 130, 213));
		btnRemoveFilter.addActionListener(this);
		panFooter.add(btnRemoveFilter);

		JPanel panHeader = new JPanel();
		panHeader.setBackground(Color.WHITE);
		panMain.add(panHeader, BorderLayout.NORTH);

		JLabel lblFilterPO = new JLabel("Filter Purchase Orders");
		lblFilterPO.setFont(new Font("Arial", Font.PLAIN, 16));
		panHeader.add(lblFilterPO);

		this.getClose().addActionListener(this);
		btnFilter.addActionListener(this);

		supplierController = SupplierController.getInstance();
		purchaseOrderController = PurchaseOrderController.getInstance();
		populate();

		setContent(panMain);
		this.repaint();
		this.revalidate();
		this.setVisible(true);

	}
        
        public Iterator getValues(){
            if(list!=null){
		list.add((String)cmbItem.getSelectedItem());
		list.add((String)cmbSupplier.getSelectedItem());
                if(dateChooser.getDate() == null)
                    list.add("");
                else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.format(dateChooser.getDate());
                    list.add(""+dateFormat.format(dateChooser.getDate()));
                    System.out.println("tryyyyy "+dateFormat.format(dateChooser.getDate()));
                }
                list.add((String)cmbUnit.getSelectedItem());
                list.add((String)cmbTotal.getSelectedItem());
		list.add(txtTotal.getText());
		list.add(txtInvoice.getText());
                return list.iterator();
            }
            return null;
	}

	public void populate() {
		Iterator iterator = supplierController.getAll();
		ArrayList<String> data = new ArrayList();
		data.add("");
		while (iterator.hasNext()) {
			data.add(((Supplier) iterator.next()).getName());
		}
		cmbSupplier.setModel(new DefaultComboBoxModel(data.toArray()));
		data.removeAll(data);

		cmbItem.setModel(new DefaultComboBoxModel(new String[] {"","Hard",
				"Soft", "Gen" }));
		cmbUnit.setModel(new DefaultComboBoxModel(new String[] {"", ">", ">=",
				"<=", "<" }));
                cmbTotal.setModel(new DefaultComboBoxModel(new String[]{"", "Australian Dollar", "Euro", "Php", "Yen" }));
	}

	public boolean checkFields()
	{
		boolean isEmpty = false;
		if(cmbSupplier.getSelectedIndex() == 0 && cmbItem.getSelectedIndex() == 0 &&
		   cmbUnit.getSelectedIndex() == 0 && txtInvoice.getText().equals("") && 
		   txtTotal.getText().equals("") && dateChooser.getDate() == null && cmbTotal.getSelectedIndex() == 0)
		{
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
        
        public boolean isClosed(){
		return closed;
	}
}
