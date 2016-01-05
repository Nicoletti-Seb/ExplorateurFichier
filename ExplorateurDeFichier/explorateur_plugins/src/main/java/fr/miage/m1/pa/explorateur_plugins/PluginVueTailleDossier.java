package fr.miage.m1.pa.explorateur_plugins;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public class PluginVueTailleDossier implements Plugin {

	@Override
	public void plug(Controleur controleur) {

		Modele modele = controleur.getModele();
		
		setDatas(modele);
		
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getModele().reset();
		
	}

	@Override
	public String getNom() {
		
		return "Plugin taille dossier";
	}

	private void setDatas(Modele modele) {
		
		List<File> fileList = modele.getFileList();
		List<Title> titles = modele.getTitles();
		
		String[][] datas = new String[fileList.size()][titles.size()];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0; i < fileList.size(); i++) {

			File f = fileList.get(i);
			
			for(int j = 0; j < titles.size(); j++) {

				Title title = titles.get(j);
				
				String item = "";
				
				switch(title) {

				case NAME :
					 item = f.getName();
					break;
					
				case MODIFIED :
					item  = sdf.format(new Date(f.lastModified()));
					break;
					
				case TYPE :
					item = getFileExtension(f);
					
					break;
					
				case SIZE :
					if(f.isDirectory()) {
						item = readDirSize(f);
					} else {
						item = humanReadableByteCount(f.length(), true);
					}
					break;
				}
				
				datas[i][j] = item;
			}
		}
		
		modele.setDatas(datas);
		
	}
	
	private String readDirSize(File dir) {
		
		return humanReadableByteCount(getDirSize(dir), true);
	}
	
	private long getDirSize(File dir) {
		
		long size = 0;
		
		if(dir.listFiles() == null) return 0;
		for(File f : dir.listFiles()) {
			
			if(f.isDirectory()) {
				size += getDirSize(f);
			}
			else size += f.length();
		}
		
		return size;
	}
	
	private String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
