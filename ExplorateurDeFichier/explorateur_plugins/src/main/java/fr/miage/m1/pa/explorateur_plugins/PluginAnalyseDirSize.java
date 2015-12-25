package fr.miage.m1.pa.explorateur_plugins;
import java.io.File;

import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class PluginAnalyseDirSize implements Plugin {

	public void plug(Controleur controleur) {

	//	File Rep = new File(path);
	File Rep = getPathCurrent();
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

	public File getPathCurrent() {
		File f = new File(".");
		return f;
	}

	@Override
	public void unplug(Controleur controleur) {
		// TODO Auto-generated method stub

	}



}
