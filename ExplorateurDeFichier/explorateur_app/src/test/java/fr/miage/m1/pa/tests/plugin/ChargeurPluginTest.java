package fr.miage.m1.pa.tests.plugin;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;

public class ChargeurPluginTest {

	private static final String PATH_CLASS = "src/test/ressources/classes";
	private static final String PATH_SAVE = "src/test/ressources/manageur-plugin";
	private ManageurPlugin manageurPlugin;
	private ControleurMock controleurMok;

	@Before
	public void init() {
		controleurMok = new ControleurMock();
		manageurPlugin = new ManageurPlugin(controleurMok);
		Assert.assertTrue(manageurPlugin.chargerPlugins(PATH_CLASS));

		List<String> listPlugin = manageurPlugin.getPlugins();
		Assert.assertFalse(listPlugin.isEmpty());
	}

	@Test
	public void chargeurPluginPlug() {
		List<String> listPlugin = manageurPlugin.getPlugins();
		for (String plugin : listPlugin) {
			manageurPlugin.activerPlugin(plugin);
			Assert.assertTrue(manageurPlugin.pluginEstActive(plugin));
		}

		Assert.assertEquals(listPlugin.size(), controleurMok.getNbGetModele());
		Assert.assertEquals(listPlugin.size(), controleurMok.getNbGetVue());
	}

	@Test
	public void chargeurPluginUnPlug() {
		List<String> listPlugin = manageurPlugin.getPlugins();
		for (String plugin : listPlugin) {
			manageurPlugin.activerPlugin(plugin);
		}

		for (String plugin : listPlugin) {
			manageurPlugin.desactiverPlugin(plugin);
			Assert.assertFalse(manageurPlugin.pluginEstActive(plugin));
		}

		Assert.assertEquals(listPlugin.size() * 2, controleurMok.getNbGetModele());
		Assert.assertEquals(listPlugin.size() * 2, controleurMok.getNbGetVue());
	}

	@Test
	public void chargeurPluginPersistance() {
		// Save
		List<String> listPlugin = manageurPlugin.getPlugins();
		for (String plugin : listPlugin) {
			manageurPlugin.activerPlugin(plugin);
		}
		Assert.assertTrue(manageurPlugin.saveEtatPlugin(PATH_SAVE));
		
		//Load
		ManageurPlugin mp = new ManageurPlugin(new ControleurMock());
		mp.chargerPlugins(PATH_CLASS);
		Assert.assertTrue(mp.loadEtatPlugin(PATH_SAVE));

		//Test
		Assert.assertEquals(manageurPlugin.getEtatPlugins(), mp.getEtatPlugins());
	}

	private class ControleurMock implements Controleur {
		private int nbGetVue;
		private int nbGetModele;

		@Override
		public Vue getVue() {
			nbGetVue++;
			return null;
		}

		@Override
		public Modele getModele() {
			nbGetModele++;
			return null;
		}

		public int getNbGetVue() {
			return nbGetVue;
		}

		public int getNbGetModele() {
			return nbGetModele;
		}
	}
}
