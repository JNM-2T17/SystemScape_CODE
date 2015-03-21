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

import com.toedter.calendar.JDateChooser;

import view.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewProjects extends JPanel implements ActionListener{
	private JButton btnAdd, btnRight, btnLeft, btnDblRight, btnDblLeft;
	private JComboBox cmbProjects;
	private List listAll, listEmp;
	private JFrame parent;
	private JDateChooser dateStart, dateEnd;

	public ViewProjects(JFrame  parent) {
		this.parent=parent;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		add(panContent);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblProject = new JLabel("Project:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblProject, 45, SpringLayout.NORTH, panContent);
		panContent.add(lblProject);

		cmbProjects = new JComboBox();
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbProjects, 0, SpringLayout.SOUTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbProjects, -464, SpringLayout.EAST, panContent);
		panContent.add(cmbProjects);
		cmbProjects.setBackground(Color.WHITE);

		btnAdd = new Button.ButtonBuilder().img(
				"src/assets/mETRO/Add.png", 27, 27).build();
		sl_panContent.putConstraint(SpringLayout.SOUTH, btnAdd, 0,
				SpringLayout.SOUTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.WEST, btnAdd, 6,
				SpringLayout.EAST, cmbProjects);
		btnAdd.addActionListener(this);
		panContent.add(btnAdd);

		JLabel lblAll = new JLabel("List of Employees:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblProject, 0, SpringLayout.WEST, lblAll);
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
		sl_panContent.putConstraint(SpringLayout.NORTH, btnRight, 100,
				SpringLayout.NORTH, listAll);
		sl_panContent.putConstraint(SpringLayout.WEST, btnRight, 20,
				SpringLayout.EAST, listAll);
		sl_panContent.putConstraint(SpringLayout.EAST, btnRight, 75,
				SpringLayout.EAST, listAll);
		sl_panContent.putConstraint(SpringLayout.WEST, listAll, 109,
				SpringLayout.WEST, panContent);
		sl_panContent.putConstraint(SpringLayout.EAST, listAll, 350,
				SpringLayout.WEST, panContent);
		sl_panContent.putConstraint(SpringLayout.NORTH, listAll, 6,
				SpringLayout.SOUTH, lblAll);
		sl_panContent.putConstraint(SpringLayout.SOUTH, listAll, 356,
				SpringLayout.SOUTH, lblAll);
		panContent.add(listAll);

		JLabel lblEmp = new JLabel("List of Project Employees:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblEmp, 0,
				SpringLayout.NORTH, lblAll);
		sl_panContent.putConstraint(SpringLayout.EAST, lblEmp, -235,
				SpringLayout.EAST, panContent);
		panContent.add(lblEmp);

		listEmp = new List();
		sl_panContent.putConstraint(SpringLayout.EAST, listEmp, -249,
				SpringLayout.EAST, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblEmp, 0,
				SpringLayout.WEST, listEmp);
		sl_panContent.putConstraint(SpringLayout.NORTH, listEmp, 0,
				SpringLayout.NORTH, listAll);
		sl_panContent.putConstraint(SpringLayout.WEST, listEmp, 20,
				SpringLayout.EAST, btnRight);
		sl_panContent.putConstraint(SpringLayout.SOUTH, listEmp, 350,
				SpringLayout.NORTH, listAll);
		panContent.add(listEmp);
		
		JLabel lblStartDate = new JLabel("Start Date:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblStartDate, 77, SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblProject, -15, SpringLayout.NORTH, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.WEST, lblStartDate, 0, SpringLayout.WEST, lblProject);
		panContent.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblEndDate, 15, SpringLayout.SOUTH, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.WEST, lblEndDate, 0, SpringLayout.WEST, lblProject);
		panContent.add(lblEndDate);
		
		dateStart= new JDateChooser();
		sl_panContent.putConstraint(SpringLayout.WEST, dateStart, 14, SpringLayout.EAST, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateStart, -464, SpringLayout.EAST, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbProjects, 0, SpringLayout.WEST, dateStart);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateStart, 0, SpringLayout.SOUTH, lblStartDate);
		dateStart.setBackground(Color.WHITE);
		panContent.add(dateStart);
		
		dateEnd = new JDateChooser();
		sl_panContent.putConstraint(SpringLayout.WEST, dateEnd, 20, SpringLayout.EAST, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateEnd, 0, SpringLayout.SOUTH, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateEnd, -464, SpringLayout.EAST, panContent);
		dateEnd.setBackground(Color.WHITE);
		panContent.add(dateEnd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnAdd){
			AddProject add=new AddProject(parent);
		}
	}
}
