package view.purchaseOrder;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import model.ItemData;
import model.PurchaseOrder;
import controller.PurchaseOrderController;
import view.Button;

public class POItemCellEdit extends DefaultCellEditor implements TableCellRenderer, ActionListener
  {
    private JButton button;
    private boolean clicked;
    private int row, col;
    private JTable table;
    private JFrame parent;
    private ItemData i;
    private PurchaseOrderController poController;
    private PurchaseOrder po;
   

    
    public POItemCellEdit(JCheckBox checkBox, JFrame parent, ItemData i, PurchaseOrder po, PurchaseOrderController poController)
    {
      super(checkBox);
      this.poController = poController;
      this.i = i;
      this.parent = parent;
      this.po = po;
      button = new Button.ButtonBuilder().img("src/assets/Round/Note2.png", 30,30).build();
      button.setOpaque(true);
      button.setHorizontalAlignment(JLabel.CENTER);
      button.addActionListener(this);
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;

      button.setForeground(Color.black);
      button.setBackground(Color.white);
     
		this.row = row ;
		String tableClick =  (table.getModel().getValueAt(this.row,0).toString());
		searchItem(tableClick);

		
      return button;
    }
    
    public Object getCellEditorValue()
    {
    	return button;
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
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	    //
//	      button.setForeground(table.getSelectionForeground());
//	      button.setBackground(table.getSelectionBackground());
	      this.row = row;

		  String tableClick =  (table.getModel().getValueAt(this.row,0).toString());
		  searchItem(tableClick);
		  this.row = 0;
	    return button;
	  }

	  public void searchItem(String name)
	  {
		  Iterator it = po.getItems();
		  while(it.hasNext())
		  {
			  ItemData d = (ItemData)it.next();
			  if(d.getName().equals(name))
				  i = d;
		  }
		  
	  }
	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		
		if(a.getSource() == button)
		{
			new EditPOItem(parent,i,po, poController);
		}
		
	}
  }

