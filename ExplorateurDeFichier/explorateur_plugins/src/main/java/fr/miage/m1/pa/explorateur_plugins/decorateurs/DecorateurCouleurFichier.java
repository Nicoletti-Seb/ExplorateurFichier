package fr.miage.m1.pa.explorateur_plugins.decorateurs;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;

public class DecorateurCouleurFichier extends JFrame implements Vue{

	private Vue vue;
	
	public DecorateurCouleurFichier(Vue vue) {
		this.vue = vue;
	}
	
	@Override
	public void setMouseListener(MouseListener listener) {
		vue.setMouseListener(listener);
		
	}

	@Override
	public void setControleurListener(ControleurVueListener listener) {
		vue.setControleurListener(listener);
		
	}

	@Override
	public void setActionListener(ActionListener listener) {
		vue.setActionListener(listener);
		
	}

	@Override
	public void setKeyListener(KeyListener listener) {
		vue.setKeyListener(listener);
		
	}

	@Override
	public JTable getMainTable() {
		return vue.getMainTable();
	}

	@Override
	public JTextField getLabelNavigateur() {
		return vue.getLabelNavigateur();
	}

	@Override
	public void setPluginMenu(List<String> plugins) {
		vue.setPluginMenu(plugins);
		
	}

	@Override
	public void setCellRenderer(TableCellRenderer renderer) {
		vue.setCellRenderer(new TableCouleurRenderer());
		
	}

	@Override
	public void setModele(Modele modele) {
		vue.setModele(modele);
		
	}

	public class TableCouleurRenderer extends DefaultTableCellRenderer {

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
		
		if (f.canWrite()==false && f.canExecute()==false) {
			component.setForeground(Color.red);
		} else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}

		return label;

	    }

	}
}
