package fr.miage.m1.pa.explorateur.interfaces;

import javax.swing.JPanel;

public interface Plugin {
	
	public void plug( Controleur controleur );
	public void unplug( Controleur controleur );
	public String getNom();
	public JPanel getPanelConfig();
	
}
