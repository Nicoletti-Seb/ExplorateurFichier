package fr.miage.m1.pa.explorateur_plugins;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class PluginVueCouleurFichier implements Plugin {
	DefaultTableModel tableModel;

	@Override
	public void plug(Controleur controleur) {

		Modele modele = controleur.getModele();
		
		//setDonnes(modele);
		
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele().reset();
		
	}

	@Override
	public String getNom() {
		return "VueCouleurFichier";
	}
	
	private void ChangeColorRight(File f){
	
	JTable jtable= new JTable(tableModel);
	JPanel jpanel =new JPanel();
	JScrollPane scrollTable = new JScrollPane(jtable);
	jpanel.add(scrollTable);
	getNewRenderedTable(jtable, f);
		
	}
	
	private static JTable getNewRenderedTable(final JTable table, File f) {
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,int row, int col) {
		
					Component c = super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, col);
						
						if (f.canWrite()==false && f.canExecute()==false) {
							c.setForeground(Color.red);
						} else {
							setBackground(table.getBackground());
							setForeground(table.getForeground());
						}
					
			

				return this;
			}
		});
		return table;
	}
	
	public static void main(String[] args) {
		System.out.println("Build class");
	}
}
