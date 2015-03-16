package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NotifWarranty extends JPanel {
	private JButton btnView;

	public NotifWarranty(String name, int days) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		setBackground(new Color(246, 246, 246));
		
		JPanel panLeft = new JPanel();
		panLeft.setBackground(new Color(246, 246, 246));
		panLeft.setPreferredSize(new Dimension(40, 10));
		add(panLeft, BorderLayout.WEST);
		SpringLayout sl_panLeft = new SpringLayout();
		panLeft.setLayout(sl_panLeft);

		String suffix= (days>6) ? "wk":"dy";
		int prefix= (days>6) ? days/7:days;
		JLabel lblDuration = new JLabel(prefix+""+suffix);
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
				"src/assets/red.png", 0.08).build();
		sl_panLeft.putConstraint(SpringLayout.NORTH, redDuration, 0,
				SpringLayout.NORTH, panLeft);
		panLeft.add(redDuration);

		JLabel lblWarranty = new JLabel(name);
		lblWarranty.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarranty.setFont(new Font("Arial", Font.PLAIN, 14));
		add(lblWarranty, BorderLayout.CENTER);

		btnView = new Button.ButtonBuilder().img(
				"src/assets/Round/Preview.png", 35, 35).build();
		add(btnView, BorderLayout.EAST);
	}
}
