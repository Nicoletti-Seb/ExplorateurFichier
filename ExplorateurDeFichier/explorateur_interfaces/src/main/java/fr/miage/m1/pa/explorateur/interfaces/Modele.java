package fr.miage.m1.pa.explorateur.interfaces;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.enums.TypeModele;

public interface Modele {

	public File getFileAt(int row);
	public boolean setCurrentPath(File currentPath);
	public File getCurrentPath();
	
	public List<Title> getTitles();
	public List<File> getFileList();
	public void setDatas(String[][] datas);
	public void reset();
	
	public TypeModele getType();
}
