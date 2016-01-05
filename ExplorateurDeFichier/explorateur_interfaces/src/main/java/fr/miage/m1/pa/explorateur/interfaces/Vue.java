package fr.miage.m1.pa.explorateur.interfaces;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

public interface Vue {

	public void setMouseListener(MouseListener listener);
	public void setControleurListener(ControleurVueListener listener);
	public JTable getMainTable();
	
	public void setPluginMenu(List<String> plugins);
}
