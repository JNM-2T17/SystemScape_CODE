package view.inventory;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import view.PopUp;

public class ExportInventory extends PopUp implements ActionListener{
	private JButton btnExport, btnBrowse;
	private JTextField txtName;
	private JTextField txtDest;
	private JComboBox cmbType;

	public ExportInventory(JFrame parent) {
		super(parent);

		JPanel panMain = new JPanel();
		panMain.setSize(new Dimension(500, 250));
		panMain.setPreferredSize(new Dimension(500, 250));
		getContentPane().add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));
		

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnExport = new JButton("Export");
		panFooter.add(btnExport);
		btnExport.setForeground(Color.white);
		btnExport.setBackground(new Color(32, 130, 213));
		btnExport.setFont(new Font("Arial", Font.PLAIN, 18));
		panFooter.add(btnExport);

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblName = new JLabel("Name:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblName, 50,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblName, 50,
				SpringLayout.WEST, panContent);
		panContent.add(lblName);

		JLabel lblFileType = new JLabel("File Type:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblFileType, 20,
				SpringLayout.SOUTH, lblName);
		sl_panContent.putConstraint(SpringLayout.WEST, lblFileType, 0,
				SpringLayout.WEST, lblName);
		panContent.add(lblFileType);

		JLabel lblDestination = new JLabel("Destination:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblDestination, 20,
				SpringLayout.SOUTH, lblFileType);
		sl_panContent.putConstraint(SpringLayout.WEST, lblDestination, 0,
				SpringLayout.WEST, lblName);
		panContent.add(lblDestination);

		txtName = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtName, 50,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, txtName, 72,
				SpringLayout.EAST, lblName);
		sl_panContent.putConstraint(SpringLayout.EAST, txtName, -30,
				SpringLayout.EAST, panContent);
		panContent.add(txtName);
		txtName.setColumns(10);

		cmbType = new JComboBox();
		cmbType.setBackground(Color.WHITE);
		sl_panContent.putConstraint(SpringLayout.WEST, cmbType, 0, SpringLayout.WEST, txtName);
		sl_panContent.putConstraint(SpringLayout.SOUTH, cmbType, 0, SpringLayout.SOUTH, lblFileType);
		sl_panContent.putConstraint(SpringLayout.EAST, cmbType, 258,
				SpringLayout.EAST, lblFileType);
		panContent.add(cmbType);

		txtDest = new JTextField();
		sl_panContent.putConstraint(SpringLayout.WEST, txtDest, 0, SpringLayout.WEST, txtName);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtDest, 0, SpringLayout.SOUTH, lblDestination);
		panContent.add(txtDest);
		txtDest.setColumns(10);

		btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(this);
		btnBrowse.setBackground(Color.white);
		sl_panContent.putConstraint(SpringLayout.EAST, txtDest, -6,
				SpringLayout.WEST, btnBrowse);
		sl_panContent.putConstraint(SpringLayout.SOUTH, btnBrowse, 0,
				SpringLayout.SOUTH, lblDestination);
		sl_panContent.putConstraint(SpringLayout.EAST, btnBrowse, 0,
				SpringLayout.EAST, txtName);
		panContent.add(btnBrowse);
		
		setContent(panMain);
		getClose().addActionListener(this);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnBrowse){
			 JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            txtDest.setText(file.getAbsolutePath());
	        }
		}
		else if(e.getSource()==getClose()){
			this.dispose();
		}
	}
}
