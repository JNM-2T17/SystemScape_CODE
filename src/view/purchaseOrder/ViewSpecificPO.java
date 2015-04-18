package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

public class ViewSpecificPO extends JPanel {
	private JPanel panDefinition, panLeft, panCenter, panRight, panSupplier,
	panDate, panAddItem, panClass, panHeader, panFooter;
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

	private DecimalFormat df; 
	private SimpleDateFormat dateFormat;
	private String sDate;
	private JFrame parent;
	private JPanel panNorth;
	private JLabel lblLogo;
	private JLabel lblTitle;
	private JLabel lblAddress;
	private JLabel lblAddressContent;
	private JPanel panApproved;
	private JLabel lblApproved;
	private JLabel lblApprovedDate;
	private JPanel panel;
	private JDateChooser approvedDateChooser;
	private JTextField txtApproved;
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
		GridBagLayout gbl_panSupplier = new GridBagLayout();
		gbl_panSupplier.columnWidths = new int[]{45, 103, 0};
		gbl_panSupplier.rowHeights = new int[]{0, 30, 0};
		gbl_panSupplier.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panSupplier.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panSupplier.setLayout(gbl_panSupplier);

		lblSupplier = new JLabel("Supplier :");
		GridBagConstraints gbc_lblSupplier = new GridBagConstraints();
		gbc_lblSupplier.anchor = GridBagConstraints.WEST;
		gbc_lblSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplier.gridx = 0;
		gbc_lblSupplier.gridy = 0;
		panSupplier.add(lblSupplier, gbc_lblSupplier);

		cmbSupplier = new JLabel();

		GridBagConstraints gbc_cmbSupplier = new GridBagConstraints();
		gbc_cmbSupplier.insets = new Insets(0, 0, 5, 0);
		gbc_cmbSupplier.anchor = GridBagConstraints.WEST;
		gbc_cmbSupplier.gridx = 1;
		gbc_cmbSupplier.gridy = 0;
		panSupplier.add(cmbSupplier, gbc_cmbSupplier);
		cmbSupplier.setBackground(Color.white);
		cmbSupplier.setPreferredSize(new Dimension(200, 30));

		lblAddress = new JLabel("Address :");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.insets = new Insets(0, 0, 0, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 1;
		panSupplier.add(lblAddress, gbc_lblAddress);

		lblAddressContent = new JLabel("New label");
		GridBagConstraints gbc_lblAddressContent = new GridBagConstraints();
		gbc_lblAddressContent.anchor = GridBagConstraints.WEST;
		gbc_lblAddressContent.gridx = 1;
		gbc_lblAddressContent.gridy = 1;
		panSupplier.add(lblAddressContent, gbc_lblAddressContent);

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

		panNorth = new JPanel();
		panNorth.setBackground(Color.WHITE);
		panDefinition.add(panNorth, BorderLayout.NORTH);
		panNorth.setLayout(new BorderLayout(0, 0));

		lblLogo = new JLabel("");
		ImageIcon ii = new ImageIcon("src/assets/logo.png");
		Image img = ii.getImage();
		Image newimg = img.getScaledInstance((int) (ii.getIconWidth() * 0.5),
				(int) (ii.getIconHeight() * 0.5), java.awt.Image.SCALE_SMOOTH);
		ii = new ImageIcon(newimg);
		lblLogo.setIcon(ii);
		panNorth.add(lblLogo, BorderLayout.WEST);

		lblTitle = new JLabel("Purchase Order");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));
		panNorth.add(lblTitle, BorderLayout.CENTER);

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

		panGrandTotal = new JPanel();
		panFooter.add(panGrandTotal, BorderLayout.EAST);
		FlowLayout flowLayout_1 = (FlowLayout) panGrandTotal.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panGrandTotal.setBackground(Color.white);

		lblGrandTotal = new JLabel("Grand Total :");
		panGrandTotal.add(lblGrandTotal);

		lblGrandValue = new JLabel("0.00");
		panGrandTotal.add(lblGrandValue);
		
		panApproved = new JPanel();
		panApproved.setBackground(Color.WHITE);
		panFooter.add(panApproved, BorderLayout.WEST);
		GridBagLayout gbl_panApproved = new GridBagLayout();
		gbl_panApproved.columnWidths = new int[]{69, 65, 30, 39, 0};
		gbl_panApproved.rowHeights = new int[]{14, 14, 14, 0};
		gbl_panApproved.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panApproved.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panApproved.setLayout(gbl_panApproved);
		
		lblApproved = new JLabel("Approved By :");
		GridBagConstraints gbc_lblApproved = new GridBagConstraints();
		gbc_lblApproved.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblApproved.insets = new Insets(0, 0, 5, 5);
		gbc_lblApproved.gridx = 0;
		gbc_lblApproved.gridy = 0;
		panApproved.add(lblApproved, gbc_lblApproved);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		panApproved.add(panel, gbc_panel);
		
		lblApprovedDate = new JLabel("Date :");
		GridBagConstraints gbc_lblApprovedDate = new GridBagConstraints();
		gbc_lblApprovedDate.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblApprovedDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblApprovedDate.gridx = 2;
		gbc_lblApprovedDate.gridy = 0;
		panApproved.add(lblApprovedDate, gbc_lblApprovedDate);
		
		approvedDateChooser = new JDateChooser();
		approvedDateChooser.setDateFormatString("MMMM dd, yyyy\r\n");
		GridBagConstraints gbc_approvedDateChooser = new GridBagConstraints();
		gbc_approvedDateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_approvedDateChooser.fill = GridBagConstraints.BOTH;
		gbc_approvedDateChooser.gridx = 3;
		gbc_approvedDateChooser.gridy = 0;
		panApproved.add(approvedDateChooser, gbc_approvedDateChooser);
		
		txtApproved = new JTextField();
		txtApproved.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gbc_txtApproved = new GridBagConstraints();
		gbc_txtApproved.gridwidth = 2;
		gbc_txtApproved.insets = new Insets(0, 0, 0, 5);
		gbc_txtApproved.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtApproved.gridx = 0;
		gbc_txtApproved.gridy = 2;
		panApproved.add(txtApproved, gbc_txtApproved);
		txtApproved.setColumns(10);
		

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
		cmbCurrency.setText(po.getCurrency());
		sDate = dateFormat.format(po.getDate());
		dateChooser.setText(sDate);
		
		/***change this to display the correct address of the supplier (i.e.) Street,City,country***/
		lblAddressContent.setText(po.getSupplier().getCountry());

		Iterator data = po.getItems();
		ItemData item;

		while (data.hasNext()) {
			item = (ItemData) data.next();

			String proj = "<html><center><b>"+ item.getName()+"</b></center>" + item.getDescription()+" </hmtl>";
			/*** populate the table ***/
			model.setRowCount(model.getRowCount() + 1);
			model.setValueAt(proj, model.getRowCount() - 1, 0);
			model.setValueAt(po.getQuantity(item), model.getRowCount() - 1, 1);
			model.setValueAt(df.format(item.getUnitPrice()), model.getRowCount() - 1, 2);
			model.setValueAt(df.format(po.computeTotal(item)), model.getRowCount() - 1, 3);
			/********DEV INSERT QUANTITY RECEIVED HERE**********/
			model.setValueAt(po.getQuantityRcvd(item), model.getRowCount() - 1, 4);
		}
		lblGrandValue.setText(String.valueOf(df.format(po.computeGrandTotal())));
	}


	/** initialize the table model **/
	public void initializeModel() {
		model.setColumnCount(5);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		String headers[] = { "Product Description", "Quantity", "Unit Price",
				"Amount","Quantity Received"};
		model.setColumnIdentifiers(headers);
		table.getColumnModel().getColumn(0).setWidth(300);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setWidth(50);
		
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setWidth(50);


	}

}
