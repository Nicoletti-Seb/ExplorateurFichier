package fr.miage.m1.pa.explorateur.abstracts;

import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;

public abstract class VueExplorer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected VueExplorerListener explorerListener;
	
	public abstract void addFileToDiplay(FileReader fileReader);
	public abstract void clean();
	public abstract FileReader getFileSelected();
	
	public void addVueExplorerListener(VueExplorerListener listener){
		this.explorerListener = listener;
	}
	
	public VueExplorerListener getVueExplorerListener(){
		return explorerListener;
	}
	
	public void removeExplorerListener(){
		explorerListener = null;
	}
}
