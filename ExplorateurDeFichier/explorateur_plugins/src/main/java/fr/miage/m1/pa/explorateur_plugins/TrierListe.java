package fr.miage.m1.pa.explorateur_plugins;

import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class TrierListe implements Plugin {

	public String getNom() {
		return this.getClass().getSimpleName();
	}

	public void plug(Controleur controleur) {
	
		RowSorter<TableModel> tri = new TableRowSorter<>(controleur.DataModel());
		controleur.DataTable().setRowSorter(tri);
		//controleur.DataTable().setAutoCreateRowSorter(true);
		
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele().reset();
	}
	
	

}