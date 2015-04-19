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
import java.text.SimpleDateFormat;
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

public class ItemTileGeneralView extends ItemPanelDecorator implements ItemPanelParticipant, TypeItemTileView, ItemListener{
	
	private JPanel panGeneral;
	private JLabel lblType;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	private JLabel lblTypeText;
	private JLabel lblDeliveryDateText;
	private JLabel lblAssigneeText;
	
	public ItemTileGeneralView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		panGeneral = new JPanel();
		panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panGeneral.setBackground(Color.WHITE);
		/* Layout */
		
		panGeneral.setLayout(new MigLayout("", "[46.00][38.00][38.00][][38.00][][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][grow][17][][]"));
		
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		panGeneral.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblTypeText = new JLabel("");
		panGeneral.add(lblTypeText, "cell 3 1 7 1");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panGeneral.add(lblDeliveryDate, "flowx,cell 1 3 6 1,alignx right");
		
		lblDeliveryDateText = new JLabel("");
		panGeneral.add(lblDeliveryDateText, "cell 7 3 3 1");
		
		lblAssignee = new JLabel("Assignee:");
		panGeneral.add(lblAssignee, "flowx,cell 1 5 4 1");
		addItemPanelReference.assignToQuad(panGeneral, 1);
		
		lblAssigneeText = new JLabel("");
		panGeneral.add(lblAssigneeText, "cell 5 5 5 1");
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileGeneralPanel();
		super.renderPanel();
	}
	
	public void renderItemTileGeneralPanel()
	{
		// TODO Auto-generated constructor stub
				panGeneral = new JPanel();
				panGeneral.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
				panGeneral.setBackground(Color.WHITE);
				/* Layout */
				
				panGeneral.setLayout(new MigLayout("", "[46.00][38.00][38.00][][38.00][][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][grow][17][][]"));
				
				String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
				
				/* Labels */
				lblType = new JLabel("Type:");
				panGeneral.add(lblType, "cell 1 1 2 1,alignx left");
				
				lblTypeText = new JLabel("Other");
				panGeneral.add(lblTypeText, "cell 3 1 7 1");
				
				lblAssignee = new JLabel("Assignee:");
				panGeneral.add(lblAssignee, "flowx,cell 1 5 4 1");
				addItemPanelReference.assignToQuad(panGeneral, 1);
				
				lblAssigneeText = new JLabel("");
				panGeneral.add(lblAssigneeText, "cell 5 5 5 1");
		addItemPanelReference.assignToQuad(panGeneral, 1);

	}
	
	@Override
	public Iterator retrieveInformation() {
		return null;
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public boolean checkInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void loadAssignee(String assignee) {
            lblAssignee.setText(assignee);
	}

	@Override
	public void loadPresets(Iterator iter) {
		if(iter.hasNext()) lblAssigneeText.setText(iter.next().toString());
	}
	
	@Override
	public void setType(String type) {
		lblTypeText.setText(type);
	}
	

	
	
	
	
}
