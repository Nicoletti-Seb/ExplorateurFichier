package fr.miage.m1.pa.explorateur.modele;

import java.io.File;
import java.util.Observable;

import fr.miage.m1.pa.explorateur.enums.TypeModele;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.Modele;

public class ModeleImpl extends Observable implements Modele {
	
	private File currentPath;
	private FileReader fileReader;
	
	public ModeleImpl(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public TypeModele getType() {
		return TypeModele.MODEL;
	}

	@Override
	public File getCurrentPath() {
		return currentPath;
	}

	@Override
	public boolean setCurrentPath(File currentPath) {
		if( currentPath == null || !currentPath.exists() || !currentPath.isDirectory() ){
			return false;
		}
		
		this.currentPath = currentPath;
		fileReader.setFile(currentPath);
		
		update();
		return true;
	}

	private void update(){
		setChanged();
		notifyObservers(fileReader);
	}

	@Override
	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public FileReader getFileReader() {
		return fileReader;
	}
}
