package fr.miage.m1.pa.explorateur_plugins.decorateurs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.miage.m1.pa.explorateur.enums.Title;
import fr.miage.m1.pa.explorateur.interfaces.Modele;

public class DecorateurTitres extends AbstractTableModel implements Modele {

	private Modele modele;
	
	public DecorateurTitres(Modele modele) {
		this.modele = modele;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFileAt(int row) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setCurrentPath(File currentPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File getCurrentPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Title> getTitles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getFileList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDatas(String[][] datas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void populate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[][] getDatas() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
