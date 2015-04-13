package view.employee;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Employee;
import model.Project;
import model.Supplier;
import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.EmployeeController;
import controller.ProjectController;
import view.Button;
import view.Message;
import view.Button.ButtonBuilder;
import view.supplier.Contact;

public class ViewSpecEmployee extends JPanel {
	private JTextField txtName;
	private JTextField cmbStatus;
	private JPanel panContact, panClose;
	private ArrayList<ProjectPanel> list;
	private ArrayList<JButton> close;
	private ArrayList<Project> projList;
	private Employee emp;
	private EmployeeController employeeController;
	private ProjectController projectController;

	public ViewSpecEmployee() {
		this.setBackground(Color.WHITE);
		projList = new ArrayList<Project>();
		list = new ArrayList<ProjectPanel>();
		close = new ArrayList<JButton>();
		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(null);

		JLabel lblSupp = new JLabel("Name:");
		lblSupp.setBounds(157, 90, 93, 27);
		panContent.add(lblSupp);

		txtName = new JTextField();
		txtName.setBounds(251, 89, 372, 25);
		panContent.add(txtName);
		txtName.setColumns(10);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(158, 130, 92, 26);
		panContent.add(lblStatus);

		cmbStatus = new JTextField();
		cmbStatus.setEditable(true);
		cmbStatus.setBackground(Color.WHITE);
		cmbStatus.setBounds(251, 130, 372, 25);
		panContent.add(cmbStatus);

		JLabel lblProjects = new JLabel("Projects:");
		lblProjects.setBounds(157, 180, 60, 14);
		panContent.add(lblProjects);

		panContact = new JPanel();
		panContact.setBackground(Color.WHITE);
		panContact.setBounds(51, 478, 457, 200);
		panContact.setLayout(new MigLayout());

		panClose = new JPanel();

		panClose.setBackground(Color.ORANGE);
		// panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose, "");
		panClose.setLayout(new BoxLayout(panClose, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(251, 180);
		scrollPane.setSize(400, 200);
		panContent.add(scrollPane, "cell 2 5,grow");
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);
		panClose.setVisible(false);

		employeeController = employeeController.getInstance();
		projectController = projectController.getInstance();
	}

	public void init() {
		txtName.setText(emp.getName());
		cmbStatus.setText(emp.getStatus());
		Iterator it = emp.getProjectList();
		while (it.hasNext()) {
			Project project = (Project) it.next();

			ProjectPanel temp = new ProjectPanel();
			temp.setButton(null);

			panClose.add(temp);
			list.add(temp);
			projList.add(project);

			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}

			panClose.setMaximumSize(new Dimension(360, panClose
					.getComponentCount() * 37));
			this.repaint();
			this.revalidate();

		}
	}

	public String checkInput() {
		String text = "";
		if (txtName.getText().equals("")) {
			txtName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			text += "Please specify employee name.\n";
		}
		return text;
	}
	
	public void setEmployee(Employee emp){
		list.clear();
		close.clear();
		projList.clear();
		panClose.removeAll();
		
		this.emp=emp;
		init();
		
	}
}
