package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

public abstract class PopUp extends JDialog {
	private JFrame parent;
	private JButton btnClose;
	private JPanel panMain;
	private SpringLayout springLayout, springMain;
	private JPanel panContent;

	public PopUp(JFrame parent) {
		super();
		this.setModal(true);

		this.parent = parent;

		// this.getContentPane().setOpaque(false);
		springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		btnClose = new Button.ButtonBuilder().img(
				"/assets/Round/Cancel.png", 35, 35).build();
		springLayout.putConstraint(SpringLayout.NORTH, btnClose, 0,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnClose, 0,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnClose);

		panMain = new JPanel();
		panMain.setBorder(new LineBorder(new Color(32, 130, 213), 3, true));
		panMain.setBackground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, panMain, 20,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panMain, -20,
				SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panMain, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panMain, -10,
				SpringLayout.SOUTH, getContentPane());
		getContentPane().add(panMain);
		springMain = new SpringLayout();
		panMain.setLayout(springMain);

		getContentPane().setBackground(Color.WHITE);
		
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setPreferredSize(new Dimension(600, 250));
		this.setSize(new Dimension(600, 250));

		int x = parent.getLocation().x;
		int y = parent.getLocation().y;

		x += (parent.getWidth() / 2);
		y += (parent.getHeight() / 2);

		x -= (this.getWidth() / 2);
		y -= (this.getHeight() / 2);

		this.setLocation(new Point(x, y));

	}
	
	public void setClosable(boolean stat){
		btnClose.setVisible(stat);
	}

	public JButton getClose() {
		return btnClose;
	}

	public void setContent(JPanel pan) {
		panContent = pan;
		panContent.setBackground(Color.WHITE);
		springMain.putConstraint(SpringLayout.NORTH, panContent, 10,
				SpringLayout.NORTH, panMain);
		springMain.putConstraint(SpringLayout.WEST, panContent, 10,
				SpringLayout.WEST, panMain);
		springMain.putConstraint(SpringLayout.SOUTH, panContent, -10,
				SpringLayout.SOUTH, panMain);
		springMain.putConstraint(SpringLayout.EAST, panContent, -10,
				SpringLayout.EAST, panMain);
		panMain.add(panContent);
		
		
		this.setPreferredSize(new Dimension(pan.getWidth()+55, pan.getHeight()+55));
		this.setSize(new Dimension(pan.getWidth()+55, pan.getHeight()+55));
		
		int x = parent.getLocation().x;
		int y = parent.getLocation().y;

		x += (parent.getWidth() / 2);
		y += (parent.getHeight() / 2);

		x -= (this.getWidth() / 2);
		y -= (this.getHeight() / 2);

		this.setLocation(new Point(x, y));
		
		this.repaint();
		this.revalidate();
		
	}

}
