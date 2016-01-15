package fr.miage.m1.pa.explorateur_plugins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileSystemView;

import fr.miage.m1.pa.explorateur.abstracts.VueExplorer;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.Plugin;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;

public class PluginMiniature extends VueExplorer implements Plugin {

	private VueExplorer oldVueExplorer;
	
	private static final long serialVersionUID = 1L;
	private JList<FileReader> list;
	private DefaultListModel<FileReader> model;

	@Override
	public void plug(Controleur controleur) {
		setLayout(new BorderLayout());
		
		model = new DefaultListModel<>();
		
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setCellRenderer(new FileReaderRenderer());
		
		JScrollPane listScroller = new JScrollPane(list);
		add(listScroller, BorderLayout.CENTER);
		
		oldVueExplorer = controleur.getVue().getVueExplorer();
		controleur.getVue().setVueExplorer(this);
		
		VueExplorerListener listener = oldVueExplorer.getVueExplorerListener();
		
		oldVueExplorer.removeExplorerListener();
		addVueExplorerListener(listener);
		controleur.update();
	}

	@Override
	public void unplug(Controleur controleur) {
		controleur.getVue().setVueExplorer(oldVueExplorer);
		
		VueExplorerListener listener = getVueExplorerListener();
		removeExplorerListener();
		oldVueExplorer.addVueExplorerListener(listener);
		
		controleur.update();
	}

	@Override
	public String getNom() {
		return "Miniature";
	}

	@Override
	public JPanel getPanelConfig() {
		return null;
	}

	
	@Override
	public void addFileToDiplay(FileReader fileReader) {
		model.addElement(fileReader);
	}

	@Override
	public void clean() {
		model.removeAllElements();
	}

	@Override
	public void addVueExplorerListener(VueExplorerListener listener) {
		super.addVueExplorerListener(listener);
		list.addMouseListener(listener);
	}
	
	@Override
	public void removeExplorerListener() {
		super.removeExplorerListener();
		list.addMouseListener(getVueExplorerListener());
	}

	@Override
	public FileReader getFileSelected() {
		return list.getSelectedValue();
	}
	
	static private class FileReaderRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
	    public Component getListCellRendererComponent( JList<?> list, Object value, int index,
	            boolean isSelected, boolean cellHasFocus) {

			FileReader fileReader = (FileReader) value;
			
			
			ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(new File(fileReader.getPath()));
			JLabel label = new JLabel(icon, JLabel.CENTER);
			
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(label, BorderLayout.CENTER);
			mainPanel.add(new JLabel(fileReader.getName(), JLabel.CENTER), BorderLayout.SOUTH);
			
			if( isSelected ){
				mainPanel.setBackground(Color.blue);
			}
			else{
				mainPanel.setBackground(Color.white);
			}
	        return mainPanel;
	    }
	}
	
	public static void main(String[] args) {
		System.out.println("Build");
	}

}
