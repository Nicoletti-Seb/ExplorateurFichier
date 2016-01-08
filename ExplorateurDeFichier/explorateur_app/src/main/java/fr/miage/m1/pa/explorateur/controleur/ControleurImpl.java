package fr.miage.m1.pa.explorateur.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginListener;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginVue;
import fr.miage.m1.pa.explorateur.controleur.save.SaveManager;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Saving;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.modele.ModeleImpl;
import fr.miage.m1.pa.explorateur.vue.VueImpl;

public class ControleurImpl implements Controleur, MouseListener, ControleurVueListener, ManageurPluginListener, Saving {

	private static final String SAVE_FILE = "Controleur";
	
	private File currentFile;
	
	private ManageurPlugin managerPlugin;
	private SaveManager saveManager;
	
	private Modele modele;
	private Vue vue;
	
	public ControleurImpl() {
		
		currentFile = new File("./");
		
		managerPlugin = new ManageurPlugin(this);
		saveManager = new SaveManager();
		
		modele = new ModeleImpl(currentFile);
		vue = new VueImpl(modele);
		//vue.setPluginMenu(managerPlugin.getPlugins());
		vue.setMouseListener(this);
		vue.setControleurListener(this);
		init();
		
	}
	
	private void init() {

		saveManager.retrieveState(managerPlugin);
		saveManager.retrieveState(this);
	}
	
	@Override
	public Vue getVue() {
		return vue;
	}

	@Override
	public Modele getModele() {
		return modele;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource().equals(vue.getMainTable())) {
			if(e.getClickCount() == 2) {
				
				File f = modele.getFileAt(vue.getMainTable().getSelectedRow());
				setCurrentPath(f);
				
			}
		}
		
	}
	
	private void setCurrentPath(File f) {
		if(f.isDirectory()) {
			modele.setCurrentPath(f);
			currentFile = f;
		}
	}

	@Override
	public void onMenuClicked(String name) {
		
		if(name.equals("Plugins")) {
			new ManageurPluginVue(this, managerPlugin);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPluginSelected(String plugin) {
		
		managerPlugin.onPluginClicked(plugin);
		
	}

	@Override
	public void onClose() {
		
		saveManager.saveState(managerPlugin);
		saveManager.saveState(this);
		
	}

	@Override
	public Object getObjectToSave() {
		return currentFile;
	}

	@Override
	public String getFileNameToSave() {
		return SAVE_FILE;
	}

	@Override
	public void retrieveSavedObject(Object obj) {
		
		setCurrentPath((File) obj);
		
	}
	

}
