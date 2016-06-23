package view.supplier;

import controller.SupplierController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.Supplier;
import model.SupplierContact;
import view.Button;
import view.Button.ButtonBuilder;
import view.Message;
import view.ViewSpecificTemplate;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class ViewSpecificSupplier extends JPanel {

	private JLabel txtSupp;
	// private JComboBox cmbCountry, cmbState, cmbCity;
	private JLabel cmbCountry, cmbState, cmbCity;
	private JPanel panContact, panClose;
	private JFrame parent;

	public ViewSpecificSupplier() {
		this.setBackground(Color.WHITE);

		// setLayout(new BorderLayout(0, 0));

		JPanel panMain = new JPanel();
		panMain.setBackground(Color.WHITE);
		add(panMain, BorderLayout.NORTH);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panMain.add(panContent, BorderLayout.CENTER);
		panContent.setBorder(new EmptyBorder(50, 50, 0, 0));
		panContent.setBackground(Color.WHITE);
		panContent.setLayout(new MigLayout("", "[100px][15px][400px]",
				"[][27px][26px][26px][28px][28px][200px]"));

		JLabel lblSupp = new JLabel("Supplier: ");
		panContent.add(lblSupp, "cell 0 0,alignx left,growy");

		txtSupp = new JLabel();
		panContent.add(txtSupp, "cell 2 0,grow");
		// txtSupp.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		panContent.add(lblAddress, "cell 0 2,alignx left,growy");

		JLabel lblStreet = new JLabel("Street:");
		panContent.add(lblStreet, "cell 0 3,alignx right,growy");

		JLabel lblCity = new JLabel("City:");
		panContent.add(lblCity, "cell 0 4,alignx right,growy");

		JLabel lblCountry = new JLabel("Country:");
		panContent.add(lblCountry, "cell 0 5,alignx right,growy");

		cmbCountry = new JLabel();
		panContent.add(cmbCountry, "cell 2 3,grow");
		cmbCountry.setBackground(Color.white);

		cmbState = new JLabel();
		panContent.add(cmbState, "cell 2 4,grow");
		cmbState.setBackground(Color.white);

		cmbCity = new JLabel();
		panContent.add(cmbCity, "cell 2 5,grow");
		cmbCity.setBackground(Color.white);

		JLabel lblContact = new JLabel("Contact #:");
		panContent.add(lblContact, "cell 0 6 3 1,alignx left,aligny top");

		panContact = new JPanel();
		panContact.setBackground(Color.WHITE);
		panContact.setBounds(51, 478, 457, 200);
		panContact.setLayout(new MigLayout());

		panClose = new JPanel();

		panClose.setBackground(Color.WHITE);
		// panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose, "");
		panClose.setLayout(new BoxLayout(panClose, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panContent.add(scrollPane, "cell 2 6,grow");
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);

		JPanel panFooter = new JPanel();
		panMain.add(panFooter, BorderLayout.SOUTH);
		panFooter.setBackground(Color.WHITE);
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		panClose.setVisible(false);
		// populate();

		//System.out.println("STUPID");
	}

	private void init(Iterator it) {
		while (it.hasNext()) {
			SupplierContact sc = (SupplierContact) it.next();

			panClose.add(new JLabel(sc.getValue() + " (" + sc.getType() + ")"),
					"newline");
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}

		}

		panClose.setMaximumSize(new Dimension(360,
				panClose.getComponentCount() * 37));

		this.repaint();
		this.revalidate();
	}
	
	public void setSupplier(Supplier supp){
		txtSupp.setText(supp.getName());
		cmbCountry.setText(supp.getCountry());
		cmbState.setText(supp.getState());
		cmbCity.setText(supp.getCity());
		init(supp.getSupplierContactList());
	}
	
	
	
	
	
	
	
	
	

}
