package fr.miage.m1.pa.explorateur_plugins;

import java.util.List;

import javax.swing.JFrame;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.PluginListener;
import fr.miage.m1.pa.explorateur_plugins.decorateurs.DecorateurTailleDossier;
import fr.miage.m1.pa.explorateur_plugins.decorateurs.DecorateurTitres;
import fr.miage.m1.pa.explorateur_plugins.gestionaire.GestionairePlugins;

public class PluginAnalyseColonnes implements Plugin {

	private List<Title> titles;
	
	@Override
	public void plug(Controleur controleur) {
		
		if(titles == null) titles = controleur.getModele().getTitles();
		GestionairePlugins gPlugin = GestionairePlugins.getInstance(controleur.getModele(), controleur.getVue());
		controleur.setModele(gPlugin.addAnalysePlugin(this));
		
	}

	@Override
	public void unplug(Controleur controleur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNom() {
		
		return "Plugin de titres";
	}

	@Override
	public Class getDecorator() {
		
		return DecorateurTitres.class;
	}

	@Override
	public void showView(boolean isActive, PluginListener listener) {
		// TODO Auto-generated method stub
		
	}

}
