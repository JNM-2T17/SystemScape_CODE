package view.inventory.itemtileview;

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
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import view.Message;
import view.inventory.InventoryItemDisplayManager;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;
import view.inventory.PanelRegistry;

import com.toedter.calendar.JDateChooser;

public class ItemTileSoftwareView extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileView, ItemListener{
	
	private JPanel panSoftware;
	private JLabel lblType;
	private JLabel lblLicenseKey;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JComboBox cbType;
	private JComboBox cbAssignee;
	
	private JTextField tfLicenseKey;
	private JDateChooser deliveryDateChooser;
	
	private JFrame parent;
	
	public ItemTileSoftwareView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		this.parent=parent;
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
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		panSoftware.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][17][][17][][]"));
		
	
		
		lblType = new JLabel("Type:");
		panSoftware.add(lblType, "cell 1 1");
		
	
		
		lblLicenseKey = new JLabel("License Key:");
		panSoftware.add(lblLicenseKey, "cell 2 3 3 1,alignx right");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panSoftware.add(lblDeliveryDate, "flowx,cell 1 5 4 1,alignx right");
		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panSoftware.add(deliveryDateChooser, "cell 5 5 3 1,growx,aligny center");
		
		lblAssignee = new JLabel("Assignee:");
		panSoftware.add(lblAssignee, "flowx,cell 1 7 3 1");
		
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("Software");
		cbType.addItemListener(this);
		cbType.setBackground(Color.white);
		panSoftware.add(cbType, "cell 3 1 5 1,growx");
		
		
		tfLicenseKey = new JTextField();
		tfLicenseKey.setColumns(10);
		panSoftware.add(tfLicenseKey, "cell 5 3 3 1,growx");
		
		
		cbAssignee = new JComboBox();
		cbAssignee.setBackground(Color.white);
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
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
			if(cbType.getSelectedItem().equals("IT Assets"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("IT");
			else if(cbType.getSelectedItem().equals("Non-IT Assets"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("Non-IT");
			else if(cbType.getSelectedItem().equals("Software"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("Software");
			else if(cbType.getSelectedItem().equals("Others"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("General");
		}
	}

	@Override
	public boolean checkInput() {
		if(tfLicenseKey.getText().equals("")){
			new Message(parent, Message.ERROR, "Please specity item license key.");
			return false;
		}
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		
		cbAssignee.setSelectedItem(iter.next().toString());
		tfLicenseKey.setText(iter.next().toString());
	}
	
	@Override
	public void setDeliveryDate(Date date)
	{
		deliveryDateChooser.setDate(date);
	}

	@Override
	public void loadAssigneeList(Iterator iter) {
		 ArrayList<String> assigneeList = new ArrayList();
	     while(iter.hasNext()){
	    	 assigneeList.add(iter.next().toString());
	     }
	     cbAssignee.setModel(new DefaultComboBoxModel(assigneeList.toArray()));
		
	}
	@Override
	public void setType(String type) {
		cbType.setSelectedItem(type);
	}
	
}
