package fr.miage.m1.pa.explorateur.interfaces;

import java.io.File;

import fr.miage.m1.pa.explorateur.enums.TypeModele;

public interface Modele {	
	public File getCurrentPath();
	public boolean setCurrentPath(File currentPath);
	
	public TypeModele getType();
	
	public void setFileReader(FileReader fileReader);
	public FileReader getFileReader();
}
