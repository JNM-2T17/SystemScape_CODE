package view.projects;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

import model.Project;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.PopUp;

import com.toedter.calendar.JDateChooser;

import controller.ProjectController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class FilterProject extends PopUp implements ActionListener {
	private JComboBox cmbName;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private JButton btnFilter;
	private boolean closed = true;
	private ProjectController projectController;
	
	public FilterProject(JFrame parent) {
		super(parent);
		projectController = ProjectController.getInstance();
		JPanel panMain = new JPanel();
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panHeader = new JPanel();
		panHeader.setBackground(Color.WHITE);
		panMain.add(panHeader, BorderLayout.NORTH);

		JLabel lblFilterProj = new JLabel("Filter Projects");
		lblFilterProj.setFont(new Font("Arial", Font.PLAIN, 16));
		panHeader.add(lblFilterProj);

		JPanel panCenter = new JPanel();
		panCenter.setBackground(Color.WHITE);
		panMain.add(panCenter, BorderLayout.CENTER);
		SpringLayout sl_panCenter = new SpringLayout();
		panCenter.setLayout(sl_panCenter);

		JLabel lblProjectTitle = new JLabel("Project Title:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblProjectTitle, 38,
				SpringLayout.NORTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblProjectTitle, 55,
				SpringLayout.WEST, panCenter);
		panCenter.add(lblProjectTitle);

		JLabel lblStartDate = new JLabel("Start Date:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblStartDate, 16,
				SpringLayout.SOUTH, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblStartDate, 0,
				SpringLayout.WEST, lblProjectTitle);
		panCenter.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblEndDate, 16,
				SpringLayout.SOUTH, lblStartDate);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblEndDate, 0,
				SpringLayout.WEST, lblProjectTitle);
		panCenter.add(lblEndDate);

		cmbName = new JComboBox();
		AutoCompleteDecorator.decorate(cmbName);
		sl_panCenter.putConstraint(SpringLayout.NORTH, cmbName, 32,
				SpringLayout.NORTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.WEST, cmbName, 15,
				SpringLayout.EAST, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.EAST, cmbName, 285,
				SpringLayout.EAST, lblProjectTitle);
		panCenter.add(cmbName);

		dateStart = new JDateChooser(new Date());
		sl_panCenter.putConstraint(SpringLayout.WEST, dateStart, 0,
				SpringLayout.WEST, cmbName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateStart, 0,
				SpringLayout.SOUTH, lblStartDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateStart, 0,
				SpringLayout.EAST, cmbName);
		panCenter.add(dateStart);

		dateEnd = new JDateChooser(new Date());
		sl_panCenter.putConstraint(SpringLayout.WEST, dateEnd, 0,
				SpringLayout.WEST, cmbName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateEnd, 0,
				SpringLayout.SOUTH, lblEndDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateEnd, 0,
				SpringLayout.EAST, cmbName);
		panCenter.add(dateEnd);

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.white);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		btnFilter.addActionListener(this);
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		panFooter.add(btnFilter);

		panMain.setPreferredSize(new Dimension(480, 250));
		panMain.setSize(new Dimension(480, 250));
		setContent(panMain);
		getClose().addActionListener(this);
		populateProjectNames();
		setVisible(true);
		this.repaint();
		this.revalidate();
		
	
	}

	public Iterator getValues() {
		ArrayList list = new ArrayList();
		
        list.add((String)cmbName.getSelectedItem());
		list.add(new java.sql.Date(dateStart.getDate().getTime()));
		list.add(new java.sql.Date(dateEnd.getDate().getTime()));

		return list.iterator();
	}

	public boolean isClosed() {
		return closed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == getClose()) {
			this.dispose();
		} else if (e.getSource() == btnFilter) {
			System.out.println("misery");
			closed = false;
			this.dispose();
		}
	}
	
	public void populateProjectNames(){
		Iterator<Project> iterator = projectController.getAll();
		ArrayList<String> projectNames = new ArrayList();
		projectNames.add("");
		while(iterator.hasNext()){
			projectNames.add(iterator.next().getName());
		}
		cmbName.setModel(new DefaultComboBoxModel(projectNames.toArray()));
	}
}
