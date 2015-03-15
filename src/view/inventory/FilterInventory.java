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

	public FilterInventory(JFrame parent) {
		super(parent);

		JPanel panMain = new JPanel();
		panMain.setBackground(Color.WHITE);
		panMain.setPreferredSize(new Dimension(750, 300));
		panMain.setSize(new Dimension(750, 300));
		add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 32));
		panFooter.add(btnFilter);

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Arial", Font.PLAIN, 24));
		sl_panContent.putConstraint(SpringLayout.NORTH, lblItem, 45,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblItem, 68,
				SpringLayout.WEST, panContent);
		panContent.add(lblItem);

		JLabel lblClassification = new JLabel("Classification: ");
		lblClassification.setFont(new Font("Arial", Font.PLAIN, 24));
		sl_panContent.putConstraint(SpringLayout.NORTH, lblClassification, 30,
				SpringLayout.SOUTH, lblItem);
		sl_panContent.putConstraint(SpringLayout.WEST, lblClassification, 0,
				SpringLayout.WEST, lblItem);
		panContent.add(lblClassification);

		cmbItem = new JComboBox();
		cmbItem.setBackground(Color.white);
		cmbItem.setFont(new Font("Arial", Font.PLAIN, 24));
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbItem, 39,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbItem, 106,
				SpringLayout.EAST, lblItem);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbItem, -75,
				SpringLayout.EAST, panContent);
		panContent.add(cmbItem);

		cmbClassification = new JComboBox();
		cmbClassification.setFont(new Font("Arial", Font.PLAIN, 24));
		cmbClassification.setBackground(Color.WHITE);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbClassification, 0,
				SpringLayout.WEST, cmbItem);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbClassification, 0,
				SpringLayout.SOUTH, lblClassification);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbClassification, 0,
				SpringLayout.EAST, cmbItem);
		panContent.add(cmbClassification);

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
