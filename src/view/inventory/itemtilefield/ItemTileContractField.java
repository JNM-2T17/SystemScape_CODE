package view.inventory.itemtilefield;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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

import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import view.ErrorListenerFactory;
import view.Message;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;

public class ItemTileContractField extends ItemPanelDecorator implements ItemPanelParticipant{
	
	private JPanel panContract = null;
	
	private JLabel lblContract;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblMaintenanceCost;
	
	private JTextField tfMainCost;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	
	private JFrame parent;
	
	public ItemTileContractField(JFrame parent, ItemPanelTemplate addItemPanelReference) {
		super(addItemPanelReference);
		this.parent=parent;
		// TODO Auto-generated constructor stub
		panContract = new JPanel();
		panContract.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panContract.setBackground(Color.WHITE);
		
		panContract.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100.00,grow][51.00]", "[][][17][][17][][]"));
		
//		itemTileContractRepresentation = new JPanel();
		/* Labels */
		
		lblContract = new JLabel("Contract:");
		panContract.add(lblContract, "cell 0 0,alignx left");
		
		lblStart = new JLabel("Start:");
		panContract.add(lblStart, "flowx,cell 1 1");
		
		startDateChooser = new JDateChooser();
		startDateChooser.addFocusListener(ErrorListenerFactory.getListener(startDateChooser));
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(startDateChooser, "cell 2 1 3 1,growx,aligny center");
		
		lblEnd = new JLabel("End:");
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		endDateChooser = new JDateChooser();
		endDateChooser.addFocusListener(ErrorListenerFactory.getListener(endDateChooser));
		endDateChooser.setOpaque(false);
		endDateChooser.setDate(new Date());
		endDateChooser.setBorder(null);
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBackground(Color.WHITE);
		endDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(endDateChooser, "cell 2 3 3 1,growx,aligny center");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
//		lblMaintenanceCost.setFont(new Font("Tahoma", Font.PLAIN, 5));
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		
		/* Maintenance Cost */
		

		
		tfMainCost = new JTextField();
		tfMainCost.addFocusListener(ErrorListenerFactory.getListener(tfMainCost));
		panContract.add(tfMainCost, "cell 2 5 3 1,growx");
		tfMainCost.setColumns(10);
		
		addItemPanelReference.assignToQuad(panContract, 4);

	}
	
	@Override
	public void renderPanel()
	{
		renderItemTileContract();
		super.renderPanel();
	}
	
	public void renderItemTileContract()
	{
		panContract = new JPanel();
		panContract.setBorder(new LineBorder(new Color(30, 144, 255), 3, true));
		panContract.setBackground(Color.WHITE);
		
		panContract.setLayout(new MigLayout("", "[46.00][38.00][100,grow][100,grow][100.00,grow][51.00]", "[][][17][][17][][]"));
		
//		itemTileContractRepresentation = new JPanel();
		/* Labels */
		
		lblContract = new JLabel("Contract:");
		panContract.add(lblContract, "cell 0 0,alignx left");
		
		lblStart = new JLabel("Start:");
		panContract.add(lblStart, "flowx,cell 1 1");
		
		startDateChooser = new JDateChooser();
		startDateChooser.getDateEditor().getUiComponent().addFocusListener(ErrorListenerFactory.getListener(startDateChooser));
		startDateChooser.setOpaque(false);
		startDateChooser.setDate(new Date());
		startDateChooser.setBorder(null);
		startDateChooser.setDateFormatString("yyyy-MM-dd");
		startDateChooser.setBackground(Color.WHITE);
		startDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(startDateChooser, "cell 2 1 3 1,growx,aligny center");
		
		lblEnd = new JLabel("End:");
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		endDateChooser = new JDateChooser();
		endDateChooser.getDateEditor().getUiComponent().addFocusListener(ErrorListenerFactory.getListener(endDateChooser));
		endDateChooser.setOpaque(false);
		endDateChooser.setDate(new Date());
		endDateChooser.setBorder(null);
		endDateChooser.setDateFormatString("yyyy-MM-dd");
		endDateChooser.setBackground(Color.WHITE);
		endDateChooser.setPreferredSize(new Dimension(150, 30));
		panContract.add(endDateChooser, "cell 2 3 3 1,growx,aligny center");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		
		/* Maintenance Cost */
		
		addItemPanelReference.assignToQuad(panContract, 4);
		
		tfMainCost = new JTextField();
		tfMainCost.addFocusListener(ErrorListenerFactory.getListener(tfMainCost));
		panContract.add(tfMainCost, "cell 2 5 3 1,growx");
		tfMainCost.setColumns(10);

		
	}

	@Override
	public Iterator retrieveInformation() {
		// TODO Auto-generated method stub
		ArrayList infoList = new ArrayList(); 
		infoList.add(tfMainCost.getText());
		infoList.add(startDateChooser.getDate());
		infoList.add(endDateChooser.getDate());
		return infoList.iterator();
	}
	
	public void setContractStartDate(Date date) {
		startDateChooser.setDate(date);
	}
	
	public void setContractEndDate(Date date) {
		endDateChooser.setDate(date);
	}

	@Override
	public String checkInput() {
		String stat="";
		Date st = startDateChooser.getDate();
		Date end = endDateChooser.getDate();
		
		if(st.compareTo(end)>0){
			stat+="Contract start date must occur before the end date\n";
			endDateChooser.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			startDateChooser.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		try{
			float f=Float.parseFloat(tfMainCost.getText());
		}
		catch(Exception e){
			tfMainCost.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			stat+="Invalid maintenance cost\n";
		}
		return stat;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		tfMainCost.setText(iter.next().toString());
		/** TEMPORARILY DISABLED**/
//		startDateChooser.setDate((Date) iter.next());
//		endDateChooser.setDate((Date) iter.next());
	}
	
	
}
