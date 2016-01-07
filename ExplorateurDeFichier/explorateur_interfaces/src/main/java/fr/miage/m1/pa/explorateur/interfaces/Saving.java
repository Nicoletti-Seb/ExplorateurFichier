package fr.miage.m1.pa.explorateur.interfaces;

import java.io.File;
import java.util.Map;

/*
 * Interface Saving.
 * Implémentée par toutes les classes nécessitant une sauvegarde de leur état.
 */
public interface Saving {

	public Object getObjectToSave();
	public String getFileNameToSave();
	
	public void retrieveSavedObject(Object obj);
	
}
