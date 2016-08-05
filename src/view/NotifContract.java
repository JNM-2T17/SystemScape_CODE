package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.InventoryItem;

public class NotifContract extends JPanel implements ActionListener{
	private JButton btnView;
	private InventoryItem item;
	private MainPanel mainPanel;

	public NotifContract(MainPanel mainPanel, InventoryItem item, int days) {
		this.mainPanel=mainPanel;
		this.item=item;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		setBackground(new Color(246, 246, 246));
		
		JPanel panLeft = new JPanel();
		panLeft.setBackground(new Color(246, 246, 246));
		panLeft.setPreferredSize(new Dimension(40, 10));
		add(panLeft, BorderLayout.WEST);
		SpringLayout sl_panLeft = new SpringLayout();
		panLeft.setLayout(sl_panLeft);

                JLabel lblDuration = new JLabel(days+"dy");
		lblDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuration.setForeground(Color.white);
		lblDuration.setFont(new Font("Arial", Font.PLAIN, 14));
		sl_panLeft.putConstraint(SpringLayout.WEST, lblDuration, 0,
				SpringLayout.WEST, panLeft);
		sl_panLeft.putConstraint(SpringLayout.EAST, lblDuration, 0,
				SpringLayout.EAST, panLeft);
		sl_panLeft.putConstraint(SpringLayout.NORTH, lblDuration, 0,
				SpringLayout.NORTH, panLeft);
		sl_panLeft.putConstraint(SpringLayout.SOUTH, lblDuration, -10,
				SpringLayout.SOUTH, panLeft);
		panLeft.add(lblDuration);

		JLabel redDuration = new ImageLabel.ImageBuilder().img(
				"/assets/red.png", 0.08).build();
		sl_panLeft.putConstraint(SpringLayout.NORTH, redDuration, 0,
				SpringLayout.NORTH, panLeft);
		panLeft.add(redDuration);

		JLabel lblContract = new JLabel(item.getName());
		lblContract.setHorizontalAlignment(SwingConstants.CENTER);
		lblContract.setFont(new Font("Arial", Font.PLAIN, 14));
		add(lblContract, BorderLayout.CENTER);

		btnView = new Button.ButtonBuilder().img(
				"/assets/Round/Preview.png", 35, 35).build();
		btnView.addActionListener(this);
		add(btnView, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mainPanel.setView(item);
	}
}
