package fr.miage.m1.pa.explorateur_plugins;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.PluginListener;
import fr.miage.m1.pa.explorateur_plugins.decorateurs.DecorateurCouleurFichier;
import fr.miage.m1.pa.explorateur_plugins.gestionaire.GestionairePlugins;
import fr.miage.m1.pa.explorateur_plugins.helper.PluginHelper;

public class PluginVueCouleurFichier implements Plugin {

	@Override
	public void plug(Controleur controleur) {

		GestionairePlugins gPlugin = GestionairePlugins.getInstance(controleur.getModele(), controleur.getVue());
		controleur.setVue(gPlugin.addVuePlugin(this));
		
	}

	@Override
	public void unplug(Controleur controleur) {
		
		GestionairePlugins gPlugin = GestionairePlugins.getInstance(controleur.getModele(), controleur.getVue());
		controleur.setVue(gPlugin.removeVuePlugin(this));
	}

	@Override
	public String getNom() {
		return "VueCouleurFichier";
	}

	@Override
	public Class getDecorator() {
		
		return DecorateurCouleurFichier.class;
	}

	@Override
	public void showView(boolean isActive, PluginListener listener) {
		
		JFrame view = PluginHelper.getBaseFrame(getNom(), isActive, null, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(listener != null) listener.onStateChecked(getNom());
				
			}
		});
		view.setVisible(true);
		
	}

}
