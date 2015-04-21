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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import view.inventory.InventoryItemDisplayManager;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;
import view.inventory.PanelRegistry;

import com.toedter.calendar.JDateChooser;

import controller.EmployeeController;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import view.Message;

public class ItemTileNonITField extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileField, ItemListener{

	private JPanel panNonIT;
	private JLabel lblType;
	private JLabel lblAssignee;
        
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JLabel lblAssignStart;
	private JLabel lblAssignEnd;
        
	private JComboBox cbType;
	private JComboBox cbAssignee;
	private JFrame parent;
	private JLabel lblDeliveryDate;
	private JDateChooser deliveryDateChooser;
	

	public ItemTileNonITField(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
                this.parent=parent;
		// TODO Auto-generated constructor stub
              
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileNonIT();
		super.renderPanel();
	}
	
	public void renderItemTileNonIT()
	{

       
		  panNonIT = new JPanel();
  		panNonIT.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
  		panNonIT.setBackground(Color.WHITE);
  		/* Layout */
  		String typeStrings[] = {"IT Asset","Non-IT Asset","Software","Others"};
  		
  		panNonIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00,grow][38.00,grow][100,grow][100][100][31.00]", "[][][17][grow][17][][][][][][][]"));
  		
  		
  		/* Label */
  		
  		lblType = new JLabel("Type:");
  		panNonIT.add(lblType, "cell 1 1 2 1,alignx left");
  		
  		/* Type Combo Box */
  		
  		cbType = new JComboBox(typeStrings);
  		cbType.setSelectedItem("Non-IT Asset");
  		cbType.setBackground(Color.white);
  		cbType.addItemListener(this);
  		panNonIT.add(cbType, "cell 3 1 5 1,growx");
  		
  		lblDeliveryDate = new JLabel("Delivery Date: ");
  		panNonIT.add(lblDeliveryDate, "cell 1 3 2 1");
  		
  		deliveryDateChooser = new JDateChooser();
  		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
  		deliveryDateChooser.setDate(new Date());
  		deliveryDateChooser.setOpaque(false);
  		deliveryDateChooser.setDateFormatString("MMMM dd, yyyy\r\n");
  		deliveryDateChooser.setBorder(null);
  		deliveryDateChooser.setBackground(Color.WHITE);
  		panNonIT.add(deliveryDateChooser, "cell 3 3 5 1,growx");
                  
  		lblAssignee = new JLabel("Assignee: ");
  		panNonIT.add(lblAssignee, "cell 1 5 2 1");
  		
  		cbAssignee = new JComboBox();
  		populateCbxEmployee();
  		cbAssignee.setBackground(Color.WHITE);
  		panNonIT.add(cbAssignee, "cell 3 5 5 1,growx");
                          		
		lblAssignStart = new JLabel("Assign Start:");
		panNonIT.add(lblAssignStart, "cell 2 7,growx");
		
		        
		
		startDateChooser = new JDateChooser();
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setDateFormatString("MMMM dd, yyyy");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panNonIT.add(startDateChooser, "flowx,cell 5 7 3 1,growx");
		
		lblAssignEnd = new JLabel("Assign End:");
		panNonIT.add(lblAssignEnd, "cell 2 8,growx");
  		
  		endDateChooser = new JDateChooser();
  		endDateChooser.setOpaque(false);
  		endDateChooser.setDate(new Date());
  		endDateChooser.setBorder(null);
  		endDateChooser.setDateFormatString("MMMM dd, yyyy");
  		endDateChooser.setBackground(Color.WHITE);
  		endDateChooser.setPreferredSize(new Dimension(150, 30));
  		panNonIT.add(endDateChooser, "flowx,cell 5 8 3 1,growx");
		addItemPanelReference.assignToQuad(panNonIT, 1);
                
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
		infoList.add(cbAssignee.getSelectedItem().toString());
                infoList.add(startDateChooser.getDate());
		infoList.add(endDateChooser.getDate());
		return infoList.iterator();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cbType)
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
		// TODO Auto-generated method stub
		
                if(startDateChooser.getDate().getTime()>endDateChooser.getDate().getTime()){
                        new Message(parent, Message.ERROR, "Start date cannot be after the End date.");
			return false;
                }
            return true;
        }

	@Override
	public void loadAssigneeList(Iterator iter) {
		 ArrayList<String> assigneeList = new ArrayList();
	     while(iter.hasNext()){
	    	 assigneeList.add(iter.next().toString());
	     }
	     cbAssignee.setModel(new DefaultComboBoxModel(assigneeList.toArray()));
	}
        
        public void saveStartDate(Date startDate){
           startDateChooser.setDate(startDate);
        }
        
        public void saveEndDate(Date endDate){
           endDateChooser.setDate(endDate);
        }
        
	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		if(iter.hasNext()) cbAssignee.setSelectedItem(iter.next().toString());
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
                startDateChooser.setVisible(stat);
                endDateChooser.setVisible(stat);
		lblAssignStart.setVisible(stat);
		lblAssignEnd.setVisible(stat);
		startDateChooser.setVisible(stat);
		endDateChooser.setVisible(stat);
		//cbAssignee.setSelectedItem("None");
	}

	@Override
	public void isSwitchable(boolean stat) {
		// TODO Auto-generated method stub
		cbType.setEnabled(stat);
	}
	
}
