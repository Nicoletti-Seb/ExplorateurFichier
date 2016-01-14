package fr.miage.m1.pa.explorateur_plugins.decorator;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.decorator.ModeleDecorator;
import fr.miage.m1.pa.explorateur.enums.Title;

public class PluginVueTailleDossierDecorator extends ModeleDecorator {

	@Override
	public String getNom() {
		return "PluginVueTailleDossierDecorator";
	}

	@Override
	public void onPlug() {
		System.out.println("onPlug");
		reset();
	}

	@Override
	public void onUnplug() {
		System.out.println("onUnplug");
	}

	@Override
	public void setDatas(String[][] datas) {
		modeleToDecorate.setDatas(datas);
		
		System.out.println("setDATA");
		List<File> fileList = this.getFileList();
		List<Title> titles = this.getTitles();

		for (int i = 0; i < fileList.size(); i++) {

			File f = fileList.get(i);
			for (int j = 0; j < titles.size(); j++) {

				Title title = titles.get(j);

				if (title.equals(Title.SIZE) && f.isDirectory()) {
					String item = readDirSize(f);
					datas[i][j] = item;
				}

			}
		}
	}

	private String readDirSize(File dir) {
		return humanReadableByteCount(getDirSize(dir), true);
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
}
