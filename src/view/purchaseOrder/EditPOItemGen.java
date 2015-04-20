package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.ItemData;
import net.miginfocom.swing.MigLayout;
import view.JTextFieldFilter;
import view.Message;
import view.PopUp;

import com.toedter.calendar.JDateChooser;

import controller.EmployeeController;
import controller.InventoryItemController;
import controller.PurchaseOrderController;
import model.Employee;
import model.InventoryItem;
import model.NonITAsset;
import model.SoftwareItem;

public class EditPOItemGen extends PopUp implements ActionListener, FocusListener {

    private JPanel panCenter, panContent, panSubmit;
    private JLabel lblInvoice, lblDeliveryDate;
    private JTextField txtInvoice;
    private JButton btnSubmit;

    private PurchaseOrderController poController;
    private JLabel lblAssiginee;
    private JComboBox cmbAssignee;
    private JDateChooser dateChooserDelivery;
    private JFrame parent;
    private JLabel lblLocation;
    private JComboBox cbxLocation;
    private JLabel lblStatus;
    private JComboBox cbxStatus;

    private ItemData itemData;
    private InventoryItemController inventoryItemController;
    private JLabel lblStartDate;
    private JDateChooser startDateChooser;
    private JLabel lblEndDate;
    private JDateChooser endDateChooser;

    public EditPOItemGen(JFrame parent, ItemData id, PurchaseOrderController poController) {
        super(parent);
        this.parent = parent;
        itemData = id;
        this.poController = poController;
        inventoryItemController = InventoryItemController.getInstance();
        this.addFocusListener(this);
        this.setUndecorated(true);

        panCenter = new JPanel();
        getContentPane().add(panCenter, BorderLayout.CENTER);

        panCenter.setBackground(Color.white);
        panCenter.setLayout(new BorderLayout(0, 0));
        panCenter.setSize(new Dimension(600, 400));
        panCenter.setPreferredSize(new Dimension(500, 235));

        panContent = new JPanel();
        panContent.setBackground(Color.white);
        panCenter.add(panContent, BorderLayout.CENTER);
        panContent.setLayout(new MigLayout("", "[grow][148.00,grow][grow][][]", "[][][45.00][37.00][][][][grow][grow][][][-44.00]"));

        lblInvoice = new JLabel("Invoice #:");
        lblInvoice.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblInvoice, "cell 0 1,alignx left");

        txtInvoice = new JTextField();
        txtInvoice.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(txtInvoice, "cell 1 1,growx");
        txtInvoice.setColumns(10);
        txtInvoice.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
        txtInvoice.addFocusListener(new FocusListener() {
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            @Override
            public void focusLost(FocusEvent e) {

            }

            @Override
            public void focusGained(FocusEvent e) {
                txtInvoice.setBorder(border);
            }
        });

        String[] types = {"IT Asset", "Non-IT Asset"};

        lblLocation = new JLabel("Location :");
        lblLocation.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblLocation, "cell 0 2,alignx left");

        cbxLocation = new JComboBox();
        cbxLocation.setBackground(Color.WHITE);
        cbxLocation.setModel(new DefaultComboBoxModel(new String[]{"1WS", "DAO"}));
        cbxLocation.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(cbxLocation, "cell 1 2,alignx left");

        lblStatus = new JLabel("Status :");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblStatus, "cell 0 3,alignx left");

        cbxStatus = new JComboBox();
        cbxStatus.setBackground(Color.WHITE);
        cbxStatus.setModel(new DefaultComboBoxModel(new String[]{"In Store", "In Use"}));
        cbxStatus.addItemListener(new ItemAssigneeChangeListener());
        cbxStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(cbxStatus, "cell 1 3,alignx left");

        lblDeliveryDate = new JLabel("Delivery Date :");
        lblDeliveryDate.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblDeliveryDate, "cell 0 4,alignx left");

        dateChooserDelivery = new JDateChooser();
        dateChooserDelivery.setDate(new Date());
        dateChooserDelivery.setDateFormatString("MMMM dd, yyyy");
        dateChooserDelivery.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(dateChooserDelivery, "cell 1 4,growx");

        lblAssiginee = new JLabel("Assignee :");
        lblAssiginee.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblAssiginee, "cell 0 5,alignx left");
        cmbAssignee = new JComboBox();
        populateCmbEmployee();
        cmbAssignee.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbAssignee.setPreferredSize(new Dimension(145, 32));
        cmbAssignee.setBackground(Color.WHITE);
        panContent.add(cmbAssignee, "cell 1 5,alignx left");
        
        lblStartDate = new JLabel("Start Date :");
        lblStartDate.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblStartDate, "cell 1 7");
        
        startDateChooser = new JDateChooser();
        startDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        startDateChooser.setDateFormatString("MMMM dd, yyyy\r\n");
        panContent.add(startDateChooser, "cell 2 7,grow");
        
        lblEndDate = new JLabel("End Date :");
        lblEndDate.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(lblEndDate, "cell 1 8");
        
        endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("MMMM dd, yyyy");
        endDateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        panContent.add(endDateChooser, "cell 2 8,grow");

        panSubmit = new JPanel();
        panSubmit.setBackground(Color.white);
        panCenter.add(panSubmit, BorderLayout.SOUTH);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 14));
        btnSubmit.addActionListener(this);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(new Color(32, 130, 213));
        panSubmit.add(btnSubmit);

        setContent(panCenter);
        getClose().addActionListener(this);

        this.setVisible(true);
    }
    
    public void populateCmbEmployee()
   	{
   		cmbAssignee.addItem("None");
   		EmployeeController ec = EmployeeController.getInstance();
   		Iterator<Employee> eList = ec.getAll();
   		while(eList.hasNext())
   		{
   			Employee e = eList.next();
   			cmbAssignee.addItem(e.getName());
   		}
   	}
       
    
    public String checkFields() {
        String error = "";
        Border border = BorderFactory.createLineBorder(Color.RED, 2);

        if (txtInvoice.getText().equals("")) {
            error += "Invoice Number Field is empty \n";
            txtInvoice.setBorder(border);
        }

        return error;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

        if (e.getSource() == getClose()) {
            this.setVisible(false);
            this.dispose();
        } else if (e.getSource() == btnSubmit) {

            String error = checkFields();

            if (error.equals("") == true) {
                InventoryItem ii = new InventoryItem(0, itemData.getName(), itemData.getDescription(), itemData.getUnitPrice(), txtInvoice.getText(),
                        (String) cbxLocation.getSelectedItem(), (String) cbxStatus.getSelectedItem(), "Others");
                inventoryItemController.addInventoryItem(ii);
                poController.incQtyRcvd(itemData);
                poController.editPurchaseOrder(poController.getPurchaseOrder());
                this.setVisible(false);
                this.dispose();
            } else if (error.equals("") == false) {
                Message msg = new Message(parent, Message.ERROR, error);
            } else {
                clearFields();
            }
        }

    }

    class ItemAssigneeChangeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object item = event.getItem();
				
				if(item.equals("In Store"))
				{
					lblAssiginee.setVisible(false);
					cmbAssignee.setVisible(false);
					lblStartDate.setVisible(false);
					lblEndDate.setVisible(false);
					startDateChooser.setVisible(false);
					endDateChooser.setVisible(false);
				}
				else if(item.equals("In Use"))
				{
					lblAssiginee.setVisible(true);
					cmbAssignee.setVisible(true);
					lblStartDate.setVisible(true);
					lblEndDate.setVisible(true);
					startDateChooser.setVisible(true);
					endDateChooser.setVisible(true);
				}
				
			}
		}   
	}
    public void clearFields() {
        txtInvoice.setText("");
        cmbAssignee.setSelectedIndex(0);
    }

    /**
     * **parse string to integer*****
     */
    public int parseStringInt(String quantity) {
        if (quantity.equals("") == false) {
            return Integer.parseInt(quantity);
        }
        return 0;
    }

    /**
     * **parse string to float***
     */
    public double parseStringFloat(String price) {
        if (price.equals("") == false) {
            return Float.parseFloat(price);
        }
        return 0.0;
    }

    @Override
    public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        JFrame f = (JFrame) e.getSource();
        f.toFront();
    }
}
