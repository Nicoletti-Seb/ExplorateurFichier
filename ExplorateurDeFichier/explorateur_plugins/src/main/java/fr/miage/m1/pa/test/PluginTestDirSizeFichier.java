package fr.miage.m1.pa.test;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class PluginTestDirSizeFichier  implements Plugin{

	@Override
	public void plug(Controleur controleur) {
		controleur.getModele();	
		controleur.getVue();
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele();	
		controleur.getVue();
	}

	@Override
	public String getNom() {
		return "Plugin de Test";
	}
	
	public static void main(String[] args) {
		System.out.println("Class de test Size");
	}

}

