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
import java.util.Date;
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
import model.ProjectAssignment;
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

public class EditEmployee extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnSubmit, btnAdmin, btnRemove;
	private JComboBox cmbStatus;
	private JPanel panContact, panClose;
	private ArrayList<ProjectPanel> list;
	private ArrayList<JButton> close;
	private ArrayList<Project> projList;
	private JPanel temp;
	private JComboBox cmbProj;
	private JButton btnAdd;
	private JFrame parent;
	private Employee emp, employee;
	private EmployeeController employeeController;
	private ProjectController projectController;
	private String prevKey;

	public EditEmployee(JFrame parent, Employee emp) {
		this.parent=parent;
		this.emp = emp;
		this.setBackground(Color.WHITE);
		projList=new ArrayList<Project>();
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

		txtName = new JTextField(emp.getName());
		txtName.setBounds(251, 89, 372, 25);
		panContent.add(txtName);
		txtName.setColumns(10);
		//prevKey = Integer.toString(emp.getID());

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
		
		cmbProj=new JComboBox();
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
		
		if(emp.getType().equals("technician") || emp.getType().equals("admin")){
			
			btnAdmin = new JButton("Give Admin Rights");
			panFooter.add(btnAdmin);
			btnAdmin.setForeground(Color.WHITE);
			btnAdmin.setBackground(new Color(32, 130, 213));
			btnAdmin.setFont(new Font("Arial", Font.PLAIN, 18));
			btnAdmin.addActionListener(this);
			
			btnRemove = new JButton("Remove Admin Rights");
			panFooter.add(btnRemove);
			btnRemove.setForeground(Color.WHITE);
			btnRemove.setBackground(new Color(32, 130, 213));
			btnRemove.setFont(new Font("Arial", Font.PLAIN, 18));
			btnRemove.addActionListener(this);
			if(emp.getType().equals("admin")){
				btnAdmin.setVisible(false);
				btnRemove.setVisible(true);
			}
			else{
				btnAdmin.setVisible(true);
				btnRemove.setVisible(false);
			}
		}
		
		
		employeeController = EmployeeController.getInstance();
		projectController = ProjectController.getInstance();
		cmbInit();
		init();
	}
	
	public void cmbInit(){
		Iterator it=projectController.getAll();
		while(it.hasNext()){
			Project temp=(Project) it.next();
			cmbProj.addItem(temp);
		}
	}
	
	public void init(){
		ArrayList<String> projectsList = new ArrayList<String>();
		Iterator projectAssignmentIT = employeeController.getProjectsFromAssignment(Integer.toString(emp.getID()));
		while(projectAssignmentIT.hasNext()){
			ProjectAssignment pa = (ProjectAssignment) projectAssignmentIT.next();
			System.out.println("Employee id: "+pa.getEmployeeID());
			projectsList.add(pa.getProject());
		}
		
		ArrayList<Project> projectArrayList = new ArrayList<Project>();
		for(int i = 0; i<projectsList.size(); i++){
			Project projTemp = (Project) projectController.getObject(projectsList.get(i));
			projectArrayList.add(projTemp);
		}
		
		Iterator it = projectArrayList.iterator();
		//Iterator it = emp.getProjectList();
		while(it.hasNext()){
			Project project = (Project) it.next();
			
			JButton close = new Button.ButtonBuilder().img(
					"src/assets/Round/Delete.png", 30, 30).build();
			close.addActionListener(this);
			this.close.add(close);
			
			ProjectPanel temp = new ProjectPanel();
			temp.setButton(close);
			temp.getBtn().setActionCommand("close");
			
			
                        
            DefaultComboBoxModel model = (DefaultComboBoxModel)cmbProj.getModel();
            
            int i=0;
            while(!model.getElementAt(i).toString().equals(project.toString())) i++;
            
            cmbProj.setSelectedIndex(i);
                        
			temp.setValue(cmbProj.getSelectedItem().toString());            
            cmbProj.removeItemAt(cmbProj.getSelectedIndex());
            cmbProj.setSelectedIndex(0);
                       
			panClose.add(temp);
			list.add(temp);
			projList.add(project);
			
			if (!panClose.isVisible()) {
				panClose.setVisible(true);
			}
			
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			this.repaint();
			this.revalidate();
                        
		}
	}
	
	
	
	public void addProject(JComboBox pan){
			JButton close = new Button.ButtonBuilder().img(
					"src/assets/Round/Delete.png", 30, 30).build();
			close.addActionListener(this);
			this.close.add(close);

			
			ProjectPanel temp = new ProjectPanel();
			temp.setButton(close);
			temp.getBtn().setActionCommand("close");

			temp.setValue(((Project) pan.getSelectedItem()).getName());

			panClose.add(temp);
			list.add(temp);
			projList.add((Project) pan.getSelectedItem());
			
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
			System.out.println(projList);
			String text=checkInput();
			if(text.equals("")){
				
				Employee checkEmployee;
				employee = new Employee(emp.getID(), txtName.getText(), (String)cmbStatus.getSelectedItem(), emp.getType());
				
				int employeeID = emp.getID();
				System.out.println("Employee id selected: "+employeeID);
				System.out.println("Employee status: "+employee.getStatus());
				int i = 0;
				System.out.println("ProjList size: "+projList.size());
				
				
				for(i = 0; i<projList.size(); i++){
					System.out.println("project k: "+projList.get(i).getName());
					
					employee.addProject(projList.get(i).getName(), projList.get(i).getStartDate(), projList.get(i).getEndDate());
				}
				prevKey = Integer.toString(employee.getID());
				employeeController.editEmployee(employee, prevKey);
				employeeController.init();
				
				Message msg = new Message(parent, Message.SUCCESS,
						"Employee added successfully.");
				
			}else{
				new Message(parent, Message.ERROR,
						text);
			}

			
			
		}
		else if (e.getActionCommand().equals("add")) {
			addProject((JComboBox) temp.getComponent(0));
			((JComboBox) temp.getComponent(0)).removeItem(((JComboBox) temp.getComponent(0)).getSelectedItem());
			if(((JComboBox) temp.getComponent(0)).getItemCount()==0){
				((JComboBox) temp.getComponent(0)).setVisible(false);
				btnAdd.setVisible(false);
				
			}
			else ((JComboBox) temp.getComponent(0)).setSelectedIndex(0);
			
			
			
		} 
		else if(e.getSource()==btnAdmin){
			String usernameToGet = employeeController.getUsernameUsingID(Integer.toString(emp.getID()));
			employeeController.turnAdmin(usernameToGet);
			emp.setType("admin");
			employeeController.editEmployee(emp, Integer.toString(emp.getID()));
			System.out.println("Username to get: "+usernameToGet);
			Message msg = new Message(parent, Message.SUCCESS,
					"Admin rights successfully granted.");
			btnAdmin.setVisible(false);
			btnRemove.setVisible(true);
		}
		else if(e.getSource()==btnRemove){
			String usernameToGet = employeeController.getUsernameUsingID(Integer.toString(emp.getID()));
			employeeController.removeAdmin(usernameToGet);
			emp.setType("technician");
			employeeController.editEmployee(emp, Integer.toString(emp.getID()));
			Message msg = new Message(parent, Message.SUCCESS,
					"Admin rights successfully removed.");
			btnAdmin.setVisible(true);
			btnRemove.setVisible(false);
		}
		else {
			int index = close.indexOf(e.getSource());
			
			((JComboBox) temp.getComponent(0)).addItem(projList.get(index));
			close.remove(index);
			panClose.remove(index);
			list.remove(index);
			projList.remove(index);
			panClose.setMaximumSize(new Dimension(360,
					panClose.getComponentCount() * 37));
			((JComboBox) temp.getComponent(0)).setVisible(true);
			btnAdd.setVisible(true);
			this.repaint();
			this.revalidate();
		}
	}
}
