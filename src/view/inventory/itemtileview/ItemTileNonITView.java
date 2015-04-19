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

public class ItemTileNonITView extends ItemPanelDecorator implements ItemPanelParticipant,TypeItemTileView, ActionListener{

	private JPanel panNonIT;
	private JLabel lblType;
	private JLabel lblAssignee;
	private JLabel lblTypeText;
	private JLabel lblAssigneeText;
	
	

	public ItemTileNonITView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		panNonIT = new JPanel();
		panNonIT.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panNonIT.setBackground(Color.WHITE);
		/* Layout */
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		panNonIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00,grow][38.00,grow][100,grow][100][100][31.00]", "[][][17][][17][][]"));
		
		
		/* Label */
		
		lblType = new JLabel("Type:");
		panNonIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblTypeText = new JLabel("");
		panNonIT.add(lblTypeText, "cell 3 1 5 1");
		
		lblAssignee = new JLabel("Assignee: ");
		panNonIT.add(lblAssignee, "cell 1 5 2 1");
		
		lblAssigneeText = new JLabel("");
		panNonIT.add(lblAssigneeText, "cell 4 5 4 1");
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
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","Others"};
		
		panNonIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00,grow][38.00,grow][100,grow][100][100][31.00]", "[][][17][][17][][]"));
		
		
		/* Label */
		
		lblType = new JLabel("Type:");
		panNonIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblTypeText = new JLabel("");
		panNonIT.add(lblTypeText, "cell 3 1 5 1");
		
		
		lblAssignee = new JLabel("Assignee: ");
		panNonIT.add(lblAssignee, "cell 1 5 2 1");
		
		lblAssigneeText = new JLabel("");
		panNonIT.add(lblAssigneeText, "cell 4 5 4 1");
		addItemPanelReference.assignToQuad(panNonIT, 1);
		
	}

	@Override
	public Iterator retrieveInformation() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		// TODO Auto-generated method stub
		if(iter.hasNext()) lblAssigneeText.setText(iter.next().toString());
	}
	@Override
	public void setType(String type) {
		lblTypeText.setText(type);
	}
	
}
