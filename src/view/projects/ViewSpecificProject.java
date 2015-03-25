package view.projects;

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
import javax.swing.JScrollPane;

public class ViewSpecificProject extends JPanel {
	private JLabel lblProjects;
	private JLabel dateStart, dateEnd;

	public ViewSpecificProject() {
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

		lblProjects = new JLabel();
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblProjects, 0,
				SpringLayout.SOUTH, lblProject);
		sl_panContent.putConstraint(SpringLayout.EAST, lblProjects, -464,
				SpringLayout.EAST, panContent);
		panContent.add(lblProjects);
		lblProjects.setBackground(Color.WHITE);

		JLabel lblAll = new JLabel("List of Project Employees:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblProject, 0,
				SpringLayout.WEST, lblAll);
		sl_panContent.putConstraint(SpringLayout.NORTH, lblAll, 140,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblAll, 109,
				SpringLayout.WEST, panContent);
		panContent.add(lblAll);

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

		dateStart = new JLabel();
		sl_panContent.putConstraint(SpringLayout.WEST, dateStart, 14,
				SpringLayout.EAST, lblStartDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateStart, -464,
				SpringLayout.EAST, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblProjects, 0,
				SpringLayout.WEST, dateStart);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateStart, 0,
				SpringLayout.SOUTH, lblStartDate);
		dateStart.setBackground(Color.WHITE);
		panContent.add(dateStart);

		dateEnd = new JLabel();
		sl_panContent.putConstraint(SpringLayout.WEST, dateEnd, 20,
				SpringLayout.EAST, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.SOUTH, dateEnd, 0,
				SpringLayout.SOUTH, lblEndDate);
		sl_panContent.putConstraint(SpringLayout.EAST, dateEnd, -464,
				SpringLayout.EAST, panContent);
		dateEnd.setBackground(Color.WHITE);
		panContent.add(dateEnd);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panContent.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, dateEnd);
		sl_panContent.putConstraint(SpringLayout.WEST, scrollPane, 6, SpringLayout.EAST, lblAll);
		sl_panContent.putConstraint(SpringLayout.SOUTH, scrollPane, 289, SpringLayout.SOUTH, dateEnd);
		sl_panContent.putConstraint(SpringLayout.EAST, scrollPane, 310, SpringLayout.EAST, lblAll);
		panContent.add(scrollPane);
		
		JPanel panEmp = new JPanel();
		scrollPane.setViewportView(panEmp);
	}
	
	public void setProject(String proj){
		lblProjects.setText(proj);
	}
}
