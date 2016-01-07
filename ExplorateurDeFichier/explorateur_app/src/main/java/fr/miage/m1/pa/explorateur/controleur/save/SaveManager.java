package fr.miage.m1.pa.explorateur.controleur.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.miage.m1.pa.explorateur.interfaces.Saving;

public class SaveManager {	

	// Le répertoire où toutes les sauvegardes sont effectuées
	//
	private static final File saveDir = new File("./stateDir");
	
	public SaveManager() {
		
		if(!saveDir.exists()) saveDir.mkdir();
	}
	
	public void saveState(Saving saving) {
		
		Object obj = saving.getObjectToSave();
		String f = saving.getFileNameToSave();
		
		if(obj != null && f != null) saveObject(obj, f);
		
	}
	
	private void saveObject(Object obj, String f) {
		
		File file = new File(saveDir, f);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			try {
				oos.writeObject(obj);
				oos.flush();
			} finally {
				oos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void retrieveState(Saving saving) {
		
		Object obj = retrieveObject(saving.getFileNameToSave());
		if(obj != null) saving.retrieveSavedObject(obj);
	}
	
	private Object retrieveObject(String f) {
		
		File file = new File(saveDir, f);
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			try {
				return ois.readObject();
			} finally {
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
