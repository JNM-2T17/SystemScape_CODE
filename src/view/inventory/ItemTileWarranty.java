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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import view.Message;

import com.toedter.calendar.JDateChooser;

public class ItemTileWarranty extends ItemPanelDecorator implements ItemPanelParticipant{

	private JPanel panWarranty;
	
	private JLabel lblWarranty;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblDeliveryDate;
	
	private JDateChooser startWarrantyDate;
	private JDateChooser endWarrantyDate;
	
	private JFrame parent;
	
	public ItemTileWarranty(JFrame parent, ItemPanelTemplate addItemPanelReference) {
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
		panWarranty	 = new JPanel();
		panWarranty.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panWarranty.setBackground(Color.WHITE);
		
		panWarranty.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100,grow][31.00]", "[25][35][35][5][17][][]"));
		
		lblWarranty = new JLabel("Warranty:");
		panWarranty.add(lblWarranty, "cell 0 0,alignx left");
		
		lblDeliveryDate = new JLabel("Start:");
		panWarranty.add(lblDeliveryDate, "flowx,cell 1 1");
		
		startWarrantyDate = new JDateChooser();
		startWarrantyDate.setOpaque(false);
		startWarrantyDate.setDate(new Date());
		startWarrantyDate.setBorder(null);
		startWarrantyDate.setDateFormatString("yyyy-MM-dd");
		startWarrantyDate.setBackground(Color.WHITE);
		startWarrantyDate.setPreferredSize(new Dimension(150, 30));
		panWarranty.add(startWarrantyDate, "cell 2 1 2 1,growx,aligny center");
		
		
		lblEnd = new JLabel("End:");
		panWarranty.add(lblEnd, "cell 1 2,alignx left");
		
		endWarrantyDate = new JDateChooser();
		endWarrantyDate.setOpaque(false);
		endWarrantyDate.setDate(new Date());
		endWarrantyDate.setBorder(null);
		endWarrantyDate.setDateFormatString("yyyy-MM-dd");
		endWarrantyDate.setBackground(Color.WHITE);
		endWarrantyDate.setPreferredSize(new Dimension(150, 30));
		panWarranty.add(endWarrantyDate, "cell 2 2 2 1,growx,aligny center");
		
		
		addItemPanelReference.assignToQuad(panWarranty, 3);
		
	}
	
	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(startWarrantyDate.getDate());
		infoList.add(endWarrantyDate.getDate());
		return infoList.iterator();
	}

	@Override
	public boolean checkInput() {
		Date st = startWarrantyDate.getDate();
		Date end = endWarrantyDate.getDate();
		
		if(st.compareTo(end)>0){
			new Message(parent, Message.ERROR, "Warranty start date must occur before the end date");
			return false;
		}
		return true;
	}
	
}
