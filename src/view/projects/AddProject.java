package view.projects;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import model.Project;
import view.ErrorListenerFactory;
import view.Message;
import view.PopUp;
import view.supplier.Contact;

import com.toedter.calendar.JDateChooser;

import controller.ProjectController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;

public class AddProject extends JPanel implements ActionListener {
	private JComboBox cmbName;
	private JDateChooser dateStart;
	private JDateChooser dateEnd;
	private JButton btnSubmit;
	private ProjectController projectController;

	private JFrame parent;
	public AddProject(JFrame parent) {
		// super(parent);
		this.setLayout(new BorderLayout(0, 0));
		this.parent=parent;
		
		JPanel panCenter = new JPanel();
		panCenter.setBackground(Color.WHITE);
		this.add(panCenter, BorderLayout.CENTER);
		SpringLayout sl_panCenter = new SpringLayout();
		panCenter.setLayout(sl_panCenter);

		JLabel lblProjectTitle = new JLabel("Project Name:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblProjectTitle, 100,
				SpringLayout.NORTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblProjectTitle, 100,
				SpringLayout.WEST, panCenter);
		panCenter.add(lblProjectTitle);

		JLabel lblStartDate = new JLabel("Start Date:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblStartDate, 16,
				SpringLayout.SOUTH, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblStartDate, 0,
				SpringLayout.WEST, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.EAST, lblStartDate, -340,
				SpringLayout.EAST, panCenter);
		panCenter.add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date:");
		sl_panCenter.putConstraint(SpringLayout.NORTH, lblEndDate, 16,
				SpringLayout.SOUTH, lblStartDate);
		sl_panCenter.putConstraint(SpringLayout.WEST, lblEndDate, 0,
				SpringLayout.WEST, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.EAST, lblEndDate, -346,
				SpringLayout.EAST, panCenter);
		panCenter.add(lblEndDate);

		cmbName = new JComboBox();
		cmbName.setEditable(true);
        AutoCompleteDecorator.decorate(cmbName);
		cmbName.addFocusListener(ErrorListenerFactory.getListener(cmbName));
		sl_panCenter.putConstraint(SpringLayout.WEST, cmbName, 46,
				SpringLayout.EAST, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, cmbName, 0,
				SpringLayout.SOUTH, lblProjectTitle);
		sl_panCenter.putConstraint(SpringLayout.EAST, cmbName, 285,
				SpringLayout.EAST, lblProjectTitle);
		panCenter.add(cmbName);
		
		Iterator it=projectController.getInstance().getAll();
		while(it.hasNext()){
			cmbName.addItem((Project)it.next());
		}

		dateStart = new JDateChooser(new Date());
		dateStart.setDateFormatString("MMMM dd, yyyy");
		dateStart.getDateEditor().getUiComponent().addFocusListener(ErrorListenerFactory.getListener(dateStart));
		sl_panCenter.putConstraint(SpringLayout.WEST, dateStart, 0,
				SpringLayout.WEST, cmbName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateStart, 0,
				SpringLayout.SOUTH, lblStartDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateStart, 0,
				SpringLayout.EAST, cmbName);
		panCenter.add(dateStart);

		dateEnd = new JDateChooser(new Date());
		dateEnd.setDateFormatString("MMMM dd, yyyy");
		dateEnd.getDateEditor().getUiComponent().addFocusListener(ErrorListenerFactory.getListener(dateEnd));
		sl_panCenter.putConstraint(SpringLayout.WEST, dateEnd, 0,
				SpringLayout.WEST, cmbName);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, dateEnd, 0,
				SpringLayout.SOUTH, lblEndDate);
		sl_panCenter.putConstraint(SpringLayout.EAST, dateEnd, 0,
				SpringLayout.EAST, cmbName);
		panCenter.add(dateEnd);

		JPanel panFooter = new JPanel();
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		panFooter.setBackground(Color.WHITE);
		this.add(panFooter, BorderLayout.SOUTH);

		btnSubmit = new JButton("Submit");
		panFooter.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);

		projectController = ProjectController.getInstance();

		setVisible(true);
		this.repaint();
		this.revalidate();
	}

	public String checkInput() {
		//System.out.println(cmbName.getSelectedItem());
		String text = "";
		if (cmbName.getSelectedItem().equals("")) {
			text += "Please specify project name.\n";
			cmbName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		if (dateStart.getDate().after(dateEnd.getDate())) {
			text += "Start date should occur before the end date.\n";
			dateStart.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			dateEnd.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		}
		return text;

	}

	public void clear()
	{
		cmbName.setSelectedItem("");
		dateStart.setDate(new Date());
		dateEnd.setDate(new Date());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String text = checkInput();
		if (e.getSource() == btnSubmit) {
			if (text.equals("")) {
				Project checkProject;
				Project project = new Project((String)cmbName.getSelectedItem(),
						dateStart.getDate(), dateEnd.getDate());

				projectController.addProject(project);
				projectController.init();
				
				Message msg = new Message(parent, Message.SUCCESS,
						"Project added successfully.");
				//System.out.println("Yey project: " + project.getName());
				clear();
			}
			else{
				new Message(parent, Message.ERROR,
						text);
			}
		}

		else {
			this.repaint();
			this.revalidate();
		}
	}
}
