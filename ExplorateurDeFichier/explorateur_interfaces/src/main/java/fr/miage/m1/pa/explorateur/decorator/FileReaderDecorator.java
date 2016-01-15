package fr.miage.m1.pa.explorateur.decorator;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.TypeFileReader;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public abstract class FileReaderDecorator implements FileReader, Plugin{
	
	protected FileReader fileReaderToDecorate;
	private FileReader fileReaderDecorateMe;
	
	public abstract void onPlug(Controleur controleur);
	public abstract void onUnplug(Controleur controleur);
	
	@Override
	public  String getModified() {
		return fileReaderToDecorate.getModified();
	}

	@Override
	public String getSize() {
		return fileReaderToDecorate.getSize();
	}

	@Override
	public void setFile(File file) {
		fileReaderToDecorate.setFile(file);
	}
	
	@Override
	public File getFile() {
		return fileReaderToDecorate.getFile();
	}
	
	@Override
	public String getPath(){
		return fileReaderToDecorate.getPath();
	}

	@Override
	public List<FileReader> getListFileReader(){
		return fileReaderToDecorate.getListFileReader();
	}
	
	@Override
	public String getFileType() {
		return fileReaderToDecorate.getFileType();
	}

	@Override
	public boolean isDirectory() {
		return fileReaderToDecorate.isDirectory();
	}
	
	@Override
	public String getName() {
		return fileReaderToDecorate.getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		return fileReaderToDecorate.equals(obj);
	}
	
	@Override
	public String toString() {
		return fileReaderToDecorate.toString();
	}
	
	@Override
	public final void plug(Controleur controleur) {
		fileReaderToDecorate = controleur.getModele().getFileReader();
		
		if( fileReaderToDecorate.getType() == TypeFileReader.DECORATOR ){
			FileReaderDecorator fileReaderDecorator = (FileReaderDecorator) fileReaderToDecorate;
			fileReaderDecorator.setFileReaderDecorateMe(this);
		}
		
		controleur.getModele().setFileReader(this);
		onPlug(controleur);
	}

	@Override
	public final void unplug(Controleur controleur) {
		if( fileReaderDecorateMe != null ){
			FileReaderDecorator fileReaderDecorator = (FileReaderDecorator) fileReaderDecorateMe;
			fileReaderDecorator.setFileReaderToDecorate(fileReaderToDecorate);
		}else{
			controleur.getModele().setFileReader(fileReaderToDecorate);
		}
		onUnplug(controleur);
	}
	
	public void setFileReaderToDecorate(FileReader fileReader){
		fileReaderToDecorate = fileReader;
	}
	
	private void setFileReaderDecorateMe(FileReader fileReader){
		fileReaderDecorateMe = fileReader;
	}
	
	
	@Override
	public final TypeFileReader getType() {
		return TypeFileReader.DECORATOR;
	}
	
	@Override
	public abstract String getNom();
}
