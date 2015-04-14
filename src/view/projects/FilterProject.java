package view.projects;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;

import view.PopUp;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FilterProject extends PopUp implements ActionListener {
	private JTextField txtName;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private JButton btnFilter;

	public FilterProject(JFrame parent) {
		super(parent);
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

		txtName = new JTextField();
		sl_panCenter.putConstraint(SpringLayout.NORTH, txtName, 32,
				SpringLayout.NORTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.WEST, txtName, 15,
				SpringLayout.EAST, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.EAST, txtName, 285,
				SpringLayout.EAST, lblProjectTitle);
		panCenter.add(txtName);
		txtName.setColumns(10);

		dateStart = new JDateChooser(new Date());
		sl_panCenter.putConstraint(SpringLayout.WEST, dateStart, 0,
				SpringLayout.WEST, txtName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateStart, 0,
				SpringLayout.SOUTH, lblStartDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateStart, 0,
				SpringLayout.EAST, txtName);
		panCenter.add(dateStart);

		dateEnd = new JDateChooser(new Date());
		sl_panCenter.putConstraint(SpringLayout.WEST, dateEnd, 0,
				SpringLayout.WEST, txtName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateEnd, 0,
				SpringLayout.SOUTH, lblEndDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateEnd, 0,
				SpringLayout.EAST, txtName);
		panCenter.add(dateEnd);

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.white);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Arial", Font.PLAIN, 18));
		panFooter.add(btnFilter);
		btnFilter.setForeground(Color.white);
		btnFilter.setBackground(new Color(32, 130, 213));
		panFooter.add(btnFilter);

		panMain.setPreferredSize(new Dimension(480, 250));
		panMain.setSize(new Dimension(480, 250));
		setContent(panMain);
		getClose().addActionListener(this);
		setVisible(true);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == getClose()) {
			this.dispose();
		}
	}
}
