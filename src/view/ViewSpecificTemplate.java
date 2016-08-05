package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.border.EmptyBorder;

public abstract class ViewSpecificTemplate extends JPanel implements ActionListener{
	private JPanel panPrev, panNext, panCenter, panContent, panButtons;
	private JButton btnPrev, btnNext, btnEdit, btnClose;
	private JPanel panButtonContainer;
	
	
	public ViewSpecificTemplate() {
		setLayout(new BorderLayout(0, 0));
		
		
		panPrev = new JPanel();
		panPrev.setBorder(new EmptyBorder(0, 10, 0, 0));
		panPrev.setBackground(Color.WHITE);
		add(panPrev, BorderLayout.WEST);
		panPrev.setLayout(new BorderLayout(0, 0));
		
		btnPrev = new Button.ButtonBuilder().img("/assets/back.png", 80,60).build();
		btnPrev.setActionCommand("prev");
		panPrev.add(btnPrev, BorderLayout.CENTER);
		
		panNext = new JPanel();
		panNext.setBorder(new EmptyBorder(0, 0, 0, 10));
		panNext.setBackground(Color.WHITE);
		add(panNext, BorderLayout.EAST);
		panNext.setLayout(new BorderLayout(0, 0));
		
		btnNext = new Button.ButtonBuilder().img("/assets/next.png", 80,60).build();
		btnNext.setActionCommand("next");
		panNext.add(btnNext, BorderLayout.CENTER);
		
		panCenter = new JPanel();
		panCenter.setBackground(Color.WHITE);
		add(panCenter, BorderLayout.CENTER);
		SpringLayout sl_panCenter = new SpringLayout();
		panCenter.setLayout(sl_panCenter);
		
//		panContent = new JPanel();
//		panCenter.add(panContent, BorderLayout.CENTER);
//		panContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panButtons = new JPanel();
		panButtons.setOpaque(false);
		sl_panCenter.putConstraint(SpringLayout.NORTH, panButtons, 10, SpringLayout.NORTH, panCenter);
		panButtons.setBackground(new Color(0, 0, 0, 0));
		sl_panCenter.putConstraint(SpringLayout.WEST, panButtons, -150, SpringLayout.EAST, panCenter);
		sl_panCenter.putConstraint(SpringLayout.EAST, panButtons, -10, SpringLayout.EAST, panCenter);
		panCenter.add(panButtons);
		panButtons.setLayout(new BorderLayout(0, 0));
		
		panButtonContainer = new JPanel();
		panButtonContainer.setOpaque(false);
		panButtonContainer.setBackground(new Color(0, 0, 0, 0));
		panButtons.add(panButtonContainer, BorderLayout.EAST);
		
		btnEdit = new Button.ButtonBuilder().img("/assets/Round/Note2.png", 40,40).build();
		btnEdit.setActionCommand("edit");
		panButtonContainer.add(btnEdit);
		
		btnClose = new Button.ButtonBuilder().img("/assets/Round/Cancel.png", 40,40).build();
		btnClose.setActionCommand("close");
		panButtonContainer.add(btnClose);
		
		panContent = new JPanel();
		panContent.setBackground(SystemColor.control);
		panContent.setBorder(new LineBorder(new Color(32, 130, 213)));
		sl_panCenter.putConstraint(SpringLayout.NORTH, panContent, 30, SpringLayout.NORTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.WEST, panContent, 30, SpringLayout.WEST, panCenter);
		sl_panCenter.putConstraint(SpringLayout.SOUTH, panContent, -30, SpringLayout.SOUTH, panCenter);
		sl_panCenter.putConstraint(SpringLayout.EAST, panContent, -30, SpringLayout.EAST, panCenter);
		panCenter.add(panContent);
		panContent.setLayout(new BorderLayout(0, 0));
		
		
	}
	
	public abstract void setButtonObjects();
	
	public JButton getEditButton(){
		return btnEdit;
	}
	
	public JButton getCloseButton(){
		return btnClose;
	}
	
	public JButton getPrevButton(){
		return btnPrev;
	}
	
	public JButton getNextButton(){
		return btnNext;
	}
	public void setContentPanel(JPanel panel){
		//panContent = panel;
//		panCenter.add(panContent, BorderLayout.CENTER);
//		panContent.setLayout(new BorderLayout(0, 0));
		panContent.add(panel, BorderLayout.CENTER);
		//panContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
}
