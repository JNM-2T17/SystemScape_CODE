package view.project;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.SpringLayout;

import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import view.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditProject extends JPanel implements ActionListener {
	private JButton btnRight, btnLeft, btnDblRight, btnDblLeft;
	private JTextField txtProjects;
	private List listAll, listEmp;
	private JFrame parent;
	private JDateChooser dateStart, dateEnd;

	public EditProject(JFrame parent, String project) {
		this.parent = parent;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JPanel panMain = new JPanel();
		add(panMain, BorderLayout.CENTER);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panMain.add(panContent, BorderLayout.CENTER);
		panContent.setBackground(Color.WHITE);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblProject = new JLabel("Project:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblProject, 45,
				SpringLayout.NORTH, panContent);
		panContent.add(lblProject);

		txtProjects = new JTextField(project);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtProjects, 0,
				SpringLayout.SOUTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.EAST, txtProjects, -464,
				SpringLayout.EAST, panContent);
		panContent.add(txtProjects);
		txtProjects.setBackground(Color.WHITE);

		JLabel lblAll = new JLabel("List of Employees:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblProject, 0,
				SpringLayout.WEST, lblAll);
		sl_panContent.putConstraint(SpringLayout.NORTH, lblAll, 140,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblAll, 109,
				SpringLayout.WEST, panContent);
		panContent.add(lblAll);

		btnRight = new JButton(">");
		btnRight.setForeground(Color.white);
		btnRight.setBackground(new Color(32, 130, 213));
		panContent.add(btnRight);

		btnLeft = new JButton("<");
		btnLeft.setForeground(Color.white);
		btnLeft.setBackground(new Color(32, 130, 213));
		sl_panContent.putConstraint(SpringLayout.NORTH, btnLeft, 5,
				SpringLayout.SOUTH, btnRight);
		sl_panContent.putConstraint(SpringLayout.WEST, btnLeft, 0,
				SpringLayout.WEST, btnRight);
		sl_panContent.putConstraint(SpringLayout.EAST, btnLeft, 0,
				SpringLayout.EAST, btnRight);
		panContent.add(btnLeft);

		btnDblRight = new JButton(">>");
		btnDblRight.setForeground(Color.white);
		btnDblRight.setBackground(new Color(32, 130, 213));
		sl_panContent.putConstraint(SpringLayout.WEST, btnDblRight, 0,
				SpringLayout.WEST, btnRight);
		btnDblRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		sl_panContent.putConstraint(SpringLayout.NORTH, btnDblRight, 5,
				SpringLayout.SOUTH, btnLeft);
		sl_panContent.putConstraint(SpringLayout.EAST, btnDblRight, 0,
				SpringLayout.EAST, btnRight);
		panContent.add(btnDblRight);

		btnDblLeft = new JButton("<<");
		btnDblLeft.setForeground(Color.white);
		btnDblLeft.setBackground(new Color(32, 130, 213));
		sl_panContent.putConstraint(SpringLayout.NORTH, btnDblLeft, 5,
				SpringLayout.SOUTH, btnDblRight);
		sl_panContent.putConstraint(SpringLayout.WEST, btnDblLeft, 0,
				SpringLayout.WEST, btnRight);
		sl_panContent.putConstraint(SpringLayout.EAST, btnDblLeft, 0,
				SpringLayout.EAST, btnRight);
		panContent.add(btnDblLeft);

		listAll = new List();
		sl_panContent.putConstraint(SpringLayout.EAST, listAll, 350, SpringLayout.WEST, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, btnRight, 20, SpringLayout.EAST, listAll);
		sl_panContent.putConstraint(SpringLayout.EAST, btnRight, 70, SpringLayout.EAST, listAll);
		sl_panContent.putConstraint(SpringLayout.WEST, listAll, 110,
				SpringLayout.WEST, panContent);
		sl_panContent.putConstraint(SpringLayout.SOUTH, listAll, 325,
				SpringLayout.SOUTH, lblAll);
		sl_panContent.putConstraint(SpringLayout.NORTH, listAll, 6,
				SpringLayout.SOUTH, lblAll);
		panContent.add(listAll);

		JLabel lblEmp = new JLabel("List of Project Employees:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblEmp, 0,
				SpringLayout.NORTH, lblAll);
		sl_panContent.putConstraint(SpringLayout.WEST, lblEmp, 249, SpringLayout.EAST, lblAll);
		sl_panContent.putConstraint(SpringLayout.EAST, lblEmp, -235, SpringLayout.EAST, panContent);
		panContent.add(lblEmp);

		listEmp = new List();
		sl_panContent.putConstraint(SpringLayout.NORTH, listEmp, 0, SpringLayout.NORTH, listAll);
		sl_panContent.putConstraint(SpringLayout.WEST, listEmp, 20,
				SpringLayout.EAST, btnRight);
		sl_panContent.putConstraint(SpringLayout.SOUTH, listEmp, 0, SpringLayout.SOUTH, listAll);
		sl_panContent.putConstraint(SpringLayout.EAST, listEmp, 260,
				SpringLayout.EAST, btnRight);
		panContent.add(listEmp);

		JLabel lblStartDate = new JLabel("Start Date:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblStartDate, 77,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblProject, -15,
				SpringLayout.NORTH, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.WEST, lblStartDate, 0,
				SpringLayout.WEST, lblProject);
		panContent.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblEndDate, 15,
				SpringLayout.SOUTH, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.WEST, lblEndDate, 0,
				SpringLayout.WEST, lblProject);
		panContent.add(lblEndDate);

		dateStart = new JDateChooser();
		sl_panContent.putConstraint(SpringLayout.WEST, dateStart, 14,
				SpringLayout.EAST, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateStart, -464,
				SpringLayout.EAST, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, txtProjects, 0,
				SpringLayout.WEST, dateStart);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateStart, 0,
				SpringLayout.SOUTH, lblStartDate);
		dateStart.setBackground(Color.WHITE);
		panContent.add(dateStart);

		dateEnd = new JDateChooser();
		sl_panContent.putConstraint(SpringLayout.NORTH, btnRight, 140, SpringLayout.SOUTH, dateEnd);
		sl_panContent.putConstraint(SpringLayout.WEST, dateEnd, 20,
				SpringLayout.EAST, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateEnd, 0,
				SpringLayout.SOUTH, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateEnd, -464,
				SpringLayout.EAST, panContent);
		dateEnd.setBackground(Color.WHITE);
		panContent.add(dateEnd);

		JPanel panFooter = new JPanel();
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);
		sl_panContent.putConstraint(SpringLayout.NORTH, panFooter, 0,
				SpringLayout.NORTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.WEST, panFooter, 10,
				SpringLayout.WEST, panContent);

		JButton btnSubmit = new JButton("Submit");
		panFooter.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
