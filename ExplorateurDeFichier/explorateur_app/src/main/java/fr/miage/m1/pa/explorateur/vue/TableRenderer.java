package fr.miage.m1.pa.explorateur.vue;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

import fr.miage.m1.pa.explorateur.interfaces.FileReader;

public class TableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1473088087232830309L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		JLabel label = (JLabel) component;
		
		FileReader fileReader = (FileReader) value;
		label.setText(fileReader.getName());
		label.setIcon(FileSystemView.getFileSystemView().getSystemIcon(new File(fileReader.getPath())));
		
		return label;
	}

}
