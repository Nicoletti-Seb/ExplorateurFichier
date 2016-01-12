package fr.miage.m1.pa.explorateur_plugins;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur_plugins.decorateurs.DecorateurTailleDossier;
import fr.miage.m1.pa.explorateur_plugins.gestionaire.GestionairePlugins;

public class PluginAnalyseTailleDossier implements Plugin {

	@Override
	public void plug(Controleur controleur) {

		GestionairePlugins gPlugin = GestionairePlugins.getInstance(controleur.getModele(), controleur.getVue());
		controleur.setModele(gPlugin.addAnalysePlugin(this));
		
	}

	@Override
	public void unplug(Controleur controleur) {
		
		GestionairePlugins gPlugin = GestionairePlugins.getInstance(controleur.getModele(), controleur.getVue());
		controleur.setModele(gPlugin.removeAnalysePlugin(this));
		
	}

	@Override
	public String getNom() {
		
		return "Plugin taille dossier";
	}


	@Override
	public Class getDecorator() {
		return DecorateurTailleDossier.class;
	}
}
