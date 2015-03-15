package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.ComponentOrientation;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JScrollPane;
import view.purchaseOrder.AddPO;
import view.purchaseOrder.TabPO;
import view.supplier.AddSupplier;

public class MainPanel extends JPanel implements ActionListener {
	private Gui parent;
	private JPanel panTop, panTabs, panUser, panNotif, panContent;
	private JButton btnPurchaseOrders, btnInventory, btnEmployees,
			btnSuppliers, btnCont, btnWarr, btnLogOut, btnClose;
	private JLabel lblUser;
	private CardLayout cl;

	public MainPanel(Gui parent) {
		super();

		cl = new CardLayout();
		this.parent = parent;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		panTop = new JPanel();
		panTop.setPreferredSize(new Dimension(10, 80));
		panTop.setBackground(new Color(32, 130, 213));
		add(panTop, BorderLayout.NORTH);
		panTop.setLayout(new BorderLayout(0, 0));

		panTabs = new JPanel();
		panTabs.setPreferredSize(new Dimension(1000, 10));
		panTabs.setBackground(new Color(32, 130, 213));
		panTop.add(panTabs, BorderLayout.WEST);
		panTabs.setLayout(null);

		panUser = new JPanel();
		panUser.setPreferredSize(new Dimension(400, 60));
		panUser.setBackground(new Color(32, 130, 213));
		panUser.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panTop.add(panUser, BorderLayout.EAST);
		panUser.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblUser = new JLabel("Loki");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 32));
		panUser.add(lblUser);

		JLabel lblFollow = new JLabel("");
		lblFollow.setBorder(new EmptyBorder(0, 5, 0, 0));
		lblFollow.setIcon(new ImageIcon(MainPanel.class
				.getResource("/assets/FollowersLogo.png")));
		panUser.add(lblFollow);

		btnLogOut = new JButton("Log out");
		btnLogOut.setBorder(new EmptyBorder(0, 100, 0, 0));
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Arial", Font.PLAIN, 21));
		btnLogOut.setBorderPainted(false);
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.addActionListener(this);
		panUser.add(btnLogOut);

		btnClose = new Button.ButtonBuilder().img(
				"src/assets/Metro/Delete.png", 40, 40).build();
		btnClose.addActionListener(this);
		panUser.add(btnClose);

		panNotif = new Notification();
                ((Observer)panNotif).update();
                
		panNotif.setPreferredSize(new Dimension(300, 10));
		add(panNotif, BorderLayout.EAST);
		panNotif.setBackground(new Color(32, 130, 213));

		btnWarr = new Button.ButtonBuilder().img(
				"src/assets/Metro/Document3.png", 90, 90).build();
		btnWarr.setContentAreaFilled(false);
		btnWarr.setBorderPainted(false);
		btnWarr.setToolTipText("Warranty");

		btnCont = new Button.ButtonBuilder().img(
				"src/assets/Metro/Documents.png", 90, 90).build();
		btnCont.setContentAreaFilled(false);
		btnCont.setBorderPainted(false);
		btnCont.setToolTipText("Contracts");

		panContent = new JPanel();
		panContent.setBorder(new EmptyBorder(0, 10, 10, 10));
		panContent.setBackground(new Color(32, 130, 213));

		add(panContent, BorderLayout.CENTER);

		panContent.setLayout(cl);
	}

	public void resetTabs() {
		for (int i = 0; i < panTabs.getComponentCount(); i++) {
			try {
				JButton btn = (JButton) panTabs.getComponent(i);
				btn.setLocation(btn.getLocation().x, 25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addTab(JPanel cont, String title) {
		int n = 200 * panTabs.getComponentCount() + 20;

		JButton btn = new JButton(title);
		btn.setFont(new Font("Arial", Font.PLAIN, 20));
		btn.setBorder(null);
		btn.setBackground(Color.WHITE);
		btn.setBounds(n, 25, 190, 45);
		btn.setPreferredSize(new Dimension(190, 45));
		btn.addActionListener(this);
		panTabs.add(btn);
		panContent.add(cont, title);
                

		try {
			setTab((JButton) panTabs.getComponent(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.repaint();
		this.revalidate();
	}
	
	public void enableNotif(boolean stat){
		panNotif.setVisible(false);
	}

	private void setTab(JButton btn) {
		resetTabs();
		int i = 0;
		btn.setLocation(btn.getLocation().x, btn.getLocation().y + 10);
		cl.show(panContent, btn.getText());
                
                if(btn.getText().equals("Purchase Orders"));
                    ((TabPO)panContent.getComponent(0)).updateSupplierBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogOut) {
			parent.setPanel("login");
		} else if (e.getSource() == btnClose) {
			System.exit(0);
		} else {

			try {
				JButton btn = (JButton) e.getSource();
				setTab(btn);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
