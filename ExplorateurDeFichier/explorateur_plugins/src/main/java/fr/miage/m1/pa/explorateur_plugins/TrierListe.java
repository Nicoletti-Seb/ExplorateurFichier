package fr.miage.m1.pa.explorateur_plugins;

import java.io.File;

import java.util.List;

import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class TrierListe implements Plugin {

	public String getNom() {
		return this.getClass().getSimpleName();
	}

	public void plug(Controleur controleur) {

		Modele modele = controleur.getModele();

		trier(modele);

	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele().reset();
	}

	public void trier(Modele modele) {

		List<File> fileList = modele.getFileList();
		List<Title> titles = modele.getTitles();
		String[][] datas = new String[fileList.size()][titles.size()];
		for (int i = 0; i < fileList.size(); i++) {
			for (int j = 0; j < titles.size(); j++) {
				RowSorter<TableModel> tri = new TableRowSorter<>(modele.DataModel());
				modele.DataTable().setRowSorter(tri);

			}
		}
		modele.setDatas(datas);
	}

}