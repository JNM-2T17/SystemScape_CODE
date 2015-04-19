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
	private JLabel lblAssignee;
	
	private JFrame parent;
	private JLabel lblTypeText;
	private JLabel lblLicenseKeyText;
	private JLabel lblAssigneeText;
	
	public ItemTileSoftwareView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		this.parent=parent;
		panSoftware = new JPanel();
		panSoftware.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panSoftware.setBackground(Color.WHITE);
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		panSoftware.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][17][][17][][]"));
		
	
		
		lblType = new JLabel("Type:");
		panSoftware.add(lblType, "cell 1 1");
		
		lblTypeText = new JLabel("");
		panSoftware.add(lblTypeText, "cell 3 1 5 1");
		
	
		
		lblLicenseKey = new JLabel("License Key:");
		panSoftware.add(lblLicenseKey, "cell 2 3 3 1,alignx right");
		
		lblLicenseKeyText = new JLabel("");
		panSoftware.add(lblLicenseKeyText, "cell 5 3 3 1");
		
		lblAssignee = new JLabel("Assignee:");
		panSoftware.add(lblAssignee, "flowx,cell 1 7 3 1");
		
		lblAssigneeText = new JLabel("");
		panSoftware.add(lblAssigneeText, "cell 4 7 4 1");
	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileSoftwarePanel();
		super.renderPanel();
	}
	
	public void renderItemTileSoftwarePanel()
	{
		// TODO Auto-generated constructor stub
				this.parent=parent;
				panSoftware = new JPanel();
				panSoftware.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
				panSoftware.setBackground(Color.WHITE);
				String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
				
				panSoftware.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00][38.00,grow][100,grow][100][100][31.00]", "[][][17.00][][17][][17][][]"));
				
			
				
				lblType = new JLabel("Type:");
				panSoftware.add(lblType, "cell 1 1");
				
				lblTypeText = new JLabel("Software");
				panSoftware.add(lblTypeText, "cell 3 1 5 1");
				
			
				
				lblLicenseKey = new JLabel("License Key:");
				panSoftware.add(lblLicenseKey, "cell 2 3 3 1,alignx right");
				
				lblLicenseKeyText = new JLabel("");
				panSoftware.add(lblLicenseKeyText, "cell 5 3 3 1");
				
				lblAssignee = new JLabel("Assignee:");
				panSoftware.add(lblAssignee, "flowx,cell 1 7 3 1");
				
				lblAssigneeText = new JLabel("");
				panSoftware.add(lblAssigneeText, "cell 4 7 4 1");
		
		addItemPanelReference.assignToQuad(panSoftware, 1);
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
		
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		
		if(iter.hasNext()) lblLicenseKeyText.setText(iter.next().toString());
		if(iter.hasNext()) lblAssigneeText.setText(iter.next().toString());
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
