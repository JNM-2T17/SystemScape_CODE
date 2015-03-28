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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Supplier;
import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import view.Button;
import view.Message;
import view.Button.ButtonBuilder;
import view.supplier.Contact;

public class EditEmployee extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnSubmit, btnAdmin;
	private JComboBox cmbStatus;
	private JPanel panContact, panClose;
	private ArrayList<ProjectPanel> list;
	private ArrayList<JButton> close;
	private JPanel temp;
	private JComboBox cmbProj;
	private JButton btnAdd;
	private JFrame parent;

	public EditEmployee(JFrame parent) {
		this.parent=parent;
		this.setBackground(Color.WHITE);
		list=new ArrayList<ProjectPanel>();
		close= new ArrayList<JButton>();
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
		
		String[] opt={"Active", "On Leave"};

		cmbStatus = new JComboBox(opt);
		cmbStatus.setEditable(true);
		AutoCompleteDecorator.decorate(cmbStatus);
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
//		panClose.setAlignmentX(Component.LEFT_ALIGNMENT);
		panContact.add(panClose, "");
		panClose.setLayout(new BoxLayout(panClose, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(251, 180);
		scrollPane.setSize(400, 200);
		panContent.add(scrollPane, "cell 2 5,grow");
		scrollPane.setBorder(null);
		scrollPane.setViewportView(panContact);
		panClose.setVisible(false);
		
		String[] opt2={"sample", "sample2"};
		cmbProj=new JComboBox(opt2);
		cmbProj.setBackground(Color.white);
		cmbProj.setPreferredSize(new Dimension(200, 25));
		btnAdd = new Button.ButtonBuilder().img("src/assets/Round/Add.png", 30,
				30).build();

		temp = new JPanel();
		FlowLayout flowLayout = (FlowLayout) temp.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		temp.setLayout(flowLayout);
		temp.add(cmbProj);
		temp.add(btnAdd);
		temp.setPreferredSize(new Dimension(365, 37));
		temp.setBackground(Color.WHITE);
		btnAdd.setActionCommand("add");
		btnAdd.addActionListener(this);
		panContact.add(temp, "newline,alignx left,aligny top");



		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panFooter.setBorder(new EmptyBorder(0, 0, 50, 0));
		add(panFooter, BorderLayout.SOUTH);

		btnSubmit = new JButton("Submit");
		panFooter.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.addActionListener(this);
		
		btnAdmin = new JButton("Give Admin Rights");
		panFooter.add(btnAdmin);
		btnAdmin.setForeground(Color.WHITE);
		btnAdmin.setBackground(new Color(32, 130, 213));
		btnAdmin.setFont(new Font("Arial", Font.PLAIN, 18));
		btnAdmin.addActionListener(this);
	}
	
	public void addProject(JComboBox pan){
			JButton close = new Button.ButtonBuilder().img(
					"src/assets/Round/Delete.png", 30, 30).build();
			close.addActionListener(this);
			this.close.add(close);

			
			ProjectPanel temp = new ProjectPanel();
			temp.setButton(close);
			temp.getBtn().setActionCommand("close");

			temp.setValue((String) pan.getSelectedItem());

			pan.setSelectedIndex(0);

			panClose.add(temp);
			list.add(temp);
			
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
			
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			this.repaint();
			this.revalidate();
	}
	
	public String checkInput(){
		String text="";
		if(txtName.getText().equals("")){
			txtName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			text+="Please specify employee name.\n";
		}
		return text;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSubmit){
			String text=checkInput();
			if(text.equals("")){
				
			}
			else{
				new Message(parent, Message.ERROR,
						text);
			}
			
		}
		else if (e.getActionCommand().equals("add")) {
//			System.out.println(((JPanel) panContact.getComponent(0)).getComponentCount());
//			System.out.println(temp.getComponentCount());
			addProject((JComboBox) temp.getComponent(0));
			((JComboBox) temp.getComponent(0)).removeItem(((JComboBox) temp.getComponent(0)).getSelectedItem());
		} else {
			int index = close.indexOf(e.getSource());
			
			((JComboBox) temp.getComponent(0)).addItem(list.get(index).getValue());
			close.remove(index);
			panClose.remove(index);
			list.remove(index);
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			this.repaint();
			this.revalidate();
		}
	}
}
