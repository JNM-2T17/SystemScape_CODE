package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;

import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Message extends PopUp implements ActionListener{
	public static final int INFORMATION = 0;
	public static final int WARNING = 1;
	public static final int SUCCESS = 2;
	public static final int ERROR = 3;

	public static final int YES = 4;
	public static final int OK = 5;
	public static final int CANCEL = 6;

	private JButton btnOk, btnCancel, btnYes;
	private int value=-1;

	public Message(JFrame parent, int type, String text) {
		super(parent);
		System.out.println("Mesage");

		getClose().addActionListener(this);
		
		JPanel panMain=new JPanel();
		panMain.setSize(new Dimension(1000,275));
		panMain.setPreferredSize(new Dimension(1000,275));
		setContent(panMain);
		
		panMain.setBackground(Color.WHITE);
		SpringLayout springLayout = new SpringLayout();
		panMain.setLayout(springLayout);
		
		JLabel icon = new JLabel("");
		if (type == Message.INFORMATION)
			icon.setIcon(new ImageIcon(Message.class
					.getResource("/assets/Metro/Info.png")));
		else if (type == Message.WARNING)
			icon.setIcon(new ImageIcon(Message.class
					.getResource("/assets/Metro/Warning.png")));
		else if (type == Message.SUCCESS)
			icon.setIcon(new ImageIcon(Message.class
					.getResource("/assets/Metro/Check.png")));
		else if (type == Message.ERROR)
			icon.setIcon(new ImageIcon(Message.class
					.getResource("/assets/Metro/Delete.png")));
		else
			icon.setIcon(new ImageIcon(Message.class
					.getResource("/assets/Metro/Info.png")));

		springLayout.putConstraint(SpringLayout.NORTH, icon, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, icon, 10,
				SpringLayout.WEST, this);
		panMain.add(icon);

		JPanel panContent = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panContent, 10,
				SpringLayout.NORTH, panMain);
		springLayout.putConstraint(SpringLayout.WEST, panContent, 10,
				SpringLayout.EAST, icon);
		springLayout.putConstraint(SpringLayout.SOUTH, panContent, -10,
				SpringLayout.SOUTH, panMain);
		springLayout.putConstraint(SpringLayout.EAST, panContent, -10,
				SpringLayout.EAST, panMain);
		panMain.add(panContent);
		panContent.setLayout(new BorderLayout(0, 0));

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panContent.add(panFooter, BorderLayout.SOUTH);

		btnOk = new JButton("Ok");
		btnOk.setForeground(Color.WHITE);
		btnOk.setBackground(new Color(32, 130, 213));
		btnOk.setFont(new Font("Arial", Font.PLAIN, 32));
		btnOk.addActionListener(this);
		panFooter.add(btnOk);

		btnYes = new JButton("Yes");
		btnYes.setForeground(Color.WHITE);
		btnYes.setBackground(new Color(32, 130, 213));
		btnYes.setFont(new Font("Arial", Font.PLAIN, 32));
		btnYes.addActionListener(this);
		panFooter.add(btnYes);

		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(32, 130, 213));
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 32));
		btnCancel.addActionListener(this);
		panFooter.add(btnCancel);
		
		if(type==Message.ERROR||type==Message.SUCCESS){
			btnYes.setVisible(false);
			btnCancel.setVisible(false);
		}
		else{
			btnOk.setVisible(false);
		}

		JTextArea msg = new JTextArea();
		msg.setText(text);
		msg.setFont(new Font("Arial", Font.PLAIN, 32));
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setBackground(Color.WHITE);
		msg.setBorder(new EmptyBorder(10, 10, 10, 10));
		panContent.add(msg, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("lol");
		if (e.getSource()==getClose()){
			this.dispose();
		}
		else if (e.getSource() == btnCancel) {
			value=Message.CANCEL;
			this.dispose();
		} else if (e.getSource() == btnYes) {
			value=Message.YES;
			this.dispose();
		} else if (e.getSource() == btnOk) {
			value=Message.OK;
			this.dispose();
		}
	}
	


}
