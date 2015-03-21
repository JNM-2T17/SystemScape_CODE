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

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class ViewSpecificSupplier extends JPanel   {

	private JLabel txtSupp;
	// private JComboBox cmbCountry, cmbState, cmbCity;
	private JLabel cmbCountry, cmbState, cmbCity;
	private JPanel panContact, panClose;
	private JFrame parent;
	
	private Supplier supp;

	public ViewSpecificSupplier(JFrame parent, Supplier supp) {
		this.parent = parent;
		this.supp=supp;
		this.setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBorder(new EmptyBorder(50, 50, 0, 0));
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(new MigLayout("", "[100px][15px][400px]", "[27px][26px][26px][28px][28px][200px]"));

		JLabel lblSupp = new JLabel("Supplier: ");
		panContent.add(lblSupp, "cell 0 0,alignx left,growy");

		txtSupp = new JLabel(supp.getName());
		panContent.add(txtSupp, "cell 2 0,grow");
//		txtSupp.setColumns(10);

		JLabel lblAddress = new JLabel("Address:");
		panContent.add(lblAddress, "cell 0 1,alignx left,growy");

		JLabel lblCountry = new JLabel("Country:");
		panContent.add(lblCountry, "cell 0 2,alignx right,growy");

		JLabel lblState = new JLabel("State:");
		panContent.add(lblState, "cell 0 3,alignx right,growy");

		JLabel lblCity = new JLabel("City:");
		panContent.add(lblCity, "cell 0 4,alignx right,growy");

		cmbCountry = new JLabel(supp.getCountry());
		panContent.add(cmbCountry, "cell 2 2,grow");
		cmbCountry.setBackground(Color.white);

		cmbState = new JLabel(supp.getState());
		panContent.add(cmbState, "cell 2 3,grow");
		cmbState.setBackground(Color.white);

		cmbCity = new JLabel(supp.getCity());
		panContent.add(cmbCity, "cell 2 4,grow");
		cmbCity.setBackground(Color.white);

		JLabel lblContact = new JLabel("Contact #:");
		panContent.add(lblContact, "cell 0 5 3 1,alignx left,aligny top");

		panContact = new JPanel();
		panContact.setBackground(Color.WHITE);
		panContact.setBounds(51, 478, 457, 200);
		panContact.setLayout(new MigLayout());
		

		panClose = new JPanel();
	
		panClose.setBackground(Color.WHITE);
//		panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose, "");
		panClose.setLayout(new BoxLayout(panClose, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		panContent.add(scrollPane, "cell 2 5,grow");
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);
		panClose.setVisible(false);
		
		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		add(panFooter, BorderLayout.SOUTH);
		// populate();
		System.out.println("STUPID");
		
		init();
	}
	
	private void init(){
		Iterator it=supp.getSupplierContactList();
		while(it.hasNext()){
			SupplierContact sc=(SupplierContact) it.next();

			panClose.add(new JLabel(sc.getValue()+" ("+sc.getType()+")"), "newline");
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
			
		}
		
		panClose.setMaximumSize(new Dimension(360,
				panClose.getComponentCount() * 37));
		
		this.repaint();
		this.revalidate();
	}

	
}
