package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.util.Arrays;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import model.database.UserDAO;

public class Settings extends PopUp implements ActionListener {
	private JPasswordField txtOld;
	private JPasswordField txtNew;
	private JPasswordField txtConf;
	private JButton btnSubmit, btnNotifSubmit;
	private JSpinner spnWarranty, spnContract;
	private JFrame parent;
	private JComboBox cmbWarranty, cmbContract;
	private CardLayout cl;
	private JPanel panCenter;
	private JButton btnChangePassword, btnNotificationSettings;
	private String user;
	
	private boolean isPass=true;

	public Settings(JFrame parent, String user) {
		super(parent);
		this.parent = parent;
		this.user = user;
		JPanel panMain = new JPanel();
		add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panTabs = new JPanel();
		panTabs.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panTabs.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		panMain.add(panTabs, BorderLayout.NORTH);

		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Arial", Font.PLAIN, 16));
		btnChangePassword.addActionListener(this);
		btnChangePassword.setPreferredSize(new Dimension(150, 30));
		btnChangePassword.setBorder(BorderFactory.createLineBorder(new Color(
				32, 130, 213), 1));
		btnChangePassword.setBackground(Color.white);
		panTabs.add(btnChangePassword);

		btnNotificationSettings = new JButton("Notification Settings");
		btnNotificationSettings.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNotificationSettings.setPreferredSize(new Dimension(150, 30));
		btnNotificationSettings.addActionListener(this);
		btnNotificationSettings.setBorder(BorderFactory.createLineBorder(
				new Color(32, 130, 213), 1));
		btnNotificationSettings.setBackground(Color.white);
		panTabs.add(btnNotificationSettings);

		panCenter = new JPanel();
		panMain.add(panCenter, BorderLayout.CENTER);
		cl = new CardLayout();
		panCenter.setLayout(cl);

		// setLayout(new BorderLayout(0, 0));

		JPanel panPass = new JPanel();
		panCenter.add(panPass, "pass");
		panMain.setPreferredSize(new Dimension(470, 250));
		panMain.setSize(new Dimension(470, 250));
		panPass.setLayout(new BorderLayout(0, 0));

		JPanel panFooter = new JPanel();
		panPass.add(panFooter, BorderLayout.SOUTH);
		panFooter.setBackground(Color.WHITE);

		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.white);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 16));
		
		panFooter.add(btnSubmit);
		
		JPanel panContent = new JPanel();
		panPass.add(panContent, BorderLayout.CENTER);
		panContent.setBackground(Color.WHITE);

		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblOldPassword = new JLabel("Old Password:");
		panContent.add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New Password:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblOldPassword, 0,
				SpringLayout.WEST, lblNewPassword);
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblOldPassword, -19,
				SpringLayout.NORTH, lblNewPassword);
		panContent.add(lblNewPassword);

		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password:");
		sl_panContent.putConstraint(SpringLayout.WEST, lblNewPassword, 0,
				SpringLayout.WEST, lblConfirmNewPassword);
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblNewPassword, -16,
				SpringLayout.NORTH, lblConfirmNewPassword);
		sl_panContent.putConstraint(SpringLayout.NORTH, lblConfirmNewPassword,
				116, SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblConfirmNewPassword,
				72, SpringLayout.WEST, panContent);
		panContent.add(lblConfirmNewPassword);

		txtOld = new JPasswordField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtOld, 47,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, txtOld, 68,
				SpringLayout.EAST, lblOldPassword);
		sl_panContent.putConstraint(SpringLayout.EAST, txtOld, 233,
				SpringLayout.EAST, lblOldPassword);
		panContent.add(txtOld);
		txtOld.setColumns(10);

		txtNew = new JPasswordField();
		sl_panContent.putConstraint(SpringLayout.WEST, txtNew, 0,
				SpringLayout.WEST, txtOld);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtNew, 0,
				SpringLayout.SOUTH, lblNewPassword);
		sl_panContent.putConstraint(SpringLayout.EAST, txtNew, 0,
				SpringLayout.EAST, txtOld);
		panContent.add(txtNew);
		txtNew.setColumns(10);

		txtConf = new JPasswordField();
		sl_panContent.putConstraint(SpringLayout.WEST, txtConf, 0,
				SpringLayout.WEST, txtOld);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtConf, 0,
				SpringLayout.SOUTH, lblConfirmNewPassword);
		sl_panContent.putConstraint(SpringLayout.EAST, txtConf, 0,
				SpringLayout.EAST, txtOld);
		panContent.add(txtConf);
		txtConf.setColumns(10);

		JPanel panNotif = new JPanel();
		panCenter.add(panNotif, "notif");
		panNotif.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panNotif.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lblWarrantyDuration = new JLabel("Warranty Duration:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblWarrantyDuration, 55,
				SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblWarrantyDuration, 80,
				SpringLayout.WEST, panel);
		panel.add(lblWarrantyDuration);

		JLabel lblContractDuration = new JLabel("Contract Duration:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblContractDuration, 23,
				SpringLayout.SOUTH, lblWarrantyDuration);
		sl_panel.putConstraint(SpringLayout.WEST, lblContractDuration, 0,
				SpringLayout.WEST, lblWarrantyDuration);
		panel.add(lblContractDuration);

		spnWarranty = new JSpinner();
		spnWarranty.setValue(2);
		sl_panel.putConstraint(SpringLayout.NORTH, spnWarranty, -3,
				SpringLayout.NORTH, lblWarrantyDuration);
		sl_panel.putConstraint(SpringLayout.WEST, spnWarranty, 18,
				SpringLayout.EAST, lblWarrantyDuration);
		sl_panel.putConstraint(SpringLayout.EAST, spnWarranty, 63,
				SpringLayout.EAST, lblWarrantyDuration);
		panel.add(spnWarranty);

		spnContract = new JSpinner();
		spnContract.setValue(2);
		sl_panel.putConstraint(SpringLayout.NORTH, spnContract, -3,
				SpringLayout.NORTH, lblContractDuration);
		sl_panel.putConstraint(SpringLayout.WEST, spnContract, 22,
				SpringLayout.EAST, lblContractDuration);
		sl_panel.putConstraint(SpringLayout.EAST, spnContract, 0,
				SpringLayout.EAST, spnWarranty);
		panel.add(spnContract);

		String[] opt = { "Days", "Weeks", "Months" };
		cmbWarranty = new JComboBox(opt);
		cmbWarranty.setSelectedIndex(1);
		cmbWarranty.setBackground(Color.white);
		sl_panel.putConstraint(SpringLayout.NORTH, cmbWarranty, -3,
				SpringLayout.NORTH, lblWarrantyDuration);
		sl_panel.putConstraint(SpringLayout.WEST, cmbWarranty, 6,
				SpringLayout.EAST, spnWarranty);
		sl_panel.putConstraint(SpringLayout.EAST, cmbWarranty, 127,
				SpringLayout.EAST, spnWarranty);
		panel.add(cmbWarranty);

		cmbContract = new JComboBox(opt);
		cmbContract.setSelectedIndex(1);
		cmbContract.setBackground(Color.white);
		sl_panel.putConstraint(SpringLayout.NORTH, cmbContract, -3,
				SpringLayout.NORTH, lblContractDuration);
		sl_panel.putConstraint(SpringLayout.WEST, cmbContract, 6,
				SpringLayout.EAST, spnContract);
		sl_panel.putConstraint(SpringLayout.EAST, cmbContract, 0,
				SpringLayout.EAST, cmbWarranty);
		panel.add(cmbContract);

		JPanel panNotFooter = new JPanel();
		panNotFooter.setBackground(Color.WHITE);
		panNotif.add(panNotFooter, BorderLayout.SOUTH);

		btnNotifSubmit = new JButton("Submit");
		btnNotifSubmit.setForeground(Color.white);
		btnNotifSubmit.setBackground(new Color(32, 130, 213));
		btnNotifSubmit.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNotifSubmit.addActionListener(this);
		panNotFooter.add(btnNotifSubmit);
		
		btnSubmit.addActionListener(this);
		getClose().addActionListener(this);
		setContent(panMain);
		//

		btnNotificationSettings.setBackground(Color.LIGHT_GRAY);
		
		cl.show(panCenter, "pass");
		this.setVisible(true);
		this.repaint();
		this.revalidate();
		
	}
	
	public String checkInput(){
		String text = "";
		if(txtOld.getPassword().length == 0 && txtNew.getPassword().length == 0 && txtConf.getPassword().length == 0){
			txtOld.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			txtNew.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			txtConf.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			text+="Please complete all fields.\n";	
		}else{
			if(txtOld.getPassword().length == 0){
				txtOld.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Please specify old password.\n";	
			}
			if(txtNew.getPassword().length == 0){
				txtNew.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Please specify new password.\n";	
			}
			if(txtConf.getPassword().length == 0){
				txtConf.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Please specify new password.\n";	
			}
			if(!Arrays.equals(txtNew.getPassword(), txtConf.getPassword())){
				txtConf.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				text+="Passwords do not match.\n";
			}
		}
		return text;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChangePassword) {
			System.out.println("X");
			cl.show(panCenter, "pass");
			btnChangePassword.setBackground(Color.white);
			btnNotificationSettings.setBackground(Color.LIGHT_GRAY);
//			isPass=true;
			System.out.println("here");
			
		} else if (e.getSource() == btnNotificationSettings) {
			System.out.println("Z");
			cl.show(panCenter, "notif");
			btnNotificationSettings.setBackground(Color.white);
			btnChangePassword.setBackground(Color.LIGHT_GRAY);
//			isPass=false;
			System.out.println("HERE");
		} else if(e.getSource()==btnSubmit){
				Message msg = new Message(parent, Message.SUCCESS,
						"Password successfully edited.");
		}else if(e.getSource()==btnNotifSubmit){
                                UserDAO userDAO = new UserDAO();
                                userDAO.updateNotificationDuration(spnContract.getValue().toString() + " " + cmbContract.getSelectedItem().toString(), spnWarranty.getValue().toString() + " " + cmbWarranty.getSelectedItem().toString(), user);
                                Message msg = new Message(parent, Message.SUCCESS,
						"Notification settings successfully edited.");
                                this.dispose();
			
		}
		else if(e.getSource()==getClose()){
			this.dispose();
		}
	}
}
