package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.PurchaseOrder;
import model.Supplier;
import view.Button;
import view.Observer;
import view.Button.ButtonBuilder;

import com.toedter.calendar.JDateChooser;

import controller.PurchaseOrderController;
import controller.SupplierController;

import java.util.ArrayList;
import java.util.Iterator;
import view.Message;

public class AddPO extends JPanel implements ActionListener, Observer {
	private JPanel panDefinition, panLeft, panCenter, panRight, panSupplier,
			panDate, panAddItem, panItemTable, panClass, panHeader, panFooter;
	private JLabel lblSupplier, lblClass, lblDate, lblItem;
	private JButton btnAddItem, btnSubmit;
	private JComboBox cmbSupplier, cmbClass;
	private DefaultTableModel model;
	private POTableModel poTableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panSubmit;
	private JPanel panGrandTotal;
	private JLabel lblGrandTotal;
	private JDateChooser dateChooser;
	private Date selectedDate;

	private PurchaseOrderController poController;
	private SupplierController supplierController;
	private JPanel panCurrency;
	private JLabel lblCurrency;
	private JComboBox cmbCurrency;
	
	private JFrame parent;
	private JLabel lblGrandValue;

	public AddPO(JFrame parent) {
		
		this.parent=parent;
		poController = PurchaseOrderController.getInstance();
		poTableModel = new POTableModel(poController);

		supplierController = SupplierController.getInstance();

		setLayout(new BorderLayout(0, 0));
		panDefinition = new JPanel();
		panDefinition.setPreferredSize(new Dimension(10, 80));
		add(panDefinition, BorderLayout.NORTH);
		panDefinition.setBackground(Color.white);
		panDefinition.setLayout(new BorderLayout(0, 0));

		panLeft = new JPanel();
		panLeft.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panDefinition.add(panLeft, BorderLayout.WEST);
		panLeft.setBackground(Color.white);
		panLeft.setLayout(new BorderLayout(0, 0));

		panSupplier = new JPanel();
		panSupplier.setBackground(Color.white);
		panLeft.add(panSupplier, BorderLayout.NORTH);

		lblSupplier = new JLabel("Supplier :");
		panSupplier.add(lblSupplier);
		lblSupplier.setFont(new Font("Arial", Font.PLAIN, 22));

		cmbSupplier = new JComboBox();
		populateSupplierNames();
                
		panSupplier.add(cmbSupplier);
		cmbSupplier.setFont(new Font("Arial", Font.PLAIN, 22));
		cmbSupplier.setBackground(Color.white);
		cmbSupplier.setPreferredSize(new Dimension(200, 30));

		panClass = new JPanel();
		panClass.setBackground(Color.white);
		panLeft.add(panClass, BorderLayout.WEST);

		lblClass = new JLabel("Item Classification :");
		lblClass.setHorizontalTextPosition(SwingConstants.LEFT);
		lblClass.setHorizontalAlignment(SwingConstants.LEFT);
		lblClass.setFont(new Font("Arial", Font.PLAIN, 22));
		panClass.add(lblClass);

		cmbClass = new JComboBox();
		cmbClass.setPreferredSize(new Dimension(100, 30));
		cmbClass.setModel(new DefaultComboBoxModel(new String[] { "Hard",
				"Soft", "Gen" }));
		cmbClass.setFont(new Font("Arial", Font.PLAIN, 22));
		cmbClass.setBackground(Color.white);
		panClass.add(cmbClass);

		panRight = new JPanel();
		panRight.setBackground(Color.white);
		panDefinition.add(panRight, BorderLayout.EAST);
		panRight.setLayout(new BorderLayout(0, 0));

		panDate = new JPanel();
		panDate.setBackground(Color.white);
		panRight.add(panDate, BorderLayout.NORTH);

		lblDate = new JLabel("Date :");
		panDate.add(lblDate);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 22));

		dateChooser = new JDateChooser();
		dateChooser.setOpaque(false);
		dateChooser.setDate(new Date());
		dateChooser.setBorder(null);
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 15));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setPreferredSize(new Dimension(150, 30));

		panDate.add(dateChooser);

		panCurrency = new JPanel();
		panCurrency.setBackground(Color.white);
		panLeft.setBackground(Color.white);
		panRight.add(panCurrency, BorderLayout.WEST);

		lblCurrency = new JLabel("Currency :");
		lblCurrency.setFont(new Font("Arial", Font.PLAIN, 22));
		panCurrency.add(lblCurrency);

		cmbCurrency = new JComboBox();
		cmbCurrency.setBackground(Color.white);
		cmbCurrency.setPreferredSize(new Dimension(110, 30));
		panCurrency.add(cmbCurrency);

		panCenter = new JPanel();
		panCenter.setBackground(Color.white);
		add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(new BorderLayout(0, 0));

		btnAddItem = new Button.ButtonBuilder().img("src/assets/Round/Add.png",
				40, 40).build();
		btnAddItem.setActionCommand("add");
		btnAddItem.addActionListener(this);

		panHeader = new JPanel();
		panHeader.setBackground(Color.white);
		panCenter.add(panHeader, BorderLayout.NORTH);
		panHeader.setLayout(new BorderLayout(0, 0));

		panAddItem = new JPanel();
		panAddItem.setBackground(Color.white);
		panHeader.add(panAddItem, BorderLayout.WEST);
		panAddItem.setSize(new Dimension(100, 80));
		panAddItem.setPreferredSize(new Dimension(125, 60));
		panAddItem.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblItem = new JLabel("Item/s");
		lblItem.setFont(new Font("Arial", Font.PLAIN, 22));
		panAddItem.add(lblItem);
		panAddItem.add(btnAddItem);

		panFooter = new JPanel();
		panFooter.setBackground(Color.white);
		panCenter.add(panFooter, BorderLayout.SOUTH);
		panFooter.setLayout(new BorderLayout(0, 0));

		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		panFooter.add(panSubmit, BorderLayout.SOUTH);

		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSubmit.addActionListener(this);
		panSubmit.add(btnSubmit);

		panGrandTotal = new JPanel();
		panGrandTotal.setBackground(Color.white);
		panFooter.add(panGrandTotal, BorderLayout.EAST);

		lblGrandTotal = new JLabel("Grand Total :");
		lblGrandTotal.setFont(new Font("Arial", Font.PLAIN, 22));
		panGrandTotal.add(lblGrandTotal);
		
		lblGrandValue = new JLabel("0.00");
		lblGrandValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panGrandTotal.add(lblGrandValue);

		panItemTable = new JPanel();
		panItemTable.setBackground(Color.white);
		panCenter.add(panItemTable, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBackground(Color.WHITE);
		panCenter.add(scrollPane);

		table = new JTable(poTableModel);
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 20));
		table.setRowHeight(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);

		supplierController.registerObserver(this);// dev
		poController.registerObserver(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		if (event.getSource() == btnAddItem) {
			AddPOItem i = new AddPOItem(parent, (String)cmbClass.getSelectedItem(), poController);
		} else if (event.getSource() == btnSubmit) {

			if (checkFields() == false) {
				selectedDate = dateChooser.getDate();
				Supplier supplier = (Supplier) supplierController.getObject((String) cmbSupplier.getSelectedItem());// dev
				poController.addPurchaseOrder(new PurchaseOrder(selectedDate, 0,cmbClass.getSelectedItem().toString(),supplier, ""));// dev
                                Message msg = new Message(parent, Message.SUCCESS, "Purchase Order added successfully.");
                        } else {
				JOptionPane.showMessageDialog(null, "No date");

			}
			
			clear();
		}

	}
	
	public void clear(){
		cmbSupplier.setSelectedIndex(0);
		cmbClass.setSelectedIndex(0);
		lblGrandValue.setText("");
		dateChooser.setDate(new Date());
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			for (int j = 0; j < table.getModel().getRowCount(); j++) {
				table.getModel().setValueAt(null, i, j);
			}
		}
		
		poTableModel.setRowCount(0);
		poController.init();
		
	}

	public boolean checkFields() {
		boolean isEmpty = false;

		if (dateChooser.getDate() == null) {
			isEmpty = true;
		}

		return isEmpty;
	}
        
        public void populateSupplierNames(){
            Iterator<Supplier> iterator = supplierController.getAll();
            ArrayList<String> supplierNames = new ArrayList();
            while(iterator.hasNext()){
                supplierNames.add(iterator.next().getName());
            }
            cmbSupplier.setModel(new DefaultComboBoxModel(supplierNames.toArray()));
        }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		poTableModel = new POTableModel(poController);
		table.setModel(poTableModel);
		lblGrandValue.setText(String.valueOf(poController.computeGrandTotal()));
	}
}
