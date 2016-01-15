package fr.miage.m1.pa.explorateur_plugins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GenerateurPlugin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable tableau;
	private JScrollPane paneTableau;
	private JButton btnImport;
	private Object[][] donnees = { { "Rafraichir pour afficher la liste des plugins" } };
	private String[] entetes = { "Plugins activees" };

	public void ImportPluginInterface() throws IOException {

		initImportPluginInterface();
	}

	private void initImportPluginInterface() {

		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);

		tableau = new JTable(donnees, entetes);
		tableau.setBackground(Color.white);
		tableau.setShowHorizontalLines(false);

		paneTableau = new JScrollPane(tableau);
		paneTableau.setPreferredSize(new Dimension(450, 300));
		paneTableau.setBackground(Color.white);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(0, 5, 5, 0);
		add(paneTableau, gbc);

		// le Button

		this.btnImport = new JButton();

		btnImport.setSize(5, 5);
		btnImport.setText("Rafraichir la liste des plugins");

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(0, 2, 2, 0);
		add(btnImport, gbc);

		pack();

	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public JButton getBtnImport() {
		return btnImport;
	}

	public void setBtnImport(JButton Btn) {
		this.btnImport = Btn;
	}

	public Object[][] getDonnees() {
		return donnees;
	}

	public void setDonnees(Object[][] donnees) {
		this.donnees = donnees;
	}

	public String[] getEntetes() {
		return entetes;
	}

	public void setEntetes(String[] entetes) {
		this.entetes = entetes;
	}

}
