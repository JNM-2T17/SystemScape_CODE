package view.purchaseOrder;

import controller.PurchaseOrderController;
import model.PurchaseOrder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

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
    
    public POItemCheckBoxCell(JCheckBox checkBox, final JFrame parent, PurchaseOrder po, final PurchaseOrderController poController)
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
      checkBoxDelivered.addChangeListener(new ChangeListener() {
		
		@Override
		public void stateChanged(ChangeEvent e) 
		{
			// TODO Auto-generated method stub
			AbstractButton abstractButton = (AbstractButton)e.getSource();
            ButtonModel buttonModel = abstractButton.getModel();
            boolean armed = buttonModel.isArmed();
            boolean pressed = buttonModel.isPressed();
            boolean selected = buttonModel.isSelected();
            
           if(pressed == true && selected == true)
           {
        	   checkBoxDelivered.setSelected(true);
        	   checkBoxDelivered.setEnabled(false);
        	   if(type.equals("Hard"))
        	   {
         		  EditPOItemHard eHard = new EditPOItemHard(parent, poController);
        	   }
         	  else if(type.equals("Soft"))
         	  {
         		 EditPOItemSoft eSoft = new EditPOItemSoft(parent, poController);
         	  }
         	  else if(type.equals("Gen"))
         	  {
         		  EditPOItemGen eGen = new EditPOItemGen(parent, poController);
         	  }
           }
         
          }

        });
    
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;
    
		 fireEditingStopped();
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
		 fireEditingStopped();
	    return checkBoxDelivered;
	  }


  }

