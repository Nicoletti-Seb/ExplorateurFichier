package fr.miage.m1.pa.explorateur.abstracts;

import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;

public abstract class VueExplorer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public abstract void addFileToDiplay(FileReader fileReader);
	public abstract void clean();
	public abstract void addVueExplorerListener(VueExplorerListener listener);
	public abstract FileReader getFileSelected();
}
