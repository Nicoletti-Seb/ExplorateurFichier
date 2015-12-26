package fr.miage.m1.pa.explorateur.controleur.plugin;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

/**
 * Cette class permet de charger et gérer les plugins disponibles.
 */
public class ManageurPlugin {

	private Map<Plugin, Boolean> listePlugin;
	private Controleur controleur;

	public ManageurPlugin(Controleur controleur) {
		this.listePlugin = new LinkedHashMap<>();
		this.controleur = controleur;
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
	 * @return L'état de chaque plugin. (Activé ou non )
	 */
	public Map<String, Boolean> getEtatPlugins() {
		Map<String, Boolean> resultat = new LinkedHashMap<>();
		Iterator<Plugin> i = listePlugin.keySet().iterator();
		
		while(i.hasNext()){
			Plugin plugin = i.next();
			resultat.put(plugin.getNom(), listePlugin.get(plugin));
		}
		
		return resultat;
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

}
