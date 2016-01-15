package fr.miage.m1.pa.explorateur_plugins.decorator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.miage.m1.pa.explorateur.decorator.FileReaderDecorator;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;

public class PluginVueTailleDossierDecorator extends  FileReaderDecorator{

	@Override
	public String getNom() {
		return "PluginVueTailleDossierDecorator";
	}

	@Override
	public void onPlug(Controleur controleur) {
		System.out.println("onPlug");
		controleur.update();
	}

	@Override
	public void onUnplug(Controleur controleur) {
		System.out.println("onUnplug");
		controleur.update();
	}

	@Override
	public String getSize() {
		return humanReadableByteCount(getDirSize(getFile()), true);
	}

	private long getDirSize(File dir) {

		long size = 0;

		if (dir.listFiles() == null)
			return 0;
		for (File f : dir.listFiles()) {

			if (f.isDirectory()) {
				size += getDirSize(f);
			} else
				size += f.length();
		}

		return size;
	}

	private String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	public static void main(String[] args) {
		System.out.println("Build PluginVueTailleDossierDecorator");
	}

	@Override
	public List<FileReader> getListFileReader() {
		
		List<FileReader> list = super.getListFileReader();
		List<FileReader> result = new ArrayList<>();
		
		for(FileReader f : list){
			PluginVueTailleDossierDecorator plugin = new PluginVueTailleDossierDecorator();
			plugin.setFileReaderToDecorate(f);
			result.add(plugin);
		}
		
		return result;
	}

}
