package view;

import controller.ContractController;
import controller.InventoryItemController;
import controller.WarrantyController;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Dimension;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import model.PurchaseOrder;
import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.GregorianCalendar;
import java.util.Iterator;
import model.Contract;
import model.InventoryItem;
import model.Warranty;
import model.database.UserDAO;

public class Notification extends JPanel implements ActionListener, Observer{
	private JPanel listContract, listWarranty, panContract, panWarranty;;
	private JButton btnContract, btnWarranty;
	private JLabel numContract, numWarranty, redContract, redWarranty;
	private JScrollPane scrollPane;
	private SpringLayout springContract, springWarranty;
	private int cntContract, cntWarranty;
        private WarrantyController warrantyController;
        private ContractController contractController;
        private InventoryItemController inventoryItemController;
        private String warrantyDuration, contractDuration;

	public Notification(String username) {
		setBackground(new Color(32, 130, 213));
		setLayout(new BorderLayout(0, 0));
                
		cntContract = cntWarranty = 0;

		JPanel panHeader = new JPanel();
		panHeader.setBackground(new Color(32, 130, 213));
		FlowLayout flowLayout = (FlowLayout) panHeader.getLayout();
		add(panHeader, BorderLayout.NORTH);

		panContract = new JPanel();
		panContract.setBackground(new Color(32, 130, 213));
		panContract.setPreferredSize(new Dimension(70, 75));
		panHeader.add(panContract);

		SpringLayout sl_panContract = new SpringLayout();
		panContract.setLayout(sl_panContract);

		numContract = new JLabel("");
		numContract.setForeground(Color.white);
		sl_panContract.putConstraint(SpringLayout.NORTH, numContract, 1,
				SpringLayout.NORTH, panContract);
		sl_panContract.putConstraint(SpringLayout.EAST, numContract, -17,
				SpringLayout.EAST, panContract);
		panContract.add(numContract);

		redContract = new ImageLabel.ImageBuilder().img("src/assets/red.png",
				0.05).build();
		sl_panContract.putConstraint(SpringLayout.NORTH, redContract, 0,
				SpringLayout.NORTH, panContract);
		sl_panContract.putConstraint(SpringLayout.EAST, redContract, 0,
				SpringLayout.EAST, panContract);
		panContract.add(redContract);

		redContract.setVisible(false);
		numContract.setVisible(false);

		btnContract = new Button.ButtonBuilder().img(
				"src/assets/Metro/Documents.png", 60, 60).build();
		btnContract.setToolTipText("Contract Notifications");
		sl_panContract.putConstraint(SpringLayout.NORTH, btnContract, 15,
				SpringLayout.NORTH, panContract);
		btnContract.addActionListener(this);
		panContract.add(btnContract);

		panWarranty = new JPanel();
		panWarranty.setBackground(new Color(32, 130, 213));
		panWarranty.setPreferredSize(new Dimension(70, 75));
		panHeader.add(panWarranty);

		SpringLayout sl_panWarranty = new SpringLayout();
		panWarranty.setLayout(sl_panWarranty);

		numWarranty = new JLabel("");
		numWarranty.setForeground(Color.white);
		sl_panWarranty.putConstraint(SpringLayout.NORTH, numWarranty, 1,
				SpringLayout.NORTH, panWarranty);
		sl_panWarranty.putConstraint(SpringLayout.EAST, numWarranty, -17,
				SpringLayout.EAST, panWarranty);
		panWarranty.add(numWarranty);

		redWarranty = new ImageLabel.ImageBuilder().img("src/assets/red.png",
				0.05).build();
		sl_panWarranty.putConstraint(SpringLayout.NORTH, redWarranty, 0,
				SpringLayout.NORTH, panWarranty);
		sl_panWarranty.putConstraint(SpringLayout.EAST, redWarranty, 0,
				SpringLayout.EAST, panWarranty);
		panWarranty.add(redWarranty);

		redWarranty.setVisible(false);
		numWarranty.setVisible(false);

		btnWarranty = new Button.ButtonBuilder().img(
				"src/assets/Metro/Document3.png", 60, 60).build();
		btnWarranty.setToolTipText("Warranty Notifications");
		sl_panWarranty.putConstraint(SpringLayout.NORTH, btnWarranty, 15,
				SpringLayout.NORTH, panWarranty);
		btnWarranty.addActionListener(this);
		panWarranty.add(btnWarranty);

		JPanel panContent = new JPanel();
		panContent.setBackground(new Color(32, 130, 213));
		add(panContent, BorderLayout.CENTER);
		SpringLayout sl_panContent = new SpringLayout();
		panContent.setLayout(sl_panContent);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.getViewport().setBackground(new Color(32, 130, 213));
		sl_panContent.putConstraint(SpringLayout.NORTH, scrollPane, 10,
				SpringLayout.NORTH, panContent);
		sl_panContent.putConstraint(SpringLayout.WEST, scrollPane, 10,
				SpringLayout.WEST, panContent);
		sl_panContent.putConstraint(SpringLayout.EAST, scrollPane, -10,
				SpringLayout.EAST, panContent);
		sl_panContent.putConstraint(SpringLayout.SOUTH, scrollPane, -10,
				SpringLayout.SOUTH, panContent);
		panContent.add(scrollPane);

		springWarranty = new SpringLayout();
		springContract = new SpringLayout();

		listContract = new JPanel();
		listContract.setBorder(new EmptyBorder(0, 0, 0, 10));
		listContract.setBackground(new Color(32, 130, 213));
		listContract.setLayout(springContract);

		listWarranty = new JPanel();
		listWarranty.setBorder(new EmptyBorder(0, 0, 0, 10));
		listWarranty.setBackground(new Color(32, 130, 213));
		listWarranty.setLayout(springWarranty);

		scrollPane.setViewportView(listContract);
                
                warrantyController = WarrantyController.getInstance();
                contractController = ContractController.getInstance();
                inventoryItemController = new InventoryItemController();
                UserDAO userDAO = new UserDAO();
                Iterator i = userDAO.getNotificationDuration(username);
                if(i.hasNext()){
                    warrantyDuration = i.next().toString();
                }
                if(i.hasNext()){
                    contractDuration = i.next().toString();
                }
                System.out.println(username + " " +warrantyDuration + " " + contractDuration);
                
                warrantyController.registerObserver(this);
                contractController.registerObserver(this);
                
               
		
	}
        @Override
        public void update(){
            listContract.removeAll();
            listWarranty.removeAll();
            cntContract = cntWarranty = 0;
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            java.util.Date date = new java.util.Date(gregorianCalendar.getTimeInMillis());
            for(Iterator i = warrantyController.search(warrantyDuration); i.hasNext();){
                Warranty warranty = (Warranty)i.next();
                InventoryItem inventoryItem = ((InventoryItem)inventoryItemController.get("" + warranty.getHardware()));
                int daysLeft = (int)((warranty.getEndDate().getTime() - date.getTime()) / (24 * 60 * 60 * 1000));
                addWarranty(inventoryItem.getName(), daysLeft);
            }
            
            for(Iterator i = contractController.search(contractDuration); i.hasNext();){
                Contract contract = (Contract)i.next();
                InventoryItem inventoryItem = (InventoryItem)inventoryItemController.get("" + contract.getHardware());
                int daysLeft = (int)((contract.getEndDate().getTime() - date.getTime()) / (24 * 60 * 60 * 1000));
                addContract(inventoryItem.getName(), daysLeft);
            }
            listContract.repaint();
            listContract.revalidate();
            listWarranty.repaint();
            listWarranty.revalidate();
            cntWarranty = listWarranty.getComponents().length;
            cntContract = listContract.getComponents().length;
            numWarranty.setText(cntWarranty + "");
            numContract.setText(cntContract + "");
            if(cntWarranty == 0){
                redWarranty.setVisible(false);
                numWarranty.setVisible(false);
            }
            if(cntContract == 0){
                redContract.setVisible(false);
                numContract.setVisible(false);
            }
        }
        
	public void addContract(String name, int days) {
		cntContract++;
		redContract.setVisible(true);
		numContract.setText(cntContract + "");
		numContract.setVisible(true);

		NotifContract notifContract = new NotifContract(name, days);
		springContract.putConstraint(SpringLayout.WEST, notifContract, 0,
				SpringLayout.WEST, listContract);
		springContract.putConstraint(SpringLayout.EAST, notifContract, 0,
				SpringLayout.EAST, listContract);
		if (listContract.getComponentCount() > 0) {
			springContract.putConstraint(SpringLayout.NORTH, notifContract, 7,
					SpringLayout.SOUTH, listContract.getComponent(listContract
							.getComponentCount() - 1));
		}
		listContract.add(notifContract);
		listContract.repaint();
		listContract.revalidate();
	}

	public void addWarranty(String name, int days) {
		cntWarranty++;
		redWarranty.setVisible(true);
		numWarranty.setText(cntWarranty + "");
		numWarranty.setVisible(true);

		NotifWarranty notifWarranty = new NotifWarranty(name, days);
		springWarranty.putConstraint(SpringLayout.WEST, notifWarranty, 0,
				SpringLayout.WEST, listWarranty);
		springWarranty.putConstraint(SpringLayout.EAST, notifWarranty, 0,
				SpringLayout.EAST, listWarranty);
		if (listWarranty.getComponentCount() > 0) {
			springWarranty.putConstraint(SpringLayout.NORTH, notifWarranty, 7,
					SpringLayout.SOUTH, listWarranty.getComponent(listWarranty
							.getComponentCount() - 1));
		}
		listWarranty.add(notifWarranty);
		listWarranty.repaint();
		listWarranty.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnContract) {
			panWarranty.setBorder(null);
			scrollPane.setViewportView(listContract);
			this.repaint();
			this.revalidate();
		} else if (e.getSource() == btnWarranty) {
			panContract.setBorder(null);
			scrollPane.setViewportView(listWarranty);
			this.repaint();
			this.revalidate();
		}
	}
}
