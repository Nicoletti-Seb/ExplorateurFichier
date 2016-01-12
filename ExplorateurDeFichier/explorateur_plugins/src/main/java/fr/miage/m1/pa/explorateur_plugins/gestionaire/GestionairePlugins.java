package fr.miage.m1.pa.explorateur_plugins.gestionaire;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.Vue;

public class GestionairePlugins {

	private static GestionairePlugins instance;
	
	private Modele baseModele;
	private Vue baseVue;
	
	private List<Plugin> analysePlugins;
	private List<Plugin> vuePlugins;
	
	private GestionairePlugins(Modele modele, Vue vue) {
		
		analysePlugins = new LinkedList<>();
		vuePlugins = new LinkedList<>();
		
		baseModele = modele;
		baseVue = vue;
	}
	
	public static GestionairePlugins getInstance(Modele modele, Vue vue) {
		
		if(instance == null) {
			instance = new GestionairePlugins(modele, vue);
		}
		
		return instance;
	}
	
	public Modele addAnalysePlugin(Plugin plugin) {
		
		analysePlugins.add(plugin);
		
		return getCurrentModele();
	}
	
	public Modele removeAnalysePlugin(Plugin plugin) {
		
		analysePlugins.remove(plugin);
		
		return getCurrentModele();
	}
	
	private Modele getCurrentModele() {
		
		Modele result = baseModele;
		
		for(Plugin p : analysePlugins) {
			
			try {
				Constructor c = p.getDecorator().getConstructor(Modele.class);
				try {
					result = (Modele) c.newInstance(result);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
