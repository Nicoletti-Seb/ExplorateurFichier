package fr.miage.m1.pa.explorateur_plugins.decorateurs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.interfaces.Modele;

public class DecorateurTailleDossier extends AbstractTableModel implements Modele {

	private Modele modele;
	
	public DecorateurTailleDossier(Modele modele) {
		this.modele = modele;
	}

	@Override
	public File getFileAt(int row) {
		return modele.getFileAt(row);
	}

	@Override
	public boolean setCurrentPath(File currentPath) {

		if( currentPath == null || !currentPath.exists()){
			return false;
		}
		
		if(!modele.getCurrentPath().equals(currentPath)) {
			modele.setCurrentPath(currentPath);
			populate();
			
			return true;
		}
		
		return false;
	}

	@Override
	public File getCurrentPath() {
		return modele.getCurrentPath();
	}

	@Override
	public List<Title> getTitles() {
		return modele.getTitles();
	}

	@Override
	public List<File> getFileList() {
		return modele.getFileList();
	}

	@Override
	public void setDatas(String[][] datas) {
		modele.setDatas(datas);
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int col) {
		return modele.getTitles().get(col).toString();
	}

	@Override
	public void reset() {
		modele.populate();
	}

	@Override
	public void populate() {
		
		List<File> fileList = modele.getFileList();
		List<Title> titles = modele.getTitles();
		
		String[][] datas = new String[fileList.size()][titles.size()];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0; i < fileList.size(); i++) {

			File f = fileList.get(i);
			
			for(int j = 0; j < titles.size(); j++) {

				Title title = titles.get(j);
				
				String item = "";
				
				switch(title) {

				case NAME :
					 item = f.getName();
					break;
					
				case MODIFIED :
					item  = sdf.format(new Date(f.lastModified()));
					break;
					
				case TYPE :
					item = getFileExtension(f);
					
					break;
					
				case SIZE :
					if(f.isDirectory()) {
						item = readDirSize(f);
					} else {
						item = humanReadableByteCount(f.length(), true);
					}
					break;
				}
				
				datas[i][j] = item;
			}
		}
		
		modele.setDatas(datas);
		fireTableStructureChanged();
		
	}
	
	private String readDirSize(File dir) {
		
		return humanReadableByteCount(getDirSize(dir), true);
	}
	
	private long getDirSize(File dir) {
		
		long size = 0;
		
		if(dir.listFiles() == null) return 0;
		for(File f : dir.listFiles()) {
			
			if(f.isDirectory()) {
				size += getDirSize(f);
			}
			else size += f.length();
		}
		
		return size;
	}
	
	private String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

	@Override
	public int getColumnCount() {
		return modele.getTitles().size();
	}

	@Override
	public int getRowCount() {
		return modele.getFileList().size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return modele.getDatas()[arg0][arg1];
	}

	@Override
	public String[][] getDatas() {
		return modele.getDatas();
	}
	

	
}
