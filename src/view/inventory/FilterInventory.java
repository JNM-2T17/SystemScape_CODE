package view.inventory;

import controller.EmployeeController;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import model.Employee;
import model.ItemData;
import model.database.ItemDataDAO;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.PopUp;

public class FilterInventory extends PopUp implements ActionListener {

    private JButton btnFilter, btnRemoveFilter;
    private JComboBox cmbItem;
    private JComboBox cmbClassification, cmbAssignee, cmbQuantity, cmbOffice;
    private JTextField txtQuantity;
    private JTextField txtService;
    private JTextField txtAsset;
    private JTextField txtInvoice;
    private ArrayList list;
    private boolean isClosed;

    public FilterInventory(JFrame parent) {
        super(parent);
        this.isClosed = true;
        list = new ArrayList();
        JPanel panMain = new JPanel();
        panMain.setBackground(Color.WHITE);
        panMain.setPreferredSize(new Dimension(450, 380));
        panMain.setSize(new Dimension(500, 380));
        getContentPane().add(panMain);
        panMain.setLayout(new BorderLayout(0, 0));

        JPanel panFooter = new JPanel();
        panFooter.setBackground(Color.WHITE);
        panMain.add(panFooter, BorderLayout.SOUTH);

        btnFilter = new JButton("Filter");
        btnFilter.addActionListener(this);
        panFooter.add(btnFilter);
        btnFilter.setForeground(Color.white);
        btnFilter.setBackground(new Color(32, 130, 213));
        btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));
        panFooter.add(btnFilter);
        
        btnRemoveFilter = new JButton("Remove Filter");
        btnRemoveFilter.setForeground(new Color(255, 255, 255));
        btnRemoveFilter.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRemoveFilter.setBackground(new Color(32, 130, 213));
        btnRemoveFilter.addActionListener(this);
        panFooter.add(btnRemoveFilter);

        JPanel panContent = new JPanel();
        panContent.setBackground(Color.WHITE);
        panMain.add(panContent, BorderLayout.CENTER);
        SpringLayout sl_panContent = new SpringLayout();
        panContent.setLayout(sl_panContent);

        JLabel lblItem = new JLabel("Item:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblItem, 35,
                SpringLayout.NORTH, panContent);
        sl_panContent.putConstraint(SpringLayout.WEST, lblItem, 50,
                SpringLayout.WEST, panContent);
        panContent.add(lblItem);

        JLabel lblClassification = new JLabel("Classification: ");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblClassification, 20,
                SpringLayout.SOUTH, lblItem);
        sl_panContent.putConstraint(SpringLayout.WEST, lblClassification, 0,
                SpringLayout.WEST, lblItem);
        panContent.add(lblClassification);

        cmbItem = new JComboBox();
        AutoCompleteDecorator.decorate(cmbItem);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbItem, 0, SpringLayout.SOUTH, lblItem);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbItem, -75,
                SpringLayout.EAST, panContent);
        cmbItem.setBackground(Color.white);
        panContent.add(cmbItem);

        String classifications[] = {"", "IT Asset", "Non-IT Asset", "Software", "Other"};
        cmbClassification = new JComboBox(classifications);
        AutoCompleteDecorator.decorate(cmbClassification);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbItem, 0, SpringLayout.WEST, cmbClassification);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbClassification, 63, SpringLayout.EAST, lblClassification);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbClassification, -75, SpringLayout.EAST, panContent);
        cmbClassification.setBackground(Color.WHITE);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbClassification, 0,
                SpringLayout.SOUTH, lblClassification);
        panContent.add(cmbClassification);

        JLabel lblOfficeLocation = new JLabel("Office Location:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblOfficeLocation, 20, SpringLayout.SOUTH, lblClassification);
        sl_panContent.putConstraint(SpringLayout.WEST, lblOfficeLocation, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblOfficeLocation);

        JLabel lblAssetTag = new JLabel("Asset Tag:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblAssetTag, 20, SpringLayout.SOUTH, lblOfficeLocation);
        sl_panContent.putConstraint(SpringLayout.WEST, lblAssetTag, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblAssetTag);

        JLabel lblServiceTag = new JLabel("Service Tag:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblServiceTag, 20, SpringLayout.SOUTH, lblAssetTag);
        sl_panContent.putConstraint(SpringLayout.WEST, lblServiceTag, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblServiceTag);

        JLabel lblAssignee = new JLabel("Assignee:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblAssignee, 20, SpringLayout.SOUTH, lblServiceTag);
        sl_panContent.putConstraint(SpringLayout.WEST, lblAssignee, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblAssignee);

        JLabel lblQuantity = new JLabel("Quantity:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblQuantity, 20, SpringLayout.SOUTH, lblAssignee);
        sl_panContent.putConstraint(SpringLayout.WEST, lblQuantity, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblQuantity);

        String offices[] = {"", "1WS", "DAO"};
        cmbOffice = new JComboBox(offices);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbOffice, 56, SpringLayout.EAST, lblOfficeLocation);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbOffice, 0, SpringLayout.SOUTH, lblOfficeLocation);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbOffice, -75, SpringLayout.EAST, panContent);
        cmbOffice.setBackground(Color.WHITE);
        panContent.add(cmbOffice);

        cmbAssignee = new JComboBox();
        AutoCompleteDecorator.decorate(cmbAssignee);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbAssignee, 0, SpringLayout.WEST, cmbItem);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbAssignee, 0, SpringLayout.SOUTH, lblAssignee);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbAssignee, 0, SpringLayout.EAST, cmbItem);
        cmbAssignee.setBackground(Color.WHITE);
        panContent.add(cmbAssignee);

        String operators[] = {"", ">", ">=", "<", "<="};
        cmbQuantity = new JComboBox(operators);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbQuantity, 0, SpringLayout.WEST, cmbItem);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbQuantity, 0, SpringLayout.SOUTH, lblQuantity);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbQuantity, 52, SpringLayout.WEST, cmbItem);
        cmbQuantity.setBackground(Color.WHITE);
        panContent.add(cmbQuantity);

        txtQuantity = new JTextField();
        sl_panContent.putConstraint(SpringLayout.NORTH, txtQuantity, 14, SpringLayout.SOUTH, cmbAssignee);
        sl_panContent.putConstraint(SpringLayout.WEST, txtQuantity, 6, SpringLayout.EAST, cmbQuantity);
        sl_panContent.putConstraint(SpringLayout.EAST, txtQuantity, 0, SpringLayout.EAST, cmbItem);
        panContent.add(txtQuantity);
        txtQuantity.setColumns(10);

        txtService = new JTextField();
        sl_panContent.putConstraint(SpringLayout.WEST, txtService, 73, SpringLayout.EAST, lblServiceTag);
        sl_panContent.putConstraint(SpringLayout.SOUTH, txtService, -14, SpringLayout.NORTH, cmbAssignee);
        sl_panContent.putConstraint(SpringLayout.EAST, txtService, 0, SpringLayout.EAST, cmbItem);
        txtService.setColumns(10);
        panContent.add(txtService);

        txtAsset = new JTextField();
        sl_panContent.putConstraint(SpringLayout.NORTH, txtAsset, 14, SpringLayout.SOUTH, cmbOffice);
        sl_panContent.putConstraint(SpringLayout.WEST, txtAsset, 0, SpringLayout.WEST, cmbItem);
        sl_panContent.putConstraint(SpringLayout.EAST, txtAsset, 0, SpringLayout.EAST, cmbItem);
        txtAsset.setColumns(10);
        panContent.add(txtAsset);

        JLabel lblInvoice = new JLabel("Invoice:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblInvoice, 20, SpringLayout.SOUTH, lblQuantity);
        sl_panContent.putConstraint(SpringLayout.WEST, lblInvoice, 0, SpringLayout.WEST, lblItem);
        panContent.add(lblInvoice);

        txtInvoice = new JTextField();
        sl_panContent.putConstraint(SpringLayout.NORTH, txtInvoice, 14, SpringLayout.SOUTH, cmbQuantity);
        sl_panContent.putConstraint(SpringLayout.WEST, txtInvoice, 0, SpringLayout.WEST, cmbItem);
        sl_panContent.putConstraint(SpringLayout.EAST, txtInvoice, 0, SpringLayout.EAST, cmbItem);
        txtInvoice.setColumns(10);
        panContent.add(txtInvoice);

        getClose().addActionListener(this);

        populateAssignees();
        populateItemNames();

        setContent(panMain);
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public Iterator getValues() {
        if (list != null) {
            list.add((String) cmbItem.getSelectedItem());
            String classification = (String) cmbClassification.getSelectedItem();
            if (classification.equalsIgnoreCase("IT Asset")) {
                classification = "IT";
            } else if (classification.equalsIgnoreCase("Non-IT Asset")) {
                classification = "Non-IT";
            } else if (classification.equalsIgnoreCase("Software")) {
                classification = "Soft";
            }

            list.add(classification);
            list.add((String) cmbOffice.getSelectedItem());
            list.add((String) txtAsset.getText());
            list.add((String) txtService.getText());
            list.add((String) cmbAssignee.getSelectedItem());
            list.add((String) cmbQuantity.getSelectedItem());
            list.add((String) txtQuantity.getText());
            list.add((String) txtInvoice.getText());
            return list.iterator();
        } 
            return null;
        
    }

    public boolean checkFields() {
        boolean isEmpty = false;
        if (cmbItem.getSelectedIndex() == 0 && cmbClassification.getSelectedIndex() == 0 && cmbOffice.getSelectedIndex() == 0 && txtAsset.getText().equals("") && txtService.getText().equals("") && cmbAssignee.getSelectedIndex() == 0 && cmbQuantity.getSelectedIndex() == 0 && txtQuantity.getText().equals("") && txtInvoice.getText().equals("")) {
            isEmpty = true;
        }
        return isEmpty;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnFilter && checkFields() == true) {
            list = null;
            isClosed = false;
            this.dispose();
        } else if (e.getSource() == btnFilter && checkFields() == false) {
            this.isClosed = false;
            this.dispose();
        } else if (e.getSource() == getClose()) {
            this.dispose();
        } else if (e.getSource() == btnRemoveFilter) {
            /**
             * *
             * DEV insert code statements here to remove the filter and set the
             * view table to the original meaning yung walang filter...
			****
             */
          
            this.dispose();
        }
    }

    public void populateAssignees() {
        ArrayList<String> employeeNames = new ArrayList();
        employeeNames.add("");
        for (Iterator i = EmployeeController.getInstance().getAll(); i.hasNext();) {
            employeeNames.add(((Employee) i.next()).getName());
        }
        cmbAssignee.setModel(new DefaultComboBoxModel(employeeNames.toArray()));
    }

    public void populateItemNames() {
        ArrayList<String> itemNames = new ArrayList();
        ItemDataDAO itemDataDAO = new ItemDataDAO();
        itemNames.add("");
        for (Iterator i = itemDataDAO.get(); i.hasNext();) {
            itemNames.add(((ItemData) i.next()).getName());
        }
        cmbItem.setModel(new DefaultComboBoxModel(itemNames.toArray()));
    }

}
