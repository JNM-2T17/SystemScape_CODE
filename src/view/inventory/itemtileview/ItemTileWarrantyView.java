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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import view.Message;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;

import com.toedter.calendar.JDateChooser;

public class ItemTileWarrantyView extends ItemPanelDecorator implements ItemPanelParticipant{

	private JPanel panWarranty;
	
	private JLabel lblWarranty;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblDeliveryDate;
	
	private JFrame parent;
	private JLabel lblStartText;
	private JLabel lblEndText;
	
	public ItemTileWarrantyView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		// TODO Auto-generated constructor stub
		this.parent=parent;
		panWarranty	 = new JPanel();
		panWarranty.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panWarranty.setBackground(Color.WHITE);
		
		panWarranty.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100,grow][31.00]", "[][][17][][17][][]"));
		
		lblWarranty = new JLabel("Warranty:");
		panWarranty.add(lblWarranty, "cell 0 0,alignx left");
		
		lblDeliveryDate = new JLabel("Start:");
		panWarranty.add(lblDeliveryDate, "flowx,cell 1 1");
		
		lblStartText = new JLabel("");
		panWarranty.add(lblStartText, "cell 2 1 2 1");
		
		
		lblEnd = new JLabel("End:");
		panWarranty.add(lblEnd, "cell 1 3,alignx left");
		
		lblEndText = new JLabel("");
		panWarranty.add(lblEndText, "cell 2 3 2 1,alignx left");
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
		
		panWarranty.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100,grow][31.00]", "[][][17][][17][][]"));
		
		lblWarranty = new JLabel("Warranty:");
		panWarranty.add(lblWarranty, "cell 0 0,alignx left");
		
		lblDeliveryDate = new JLabel("Start:");
		panWarranty.add(lblDeliveryDate, "flowx,cell 1 1");
		
		lblStartText = new JLabel("");
		panWarranty.add(lblStartText, "cell 2 1 2 1");
		
		
		lblEnd = new JLabel("End:");
		panWarranty.add(lblEnd, "cell 1 3,alignx left");
		
		lblEndText = new JLabel("");
		panWarranty.add(lblEndText, "cell 2 3 2 1,alignx left");
		
		addItemPanelReference.assignToQuad(panWarranty, 3);
		
	}
	
	@Override
	public Iterator retrieveInformation() {
		return null;
	}
	
	/**
	 * Sets the start date of the warranty in the JDateChooser
	 * @param date
	 */
	public void setWarrantyStartDate(Date date)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		lblStartText.setText(formattedDate);
	}
	
	/**
	 * Sets the end date of the warranty in the JDateChooser
	 * @param date
	 */
	public void setWarrantyEndDate(Date date)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		lblEndText.setText(formattedDate);
	}

	@Override
	public boolean checkInput() {
		
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(iter.next().toString());
		lblStartText.setText(formattedDate);
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		formattedDate = formatter.format(iter.next().toString());
		lblEndText.setText(formattedDate);
	}
	
	
}
