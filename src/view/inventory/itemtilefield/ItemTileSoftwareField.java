package view.inventory.itemtilefield;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import model.Employee;
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

import controller.EmployeeController;

public class ItemTileSoftwareField extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileField, ItemListener{
	
	private JPanel panSoftware;
	private JLabel lblType;
	private JLabel lblLicenseKey;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	private JComboBox cbType;
	private JComboBox cbAssignee;
	
	private JTextField tfLicenseKey;
	
	private JFrame parent;
	
	public ItemTileSoftwareField(JFrame parent, ItemPanelTemplate addItemPanelReference) {
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
		String typeStrings[] = {"IT Asset","Non-IT Asset","Software","Others"};
		
		panSoftware.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][17][][17][][]"));
		
	
		
		lblType = new JLabel("Type:");
		panSoftware.add(lblType, "cell 1 1");
		
	
		
		lblLicenseKey = new JLabel("License Key:");
		panSoftware.add(lblLicenseKey, "cell 2 3 3 1,alignx right");
		
		//lblDeliveryDate = new JLabel("Delivery Date:");
		//panSoftware.add(lblDeliveryDate, "flowx,cell 1 5 4 1,alignx right");
		
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
		populateCbxEmployee();
		panSoftware.add(cbAssignee, "cell 4 7 4 1,growx");
		
		addItemPanelReference.assignToQuad(panSoftware, 1);
	}
	
	public void populateCbxEmployee()
	{
		cbAssignee.addItem("None");
		EmployeeController ec = EmployeeController.getInstance();
		Iterator<Employee> eList = ec.getAll();
		while(eList.hasNext())
		{
			Employee e = eList.next();
			cbAssignee.addItem(e.getName());
		}
	}
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(cbAssignee.getSelectedItem().toString());
		infoList.add(tfLicenseKey.getText());
		return infoList.iterator();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			if(cbType.getSelectedItem().equals("IT Asset"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("IT");
			else if(cbType.getSelectedItem().equals("Non-IT Asset"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("Non-IT");
			else if(cbType.getSelectedItem().equals("Software"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("Soft");
			else if(cbType.getSelectedItem().equals("Others"))
				InventoryItemDisplayManager.getInstance().overrideContentPanel("Others");
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
		//if(iter.hasNext()) cbAssignee.setSelectedItem(iter.next().toString());
		if(iter.hasNext()) tfLicenseKey.setText(iter.next().toString());
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
	
	@Override
	public void setAssigneeVisible(boolean stat) {
		// TODO Auto-generated method stub
		cbAssignee.setVisible(stat);
		lblAssignee.setVisible(stat);
		cbAssignee.setSelectedItem("None");
	}
}
