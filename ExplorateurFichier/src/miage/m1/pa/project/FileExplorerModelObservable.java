package miage.m1.pa.project;

import java.io.File;
import java.util.List;
import java.util.Observable;

import miage.m1.pa.project.beans.Title;

public class FileExplorerModelObservable extends Observable {

	private FileExplorerModel model;
	
	public FileExplorerModelObservable(File currentPath) {
		super();
		model = new FileExplorerModel(currentPath);
	}

	public FileExplorerModelObservable(List<Title> titles, File currentPath) {
		super();
		model = new FileExplorerModel(titles, currentPath);
	}
	
	public List<Title> getTitles() {
		return model.getTitles();
	}

	public void setTitles(List<Title> titles) {
		model.setTitles(titles);
	}

	public File getCurrentPath() {
		return model.getCurrentPath();
	}

	public void setCurrentPath(File currentPath) {
		model.setCurrentPath(currentPath);
		setChanged();
		notifyObservers();
	}
}
