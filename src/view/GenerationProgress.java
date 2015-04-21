package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class GenerationProgress extends PopUp implements ActionListener {
	private JProgressBar progress;
	private String dest, name;
	private Iterator data;
	
	public GenerationProgress(JFrame parent, String dest, String name, Iterator data) {
		super(parent);
		this.dest=dest;
		this.name=name;
		JPanel panMain = new JPanel();
		getContentPane().add(panMain, BorderLayout.CENTER);
		panMain.setLayout(null);
		
		panMain.setSize(new Dimension(850, 400));
		panMain.setPreferredSize(new Dimension(850, 400));
		
		setContent(panMain);
		
		ArrayList copy=new ArrayList();
		int i=0;
		while(data.hasNext()){
			copy.add(data.next());
			i++;
		}
		this.data=copy.iterator();
		progress = new JProgressBar(0, i);
		progress.setValue(0);
		progress.setStringPainted(true);
		progress.setBounds(104, 184, 599, 42);
		panMain.add(progress);
		JLabel lblGeneratingReport = new JLabel("Generating Report");
		lblGeneratingReport.setFont(new Font("Arial", Font.PLAIN, 32));
		lblGeneratingReport.setBounds(271, 90, 277, 73);
		panMain.add(lblGeneratingReport);
		setClosable(false);
		
		getClose().addActionListener(this);
		
		setModal(false);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}
	
	public void writeCsvFile(String fileName) {
		System.out.println("here");
		FileWriter fileWriter = null;
		
		try {

			fileWriter = new FileWriter(fileName);
			while (data.hasNext()) {
				fileWriter.append(data.next().toString());
				repaint();
				revalidate();
				progress.setValue(progress.getValue()+1);
				
				
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {

			System.out.println("Error in CsvFileWriter !!!");

			e.printStackTrace();

		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

			} catch (Exception e) {

				System.out.println("Error while flushing/closing fileWriter");

				e.printStackTrace();

			}

		}
		System.out.println("there");
		setClosable(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.dispose();
	}
	
}
