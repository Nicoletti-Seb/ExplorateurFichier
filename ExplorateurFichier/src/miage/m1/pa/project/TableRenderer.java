package miage.m1.pa.project;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1473088087232830309L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {

	Component component = super.getTableCellRendererComponent(table, value,
		isSelected, hasFocus, row, column);
	
	JLabel label = (JLabel) component;
	
	File f = new File((String) value);
	label.setText(f.getName());
	label.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));

	return label;

    }

}
