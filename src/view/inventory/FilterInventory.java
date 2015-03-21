package view.inventory;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import view.PopUp;

public class FilterInventory extends PopUp implements ActionListener {

	private JButton btnFilter;
	private JComboBox cmbItem;
	private JComboBox cmbClassification;
	private JTextField txtQuantity;
	private JTextField txtService;
	private JTextField txtAsset;
	private JTextField txtInvoice;

	public FilterInventory(JFrame parent) {
		super(parent);

		JPanel panMain = new JPanel();
		panMain.setBackground(Color.WHITE);
		panMain.setPreferredSize(new Dimension(450, 380));
		panMain.setSize(new Dimension(500, 380));
		add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		panFooter.add(btnFilter);

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblItem = new JLabel("Item:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblItem, 35,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblItem, 50,
				SpringLayout.WEST, panContent);
		panContent.add(lblItem);

		JLabel lblClassification = new JLabel("Classification: ");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblClassification, 20,
				SpringLayout.SOUTH, lblItem);
		sl_panContent.putConstraint(SpringLayout.WEST, lblClassification, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblClassification);

		cmbItem = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbItem, 0, SpringLayout.SOUTH, lblItem);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbItem, -75,
				SpringLayout.EAST, panContent);
		cmbItem.setBackground(Color.white);
		panContent.add(cmbItem);

		cmbClassification = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.WEST, cmbItem, 0, SpringLayout.WEST, cmbClassification);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbClassification, 63, SpringLayout.EAST, lblClassification);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbClassification, -75, SpringLayout.EAST, panContent);
		cmbClassification.setBackground(Color.WHITE);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbClassification, 0,
				SpringLayout.SOUTH, lblClassification);
		panContent.add(cmbClassification);
		
		JLabel lblOfficeLocation = new JLabel("Office Location:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblOfficeLocation, 20, SpringLayout.SOUTH, lblClassification);
		sl_panContent.putConstraint(SpringLayout.WEST, lblOfficeLocation, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblOfficeLocation);
		
		JLabel lblAssetTag = new JLabel("Asset Tag:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblAssetTag, 20, SpringLayout.SOUTH, lblOfficeLocation);
		sl_panContent.putConstraint(SpringLayout.WEST, lblAssetTag, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblAssetTag);
		
		JLabel lblServiceTag = new JLabel("Service Tag:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblServiceTag, 20, SpringLayout.SOUTH, lblAssetTag);
		sl_panContent.putConstraint(SpringLayout.WEST, lblServiceTag, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblServiceTag);
		
		JLabel lblAssignee = new JLabel("Assignee:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblAssignee, 20, SpringLayout.SOUTH, lblServiceTag);
		sl_panContent.putConstraint(SpringLayout.WEST, lblAssignee, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblAssignee);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblQuantity, 20, SpringLayout.SOUTH, lblAssignee);
		sl_panContent.putConstraint(SpringLayout.WEST, lblQuantity, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblQuantity);
		
		JComboBox cmbOffice = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.WEST, cmbOffice, 56, SpringLayout.EAST, lblOfficeLocation);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbOffice, 0, SpringLayout.SOUTH, lblOfficeLocation);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbOffice, -75, SpringLayout.EAST, panContent);
		cmbOffice.setBackground(Color.WHITE);
		panContent.add(cmbOffice);
		
		JComboBox cmbAssignee = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.WEST, cmbAssignee, 0, SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbAssignee, 0, SpringLayout.SOUTH, lblAssignee);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbAssignee, 0, SpringLayout.EAST, cmbItem);
		cmbAssignee.setBackground(Color.WHITE);
		panContent.add(cmbAssignee);
		
		JComboBox cmbQuantity = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.WEST, cmbQuantity, 0, SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbQuantity, 0, SpringLayout.SOUTH, lblQuantity);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbQuantity, 52, SpringLayout.WEST, cmbItem);
		cmbQuantity.setBackground(Color.WHITE);
		panContent.add(cmbQuantity);
		
		txtQuantity = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtQuantity, 14, SpringLayout.SOUTH, cmbAssignee);
		sl_panContent.putConstraint(SpringLayout.WEST, txtQuantity, 6, SpringLayout.EAST, cmbQuantity);
		sl_panContent.putConstraint(SpringLayout.EAST, txtQuantity, 0, SpringLayout.EAST, cmbItem);
		panContent.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		txtService = new JTextField();
		sl_panContent.putConstraint(SpringLayout.WEST, txtService, 73, SpringLayout.EAST, lblServiceTag);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtService, -14, SpringLayout.NORTH, cmbAssignee);
		sl_panContent.putConstraint(SpringLayout.EAST, txtService, 0, SpringLayout.EAST, cmbItem);
		txtService.setColumns(10);
		panContent.add(txtService);
		
		txtAsset = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtAsset, 14, SpringLayout.SOUTH, cmbOffice);
		sl_panContent.putConstraint(SpringLayout.WEST, txtAsset, 0, SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, txtAsset, 0, SpringLayout.EAST, cmbItem);
		txtAsset.setColumns(10);
		panContent.add(txtAsset);
		
		JLabel lblInvoice = new JLabel("Invoice:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblInvoice, 20, SpringLayout.SOUTH, lblQuantity);
		sl_panContent.putConstraint(SpringLayout.WEST, lblInvoice, 0, SpringLayout.WEST, lblItem);
		panContent.add(lblInvoice);
		
		txtInvoice = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtInvoice, 14, SpringLayout.SOUTH, cmbQuantity);
		sl_panContent.putConstraint(SpringLayout.WEST, txtInvoice, 0, SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.EAST, txtInvoice, 0, SpringLayout.EAST, cmbItem);
		txtInvoice.setColumns(10);
		panContent.add(txtInvoice);

		getClose().addActionListener(this);

		setContent(panMain);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnFilter) {
			this.dispose();
		} else if (e.getSource() == getClose()) {
			this.dispose();
		}
	}
}
