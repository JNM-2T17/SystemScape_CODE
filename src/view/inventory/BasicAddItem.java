package view.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

public class BasicAddItem extends JPanel implements ActionListener, ItemPanelTemplate{
	
	private PanelRegistry panelRegistry;
	private JButton btnSubmit;
	private JPanel panSubmit;
	private JPanel panQuad2;
	private JPanel panQuad1;
	private JPanel panQuad3;
	private JPanel panQuad4;
	
	
	public BasicAddItem()
	{
		
		setLayout(new MigLayout("", "[450px,grow][450px,grow]", "[207.00,grow][207.00,grow][]"));
		setBackground(Color.white);
		panQuad2 = new JPanel();
		panQuad2.setBackground(Color.white);
		add(panQuad2, "cell 0 0,grow");
		
		panQuad1 = new JPanel();
		panQuad1.setBackground(Color.white);
		add(panQuad1, "cell 1 0,grow");
		
		panQuad3 = new JPanel();
		panQuad3.setBackground(Color.white);
		add(panQuad3, "cell 0 1,grow");
		
		panQuad4 = new JPanel();
		panQuad4.setBackground(Color.white);
		add(panQuad4, "cell 1 1,grow");
		
		panSubmit = new JPanel();

		
		panSubmit = new JPanel();
		panSubmit.setBackground(Color.white);
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		panSubmit.add(btnSubmit);
		btnSubmit.setForeground(Color.white);
		btnSubmit.setBackground(new Color(32, 130, 213));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		
		add(panSubmit, "cell 0 2 2 1,alignx center");
		
	}
	@Override
	public void renderPanel() {
		// TODO Auto-generated method stub
		repaint();
		revalidate();
		setVisible(true);
	}
	
	public void assignToQuad(JPanel itemPanel, int quadNo)
	{
		switch(quadNo)
		{
		case 1:
			
			add(itemPanel, "cell 1 0,grow");
			remove(panQuad1);
			panQuad1 = itemPanel;
			break;
		case 2:
			add(itemPanel, "cell 0 0,grow");
			remove(panQuad2);
			panQuad2 = itemPanel;
			break;
		case 3:
			remove(panQuad3);
			panQuad3 = itemPanel;
			add(panQuad3, "cell 0 1,grow");
			break;
		case 4:
			remove(panQuad4);
			panQuad4 = itemPanel;
			add(panQuad4, "cell 1 1,grow");
			break;
		}
		repaint();
		revalidate();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnSubmit)
		{
			PanelRegistry.getInstance().retrieveInformationFromAll();
		}
	}
	
}
