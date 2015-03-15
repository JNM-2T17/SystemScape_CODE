package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.Color;

public abstract class ViewSpecificTemplate extends JPanel {
	private JPanel panPrev, panNext, panCenter, panContent, panButtons;
	private JButton btnPrev, btnNext, btnEdit, btnClose;
	private JPanel panButtonContainer;
	
	
	public ViewSpecificTemplate() {
		setLayout(new BorderLayout(0, 0));
		
		panPrev = new JPanel();
		panPrev.setBackground(Color.WHITE);
		add(panPrev, BorderLayout.WEST);
		panPrev.setLayout(new BorderLayout(0, 0));
		
		btnPrev = new Button.ButtonBuilder().img("src/assets/Round/Previous.png", 50,50).build();
		btnPrev.setActionCommand("prev");
		panPrev.add(btnPrev, BorderLayout.CENTER);
		
		panNext = new JPanel();
		panNext.setBackground(Color.WHITE);
		add(panNext, BorderLayout.EAST);
		panNext.setLayout(new BorderLayout(0, 0));
		
		btnNext = new Button.ButtonBuilder().img("src/assets/Round/Next.png", 50,50).build();
		btnNext.setActionCommand("next");
		panNext.add(btnNext, BorderLayout.CENTER);
		
		panCenter = new JPanel();
		add(panCenter, BorderLayout.CENTER);
		panCenter.setLayout(new BorderLayout(0, 0));
		
//		panContent = new JPanel();
//		panCenter.add(panContent, BorderLayout.CENTER);
//		panContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panButtons = new JPanel();
		panCenter.add(panButtons, BorderLayout.NORTH);
		panButtons.setLayout(new BorderLayout(0, 0));
		
		panButtonContainer = new JPanel();
		panButtons.add(panButtonContainer, BorderLayout.EAST);
		
		btnEdit = new Button.ButtonBuilder().img("src/assets/Round/Note2.png", 40,40).build();
		btnEdit.setActionCommand("edit");
		panButtonContainer.add(btnEdit);
		
		btnClose = new Button.ButtonBuilder().img("src/assets/Round/Cancel.png", 40,40).build();
		btnClose.setActionCommand("close");
		panButtonContainer.add(btnClose);
	}
	
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
		panContent = panel;
		panCenter.add(panContent, BorderLayout.CENTER);
		//panContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
}
