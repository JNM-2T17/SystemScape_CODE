package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.border.LineBorder;

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
import model.ITAsset;
import model.InventoryItem;

public class EditPOItemHard extends PopUp implements ActionListener, FocusListener {

    private JPanel panCenter, panContent, panSubmit;
    private JLabel lblInvoice, lblDeliveryDate;
    private JTextField txtInvoice;
    private JButton btnSubmit;

    private PurchaseOrderController poController;
    private JLabel lblAssiginee;
    private JComboBox cmbAssignee;
    private JDateChooser dateChooserDelivery;
    private JLabel lblAsset;
    private JTextField txtAsset;
    private JLabel lblService;
    private JTextField txtService;
    private JPanel panWarranty;
    private JLabel lblWarranty;
    private JLabel lblWarrantyStart;
    private JDateChooser dateChooserWarrantyStart;
    private JLabel lblWarrantyEnd;
    private JDateChooser dateChooserWarrantyEnd;
    private JPanel panContract;
    private JLabel lblContract;
    private JLabel lblContractStart;
    private JDateChooser dateChooserContractStart;
    private JLabel lblContractEnd;
    private JDateChooser dateChooserContractEnd;
    private JLabel lblMaintenance;
    private JTextField txtMaintenance;
    private JFrame parent;
    private JLabel lblLocation;
    private JComboBox cbxLocation;
    private JLabel lblStatus;
    private JComboBox cbxStatus;
    private ItemData itemData;
    private InventoryItemController inventoryItemController;
    private JLabel lblType;
    private JComboBox cmbType;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;

    public EditPOItemHard(JFrame parent, ItemData id, PurchaseOrderController poController) {
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
        panCenter.setSize(new Dimension(500, 430));
        panCenter.setPreferredSize(new Dimension(500, 470));

        panContent = new JPanel();
        panContent.setBackground(Color.white);
        panCenter.add(panContent, BorderLayout.CENTER);
        panContent.setLayout(new MigLayout("", "[][grow][188.00,grow][grow]", "[][][45.00][37.00][][][][][][][][pref!,grow][grow][][][][][][-29.00][14.00][][][][][][-44.00]"));

        lblInvoice = new JLabel("Invoice #:");
        lblInvoice.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblInvoice, "cell 1 1,alignx left");

        txtInvoice = new JTextField();
        txtInvoice.setMinimumSize(new Dimension(120, 20));
        txtInvoice.setPreferredSize(new Dimension(120, 20));
        panContent.add(txtInvoice, "cell 2 1,growx");
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

        lblDeliveryDate = new JLabel("Delivery Date :");
        lblDeliveryDate.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblDeliveryDate, "cell 1 2,alignx left");

        dateChooserDelivery = new JDateChooser();
        dateChooserDelivery.setFont(new Font("Arial", Font.PLAIN, 11));
        dateChooserDelivery.setPreferredSize(new Dimension(120, 20));
        dateChooserDelivery.setDate(new Date());
        dateChooserDelivery.setDateFormatString("MMMM dd, yyyy");
        panContent.add(dateChooserDelivery, "cell 2 2,growx");

        lblAsset = new JLabel("Asset Tag :");
        lblAsset.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblAsset, "cell 1 3,alignx left");

        txtAsset = new JTextField();
        txtAsset.setFont(new Font("Arial", Font.PLAIN, 11));
        txtAsset.setPreferredSize(new Dimension(120, 20));
        panContent.add(txtAsset, "cell 2 3,growx");
        txtAsset.setColumns(10);
        txtAsset.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
        txtAsset.addFocusListener(new FocusListener() {
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            @Override
            public void focusLost(FocusEvent e) {

            }

            @Override
            public void focusGained(FocusEvent e) {
                txtAsset.setBorder(border);
            }
        });

        lblService = new JLabel("Service Tag :");
        lblService.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblService, "cell 1 4,alignx left");

        txtService = new JTextField();
        txtService.setFont(new Font("Arial", Font.PLAIN, 11));
        txtService.setPreferredSize(new Dimension(120, 20));
        panContent.add(txtService, "cell 2 4,growx");
        txtService.setColumns(10);
        txtService.addFocusListener(new FocusListener() {
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            @Override
            public void focusLost(FocusEvent e) {

            }

            @Override
            public void focusGained(FocusEvent e) {
                txtService.setBorder(border);
            }
        });

        lblLocation = new JLabel("Location :");
        lblLocation.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblLocation, "cell 1 5,alignx left");

        cbxLocation = new JComboBox();
        cbxLocation.setBackground(Color.WHITE);
        cbxLocation.setModel(new DefaultComboBoxModel(new String[]{"1WS", "DAO"}));
        cbxLocation.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(cbxLocation, "cell 2 5,alignx left");

        lblStatus = new JLabel("Status :");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblStatus, "cell 1 6,alignx left");

        cbxStatus = new JComboBox();
        cbxStatus.setBackground(Color.WHITE);
        cbxStatus.setFont(new Font("Arial", Font.PLAIN, 11));
        cbxStatus.setModel(new DefaultComboBoxModel(new String[]{"In Store", "In Use"}));
        cbxStatus.addItemListener(new ItemAssigneeChangeListener());
        panContent.add(cbxStatus, "cell 2 6,alignx left");

        lblType = new JLabel("Type :");
        lblType.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblType, "cell 1 7,alignx left");

        cmbType = new JComboBox();
        cmbType.addItemListener(new ItemChangeListener());
        cmbType.setModel(new DefaultComboBoxModel(new String[]{"IT", "Non-IT"}));
        cmbType.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(cmbType, "cell 2 7,growx");

        lblAssiginee = new JLabel("Assginee :");
        lblAssiginee.setFont(new Font("Arial", Font.PLAIN, 11));
        panContent.add(lblAssiginee, "cell 1 9,alignx left");

        cmbAssignee = new JComboBox();
        cmbAssignee.setFont(new Font("Arial", Font.PLAIN, 11));
        cmbAssignee.setPreferredSize(new Dimension(120, 32));
        cmbAssignee.setBackground(Color.WHITE);
        panContent.add(cmbAssignee, "cell 2 9,growx");
        populateCmbEmployee();
        
        lblStartDate = new JLabel("Start Date :");
        panContent.add(lblStartDate, "cell 2 11");
        
        startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("MMMM dd, yyyy");
        panContent.add(startDateChooser, "cell 3 11,grow");
        
        lblEndDate = new JLabel("End Date :");
        panContent.add(lblEndDate, "cell 2 12");
        
        endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("MMMM dd, yyyy");
        panContent.add(endDateChooser, "cell 3 12,grow");

        panWarranty = new JPanel();
        panWarranty.setBorder(new LineBorder(new Color(0, 102, 204), 1, true));
        panWarranty.setBackground(Color.WHITE);
        panContent.add(panWarranty, "cell 1 14 2 1,growx");
        GridBagLayout gbl_panWarranty = new GridBagLayout();
        gbl_panWarranty.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panWarranty.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panWarranty.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panWarranty.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panWarranty.setLayout(gbl_panWarranty);

        lblWarranty = new JLabel("Warranty :");
        lblWarranty.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblWarranty = new GridBagConstraints();
        gbc_lblWarranty.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarranty.gridx = 1;
        gbc_lblWarranty.gridy = 1;
        panWarranty.add(lblWarranty, gbc_lblWarranty);

        lblWarrantyStart = new JLabel("Start :");
        lblWarrantyStart.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblWarrantyStart = new GridBagConstraints();
        gbc_lblWarrantyStart.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarrantyStart.gridx = 2;
        gbc_lblWarrantyStart.gridy = 2;
        panWarranty.add(lblWarrantyStart, gbc_lblWarrantyStart);

        dateChooserWarrantyStart = new JDateChooser();
        dateChooserWarrantyStart.setFont(new Font("Arial", Font.PLAIN, 11));
        dateChooserWarrantyStart.setPreferredSize(new Dimension(140, 20));
        dateChooserWarrantyStart.setDateFormatString("MMMM dd, yyyy");
        dateChooserWarrantyStart.setDate(new Date());
        GridBagConstraints gbc_dateChooserWarrantyStart = new GridBagConstraints();
        gbc_dateChooserWarrantyStart.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserWarrantyStart.fill = GridBagConstraints.BOTH;
        gbc_dateChooserWarrantyStart.gridx = 3;
        gbc_dateChooserWarrantyStart.gridy = 2;
        panWarranty.add(dateChooserWarrantyStart, gbc_dateChooserWarrantyStart);

        lblWarrantyEnd = new JLabel("End :");
        lblWarrantyEnd.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblWarrantyEnd = new GridBagConstraints();
        gbc_lblWarrantyEnd.insets = new Insets(0, 0, 5, 5);
        gbc_lblWarrantyEnd.gridx = 2;
        gbc_lblWarrantyEnd.gridy = 3;
        panWarranty.add(lblWarrantyEnd, gbc_lblWarrantyEnd);

        dateChooserWarrantyEnd = new JDateChooser();
        dateChooserWarrantyEnd.setFont(new Font("Arial", Font.PLAIN, 11));
        dateChooserWarrantyEnd.setPreferredSize(new Dimension(140, 20));
        dateChooserWarrantyEnd.setDate(new Date());
        dateChooserWarrantyEnd.setDateFormatString("MMMM dd, yyyy");
        GridBagConstraints gbc_dateChooserWarrantyEnd = new GridBagConstraints();
        gbc_dateChooserWarrantyEnd.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserWarrantyEnd.fill = GridBagConstraints.BOTH;
        gbc_dateChooserWarrantyEnd.gridx = 3;
        gbc_dateChooserWarrantyEnd.gridy = 3;
        panWarranty.add(dateChooserWarrantyEnd, gbc_dateChooserWarrantyEnd);

        panContract = new JPanel();
        panContract.setBorder(new LineBorder(new Color(0, 102, 204), 1, true));
        panContract.setBackground(new Color(255, 255, 255));
        panContent.add(panContract, "cell 1 16 2 1,growx");
        GridBagLayout gbl_panContract = new GridBagLayout();
        gbl_panContract.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panContract.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panContract.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panContract.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        panContract.setLayout(gbl_panContract);

        lblContract = new JLabel("Contract :");
        lblContract.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblContract = new GridBagConstraints();
        gbc_lblContract.insets = new Insets(0, 0, 5, 5);
        gbc_lblContract.gridx = 1;
        gbc_lblContract.gridy = 1;
        panContract.add(lblContract, gbc_lblContract);

        lblContractStart = new JLabel("Start :");
        lblContractStart.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblContractStart = new GridBagConstraints();
        gbc_lblContractStart.anchor = GridBagConstraints.WEST;
        gbc_lblContractStart.insets = new Insets(0, 0, 5, 5);
        gbc_lblContractStart.gridx = 2;
        gbc_lblContractStart.gridy = 2;
        panContract.add(lblContractStart, gbc_lblContractStart);

        dateChooserContractStart = new JDateChooser();
        dateChooserContractStart.setFont(new Font("Arial", Font.PLAIN, 11));
        dateChooserContractStart.setPreferredSize(new Dimension(140, 20));
        dateChooserContractStart.setDateFormatString("MMMM dd, yyyy");
        dateChooserContractStart.setDate(new Date());
        GridBagConstraints gbc_dateChooserContractStart = new GridBagConstraints();
        gbc_dateChooserContractStart.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserContractStart.fill = GridBagConstraints.BOTH;
        gbc_dateChooserContractStart.gridx = 3;
        gbc_dateChooserContractStart.gridy = 2;
        panContract.add(dateChooserContractStart, gbc_dateChooserContractStart);

        lblContractEnd = new JLabel("End :");
        lblContractEnd.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblContractEnd = new GridBagConstraints();
        gbc_lblContractEnd.anchor = GridBagConstraints.WEST;
        gbc_lblContractEnd.insets = new Insets(0, 0, 5, 5);
        gbc_lblContractEnd.gridx = 2;
        gbc_lblContractEnd.gridy = 3;
        panContract.add(lblContractEnd, gbc_lblContractEnd);

        dateChooserContractEnd = new JDateChooser();
        dateChooserContractEnd.setFont(new Font("Arial", Font.PLAIN, 11));
        dateChooserContractEnd.setPreferredSize(new Dimension(140, 20));
        dateChooserContractEnd.setDateFormatString("MMMM dd, yyyy");
        dateChooserContractEnd.setDate(new Date());
        GridBagConstraints gbc_dateChooserContractEnd = new GridBagConstraints();
        gbc_dateChooserContractEnd.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooserContractEnd.fill = GridBagConstraints.BOTH;
        gbc_dateChooserContractEnd.gridx = 3;
        gbc_dateChooserContractEnd.gridy = 3;
        panContract.add(dateChooserContractEnd, gbc_dateChooserContractEnd);

        lblMaintenance = new JLabel("Maintenance Cost :");
        lblMaintenance.setFont(new Font("Arial", Font.PLAIN, 11));
        GridBagConstraints gbc_lblMaintenance = new GridBagConstraints();
        gbc_lblMaintenance.anchor = GridBagConstraints.EAST;
        gbc_lblMaintenance.insets = new Insets(0, 0, 5, 5);
        gbc_lblMaintenance.gridx = 2;
        gbc_lblMaintenance.gridy = 4;
        panContract.add(lblMaintenance, gbc_lblMaintenance);

        txtMaintenance = new JTextField();
        txtMaintenance.setFont(new Font("Arial", Font.PLAIN, 11));
        txtMaintenance.setMinimumSize(new Dimension(150, 20));
        txtMaintenance.setPreferredSize(new Dimension(150, 20));
        txtMaintenance.setColumns(10);
        txtMaintenance.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
        txtMaintenance.addFocusListener(new FocusListener() {
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            @Override
            public void focusLost(FocusEvent e) {

            }

            @Override
            public void focusGained(FocusEvent e) {
                txtMaintenance.setBorder(border);
            }
        });

        GridBagConstraints gbc_txtMaintenance = new GridBagConstraints();
        gbc_txtMaintenance.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMaintenance.insets = new Insets(0, 0, 5, 5);
        gbc_txtMaintenance.gridx = 3;
        gbc_txtMaintenance.gridy = 4;
        panContract.add(txtMaintenance, gbc_txtMaintenance);

        panSubmit = new JPanel();
        panSubmit.setBackground(Color.white);
        panCenter.add(panSubmit, BorderLayout.SOUTH);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 11));
        btnSubmit.addActionListener(this);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(new Color(32, 130, 213));
        panSubmit.add(btnSubmit);

        setContent(panCenter);
        getClose().addActionListener(this);

        this.setVisible(true);
    }

    public void populateCmbEmployee() {
        cmbAssignee.addItem("None");
        EmployeeController ec = EmployeeController.getInstance();
        Iterator<Employee> eList = ec.getAll();
        while (eList.hasNext()) {
            Employee e = eList.next();
            cmbAssignee.addItem(e.getName());
        }
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
                /**
                 * *insert code statements here to add the information of a
                 * hardware item**
                 */
                InventoryItem ii = new ITAsset(0, itemData.getName(), itemData.getDescription(), itemData.getUnitPrice(), txtInvoice.getText(),
                        (String) cbxLocation.getSelectedItem(), (String) cbxStatus.getSelectedItem(), (String) cmbType.getSelectedItem(), Integer.parseInt(txtAsset.getText()),
                        txtService.getText(), dateChooserDelivery.getDate(), dateChooserWarrantyStart.getDate(), dateChooserWarrantyEnd.getDate(),
                        dateChooserContractStart.getDate(), dateChooserContractEnd.getDate(), Float.parseFloat(txtMaintenance.getText()));

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

    public void clearFields() {
        txtInvoice.setText("");
        txtAsset.setText("");
        txtService.setText("");
        txtMaintenance.setText("");
        cmbAssignee.setSelectedIndex(0);
    }

    public String checkFields() {
        String error = "";
        Border border = BorderFactory.createLineBorder(Color.RED, 2);

        if (txtInvoice.getText().equals("")) {
            error += "Invoice Number Field is empty \n";
            txtInvoice.setBorder(border);
        }
        if (txtAsset.getText().equals("")) {
            error += "Item Asset Tag Field is empty \n";
            txtAsset.setBorder(border);
        }
        if (txtService.getText().equals("")) {
            error += "Item Service Tag is empty \n";
            txtService.setBorder(border);
        }
        if (txtMaintenance.getText().equals("")) {
            error += "Maintenance Cost Field is empty";
            txtMaintenance.setBorder(border);
        }

        return error;
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
    
    class ItemChangeListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				Object item = event.getItem();
				// DEV do something with object to get the address of the supplier :D 
				if(item.equals("Non-IT"))
				{
					lblAsset.setVisible(false);
					txtAsset.setVisible(false);
					lblService.setVisible(false);
					txtService.setVisible(false);
					
				}
				else
				{
					lblAsset.setVisible(true);
					txtAsset.setVisible(true);
					lblService.setVisible(true);
					txtService.setVisible(true);
				}
				
			}
		}   
	}
}
