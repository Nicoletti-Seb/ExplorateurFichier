package fr.miage.m1.pa.explorateur_plugins;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class PluginVueCouleurFichier implements Plugin {

	@Override
	public void plug(Controleur controleur) {

		Modele modele = controleur.getModele();
		
		setDonnes(modele);
		
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele().reset();
		
	}

	@Override
	public String getNom() {
		return "VueCouleurFichier";
	}
	
	private void setDonnes(Modele modele) {
	
	}
	
	public static void main(String[] args) {
		System.out.println("Build class");
	}
}
