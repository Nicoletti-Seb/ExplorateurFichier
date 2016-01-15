package fr.miage.m1.pa.explorateur.interfaces;

import java.io.File;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.TypeFileReader;

public interface FileReader {	
	public String getName();
	public String getModified();
	public String getFileType();
	public String getSize();
	public String getPath();
	public boolean isDirectory();
	public void setFile(File file);
	public File getFile();
	public List<FileReader> getListFileReader();
	public TypeFileReader getType();
}
