package fr.miage.m1.pa.ExplorateurDeFichier;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class FileExplorer extends JFrame implements MouseListener {

	private static final long serialVersionUID = 5203037217532265384L;

	private JPanel mainPanel;
	
	private JTable mainTable;
	private JScrollPane scrollPane;
	
	private File currentFile;
	
	public static void main(String[] args) {
		new FileExplorer();
	}
	
	public FileExplorer() {
		super();
		
		currentFile = new File("C:/");
		
		init();
		
	}
	
	private void init() {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1));
		
		mainTable = new JTable(new FileExplorerModel(currentFile));
		mainTable.addMouseListener(this);
		mainTable.getColumnModel().getColumn(0).setCellRenderer(new TableRenderer());
		mainTable.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
		mainTable.setShowGrid(false);
		
		scrollPane = new JScrollPane(mainTable);

		mainPanel.add(scrollPane, "0,1");
		
		add(mainPanel);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getSource().equals(mainTable)) {
			
			if(e.getClickCount() == 2) {
				
				File f = ((FileExplorerModel)mainTable.getModel()).getFileAt(mainTable.getSelectedRow());
				if(f.isDirectory()) {
					((FileExplorerModel)mainTable.getModel()).setCurrentPath(f);
				}
				
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
