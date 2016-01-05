package fr.miage.m1.pa.explorateur.interfaces;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.Title;

public interface Modele {

	public File getFileAt(int row);
	public void setCurrentPath(File currentPath);
	
	public List<Title> getTitles();
	public List<File> getFileList();
	public void setDatas(String[][] datas);
	public void reset();
}
