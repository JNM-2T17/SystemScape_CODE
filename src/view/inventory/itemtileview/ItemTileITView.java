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
import java.awt.Window.Type;
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

import view.Message;
import view.inventory.InventoryItemDisplayManager;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;
import view.inventory.PanelRegistry;

import com.toedter.calendar.JDateChooser;

public class ItemTileITView extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileView, ItemListener{
	
	private JPanel panIT;
	private JLabel lblType;
	private JLabel lblAssetTag;
	private JLabel lblServiceTag;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JFrame parent;
	private JLabel lblTypeText;
	private JLabel lblAssetTagText;
	private JLabel lblServiceTagText;
	private JLabel lblDeliveryDateText;
	private JLabel lblAssigneeText;
	
	public ItemTileITView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		this.parent=parent;
		panIT = new JPanel();
		panIT.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panIT.setBackground(Color.WHITE);
		/* Layout */
		
		panIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][9.00][39.00][11.00][][17][][]"));
		
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		panIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblTypeText = new JLabel("");
		panIT.add(lblTypeText, "cell 3 1 5 1");
		
		lblAssetTag = new JLabel("Asset Tag:");
		panIT.add(lblAssetTag, "cell 2 3 3 1,alignx left");
		
		lblAssetTagText = new JLabel("");
		panIT.add(lblAssetTagText, "cell 5 3 3 1");
		
		lblServiceTag = new JLabel("Service Tag:");
		panIT.add(lblServiceTag, "cell 2 5 3 1");
		
		lblServiceTagText = new JLabel("");
		panIT.add(lblServiceTagText, "cell 5 5 3 1");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panIT.add(lblDeliveryDate, "flowx,cell 1 7 4 1");
		
		lblDeliveryDateText = new JLabel("");
		panIT.add(lblDeliveryDateText, "cell 5 7 3 1");
		
		lblAssignee = new JLabel("Assignee:");
		panIT.add(lblAssignee, "flowx,cell 1 9 3 1");
		
		lblAssigneeText = new JLabel("");
		panIT.add(lblAssigneeText, "cell 4 9 4 1");
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
		
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		/* Labels */
		lblType = new JLabel("Type:");
		panIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblTypeText = new JLabel("");
		panIT.add(lblTypeText, "cell 3 1 5 1");
		
		lblAssetTag = new JLabel("Asset Tag:");
		panIT.add(lblAssetTag, "cell 2 3 3 1,alignx left");
		
		lblAssetTagText = new JLabel("");
		panIT.add(lblAssetTagText, "cell 5 3 3 1");
		
		lblServiceTag = new JLabel("Service Tag:");
		panIT.add(lblServiceTag, "cell 2 5 3 1");
		
		lblServiceTagText = new JLabel("");
		panIT.add(lblServiceTagText, "cell 5 5 3 1");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		panIT.add(lblDeliveryDate, "flowx,cell 1 7 4 1");
		
		lblDeliveryDateText = new JLabel("");
		panIT.add(lblDeliveryDateText, "cell 5 7 3 1");
		
		lblAssignee = new JLabel("Assignee:");
		panIT.add(lblAssignee, "flowx,cell 1 9 3 1");
		
		lblAssigneeText = new JLabel("");
		panIT.add(lblAssigneeText, "cell 4 9 4 1");
		
		addItemPanelReference.assignToQuad(panIT, 1);

	}
	
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDeliveryDate(Date date)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		lblDeliveryDateText.setText(formattedDate);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public boolean checkInput() {
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
//		if(iter.hasNext()) {
//		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
//		String formattedDate = formatter.format(iter.next().toString());
//		lblDeliveryDateText.setText(formattedDate);
//		}
		
		//if(iter.hasNext()) lblAssigneeText.setText(iter.next().toString());
		if(iter.hasNext()) lblAssetTagText.setText(iter.next().toString());
		if(iter.hasNext()) lblServiceTagText.setText(iter.next().toString());
                if(iter.hasNext()) lblDeliveryDateText.setText(iter.next().toString());
	}

	@Override
	public void loadAssignee(String assignee) {
            lblAssignee.setText(assignee);
	}

	@Override
	public void setType(String type) {
		lblTypeText.setText(type);
	}
	
	
}
