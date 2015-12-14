package miage.m1.pa.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import miage.m1.pa.project.beans.Title;

public class FileExplorerModel extends AbstractTableModel {

	private static final long serialVersionUID = 6649734622340343522L;
	
	private List<Title> titles;
	private String[][] datas;
	private File currentPath;
	private List<File> fileList;

	public FileExplorerModel(File currentPath) {
		super();
		this.currentPath = currentPath;
		titles = new LinkedList<Title>(Arrays.asList(Title.values()));
		populate();
	}


	public FileExplorerModel(List<Title> titles, File currentPath) {
		super();
		this.titles = titles;
		
		if(!currentPath.equals(this.currentPath)) {
			this.currentPath = currentPath;
			populate();
		}
	}

	private void populate() {

		datas = new String[currentPath.listFiles().length][titles.size()];
		fileList = Arrays.asList(currentPath.listFiles());
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
					item = humanReadableByteCount(f.length(), true);
					break;
				}
				
				datas[i][j] = item;
			}
		}
		
		fireTableDataChanged();

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
		return titles.size();
	}

	@Override
	public int getRowCount() {
		return datas.length;
	}
	
	public String getColumnName(int col) {
		return titles.get(col).toString();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return datas[arg0][arg1];
	}


	public List<Title> getTitles() {
		return titles;
	}

	public File getFileAt(int row) {
		
		return fileList.get(row);
	}


	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}



	public File getCurrentPath() {
		return currentPath;
	}



	public void setCurrentPath(File currentPath) {
		if(!currentPath.equals(this.currentPath)) {
			this.currentPath = currentPath;
			populate();
		}
	}


}
