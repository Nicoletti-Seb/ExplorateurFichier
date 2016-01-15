package fr.miage.m1.pa.explorateur_plugins;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class Panel {
	public static void main(String args[]) {
		
		JFrame frame = new JFrame("Gestion Plugin");
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// On cree un panel à onglets
		JTabbedPane onglets = new JTabbedPane();
		
		
		JPanel onglet2 = new JPanel();
		JLabel pluginc = new JLabel("Plugin de couleur fichier");
		onglet2.add(pluginc);
		onglets.addTab("Plugin de Couleur", null);
	
		
		JPanel onglet1 = new JPanel();
		JLabel plugint = new JLabel("Plugin de taille Fichier");
		onglet1.add(plugint);
		onglets.addTab("Plugin de Taille ", null);


		

		// et voila, il faut en plus ajouter le panel à onglet dans le panel
		// principal
		Container contentPane = frame.getContentPane();
		contentPane.add(onglets);

		frame.pack();
		frame.setVisible(true);
	}
}