package fr.miage.m1.pa.explorateur.decorator;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.enums.TypeModele;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;

public abstract class ModeleDecorator implements Modele, Plugin{
	
	protected Modele modeleToDecorate;
	private Modele modeleDecorateMe;
	
	
	public abstract void onPlug();
	public abstract void onUnplug();
	
	@Override
	public File getFileAt(int row) {
		return modeleToDecorate.getFileAt(row);
	}

	@Override
	public boolean setCurrentPath(File currentPath) {
		return modeleToDecorate.setCurrentPath(currentPath);
	}

	@Override
	public File getCurrentPath() {
		return modeleToDecorate.getCurrentPath();
	}

	@Override
	public List<Title> getTitles() {
		return modeleToDecorate.getTitles();
	}

	@Override
	public List<File> getFileList() {
		return modeleToDecorate.getFileList();
	}

	@Override
	public void setDatas(String[][] datas) {
		modeleToDecorate.setDatas(datas);
	}

	@Override
	public void reset() {
		modeleToDecorate.reset();
	}
	
	@Override
	public boolean equals(Object obj) {
		return modeleToDecorate.equals(obj);
	}
	
	@Override
	public String toString() {
		return modeleToDecorate.toString();
	}

	@Override
	public final void plug(Controleur controleur) {
		modeleToDecorate = controleur.getModele();
		
		if( modeleToDecorate.getType() == TypeModele.DECORATOR ){
			ModeleDecorator modeleDecorator = (ModeleDecorator) modeleToDecorate;
			modeleDecorator.setModeleDecorateMe(this);
		}
		
		controleur.setModele(this);
		onPlug();
	}

	@Override
	public final void unplug(Controleur controleur) {
		if( modeleDecorateMe != null ){
			ModeleDecorator modeleDecorator = (ModeleDecorator) modeleDecorateMe;
			modeleDecorator.setModeleToDecorate(modeleToDecorate);
		}else{
			controleur.setModele(modeleToDecorate);
		}
		
		onUnplug();
	}
	
	private void setModeleToDecorate(Modele modele){
		modeleToDecorate = modele;
	}
	
	private void setModeleDecorateMe(Modele modele){
		modeleDecorateMe = modele;
	}

	public final TypeModele getType(){
		return TypeModele.DECORATOR;
	}
	
	@Override
	public abstract String getNom();
	
}