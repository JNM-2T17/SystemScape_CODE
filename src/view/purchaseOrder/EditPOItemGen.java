package view.purchaseOrder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        panContent.setLayout(new MigLayout("", "[grow][188.00,grow][][][]", "[][][45.00][37.00][][][][][][][][-44.00]"));

        lblInvoice = new JLabel("Invoice #:");
        lblInvoice.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(lblInvoice, "cell 0 1,alignx left");

        txtInvoice = new JTextField();
        txtInvoice.setFont(new Font("Arial", Font.PLAIN, 18));
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
        lblLocation.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(lblLocation, "cell 0 2,alignx left");

        cbxLocation = new JComboBox();
        cbxLocation.setBackground(Color.WHITE);
        cbxLocation.setModel(new DefaultComboBoxModel(new String[]{"1WS", "DAO"}));
        cbxLocation.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(cbxLocation, "cell 1 2,alignx left");

        lblStatus = new JLabel("Status :");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(lblStatus, "cell 0 3,alignx left");

        cbxStatus = new JComboBox();
        cbxStatus.setBackground(Color.WHITE);
        cbxStatus.setModel(new DefaultComboBoxModel(new String[]{"In Store", "In Use"}));
        cbxStatus.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(cbxStatus, "cell 1 3,alignx left");

        lblDeliveryDate = new JLabel("Delivery Date :");
        lblDeliveryDate.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(lblDeliveryDate, "cell 0 4,alignx left");

        dateChooserDelivery = new JDateChooser();
        dateChooserDelivery.setDate(new Date());
        dateChooserDelivery.setDateFormatString("MMMM dd, yyyy");
        dateChooserDelivery.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(dateChooserDelivery, "cell 1 4,growx");

        lblAssiginee = new JLabel("Assginee :");
        lblAssiginee.setFont(new Font("Arial", Font.PLAIN, 18));
        panContent.add(lblAssiginee, "cell 0 5,alignx left");
        cmbAssignee = new JComboBox();
        populateCmbEmployee();
        cmbAssignee.setFont(new Font("Arial", Font.PLAIN, 18));
        cmbAssignee.setPreferredSize(new Dimension(185, 32));
        cmbAssignee.setBackground(Color.WHITE);
        panContent.add(cmbAssignee, "cell 1 5,alignx left");

        panSubmit = new JPanel();
        panSubmit.setBackground(Color.white);
        panCenter.add(panSubmit, BorderLayout.SOUTH);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
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

                /**
                 * *insert code statements here to add the information of a
                 * software item**
                 */
//                InventoryItem ii = new NonITAsset(0, itemData.getName(), itemData.getDescription(), itemData.getUnitPrice(), txtInvoice.getText(),
//                        (String) cbxLocation.getSelectedItem(), (String) cbxStatus.getSelectedItem(), "Others");
//                inventoryItemController.addInventoryItem(ii);
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
