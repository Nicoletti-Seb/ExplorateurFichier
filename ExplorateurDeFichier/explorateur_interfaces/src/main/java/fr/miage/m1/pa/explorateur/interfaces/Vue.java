package fr.miage.m1.pa.explorateur.interfaces;

import fr.miage.m1.pa.explorateur.abstracts.VueExplorer;
import fr.miage.m1.pa.explorateur.abstracts.VueNavigator;

public interface Vue {

	public void setVueNavigator(VueNavigator vue);
	public VueNavigator getVueNavigator();
	
	public void setVueExplorer(VueExplorer vue);
	public VueExplorer getVueExplorer();
	
	public void addVueExplorerListener(VueExplorerListener listener);
	public void addVueNavigatorListener(VueNavigatorListener listener);
	public void addControllerVueListener(ControleurVueListener listener);
	
	public void update();
}
