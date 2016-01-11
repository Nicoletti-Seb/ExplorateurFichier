package fr.miage.m1.pa.explorateur.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginListener;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginVue;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.modele.ModeleImpl;
import fr.miage.m1.pa.explorateur.vue.VueImpl;

public class ControleurImpl implements Controleur, MouseListener, ControleurVueListener, ManageurPluginListener {

	private File currentFile;
	
	private ManageurPlugin managerPlugin;
	
	private Modele modele;
	private Vue vue;
	
	public ControleurImpl() {
		
		//currentFile = new File("C:/Users/kenzo/Documents/M1_MIAGE/ProjetPA");
		currentFile = new File("C:/Users/deptinfo/Desktop/TestNour");
		    
		
		managerPlugin = new ManageurPlugin();
		
		modele = new ModeleImpl(currentFile);
		vue = new VueImpl(modele);
		//vue.setPluginMenu(managerPlugin.getPlugins());
		vue.setMouseListener(this);
		vue.setControleurListener(this);
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
				if(f.isDirectory()) {
					modele.setCurrentPath(f);
				}
				
			}
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
		
		managerPlugin.onPluginClicked(plugin, this);
		
	}

	@Override
	public JTable DataTable() {
		// Recuperer Jtable du frame
		return null;
	}

	@Override
	public TableModel DataModel() {
		// Recuperer TableModel du Jtable
		return null;
	}
	

}
