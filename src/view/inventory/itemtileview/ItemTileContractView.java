package view.inventory.itemtileview;

import javafx.util.converter.DateStringConverter;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import view.Message;
import view.inventory.ItemPanelDecorator;
import view.inventory.ItemPanelParticipant;
import view.inventory.ItemPanelTemplate;

public class ItemTileContractView extends ItemPanelDecorator implements ItemPanelParticipant{
	
	private JPanel panContract = null;
	
	private JLabel lblContract;
	private JLabel lblStart;
	private JLabel lblEnd;
	private JLabel lblMaintenanceCost;
	
	private JFrame parent;
	private JLabel lblStartText;
	private JLabel lblEndText;
	private JLabel lblMainCostText;
	
	public ItemTileContractView(JFrame parent, ItemPanelTemplate addItemPanelReference) {
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
		
		lblStartText = new JLabel("");
		panContract.add(lblStartText, "cell 2 1 3 1");
		
		lblEnd = new JLabel("End:");
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		lblEndText = new JLabel("");
		panContract.add(lblEndText, "cell 2 3 3 1");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
//		lblMaintenanceCost.setFont(new Font("Tahoma", Font.PLAIN, 5));
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		addItemPanelReference.assignToQuad(panContract, 4);
		
		lblMainCostText = new JLabel("");
		panContract.add(lblMainCostText, "cell 2 5 3 1");

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
		
		lblStartText = new JLabel("");
		panContract.add(lblStartText, "cell 2 1 3 1");
		
		lblEnd = new JLabel("End:");
		panContract.add(lblEnd, "cell 1 3,alignx left");
		
		lblEndText = new JLabel("");
		panContract.add(lblEndText, "cell 2 3 3 1");
		
		lblMaintenanceCost = new JLabel("Maintenance Cost:");
//		lblMaintenanceCost.setFont(new Font("Tahoma", Font.PLAIN, 5));
		panContract.add(lblMaintenanceCost, "flowx,cell 0 5 2 1");
		
		addItemPanelReference.assignToQuad(panContract, 4);
		
		lblMainCostText = new JLabel("");
		panContract.add(lblMainCostText, "cell 2 5 3 1");

		
		addItemPanelReference.assignToQuad(panContract, 4);
		
	}

	@Override
	public Iterator retrieveInformation() {
		return null;
	}
	
	public void setContractStartDate(Date date) {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		lblStartText.setText(formattedDate);
	}
	
	public void setContractEndDate(Date date) {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(date);
		lblStartText.setText(formattedDate);
	}

	@Override
	public boolean checkInput() {
		return true;
	}

	@Override
	public void loadPresets(Iterator iter) {
		// TODO Auto-generated method stub
		lblMainCostText.setText(iter.next().toString());
		/** TEMPORARILY DISABLED**/
//		startDateChooser.setDate((Date) iter.next());
//		endDateChooser.setDate((Date) iter.next());
	}
	
	
}
