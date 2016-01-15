package fr.miage.m1.pa.explorateur.abstracts;

import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.VueNavigatorListener;

public abstract class VueNavigator extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public abstract void setPathFile(String path);
	public abstract String getPathFile();
	public abstract void addVueNavigateurListener(VueNavigatorListener listener);
}
