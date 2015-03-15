package view.inventory;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import view.Content.ContentBuilder;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;

public class AddInventoryContent extends JPanel {
	
	private JPanel panQuad2, panQuad1, panQuad3, panQuad4, panSubmit;
	private JButton btnSubmit;
	private JPanel panel_Quad1;
	private JPanel panel_Quad2;
	private JPanel panel_Quad3;
	private JPanel panel_Quad4;
	
	private AddInventoryContent(AddInventoryContentBuilder builder) 
	{
		setLayout(new MigLayout("", "[450px,grow][450px,grow]", "[207.00,grow][207.00,grow][]"));
		
		panel_Quad2 = new JPanel();
		panel_Quad2 = builder.panQuad2;
		add(panel_Quad2, "cell 0 0,grow");
		
		panel_Quad1 = new JPanel();
		panel_Quad1 = builder.panQuad1;
		add(panel_Quad1, "cell 1 0,grow");
		
		panel_Quad3 = new JPanel();
		panel_Quad3 = builder.panQuad3;
		add(panel_Quad3, "cell 0 1,grow");
		
		panel_Quad4 = new JPanel();
		panel_Quad4 = builder.panQuad4;
		add(panel_Quad4, "cell 1 1,grow");
		
		panSubmit = new JPanel();
		add(panSubmit, "cell 0 2 2 1,alignx center");
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setActionCommand("submit");
		panSubmit.add(btnSubmit);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
		
		setVisible(true);
	}
	

	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public static class AddInventoryContentBuilder {
		
		private boolean quad1;
		private boolean quad2;
		private boolean quad3;
		private boolean quad4;
		private JPanel panQuad1;
		private JPanel panQuad2;
		private JPanel panQuad3;
		private JPanel panQuad4;

		public AddInventoryContentBuilder() {
			quad1 = false;
			quad2 = false;
			quad3 = false;
			quad4 = false;
			panQuad1 = new JPanel();
			panQuad2 = new JPanel();
			panQuad3 = new JPanel();
			panQuad4 = new JPanel();
		}

		public AddInventoryContentBuilder panQuad1(JPanel content) {
			panQuad1 = content;
			quad1 = true;
			return this;
		}

		public AddInventoryContentBuilder panQuad2(JPanel content) {
			panQuad2 = content;
			quad2 = true;
			return this;
		}
		
		public AddInventoryContentBuilder panQuad3(JPanel content) {
			panQuad3 = content;
			quad3 = true;
			return this;
		}
		
		public AddInventoryContentBuilder panQuad4(JPanel content) {
			panQuad4 = content;
			return this;
		}

		public AddInventoryContent build() {
			return new AddInventoryContent(this);
		}
	}

}
