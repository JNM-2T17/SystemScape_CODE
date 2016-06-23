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
import java.awt.Window.Type;
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

public class ItemTileITField extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileField, ItemListener{
	
	private JPanel panIT;
	private JLabel lblType;
	private JLabel lblAssetTag;
	private JLabel lblServiceTag;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;

	private JTextField tfAssetTag;
	private JTextField tfServiceTag;
	
	private JComboBox cbType;
	private JComboBox cbAssignee;
	
	private JDateChooser endDateChooser;
	private JDateChooser startDateChooser;
        private JLabel lblAssignEnd;
	private JLabel lblAssignStart;
        
	private JDateChooser deliveryDateChooser;
	
	private JFrame parent;
	
	public ItemTileITField(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		this.parent=parent;
		

		
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileGeneralPanel();
		super.renderPanel();
	}
	
	public void renderItemTileGeneralPanel()
	{
		panIT = new JPanel();
		panIT.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panIT.setBackground(Color.WHITE);
		/* Layout */
		
		panIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][][17][][]"));
		
		String typeStrings[] = {"IT Asset","Non-IT Asset","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		panIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblAssetTag = new JLabel("Asset Tag:");
		panIT.add(lblAssetTag, "cell 2 3 3 1,alignx left");
		
		lblServiceTag = new JLabel("Service Tag:");
		panIT.add(lblServiceTag, "cell 2 5 3 1");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panIT.add(lblDeliveryDate, "flowx,cell 1 7 4 1");
		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setDateFormatString("MMMM dd, yyyy");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panIT.add(deliveryDateChooser, "cell 5 7 3 1,grow");
		
		lblAssignee = new JLabel("Assignee:");
		panIT.add(lblAssignee, "flowx,cell 1 9 3 1");
		
		/* Type Combo Box */
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("IT Assets");
		cbType.setBackground(Color.white);
		cbType.addItemListener(this);
		panIT.add(cbType, "cell 3 1 5 1,growx");
		
		/* Asset and Service Fields */
		
		tfAssetTag = new JTextField();
		tfAssetTag.setColumns(10);
		panIT.add(tfAssetTag, "cell 5 3 3 1,growx");
		
		
		tfServiceTag = new JTextField();
		tfServiceTag.setColumns(10);
		panIT.add(tfServiceTag, "cell 5 5 3 1,growx");
		
		/* Assignee Combo Boxes */
		
		cbAssignee = new JComboBox();
		cbAssignee.setBackground(Color.white);
                
		populateCbxEmployee();
		panIT.add(cbAssignee, "cell 4 9 4 1,growx");
                
		
		startDateChooser = new JDateChooser();
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setDateFormatString("MMMM dd, yyyy");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panIT.add(startDateChooser, "flowx,cell 5 10 3 1,growx");
		
                lblAssignEnd = new JLabel("Assign End:");
		panIT.add(lblAssignEnd, "cell 2 11,growx");
		
		endDateChooser = new JDateChooser();
		endDateChooser.setOpaque(false);
		endDateChooser.setDate(new Date());
		endDateChooser.setBorder(null);
		endDateChooser.setDateFormatString("MMMM dd, yyyy");
		endDateChooser.setBackground(Color.WHITE);
		endDateChooser.setPreferredSize(new Dimension(150, 30));
		panIT.add(endDateChooser, "flowx,cell 5 11 3 1,growx");
		
		lblAssignStart = new JLabel("Assign Start:");
		panIT.add(lblAssignStart, "cell 2 10,growx");
		
		addItemPanelReference.assignToQuad(panIT, 1);
                
                setAssigneeVisible(false);
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
		//System.out.println("Date: " + (deliveryDateChooser.getDate() instanceof java.util.Date));
                infoList.add(cbAssignee.getSelectedItem().toString());
                infoList.add(endDateChooser.getDate());
		infoList.add(startDateChooser.getDate());
                infoList.add(tfServiceTag.getText());
		infoList.add(deliveryDateChooser.getDate());
		infoList.add(tfAssetTag.getText());
		
		return infoList.iterator();
	}

	public void setDeliveryDate(Date date)
	{
		deliveryDateChooser.setDate(date);
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
		boolean stat=true;
		if(tfAssetTag.getText().equals("")){
			//System.out.println("ASSET: "+tfAssetTag.getText());
			new Message(parent, Message.ERROR, "Please specity item asset tag.");
			stat=false;
		}
		else if(tfServiceTag.getText().equals("")){
			new Message(parent, Message.ERROR, "Please specity item service tag.");
			stat=false;
		}
                else if(startDateChooser.getDate().getTime()>endDateChooser.getDate().getTime()&&!cbAssignee.getSelectedItem().toString().equalsIgnoreCase("none")){
                        new Message(parent, Message.ERROR, "Start date cannot be after the End date.");
			stat=false;
                }
                try{
                    //Integer.parseInt(tfServiceTag.getText());
                    Integer.parseInt(tfAssetTag.getText());
                }catch(NumberFormatException nfe){
                    new Message(parent, Message.ERROR, "Asset Tag should be numeric");
                    stat = false;
                }
		
		return stat;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		/*if(iter.hasNext()) deliveryDateChooser.setDate((Date) iter.next());
		if(iter.hasNext()) cbAssignee.setSelectedItem(iter.next().toString());*/
		if(iter.hasNext()) tfAssetTag.setText(iter.next().toString());
		if(iter.hasNext()) tfServiceTag.setText(iter.next().toString());
                if(iter.hasNext()) startDateChooser.setDate((Date)iter.next());
                if(iter.hasNext()) endDateChooser.setDate((Date)iter.next());
	}

	@Override
	public void loadAssigneeList(Iterator iter) {
		// TODO Auto-generated method stub
		 ArrayList<String> assigneeList = new ArrayList();
	     while(iter.hasNext()){
	    	 assigneeList.add(iter.next().toString());
	     }
	     if(cbAssignee == null)
	     {
	    	 //System.out.println("Assignee is null");
	     }
	     if(assigneeList == null)
	     {
	    	 //System.out.println("List is null");
	     }
	     cbAssignee.setModel(new DefaultComboBoxModel(assigneeList.toArray()));
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		cbType.setSelectedItem(type);
	}
	
	@Override
	public void setAssigneeVisible(boolean stat) {
		// TODO Auto-generated method stub
		cbAssignee.setVisible(stat);
		lblAssignee.setVisible(stat);
                startDateChooser.setVisible(stat);
                endDateChooser.setVisible(stat);
		lblAssignStart.setVisible(stat);
		lblAssignEnd.setVisible(stat);
		startDateChooser.setVisible(stat);
		endDateChooser.setVisible(stat);
		cbAssignee.setSelectedItem("None");
	}

	@Override
	public void isSwitchable(boolean stat) {
		// TODO Auto-generated method stub
		cbType.setEnabled(stat);
	}
	
	
}
