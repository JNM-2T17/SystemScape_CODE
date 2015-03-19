package view.purchaseOrder;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.PurchaseOrder;
import controller.PurchaseOrderController;

public class POItemCheckBoxCell extends DefaultCellEditor implements TableCellRenderer
  {
    private JCheckBox checkBoxDelivered;
    private boolean clicked;
    private int row, col;
    private JTable table;
    private JFrame parent;
    private String type;
    private PurchaseOrderController poController;
    private PurchaseOrder po;
    
    public POItemCheckBoxCell(JCheckBox checkBox)
    {
    	super(checkBox);
    }
    public POItemCheckBoxCell(JCheckBox checkBox, JFrame parent, PurchaseOrder po, PurchaseOrderController poController)
    {
      super(checkBox);
      this.parent = parent;
      this.po = po;
      this.type =  po.getType();
      this.poController = poController;
      
      checkBoxDelivered = new JCheckBox();
      checkBoxDelivered.setHorizontalAlignment(JLabel.CENTER);
      checkBoxDelivered.setOpaque(true);
      checkBoxDelivered.setSelected(false);
      checkBoxDelivered.setBackground(Color.WHITE);
      checkBoxDelivered.addActionListener(new ActionListener() 
      {
        public void actionPerformed(ActionEvent e)
        {
        	if(type.equals("Hard"))
      		  new EditPOItemHard(parent, poController);
      	  else if(type.equals("Soft"))
      		  new EditPOItemSoft(parent, poController);
      	  else if(type.equals("Gen"))
      		  new EditPOItemGen(parent, poController);
        	fireEditingStopped();
        }
      });
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;
      
      checkBoxDelivered.setSelected(true);
      clicked = true;
      return checkBoxDelivered;
    }
    
    public Object getCellEditorValue()
    {
      return checkBoxDelivered;
    }

    public boolean stopCellEditing()
    {
      clicked = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped()
    {
      super.fireEditingStopped();
    }
	   
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column)
	  {
	    return checkBoxDelivered;
	  }
  }

