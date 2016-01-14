package fr.miage.m1.pa.explorateur.interfaces;

import javax.swing.JFrame;

public interface Plugin {
	
	public void plug( Controleur controleur );
	public void unplug( Controleur controleur );
	
	public String getNom();
	public Class getDecorator();
	public void showView(boolean isActive, PluginListener listener);
	
}
