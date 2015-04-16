package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.ItemData;
import model.PurchaseOrder;
import model.Supplier;
import view.Button;
import view.Observer;
import view.PanelCell;
import view.Button.ButtonBuilder;

import com.toedter.calendar.JDateChooser;

import controller.PurchaseOrderController;
import controller.SupplierController;

import java.util.ArrayList;
import java.util.Iterator;

import view.Message;

import javax.swing.border.EmptyBorder;

public class ViewSpecificPO extends JPanel {
	private JPanel panDefinition, panLeft, panCenter, panRight, panSupplier,
			panDate, panAddItem, panDetails, panClass, panHeader, panFooter;
	private JLabel lblSupplier, lblClass, lblDate, lblItem;
	private JLabel cmbSupplier, cmbClass;
	private DefaultTableModel model;
	//private POTableModel poTableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panSubmit;
	private JPanel panGrandTotal;
	private JLabel lblGrandTotal;
	private JLabel dateChooser;
	private Date selectedDate;

	private PurchaseOrderController poController;
	private SupplierController supplierController;
	private JPanel panCurrency;
	private JLabel lblCurrency;
	private JLabel cmbCurrency;

	private JLabel lblGrandValue;
	private JPanel panVat;
	private JLabel lblVat;
	private JLabel lblInclusive;

	private DecimalFormat df; 
	private SimpleDateFormat dateFormat;
	private String sDate;
	private JFrame parent;
	public ViewSpecificPO(JFrame parent) {
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(30, 30, 30, 30));
		
		df = new DecimalFormat("#,###,###,###,##0.00");
		df.setMaximumFractionDigits(2);
		dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		
		this.parent=parent;
		poController = PurchaseOrderController.getInstance();
		supplierController = SupplierController.getInstance();

		setLayout(new BorderLayout(0, 0));

		panDefinition = new JPanel();
		panDefinition.setPreferredSize(new Dimension(10, 80));
		panDefinition.setBackground(Color.white);
		panDefinition.setLayout(new BorderLayout(0, 0));
		add(panDefinition, BorderLayout.NORTH);

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

		cmbSupplier = new JLabel();

		panSupplier.add(cmbSupplier);
		cmbSupplier.setBackground(Color.white);
		cmbSupplier.setPreferredSize(new Dimension(200, 30));

		panClass = new JPanel();
		panClass.setBackground(Color.white);
		panLeft.add(panClass, BorderLayout.WEST);

		lblClass = new JLabel("Item Classification :");
		lblClass.setHorizontalTextPosition(SwingConstants.LEFT);
		lblClass.setHorizontalAlignment(SwingConstants.LEFT);
		panClass.add(lblClass);

		cmbClass = new JLabel();
		cmbClass.setPreferredSize(new Dimension(100, 30));
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

		dateChooser = new JLabel();
		dateChooser.setOpaque(false);
		dateChooser.setBorder(null);
		dateChooser.setBackground(Color.WHITE);
		dateChooser.setPreferredSize(new Dimension(150, 30));
		panDate.add(dateChooser);

		panCurrency = new JPanel();
		panCurrency.setBackground(Color.white);
		panLeft.setBackground(Color.white);
		panRight.add(panCurrency, BorderLayout.WEST);

		lblCurrency = new JLabel("Currency :");
		panCurrency.add(lblCurrency);

		cmbCurrency = new JLabel();
		cmbCurrency.setBackground(Color.white);
		cmbCurrency.setPreferredSize(new Dimension(110, 30));
		panCurrency.add(cmbCurrency);

		panCenter = new JPanel();
		panCenter.setBackground(Color.white);
		add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(new BorderLayout(0, 0));
		
		panHeader = new JPanel();
		panHeader.setBackground(Color.white);
		panCenter.add(panHeader, BorderLayout.NORTH);
		panHeader.setLayout(new BorderLayout(0, 0));

		panAddItem = new JPanel();
		panAddItem.setBackground(Color.white);
		panHeader.add(panAddItem, BorderLayout.WEST);
		panAddItem.setSize(new Dimension(100, 80));
		panAddItem.setPreferredSize(new Dimension(125, 60));
		panAddItem.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		lblItem = new JLabel("Item/s");
		panAddItem.add(lblItem);
		
		panFooter = new JPanel();
		panFooter.setBackground(Color.white);
		panCenter.add(panFooter, BorderLayout.SOUTH);
		panFooter.setLayout(new BorderLayout(0, 0));

		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		panFooter.add(panSubmit, BorderLayout.SOUTH);

		panDetails = new JPanel();
		panFooter.add(panDetails, BorderLayout.EAST);
		panDetails.setBackground(Color.white);
		panDetails.setLayout(new BorderLayout(0, 0));

		panGrandTotal = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panGrandTotal.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panDetails.add(panGrandTotal, BorderLayout.SOUTH);
		panGrandTotal.setBackground(Color.white);

		lblGrandTotal = new JLabel("Grand Total :");
		panGrandTotal.add(lblGrandTotal);

		lblGrandValue = new JLabel("0.00");
		panGrandTotal.add(lblGrandValue);

		panVat = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panVat.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panVat.setBackground(Color.WHITE);
		panDetails.add(panVat, BorderLayout.NORTH);

		lblVat = new JLabel("VAT:");
		panVat.add(lblVat);

		lblInclusive = new JLabel("Inclusive");
		panVat.add(lblInclusive);

		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBackground(Color.WHITE);
		panCenter.add(scrollPane);

		model = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}

			public boolean isFocusable(int rowIndex, int mColIndex) {
				return false;
			}

			public boolean isCellSelectable(int rowIndex, int mColIndex) {
				if (mColIndex == 5) {
					return true;
				}
				return false;
			}
		};

		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(55);

		initializeModel();

	}
	
	public void setPO(PurchaseOrder po){
		
		
		cmbSupplier.setText(po.getSupplier().getName());
		cmbClass.setText(po.getType());
		
		sDate = dateFormat.format(po.getDate());
		dateChooser.setText(sDate);
		
		Iterator data = po.getItems();
		ItemData item;
		
		while (data.hasNext()) {
			item = (ItemData) data.next();

			/*** populate the table ***/
			model.setRowCount(model.getRowCount() + 1);
			model.setValueAt(item.getName(), model.getRowCount() - 1, 0);
			model.setValueAt(item.getDescription(), model.getRowCount() - 1, 1);
			model.setValueAt(po.getQuantity(item), model.getRowCount() - 1, 2);
			model.setValueAt(df.format(item.getUnitPrice()), model.getRowCount() - 1, 3);
			model.setValueAt(df.format(po.computeTotal(item)), model.getRowCount() - 1, 4);
			/********DEV INSERT QUANTITY RECEIVED HERE**********/
			model.setValueAt(po.getQuantityRcvd(item), model.getRowCount() - 1, 5);
		}
		lblGrandValue.setText(String.valueOf(df.format(po.computeGrandTotal())));
	}


	/** initialize the table model **/
	public void initializeModel() {
		model.setColumnCount(6);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		String headers[] = { "Item", "Description", "Quantity", "Unit Price",
							 "Amount","Quantity Received"};
		model.setColumnIdentifiers(headers);

		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);


	}

}
