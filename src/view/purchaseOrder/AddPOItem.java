package view.purchaseOrder;

import controller.InventoryItemController;
import controller.ItemDataController;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.ItemData;
import net.miginfocom.swing.MigLayout;
import view.ErrorListenerFactory;
import view.JTextFieldFilter;
import view.Message;
import view.PopUp;
import controller.PurchaseOrderController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import model.InventoryItem;

public class AddPOItem extends PopUp implements ActionListener, FocusListener {

    private JPanel panHeader, panCenter, panClose, panContent, panFooter,
            panWest, panEast, panSubmit;
    private JLabel lblItem, lblAmount, lblAmountValue, lblQuantity,
            lblDescription, lblPrice;
    private JTextArea txtDescription;
    private JTextField txtQuantity, txtPrice;
    private JButton btnSubmit;
    private JScrollPane scrollPane;

    private PurchaseOrderController poController;
    private InventoryItemController inventoryItemController;
    private ItemDataController itemDataController;
    private DefaultComboBoxModel specificItems;

    private JFrame parent;
    private DecimalFormat df;
    private SimpleDateFormat dateFormat;
    private String sDate;
    private JComboBox cmbItem;
    private String type;

    public AddPOItem(JFrame parent, String type,
            PurchaseOrderController poController) {

    	super(parent);
        this.parent = parent;
        this.poController = poController;
        this.addFocusListener(this);
        this.setUndecorated(true);
        inventoryItemController = InventoryItemController.getInstance();
        itemDataController = ItemDataController.getInstance();
        this.type = type;
        panCenter = new JPanel();
        getContentPane().add(panCenter, BorderLayout.CENTER);

        df = new DecimalFormat("#,###,###,###,##0.00");
        df.setMaximumFractionDigits(2);
        dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        panCenter.setBackground(Color.white);
        panCenter.setLayout(new BorderLayout(0, 0));
        panCenter.setSize(new Dimension(500, 400));
        panCenter.setPreferredSize(new Dimension(500, 400));

        panContent = new JPanel();
        panContent.setBackground(Color.white);
        panCenter.add(panContent, BorderLayout.CENTER);
        panContent.setLayout(new MigLayout("", "[grow][188.00,grow][][][]", "[][][][grow][][][][][][][][]"));

        lblItem = new JLabel("Name :");
        panContent.add(lblItem, "cell 0 1,alignx left");

        cmbItem = new JComboBox();
        cmbItem.setBackground(Color.WHITE);
        cmbItem.setEditable(true);
        cmbItem.addItemListener(new AddPOItem.ItemChangeListener());
        populateItems();
        panContent.add(cmbItem, "cell 1 1,growx");

        lblDescription = new JLabel("Item Description :");
        panContent.add(lblDescription, "cell 0 2,alignx left");

        scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(Color.white);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        panContent.add(scrollPane, "cell 1 3,growy");

        txtDescription = new JTextArea("");
        txtDescription.addFocusListener(ErrorListenerFactory
                .getListener(txtDescription));
        txtDescription.setLineWrap(true);
        scrollPane.setViewportView(txtDescription);

        String[] types = {"IT Asset", "Non-IT Asset"};

        lblQuantity = new JLabel("Quantity :");
        panContent.add(lblQuantity, "cell 0 6,alignx left");

        txtQuantity = new JTextField();
        txtQuantity.addFocusListener(ErrorListenerFactory
                .getListener(txtQuantity));
        txtQuantity.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC));
        txtQuantity.setPreferredSize(new Dimension(10, 25));
        txtQuantity.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int quantity = parseStringInt(txtQuantity.getText());
                float price = (float) parseStringFloat(txtPrice.getText());
                float result = quantity * price;
                lblAmountValue.setText(String.valueOf(df.format(result)));
            }
        });
        panContent.add(txtQuantity, "cell 1 6");
        txtQuantity.setColumns(10);

        lblPrice = new JLabel("Unit Price :");
        panContent.add(lblPrice, "cell 0 7");

        txtPrice = new JTextField("");
        txtPrice.addFocusListener(ErrorListenerFactory.getListener(txtPrice));
        txtPrice.setDocument(new JTextFieldFilter(JTextFieldFilter.FLOAT));
        txtPrice.setPreferredSize(new Dimension(10, 25));
        txtPrice.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int quantity = parseStringInt(txtQuantity.getText());
                float price = (float) parseStringFloat(txtPrice.getText());
                float result = quantity * price;
                lblAmountValue.setText(String.valueOf(result));
            }
        });
        panContent.add(txtPrice, "cell 1 7");
        txtPrice.addActionListener(new TextAmountActionListener());
        txtPrice.setColumns(10);

        lblAmount = new JLabel("Amount :");
        panContent.add(lblAmount, "cell 0 8");

        lblAmountValue = new JLabel("0.00");
        panContent.add(lblAmountValue, "cell 1 8");

        panSubmit = new JPanel();
        panSubmit.setBackground(Color.white);
        panCenter.add(panSubmit, BorderLayout.SOUTH);

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSubmit.setBackground(new Color(32, 130, 213));
        panSubmit.add(btnSubmit);
        fillForm();
        setContent(panCenter);
        getClose().addActionListener(this);

        this.setVisible(true);

        this.repaint();
        this.revalidate();
    }

    public void populateItems() {
        specificItems = new DefaultComboBoxModel();
        Iterator items = inventoryItemController.getDistinct("itemData");
        InventoryItem ii = null;
        while (items.hasNext()) {
            ii = (InventoryItem) items.next();
            if (type.equals("Hard")) {
                if (ii.getClassification().equals("Non-IT") || ii.getClassification().equals("IT")) {
                    specificItems.addElement(ii.getName());
                }
            } else if (type.equals("Soft")) {
                if (ii.getClassification().equals("Soft")) {
                    specificItems.addElement(ii.getName());
                }
            } else {
                if (ii.getClassification().equals("Gen") || ii.getClassification().equals("Others")) {
                    specificItems.addElement(ii.getName());
                }
            }
        }
        cmbItem.setModel(specificItems);
    }

    class ItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                fillForm();
            }
        }
    }

    public void fillForm() {
        ItemData ii = (ItemData) itemDataController.get((String) cmbItem.getSelectedItem());
       
        if (ii != null) {
            txtDescription.setText(ii.getDescription());
            txtPrice.setText(String.valueOf(ii.getUnitPrice()));
            
            int quantity = parseStringInt(txtQuantity.getText());
            float price = (float) parseStringFloat(txtPrice.getText());
            double result = quantity * price;
            lblAmountValue.setText(String.valueOf(df.format(result)));
            System.out.println("AMOUNT: "+ String.valueOf(df.format(result)));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == getClose()) {
            this.setVisible(false); // you can't see me! - LOL
            this.dispose();
        } else if (e.getSource() == btnSubmit) {

            String error = checkFields();
            if (error.equals("") == true) {
                //change this DEV :D :D 
                if (!poController.checkItemExists(cmbItem.getSelectedItem().toString())) {
                    String item = cmbItem.getSelectedItem().toString();
                    String description = txtDescription.getText();
                    int quantity = parseStringInt(txtQuantity.getText());
                    float price = (float) parseStringFloat(txtPrice.getText());

                    poController.addItem(new ItemData(item, description, price), quantity, 0);
                    this.setVisible(false); // you can't see me!
                    this.dispose();
                } else {
                    Message msg = new Message(parent, Message.ERROR, "Item already exists!");
                }

            } else if (error.equals("") == false) {
                Message msg = new Message(parent, Message.ERROR, error);
            } else {
                clearFields();
            }

        }
        //
    }

    public void clearFields() {
        txtDescription.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        cmbItem.setSelectedIndex(-1);
    }

    /**
     * ** parse string to integer *****
     */
    public int parseStringInt(String quantity) {
        if (quantity.equals("") == false) {
            return Integer.parseInt(quantity);
        }
        return 0;
    }

    /**
     * ** parse string to float ***
     */
    public double parseStringFloat(String price) {
        if (price.equals("") == false) {
            return Float.parseFloat(price);
        }
        return 0.0;
    }

    public String checkFields() {
        String error = "";
        Border border = BorderFactory.createLineBorder(Color.RED, 1);

//		if (txtItem.getText().equals("")) {
//			error += "Item Name Field is empty \n";
//			txtItem.setBorder(border);
//		}
        if (txtDescription.getText().equals("")) {
            error += "Item Description Area is empty \n";
            txtDescription.setBorder(border);
        }
        if (txtQuantity.getText().equals("")) {
            error += "Quantity Field is empty \n";
            txtQuantity.setBorder(border);
        }
        if (txtPrice.getText().equals("")) {
            error += "Price Field is empty";
            txtPrice.setBorder(border);
        }

        return error;
    }

    class TextAmountActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int quantity = parseStringInt(txtQuantity.getText());
            float price = (float) parseStringFloat(txtPrice.getText());
            float result = quantity * price;
            lblAmountValue.setText(String.valueOf(df.format(result)));
        }
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
