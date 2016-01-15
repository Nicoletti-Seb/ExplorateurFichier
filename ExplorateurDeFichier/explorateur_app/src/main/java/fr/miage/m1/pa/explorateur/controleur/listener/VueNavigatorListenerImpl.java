package fr.miage.m1.pa.explorateur.controleur.listener;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import fr.miage.m1.pa.explorateur.abstracts.VueNavigator;
import fr.miage.m1.pa.explorateur.controleur.ControleurImpl;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.VueNavigatorListener;

public class VueNavigatorListenerImpl implements VueNavigatorListener{
	
	private Controleur controleur;
	
	public VueNavigatorListenerImpl(Controleur controleur) {
		this.controleur = controleur;
	}
	
	@Override
	public void goToUpFolder() {
		Modele modele = controleur.getModele();
		if (modele.setCurrentPath(modele.getCurrentPath().getParentFile())) {
			String path = modele.getCurrentPath().getAbsolutePath();
			controleur.getVue().getVueNavigator().setPathFile(path);
		}
		
	}
	
	@Override
	public void changePath() {
		Modele modele = controleur.getModele();
		VueNavigator vue = controleur.getVue().getVueNavigator();
		String path = vue.getPathFile();

		if (modele.setCurrentPath(new File(path))) {
			vue.setPathFile(modele.getCurrentPath().getAbsolutePath());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if( ControleurImpl.ACTION_PRECEDENT == e.getActionCommand() ){
			goToUpFolder();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
			changePath();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
