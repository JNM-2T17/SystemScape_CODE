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
import java.io.FileWriter;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import view.GenerationProgress;
import view.PopUp;

public class ExportInventory extends PopUp implements ActionListener {
	private JButton btnExport, btnBrowse;
	private JTextField txtName;
	private JTextField txtDest;
	private Iterator data;
	
	private JFrame parent;

	public ExportInventory(JFrame parent, Iterator i) {
		super(parent);
		this.parent=parent;
		data = i;
		JPanel panMain = new JPanel();
		panMain.setSize(new Dimension(500, 200));
		panMain.setPreferredSize(new Dimension(500, 200));
		getContentPane().add(panMain);
		panMain.setLayout(new BorderLayout(0, 0));

		JPanel panHeader = new JPanel();
		panHeader.setBackground(Color.WHITE);
		panMain.add(panHeader, BorderLayout.NORTH);

		JLabel lblExportInv = new JLabel("Export Inventory");
		lblExportInv.setFont(new Font("Arial", Font.PLAIN, 16));
		panHeader.add(lblExportInv);

		JPanel panFooter = new JPanel();
		panFooter.setBackground(Color.WHITE);
		panMain.add(panFooter, BorderLayout.SOUTH);

		btnExport = new JButton("Export");
		panFooter.add(btnExport);
		btnExport.setForeground(Color.white);
		btnExport.setBackground(new Color(32, 130, 213));
		btnExport.setFont(new Font("Arial", Font.PLAIN, 18));
		panFooter.add(btnExport);
		btnExport.addActionListener(this);

		JPanel panContent = new JPanel();
		panContent.setBackground(Color.WHITE);
		panMain.add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		JLabel lblName = new JLabel("Filename:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblName, 50,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, lblName, 50,
				SpringLayout.WEST, panContent);
		panContent.add(lblName);

		JLabel lblDestination = new JLabel("Destination:");
		sl_panContent.putConstraint(SpringLayout.NORTH, lblDestination, 20,
				SpringLayout.SOUTH, lblName);
		sl_panContent.putConstraint(SpringLayout.WEST, lblDestination, 0,
				SpringLayout.WEST, lblName);
		panContent.add(lblDestination);

		txtName = new JTextField();
		sl_panContent.putConstraint(SpringLayout.NORTH, txtName, -3,
				SpringLayout.NORTH, lblName);
		panContent.add(txtName);
		txtName.setColumns(10);

		txtDest = new JTextField();
		sl_panContent.putConstraint(SpringLayout.WEST, txtName, 0,
				SpringLayout.WEST, txtDest);
		sl_panContent.putConstraint(SpringLayout.EAST, txtName, 0,
				SpringLayout.EAST, txtDest);
		sl_panContent.putConstraint(SpringLayout.WEST, txtDest, 22,
				SpringLayout.EAST, lblDestination);
		sl_panContent.putConstraint(SpringLayout.SOUTH, txtDest, 0,
				SpringLayout.SOUTH, lblDestination);
		panContent.add(txtDest);
		txtDest.setColumns(10);

		btnBrowse = new JButton("Browse");
		sl_panContent.putConstraint(SpringLayout.EAST, txtDest, -10,
				SpringLayout.WEST, btnBrowse);
		sl_panContent.putConstraint(SpringLayout.EAST, btnBrowse, -30,
				SpringLayout.EAST, panContent);
		btnBrowse.addActionListener(this);
		btnBrowse.setBackground(Color.white);
		sl_panContent.putConstraint(SpringLayout.SOUTH, btnBrowse, 0,
				SpringLayout.SOUTH, lblDestination);
		panContent.add(btnBrowse);

		JLabel lblcsv = new JLabel(".csv");
		sl_panContent.putConstraint(SpringLayout.WEST, lblcsv, 10,
				SpringLayout.EAST, txtName);
		sl_panContent.putConstraint(SpringLayout.SOUTH, lblcsv, 0,
				SpringLayout.SOUTH, lblName);
		panContent.add(lblcsv);

		setContent(panMain);
		getClose().addActionListener(this);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnBrowse) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// This is where a real application would open the file.
				txtDest.setText(file.getAbsolutePath());
			}
		} else if (e.getSource() == btnExport) {
			System.out.println("a");this.dispose();
			GenerationProgress gp=new GenerationProgress(parent, txtDest.getText(), txtName.getText(), data);
//			System.out.println("b");
			gp.writeCsvFile(txtDest.getText() + "\\" + txtName.getText() + ".csv");
			
			
			
		} else if (e.getSource() == getClose()) {
			this.dispose();
		}

	}
}
