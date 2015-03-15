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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

public class ItemTileNonIT extends ItemPanelDecorator implements ItemPanelParticipant, ActionListener{

	private JPanel panNonIT;
	private JLabel lblType;
	private JLabel lblDeliveryDate;
	private JLabel lblAssignee;
	
	private JComboBox cbType;
	private JDateChooser deliveryDateChooser;
	private JComboBox cbAssignee;
	
	

	public ItemTileNonIT(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
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
		String typeStrings[] = {"IT Assets","Non-IT Assets","Software","General"};
		
		panNonIT.setLayout(new MigLayout("", "[46.00][38.00][38.00][38.00,grow][38.00,grow][100,grow][100][100][31.00]", "[][][17][grow][17][][]"));
		
		
		/* Label */
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Arial", Font.BOLD, 20));
		panNonIT.add(lblType, "cell 1 1 2 1,alignx left");
		
		lblDeliveryDate = new JLabel("Delivery Date:");
		lblDeliveryDate.setFont(new Font("Arial", Font.BOLD, 20));
		panNonIT.add(lblDeliveryDate, "flowx,cell 1 3 4 1");
		
		/* Type Combo Box */
		
		cbType = new JComboBox(typeStrings);
		cbType.setSelectedItem("Non-IT Assets");
		cbType.setBackground(Color.white);
		cbType.setFont(new Font("Arial", Font.BOLD, 20));
		cbType.addActionListener(this);
		panNonIT.add(cbType, "cell 3 1 5 1,growx");
		

		
		deliveryDateChooser = new JDateChooser();
		deliveryDateChooser.setOpaque(false);
		deliveryDateChooser.setDate(new Date());
		deliveryDateChooser.setBorder(null);
		deliveryDateChooser.setFont(new Font("Arial", Font.BOLD, 20));
		deliveryDateChooser.setDateFormatString("yyyy-MM-dd");
		deliveryDateChooser.setBackground(Color.WHITE);
		deliveryDateChooser.setPreferredSize(new Dimension(150, 30));
		panNonIT.add(deliveryDateChooser, "cell 5 3 3 1,growx,aligny center");
		
		lblAssignee = new JLabel("Assignee: ");
		lblAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		panNonIT.add(lblAssignee, "cell 1 5 2 1");
		
		cbAssignee = new JComboBox();
		cbAssignee.setFont(new Font("Arial", Font.BOLD, 20));
		cbAssignee.setModel(new DefaultComboBoxModel(new String[] { "Shayane Tan",
				"Rissa Quindoza", "Gio Velez" }));
		cbAssignee.setBackground(Color.WHITE);
		panNonIT.add(cbAssignee, "cell 3 5 5 1,growx");
		addItemPanelReference.assignToQuad(panNonIT, 1);
		
	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(deliveryDateChooser.getDate());
		infoList.add(cbAssignee.getSelectedItem().toString());
		return infoList.iterator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cbType)
		{
			PanelRegistry.getInstance().setCurrentType(cbType.getSelectedItem().toString());
		}
	}

	@Override
	public boolean checkInput() {
		// TODO Auto-generated method stub
		return true;
	}
}
