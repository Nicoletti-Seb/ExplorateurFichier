package fr.miage.m1.pa.explorateur.controleur.listener;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;

public class VueExplorerListenerImpl implements VueExplorerListener{
	
	private Controleur controleur;
	
	public VueExplorerListenerImpl(Controleur controleur) {
		this.controleur = controleur;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			handleClick();
		}
	}
	
	@Override
	public void handleClick() {
		Vue vue = controleur.getVue();
		Modele modele = controleur.getModele();
		
		File f = getCurrentFile();
		if(f.isDirectory()) {
			if( modele.setCurrentPath(f) ){
				vue.getVueNavigator().setPathFile(f.getPath());
			}
		} else {
			try {
				Desktop.getDesktop().open(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	
	private File getCurrentFile() {
		Vue vue = controleur.getVue();
		
		return new File(vue.getVueExplorer().getFileSelected().getPath());
	}
}
