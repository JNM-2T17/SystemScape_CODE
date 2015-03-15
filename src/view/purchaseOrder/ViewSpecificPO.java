package view.purchaseOrder;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

public class ViewSpecificPO extends JPanel{
	
	private JPanel panHeader, panFooter, panLeft, panRight, panSupplier, panPO, panGrandTotal;
	private JLabel lblSupplier, lblSupplierValue, lblPO, lblPOValue, lblDate, lblDateValue, lblTotal, lblTotalValue;
//	private JScrollPane scrollPane;
//	private JTable table;
//	private DefaultTableModel model;
	private JPanel panDate;
	private JPanel panCurrency;
	private JLabel lblCurrency;
	private JLabel lblCurrencyValue;
	private ViewPOItemTable tblPOItem;
	private JPanel panCenter;
	private JPanel panWest;
	private JPanel panEast;
	
	public ViewSpecificPO() {
		setLayout(new BorderLayout(0, 0));
		
		panCenter = new JPanel();
		add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(new BorderLayout(0, 0));
		
		panHeader = new JPanel();
		panCenter.add(panHeader, BorderLayout.NORTH);
		panHeader.setLayout(new BorderLayout(0, 0));
		
		panLeft = new JPanel();
		panHeader.add(panLeft, BorderLayout.WEST);
		panLeft.setLayout(new BorderLayout(0, 0));
		
		panSupplier = new JPanel();
		panLeft.add(panSupplier, BorderLayout.NORTH);
		
		lblSupplier = new JLabel("Supplier :");
		lblSupplier.setFont(new Font("Arial", Font.PLAIN, 18));
		panSupplier.add(lblSupplier);
		
		lblSupplierValue = new JLabel("");
		lblSupplierValue.setFont(new Font("Arial", Font.PLAIN, 18));
		panSupplier.add(lblSupplierValue);
		
		panPO = new JPanel();
		panLeft.add(panPO, BorderLayout.WEST);
		panPO.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPO = new JLabel("P.O. Number :");
		lblPO.setFont(new Font("Arial", Font.PLAIN, 18));
		panPO.add(lblPO);
		
		lblPOValue = new JLabel("");
		lblPOValue.setFont(new Font("Arial", Font.PLAIN, 18));
		panPO.add(lblPOValue);
		
		panRight = new JPanel();
		panHeader.add(panRight, BorderLayout.EAST);
		panRight.setLayout(new BorderLayout(0, 0));
		
		panDate = new JPanel();
		panRight.add(panDate, BorderLayout.NORTH);
		
		lblDate = new JLabel("Date :");
		panDate.add(lblDate);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		
		lblDateValue = new JLabel("");
		panDate.add(lblDateValue);
		lblDateValue.setFont(new Font("Arial", Font.PLAIN, 18));
		
		panCurrency = new JPanel();
		panRight.add(panCurrency, BorderLayout.WEST);
		
		lblCurrency = new JLabel("Currency :");
		lblCurrency.setFont(new Font("Arial", Font.PLAIN, 18));
		panCurrency.add(lblCurrency);
		
		lblCurrencyValue = new JLabel("");
		lblCurrencyValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panCurrency.add(lblCurrencyValue);
		
		panFooter = new JPanel();
		panCenter.add(panFooter, BorderLayout.SOUTH);
		panFooter.setLayout(new BorderLayout(0, 0));
		
		panGrandTotal = new JPanel();
		panFooter.add(panGrandTotal, BorderLayout.EAST);
		panGrandTotal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblTotal = new JLabel("Grand Total :");
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 18));
		panGrandTotal.add(lblTotal);
		
		lblTotalValue = new JLabel("");
		lblTotalValue.setFont(new Font("Arial", Font.PLAIN, 18));
		panGrandTotal.add(lblTotalValue);
		
		tblPOItem = new ViewPOItemTable();
		panCenter.add(tblPOItem, BorderLayout.CENTER);
		
		panWest = new JPanel();
		add(panWest, BorderLayout.WEST);
		
		panEast = new JPanel();
		add(panEast, BorderLayout.EAST);
		
//		String[] tableHeaders = {"Item", "Description", "Quantity", "UnitPrice", "Amount"};
//		model = new DefaultTableModel(tableHeaders, 5) {
//			public boolean isCellEditable(int rowIndex, int mColIndex) {
//				if(mColIndex==3) return true;
//				return false;
//			}
//
//			public boolean isFocusable(int rowIndex, int mColIndex) {
//				return true;
//			}
//
//			public boolean isCellSelectable(int rowIndex, int mColIndex) {
//				return true;
//			}
//		};

//		scrollPane = new JScrollPane();
//		scrollPane.setBackground(Color.WHITE);
//		panContent.add(scrollPane);
//		
//		table = new JTable(model);
//		scrollPane.setViewportView(table);
//		
//		table.setFont(new Font("Arial", Font.PLAIN, 18));
//		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 20));
//		table.setRowHeight(55);
//		model.setColumnCount(5);
//		model.setRowCount(0);
//		
//		table.getColumnModel().getColumn(2).setPreferredWidth(20);
	}

	public void setSupplier(String supplier)
	{
		this.lblSupplierValue.setText(supplier);
	}
	
	public void setDate(Date date)
	{
		this.lblDateValue.setText(date.toString());
	}
	
	public void setGrandTotal(Double amount)
	{
		this.lblTotalValue.setText(String.valueOf(amount));
	}
	
	public void setPONumber(String poNumber)
	{
		this.lblPOValue.setText(poNumber);
	}
	
}
