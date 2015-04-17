package view.employee;

import controller.EmployeeController;
import controller.ProjectController;

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
import model.Project;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.PopUp;

public class FilterEmployee extends PopUp implements ActionListener {

    private JButton btnFilter, btnRemoveFilter;
    private JComboBox cmbName;
    private JComboBox cmbStatus, cmbProject;
    private boolean closed = true;
    private EmployeeController employeeController;
    private ProjectController projectController;
    private ArrayList list;

    public FilterEmployee(JFrame parent) {
        super(parent);
        list = new ArrayList();
        employeeController = EmployeeController.getInstance();
        projectController = ProjectController.getInstance();
        JPanel panMain = new JPanel();
        panMain.setBackground(Color.WHITE);
        panMain.setPreferredSize(new Dimension(450, 250));
        panMain.setSize(new Dimension(450, 250));
        getContentPane().add(panMain);
        panMain.setLayout(new BorderLayout(0, 0));

        JPanel panHeader = new JPanel();
        panHeader.setBackground(Color.WHITE);
        panMain.add(panHeader, BorderLayout.NORTH);

        JLabel lblFilterEmp = new JLabel("Filter Employees");
        lblFilterEmp.setFont(new Font("Arial", Font.PLAIN, 16));
        panHeader.add(lblFilterEmp);

        JPanel panFooter = new JPanel();
        panFooter.setBackground(Color.WHITE);
        panMain.add(panFooter, BorderLayout.SOUTH);

        btnFilter = new JButton("Filter");
        panFooter.add(btnFilter);
        btnFilter.addActionListener(this);
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

        JLabel lblName = new JLabel("Name:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblName, 50,
                SpringLayout.NORTH, panContent);
        sl_panContent.putConstraint(SpringLayout.WEST, lblName, 50,
                SpringLayout.WEST, panContent);
        panContent.add(lblName);

        JLabel lblStatus = new JLabel("Status: ");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblStatus, 20,
                SpringLayout.SOUTH, lblName);
        sl_panContent.putConstraint(SpringLayout.WEST, lblStatus, 0,
                SpringLayout.WEST, lblName);
        panContent.add(lblStatus);

        cmbName = new JComboBox();
        AutoCompleteDecorator.decorate(cmbName);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbName, 30,
                SpringLayout.EAST, lblName);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbName, 0, SpringLayout.SOUTH, lblName);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbName, -50,
                SpringLayout.EAST, panContent);
        cmbName.setBackground(Color.white);
        panContent.add(cmbName);

        String[] opt={"Active", "On Leave"};

		cmbStatus = new JComboBox(opt);
        cmbStatus.setEditable(true);
        AutoCompleteDecorator.decorate(cmbStatus);
        cmbStatus.setBackground(Color.WHITE);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbStatus, 0,
                SpringLayout.WEST, cmbName);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbStatus, 0,
                SpringLayout.SOUTH, lblStatus);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbStatus, 0,
                SpringLayout.EAST, cmbName);
        panContent.add(cmbStatus);

        JLabel lblProject = new JLabel("Project:");
        sl_panContent.putConstraint(SpringLayout.NORTH, lblProject, 20, SpringLayout.SOUTH, lblStatus);
        sl_panContent.putConstraint(SpringLayout.WEST, lblProject, 0, SpringLayout.WEST, lblName);
        panContent.add(lblProject);

        cmbProject = new JComboBox();
        cmbProject.setEditable(true);
        AutoCompleteDecorator.decorate(cmbProject);
        sl_panContent.putConstraint(SpringLayout.WEST, cmbProject, 0, SpringLayout.WEST, cmbName);
        sl_panContent.putConstraint(SpringLayout.SOUTH, cmbProject, 0, SpringLayout.SOUTH, lblProject);
        sl_panContent.putConstraint(SpringLayout.EAST, cmbProject, 0, SpringLayout.EAST, cmbName);
        cmbProject.setBackground(Color.WHITE);
        panContent.add(cmbProject);

        getClose().addActionListener(this);

        setContent(panMain);
        populateEmployeeNames();
       // populateEmployeeStatus();
        populateEmployeeProjects();
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    public Iterator getValues() {

        if (list != null) {
            list.add((String) cmbName.getSelectedItem());
            list.add((String) cmbStatus.getSelectedItem());
            list.add((String) cmbProject.getSelectedItem());
            return list.iterator();
        }
        return null;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean checkFields() {
        boolean isEmpty = false;
        if (cmbName.getSelectedIndex() == 0 && cmbStatus.getSelectedIndex() == 0
                && cmbProject.getSelectedIndex() == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getClose()) {
            this.dispose();
        } /**
         * **
         * DEV Insert Code statements here to filter the list of suppliers if at
         * least one of the fields is not empty
		 ****
         */
        else if (checkFields() == false && e.getSource() == btnFilter) {
            closed = false;
            this.dispose();
        } else if (checkFields() == true && e.getSource() == btnFilter) {
            /**
             * **
             * DEV Insert Code statements here to set the list to the original
             * if not one of the fields is filled up/ All fields are empty
			 ****
             */
            list = null;
            closed = false;
            this.dispose();
        } else if (e.getSource() == btnRemoveFilter) {
            /**
             * *
             * DEV insert code statements here to remove the filter and set the
             * view table to the original meaning yung walang filter...
			****
             */
            list = null;
            closed = false;
            this.dispose();
        }
    }

    public void populateEmployeeNames() {
        Iterator<Employee> iterator = employeeController.getAll();
        ArrayList<String> employeeNames = new ArrayList();
        employeeNames.add("");
        while (iterator.hasNext()) {
            employeeNames.add(iterator.next().getName());
        }
        cmbName.setModel(new DefaultComboBoxModel(employeeNames.toArray()));
    }

//    public void populateEmployeeStatus() {
//        Iterator<Employee> iterator = employeeController.getAll();
//        ArrayList<String> employeeStatus = new ArrayList();
//        employeeStatus.add("");
//        while (iterator.hasNext()) {
//            String status = iterator.next().getStatus();
//            employeeStatus.add(status);
//
//        }
//        cmbStatus.setModel(new DefaultComboBoxModel(employeeStatus.toArray()));
//    }

    public void populateEmployeeProjects() {
        ArrayList<String> employeeProjects = new ArrayList();
        employeeProjects.add("");
        Iterator<Project> projectsIterator = projectController.getAll();
        while (projectsIterator.hasNext()) {
            employeeProjects.add(projectsIterator.next().getName());

        }
        cmbProject.setModel(new DefaultComboBoxModel(employeeProjects.toArray()));
    }
}
