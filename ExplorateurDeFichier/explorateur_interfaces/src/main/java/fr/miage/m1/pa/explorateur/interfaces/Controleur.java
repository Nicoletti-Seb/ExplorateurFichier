package fr.miage.m1.pa.explorateur.interfaces;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public interface Controleur {
	
	public Vue getVue();
	public Modele getModele();
	public TableModel DataModel();
	public JTable DataTable();
}
