package view.employee;

import controller.EmployeeController;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import view.PopUp;

public class FilterEmployee extends JFrame implements ActionListener {

	private JButton btnFilter;
	private JComboBox cmbName;
	private JComboBox cmbStatus;
        private EmployeeController employeeController;

	public FilterEmployee(JFrame parent) {
//		super(parent);

		JPanel panMain = new JPanel();
		panMain.setBackground(Color.WHITE);
		panMain.setPreferredSize(new Dimension(750, 350));
		panMain.setSize(new Dimension(750, 350));
		add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 15));
		panFooter.add(btnFilter);

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblName = new JLabel("Name:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblName, 45,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblName, 68,
				SpringLayout.WEST, panContent);
		panContent.add(lblName);

		JLabel lblStatus = new JLabel("Status: ");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblStatus, 20,
				SpringLayout.SOUTH, lblName);
		sl_panContent.putConstraint(SpringLayout.WEST, lblStatus, 0,
				SpringLayout.WEST, lblName);
		panContent.add(lblStatus);

		cmbName = new JComboBox();
		cmbName.setBackground(Color.white);
		cmbName.setFont(new Font("Arial", Font.PLAIN, 24));
		sl_panContent.putConstraint(SpringLayout.NORTH, cmbName, 39,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbName, 106,
				SpringLayout.EAST, lblName);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbName, -75,
				SpringLayout.EAST, panContent);
		panContent.add(cmbName);

		cmbStatus = new JComboBox();
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
		
		JComboBox cmbProject = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.WEST, cmbProject, 0, SpringLayout.WEST, cmbName);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbProject, 0, SpringLayout.SOUTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbProject, 0, SpringLayout.EAST, cmbName);
		cmbProject.setFont(new Font("Arial", Font.PLAIN, 24));
		cmbProject.setBackground(Color.WHITE);
		panContent.add(cmbProject);

//		getClose().addActionListener(this);
                
                employeeController = EmployeeController.getInstance();
                
//		setContent(panMain);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if (e.getSource() == btnFilter) {
//			this.dispose();
//		} else if (e.getSource() == getClose()) {
//			this.dispose();
//		}
	}
}
