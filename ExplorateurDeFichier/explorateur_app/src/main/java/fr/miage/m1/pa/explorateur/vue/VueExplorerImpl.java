package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import fr.miage.m1.pa.explorateur.abstracts.VueExplorer;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;

/**
 * Cette classe permet de gérer la visualisation des dossiers.
 */
public class VueExplorerImpl extends VueExplorer{

	private static final long serialVersionUID = 1L;
	
	private static final String[] COLUMN_NAME = {"NOM", "TYPE", "MODIFIER", "SIZE" };
	
	private JTable mainTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	
	public VueExplorerImpl() {
		this.setLayout(new BorderLayout());

		tableModel = new NoEditableModel(COLUMN_NAME,0);
		mainTable = new JTable(tableModel);
		mainTable.getColumnModel().getColumn(0).setCellRenderer(new TableRenderer());
		mainTable.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
		mainTable.setShowGrid(false);
		mainTable.getTableHeader().setReorderingAllowed(false);
		
		scrollPane = new JScrollPane(mainTable);
		this.add(scrollPane, BorderLayout.CENTER);
		
	}

	@Override
	public void addFileToDiplay(FileReader fileReader) {
		Object[] tab = {fileReader, fileReader.getFileType(), fileReader.getModified(), ""+fileReader.getSize()};
		tableModel.addRow(tab);
	}

	@Override
	public void clean() {
		for( int i = 0; i < tableModel.getRowCount(); i++){
			tableModel.removeRow(i);
		}
		tableModel.setRowCount(0);
	}

	@Override
	public void addVueExplorerListener(VueExplorerListener listener) {
		mainTable.addMouseListener(listener);
	}

	
	 private static class NoEditableModel extends DefaultTableModel {
		 
		private static final long serialVersionUID = 1L;

		public NoEditableModel(Object[] names, int row){
			super(names, row);
		}
		 
	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	}


	@Override
	public FileReader getFileSelected() {
		return (FileReader) mainTable.getValueAt(mainTable.getSelectedRow(), 0);
	}
}
