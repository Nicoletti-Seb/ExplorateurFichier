package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import fr.miage.m1.pa.explorateur.interfaces.Modele;

/**
 * Cette classe permet de gérer la visualisation des dossiers.
 */
public class ExplorateurPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTable mainTable;
	private JScrollPane scrollPane;
	
	public ExplorateurPanel(Modele modele) {
		this.setLayout(new BorderLayout());

		mainTable = new JTable((TableModel) modele);
		mainTable.getColumnModel().getColumn(0).setCellRenderer(new TableRenderer());
		mainTable.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
		mainTable.setShowGrid(false);

		scrollPane = new JScrollPane(mainTable);
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void setModel(Modele modele) {
		mainTable.setModel((TableModel)modele);
	}
	
	
	public void setMouseListener(MouseListener listener) {
		mainTable.addMouseListener(listener);
	}

	public JTable getMainTable() {
		return mainTable;
	}

}
