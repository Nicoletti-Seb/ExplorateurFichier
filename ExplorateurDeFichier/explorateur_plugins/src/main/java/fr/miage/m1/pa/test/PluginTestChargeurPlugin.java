package fr.miage.m1.pa.test;

import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

/**
 *Ce plugin permet de tester la class ManageurPlugin.
 */
public class PluginTestChargeurPlugin implements Plugin{

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
		System.out.println("Build class");
	}

	@Override
	public JPanel getPanelConfig() {
		// TODO Auto-generated method stub
		return null;
	}

}
