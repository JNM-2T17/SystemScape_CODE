package view.inventory.itemtile;

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
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;
import view.inventory.PanelRegistry;

import com.toedter.calendar.JDateChooser;

public class ItemTileIT extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTile, ItemListener{
	
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
	
	private JDateChooser deliveryDateChooser;
	
	private JFrame parent;
	
	public ItemTileIT(JFrame parent, ItemPanelTemplate addItemPanelReference) {
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
		
		panIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][grow][17][][]"));
		
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblAssetTag = new JLabel("Asset Tag:");
		lblAssetTag.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(lblAssetTag, "cell 2 3 3 1,alignx left");
		
		lblServiceTag = new JLabel("Service Tag:");
		lblServiceTag.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(lblServiceTag, "cell 2 5 3 1");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		lblDeliveryDate.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(lblDeliveryDate, "flowx,cell 1 7 4 1");
		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panIT.add(deliveryDateChooser, "cell 5 7 3 1,grow");
		
		lblAssignee = new JLabel("Assignee:");
		lblAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(lblAssignee, "flowx,cell 1 9 3 1");
		
		/* Type Combo Box */
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("IT Assets");
		cbType.setBackground(Color.white);
		cbType.addItemListener(this);
		cbType.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(cbType, "cell 3 1 5 1,growx");
		
		/* Asset and Service Fields */
		
		tfAssetTag = new JTextField();
		tfAssetTag.setFont(new Font("Arial", Font.BOLD, 20));
		tfAssetTag.setColumns(10);
		panIT.add(tfAssetTag, "cell 5 3 3 1,growx");
		
		
		tfServiceTag = new JTextField();
		tfServiceTag.setFont(new Font("Arial", Font.BOLD, 20));
		tfServiceTag.setColumns(10);
		panIT.add(tfServiceTag, "cell 5 5 3 1,growx");
		
		/* Assignee Combo Boxes */
		
		cbAssignee = new JComboBox();
		cbAssignee.setBackground(Color.white);
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
		cbAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		panIT.add(cbAssignee, "cell 4 9 4 1,growx");
		
		addItemPanelReference.assignToQuad(panIT, 1);

	}
	
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList();
		System.out.println("Date: " + (deliveryDateChooser.getDate() instanceof java.util.Date));
		infoList.add(deliveryDateChooser.getDate());
		infoList.add(cbAssignee.getSelectedItem().toString());
		infoList.add(tfAssetTag.getText());
		infoList.add(tfServiceTag.getText());
		return infoList.iterator();
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED)
		{
			PanelRegistry.getInstance().setCurrentType(cbType.getSelectedItem().toString());
		}
	}

	@Override
	public boolean checkInput() {
		boolean stat=true;
		if(tfAssetTag.getText().equals("")){
			System.out.println("ASSET: "+tfAssetTag.getText());
			new Message(parent, Message.ERROR, "Please specity item asset tag.");
			stat=false;
		}
		else if(tfServiceTag.getText().equals("")){
			new Message(parent, Message.ERROR, "Please specity item service tag.");
			stat=false;
		}
		
		return stat;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		deliveryDateChooser.setDate((Date) iter.next());
		cbAssignee.setSelectedItem(iter.next().toString());
		tfAssetTag.setText(iter.next().toString());
		tfServiceTag.setText(iter.next().toString());
	}

	@Override
	public void loadAssigneeList(Iterator iter) {
		// TODO Auto-generated method stub
		 ArrayList<String> assigneeList = new ArrayList();
	     while(iter.hasNext()){
	    	 assigneeList.add(iter.next().toString());
	     }
	     cbAssignee.setModel(new DefaultComboBoxModel(assigneeList.toArray()));
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		cbType.setSelectedItem(type);
	}
	
	
}
