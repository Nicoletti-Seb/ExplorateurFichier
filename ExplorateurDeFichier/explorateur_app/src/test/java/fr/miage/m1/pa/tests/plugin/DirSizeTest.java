package fr.miage.m1.pa.tests.plugin;

	import java.io.File;
	import java.util.List;

	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.Ignore;
	import org.junit.Test;

	import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
	import fr.miage.m1.pa.explorateur.interfaces.Controleur;
	import fr.miage.m1.pa.explorateur.interfaces.Modele;
	import fr.miage.m1.pa.explorateur.interfaces.Vue;

	public class DirSizeTest {
		private static final String PATH = "src/test/ressources/classes";
		private ManageurPlugin manageurPlugin;
		private ControleurMock controleurMok;

		@Before
		public void init() {
			controleurMok = new ControleurMock();
			manageurPlugin = new ManageurPlugin(controleurMok);
			Assert.assertTrue(manageurPlugin.chargerPlugins(PATH));

			List<String> listPlugin = manageurPlugin.getPlugins();
			Assert.assertFalse(listPlugin.isEmpty());
		}
		@Test
		public void plugSize() {

			File Rep = new File(PATH);

			long taille = 0;

			for (File f : Rep.listFiles()) {

				if (f.isDirectory())
					taille += calculRecursive(f);
				else if (f.isFile())
					taille += f.length();

			}
			System.out.println("la taille du compos " + taille);

		}

		public double calculRecursive(File f) {

			double taille = 0;

			for (File file : f.listFiles()) {

				if (file.isDirectory())
					taille += calculRecursive(file);
				else if (file.isFile())
					taille += file.length();

			}
			return taille;
		}
		@Test
		public void unplug() {
			// TODO Auto-generated method stub

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
