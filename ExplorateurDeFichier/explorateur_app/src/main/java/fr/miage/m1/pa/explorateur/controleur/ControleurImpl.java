package fr.miage.m1.pa.explorateur.controleur;

import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.miage.m1.pa.explorateur.controleur.classloader.DynamicPluginLoader;
import fr.miage.m1.pa.explorateur.controleur.listener.VueExplorerListenerImpl;
import fr.miage.m1.pa.explorateur.controleur.listener.VueNavigatorListenerImpl;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginListener;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginVue;
import fr.miage.m1.pa.explorateur.controleur.save.SaveManager;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.Saving;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.modele.FileReaderImpl;
import fr.miage.m1.pa.explorateur.modele.ModeleImpl;
import fr.miage.m1.pa.explorateur.vue.VueImpl;

public class ControleurImpl implements Controleur, ControleurVueListener, ManageurPluginListener, Saving {

	private static final String SAVE_FILE = "Controleur";
	public static final String ACTION_PRECEDENT = "ACTION_PRECEDENT";


	private ManageurPlugin managerPlugin;
	private SaveManager saveManager;
	private Modele modele;
	private Vue vue;
	
	private VueExplorerListenerImpl explorerListener;
	private VueNavigatorListenerImpl navigatorListener;

	public ControleurImpl() {
		managerPlugin = new ManageurPlugin(this);
		saveManager = new SaveManager();
		
		//Model
		File currentPath = new File(System.getProperty("user.dir"));
		ModeleImpl modele = new ModeleImpl(new FileReaderImpl(currentPath));
		this.modele = modele;
		
		//Listener
		explorerListener = new VueExplorerListenerImpl(this);
		navigatorListener = new VueNavigatorListenerImpl(this);
		
		//Vue
		VueImpl vue = new VueImpl();
		this.vue = vue;
		vue.addVueExplorerListener(explorerListener);
		vue.addVueNavigatorListener(navigatorListener);
		vue.addControleurVueListener(this);
		
		//observer
		modele.addObserver(vue);

		modele.setCurrentPath(currentPath);
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
	public void onMenuClicked(String name) {

		if (name.equals("Plugins")) {
			new ManageurPluginVue(this, managerPlugin);
		} else if (name.equals("Charger plugins")) {
			showFileChooser();
		}
		
		System.out.println(name);

	}
	
	private void showFileChooser() {
		
		JFileChooser chooser = new JFileChooser(modele.getCurrentPath());
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JAR ou ZIP", "jar", "zip");
		chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       File result = chooser.getSelectedFile();
	       
	       DynamicPluginLoader<Plugin> dpl = new DynamicPluginLoader<>(Plugin.class);
	       List<Class<? extends Plugin>> plugins = dpl.load(result);
	       managerPlugin.addPlugins(plugins);
	    }
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
		return modele.getCurrentPath();
	}

	@Override
	public String getFileNameToSave() {
		return SAVE_FILE;
	}

	@Override
	public void retrieveSavedObject(Object obj) {
		modele.setCurrentPath((File) obj);
	}

	@Override
	public void setModele(Modele model) {
		this.modele = model;
	}

	@Override
	public void update() {
		modele.update();
		vue.update();
	}

}
