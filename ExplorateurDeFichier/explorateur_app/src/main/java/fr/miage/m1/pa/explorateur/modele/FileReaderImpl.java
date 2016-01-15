package fr.miage.m1.pa.explorateur.modele;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.miage.m1.pa.explorateur.enums.TypeFileReader;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;

public class FileReaderImpl implements FileReader{
	
	private File file;
	
	public FileReaderImpl(File file) {
		this.file = file;
	}

	@Override
	public String getName() {		
		return file.getName();
	}

	@Override
	public String getModified() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date(file.lastModified()));
	}

	@Override
	public String getFileType() {
		return getFileExtension(file);
	}
	
	private String getFileExtension(File file){
		String fileName = file.getName();
		
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
        	return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        else{
        	return "";
        }
		
	}

	@Override
	public String getSize() {
		return humanReadableByteCount(file.length(), true);
	}
	
	private String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    
	    if (bytes < unit){
	    	return bytes + " B";
	    }
	    
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	@Override
	public String getPath() {
		return file.getAbsolutePath();
	}
	
	@Override
	public boolean isDirectory() {
		return file.isDirectory();
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}
	

	@Override
	public List<FileReader> getListFileReader() {
		List<FileReader> result = new ArrayList<>();
		
		for(File f : file.listFiles()){
			result.add(new FileReaderImpl(f));
		}
		
		return result;
	}

	@Override
	public final TypeFileReader getType() {
		return TypeFileReader.FILE_READER;
	}

}
