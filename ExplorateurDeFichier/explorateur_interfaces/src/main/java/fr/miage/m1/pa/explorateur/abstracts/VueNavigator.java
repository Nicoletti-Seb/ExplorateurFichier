package fr.miage.m1.pa.explorateur.abstracts;

import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.VueNavigatorListener;

public abstract class VueNavigator extends JPanel{

	private static final long serialVersionUID = 1L;
	
	protected VueNavigatorListener navigatorListener;
	
	public abstract void setPathFile(String path);
	public abstract String getPathFile();
	
	public void addVueNavigatorListener(VueNavigatorListener listener){
		this.navigatorListener = listener;
	}
	
	public VueNavigatorListener getVueNavigateurListener(){
		return navigatorListener;
	}
	
	public void removeNavigatorListener(){
		 navigatorListener = null;
	}
}
