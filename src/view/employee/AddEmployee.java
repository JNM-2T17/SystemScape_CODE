package view.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Employee;
import model.Encryption;
import model.User;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.EmployeeController;
import controller.UserController;
import view.ErrorListenerFactory;
import view.Message;

public class AddEmployee extends JPanel implements ActionListener {
	private JTextField txtName;
	private JButton btnSubmit;
	private JComboBox cmbStatus;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirm;
	private JRadioButton rdEmployee, rdTechnician; 
	private JPanel panDetails;
	
	private JFrame parent;
	private EmployeeController employeeController;
	private UserController userController;

	public AddEmployee(JFrame parent) {
		this.setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		this.parent=parent;
		
		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		add(panContent);
		panContent.setLayout(null);

		JLabel lblSupp = new JLabel("Name:");
		lblSupp.setBounds(157, 90, 93, 27);
		panContent.add(lblSupp);

		txtName = new JTextField();
		txtName.addFocusListener(ErrorListenerFactory.getListener(txtName));
		txtName.setBounds(288, 90, 372, 25);
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
		cmbStatus.setBounds(288, 130, 372, 25);
		panContent.add(cmbStatus);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(157, 172, 92, 26);
		panContent.add(lblType);
		
		 rdEmployee = new JRadioButton("Employee");
		 rdEmployee.setSelected(true);
		 rdEmployee.addActionListener(this);
		rdEmployee.setBackground(Color.WHITE);
		rdEmployee.setBounds(288, 168, 161, 35);
		panContent.add(rdEmployee);
		
		 rdTechnician = new JRadioButton("CNS");
		rdTechnician.setBackground(Color.WHITE);
		rdTechnician.setBounds(446, 168, 201, 35);
		rdTechnician.addActionListener(this);
		panContent.add(rdTechnician);
		
		ButtonGroup grp= new ButtonGroup();
		grp.add(rdEmployee);
		grp.add(rdTechnician);
		
		 panDetails = new JPanel();
		panDetails.setBackground(Color.WHITE);
		panDetails.setBounds(157, 219, 503, 125);
		panContent.add(panDetails);
		panDetails.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblUsername = new JLabel("Username:");
		panDetails.add(lblUsername, "cell 0 0");
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(ErrorListenerFactory.getListener(txtUsername));
		panDetails.add(txtUsername, "cell 1 0,growx");
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		panDetails.add(lblPassword, "cell 0 1");
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(ErrorListenerFactory.getListener(txtPassword));
		panDetails.add(txtPassword, "cell 1 1,growx");
		txtPassword.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		panDetails.add(lblConfirmPassword, "cell 0 2");
		
		txtConfirm = new JPasswordField();
		txtConfirm.addFocusListener(ErrorListenerFactory.getListener(txtConfirm));
		panDetails.add(txtConfirm, "cell 1 2,growx");
		txtConfirm.setColumns(10);

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
		
		panDetails.setVisible(false);
		employeeController = EmployeeController.getInstance();
		userController = UserController.getInstance();
	}
	
	public void toggle(boolean stat){
		panDetails.setVisible(stat);
	}
	
	public String checkInput(){
		String text="";	
		if(txtName.getText().equals("")){
			txtName.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			if(rdTechnician.isSelected()) text+="Please specify technician name.\n";
			else text+="Please specify employee name.\n";
		}
		if(rdTechnician.isSelected()){
			if(txtUsername.getText().equals("")){
				txtUsername.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Please specify technician username.\n";
			}
			else if(!txtUsername.getText().equals("")){
				boolean exists = userController.checkExistingUser(txtUsername.getText());
				if(exists == true){
					text+="Username already exists.\n";
				}
			}
			if(txtPassword.getPassword().length==0){
				txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Please specify technician password.\n";
			}
			if(!Arrays.equals(txtPassword.getPassword(), txtConfirm.getPassword())){
				txtConfirm.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Passwords do not match.\n";
			}
		}
		return text; 
	}
	public void clear()
	{
		 txtName.setText("");
		cmbStatus.setSelectedIndex(0);
		txtUsername.setText("");
		txtPassword.setText("");
		txtConfirm.setText("");
		rdEmployee.setSelected(true);
		rdTechnician.setSelected(false);
		toggle(false);
		
	}
	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSubmit) {
			String text=checkInput();
			if(text.equals("")){
				
				Employee employee = new Employee(employeeController.getInstance().getEmployeeID()+1, txtName.getText(), (String)cmbStatus.getSelectedItem(), "employee");
				
				String pass = String.valueOf(txtPassword.getPassword());
				Encryption encryption = new Encryption();
				try {
					pass = encryption.encryptString(pass);
					String gg = encryption.decryptString(pass);
					//System.out.println("User pass:" +gg);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(rdTechnician.isSelected() == true){
					int idKo = employeeController.getInstance().getEmployeeID()+1;
					//System.out.println("ID: "+ idKo);
					User user = new User(txtUsername.getText(), pass, false, idKo);
					//System.out.println("ID ni user: "+user.getEmployeeID());
					userController.addUser(user);
					employee.setType("technician");
				}
				employeeController.addEmployee(employee);
				clear();
				Message msg = new Message(parent, Message.SUCCESS,
						"Employee added successfully.");
				
			}
			else{
				Message msg = new Message(parent, Message.ERROR,
						text);
			}
			
			employeeController.init();
		}
		else if(e.getSource()==rdEmployee){
			if(rdEmployee.isSelected()) 
				toggle(false);
			
			
		}
		else if(e.getSource()==rdTechnician){
			if(rdTechnician.isSelected()){
				toggle(true);
				
			}
		}
	}
}
