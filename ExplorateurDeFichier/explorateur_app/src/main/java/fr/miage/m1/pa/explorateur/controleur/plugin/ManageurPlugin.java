package fr.miage.m1.pa.explorateur.controleur.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fr.miage.m1.pa.explorateur.controleur.classloader.RepositoryG;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.PluginListener;
import fr.miage.m1.pa.explorateur.interfaces.Saving;

/**
 * Cette class permet de charger et gérer les plugins disponibles.
 */
public class ManageurPlugin implements Saving, PluginListener {

	private static final String STATE_FILE = "ManageurPlugin";
	private static final String PATH = "../explorateur_plugins/target/classes";
	
	private Map<Plugin, Boolean> listePlugin;
	private Controleur controleur;
	

	public ManageurPlugin(Controleur controleur) {
		this.listePlugin = new LinkedHashMap<>();
		this.controleur = controleur;
		chargerPlugins(PATH);
	}
	
	public boolean onPluginClicked(String nomPlugin) {
		
		if(pluginEstActive(nomPlugin)) {
			return desactiverPlugin(nomPlugin);
		} else {
			return activerPlugin(nomPlugin);
		}
	}

	/**
	 * @return true, si le plugin existe et qu'il est activé.
	 */
	public boolean pluginEstActive(String nomPlugin) {
		Plugin plugin = getPlugin(nomPlugin);
		if (plugin == null) {
			return false;
		}

		return listePlugin.get(plugin);
	}
	
	public void showPluginView(String nomPlugin) {
		
		Plugin plugin = getPlugin(nomPlugin);
		if(plugin != null) plugin.showView(pluginEstActive(nomPlugin), this);
	}

	/**
	 * Activer un plugin
	 * 
	 * @return return true, si cela a fonctionné ou s'il est déja activé.
	 */
	public boolean activerPlugin(String nomPlugin) {
		Plugin plugin = getPlugin(nomPlugin);
		if (plugin == null) {
			return false;
		}

		if (listePlugin.get(plugin)) {
			return true;
		}

		plugin.plug(controleur);
		listePlugin.put(plugin, true);
		return true;
	}

	/**
	 * Désactiver un plugin
	 * 
	 * @return return true, si cela a fonctionné ou s'il est déja désactivé.
	 */
	public boolean desactiverPlugin(String nomPlugin) {
		Plugin plugin = getPlugin(nomPlugin);
		if (plugin == null) {
			return false;
		}

		if (!listePlugin.get(plugin)) {
			return true;
		}

		plugin.unplug(controleur);
		listePlugin.put(plugin, false);
		return true;
	}

	/**
	 * @return L'état de chaque plugin. ( Activé ou non )
	 */
	public Map<String, Boolean> getEtatPlugins() {
		Map<String, Boolean> resultat = new LinkedHashMap<>();
		Iterator<Plugin> i = listePlugin.keySet().iterator();

		while (i.hasNext()) {
			Plugin plugin = i.next();
			resultat.put(plugin.getNom(), listePlugin.get(plugin));
		}

		return resultat;
	}

	/**
	 * Charge les plugins à partir de l'emplacement donné en paramétre.
	 * 
	 * @return true, si la fonction c'est effectué sans erreur.
	 */
	public boolean chargerPlugins(String path) {
		File dossier = new File(path);
		if (!dossier.exists() || !dossier.isDirectory()) {
			return false;
		}

		RepositoryG<Plugin> repositoryG = new RepositoryG<>(new File(path), Plugin.class);
		List<Class<? extends Plugin>> listClass = repositoryG.load();

		for (Class<? extends Plugin> cl : listClass) {
			try {
				Plugin plugin = cl.newInstance();
				listePlugin.put(plugin, false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * @return la liste de tous les plugins charger
	 */
	public List<String> getPlugins() {
		List<String> resultat = new LinkedList<>();

		Iterator<Plugin> i = listePlugin.keySet().iterator();
		while (i.hasNext()) {
			Plugin plugin = i.next();
			resultat.add(plugin.getNom());
		}

		return resultat;
	}

	/**
	 * Sauvegarde l'etat de tous les plugins charger.
	 * 
	 * @param path
	 *            : Le chemin vers le fichier de destination.
	 * @return
	 */
	public boolean saveEtatPlugin(String path) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			try {
				oos.writeObject(getEtatPlugins());
				oos.flush();
			} finally {
				oos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Charge l'état des plugins à partire d'un fichier. (Active ou désactive
	 * les plugins)
	 * 
	 * @param path
	 *            Le chemin vers le fichier source.
	 * @return true, si le chargement c'est éffectué sans erreur.
	 */
	@SuppressWarnings("unchecked")
	public boolean loadEtatPlugin(String path) {
		File file = new File(path);
		if (!file.exists() || !file.isFile()) {
			return false;
		}

		Map<String, Boolean> etatPlugin = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			try {
				etatPlugin = (Map<String, Boolean>) ois.readObject();
			} finally {
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		updatePlugin(etatPlugin);
		return true;
	}

	private void updatePlugin(Map<String, Boolean> etatPlugin) {
		Iterator<String> i = etatPlugin.keySet().iterator();
		while (i.hasNext()) {
			String nomPlugin = i.next();
			if (etatPlugin.get(nomPlugin)) {
				activerPlugin(nomPlugin);
			} else {
				desactiverPlugin(nomPlugin);
			}
		}
	}

	private Plugin getPlugin(String nomPlugin) {
		if (nomPlugin == null || nomPlugin.isEmpty()) {
			return null;
		}

		Iterator<Plugin> i = listePlugin.keySet().iterator();
		while (i.hasNext()) {
			Plugin plugin = i.next();
			if (nomPlugin.equals(plugin.getNom())) {
				return plugin;
			}
		}

		return null;
	}

	@Override
	public Object getObjectToSave() {
		
		return getEtatPlugins();
	}

	@Override
	public String getFileNameToSave() {
		
		return STATE_FILE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void retrieveSavedObject(Object obj) {
		
		Map<String, Boolean> etatPlugin = (Map<String, Boolean>) obj;
		if(etatPlugin != null) updatePlugin(etatPlugin);
	}

	@Override
	public void onStateChecked(String nomPlugin) {
		
		if(pluginEstActive(nomPlugin)) {
			desactiverPlugin(nomPlugin);
		} else {
			activerPlugin(nomPlugin);
		}
		
		
	}
	

}