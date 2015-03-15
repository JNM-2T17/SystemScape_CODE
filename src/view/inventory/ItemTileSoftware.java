package view.inventory;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

public class ItemTileSoftware extends ItemPanelDecorator implements ItemPanelParticipant, ItemListener{
	
	private JPanel panSoftware;
	private JLabel lblType;
	private JLabel lblLicenseKey;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JComboBox cbType;
	private JComboBox cbAssignee;
	
	private JTextField tfLicenseKey;
	private JDateChooser deliveryDateChooser;
	
	public ItemTileSoftware(ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileSoftwarePanel();
		super.renderPanel();
	}
	
	public void renderItemTileSoftwarePanel()
	{
		panSoftware = new JPanel();
		panSoftware.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panSoftware.setBackground(Color.WHITE);
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","General"};
		
		panSoftware.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][17][grow][17][][]"));
		
	
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(lblType, "cell 1 1");
		
	
		
		lblLicenseKey = new JLabel("License Key:");
		lblLicenseKey.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(lblLicenseKey, "cell 2 3 3 1,alignx right");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		lblDeliveryDate.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(lblDeliveryDate, "flowx,cell 1 5 4 1,alignx right");
		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panSoftware.add(deliveryDateChooser, "cell 5 5 3 1,growx,aligny center");
		
		lblAssignee = new JLabel("Assignee:");
		lblAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(lblAssignee, "flowx,cell 1 7 3 1");
		
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("Software");
		cbType.addItemListener(this);
		cbType.setBackground(Color.white);
		cbType.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(cbType, "cell 3 1 5 1,growx");
		
		
		tfLicenseKey = new JTextField();
		tfLicenseKey.setFont(new Font("Arial", Font.BOLD, 20));
		tfLicenseKey.setColumns(10);
		panSoftware.add(tfLicenseKey, "cell 5 3 3 1,growx");
		
		
		cbAssignee = new JComboBox();
		cbAssignee.setBackground(Color.white);
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
		cbAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		panSoftware.add(cbAssignee, "cell 4 7 4 1,growx");
		
		addItemPanelReference.assignToQuad(panSoftware, 1);
	}
	
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(deliveryDateChooser.getDate());
		infoList.add(cbAssignee.getSelectedItem().toString());
		infoList.add(tfLicenseKey.getText());
		return infoList.iterator();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			PanelRegistry.getInstance().setCurrentType(cbType.getSelectedItem().toString());
			System.out.println("HAI!");
		}
	}
}
