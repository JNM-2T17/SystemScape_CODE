package view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PanelCell extends AbstractCellEditor implements TableCellEditor,
		TableCellRenderer {
	CellEdit panel;

	public PanelCell() {
		panel = new CellEdit();
	}

	private void updateData(CellEdit pan, boolean isSelected, JTable table) {
		panel = pan;
		if (isSelected) {
			panel.setBackground(table.getSelectionBackground());
		} else {
			panel.setBackground(table.getBackground());
		}
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		CellEdit pan = (CellEdit) value;
		updateData(pan, true, table);
		return panel;
	}

	public Object getCellEditorValue() {
		return panel;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		CellEdit panel = (CellEdit) value;
		updateData(panel, isSelected, table);
		return this.panel;
	}
}