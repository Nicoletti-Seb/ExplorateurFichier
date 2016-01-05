package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableModel;

import fr.miage.m1.pa.ExplorateurDeFichier.TableRenderer;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;

public class VueImpl extends JFrame implements Vue {

	private static final long serialVersionUID = 2636414275903248790L;

	private JPanel mainPanel;
	
	private JTable mainTable;
	private JScrollPane scrollPane;
	
	private JMenu menuPlugin;
	
	private ControleurVueListener listener;
	
	public VueImpl(Modele modele) {
		init(modele);
	}
	
	private void init(Modele modele) {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		mainTable = new JTable((TableModel) modele);
		mainTable.getColumnModel().getColumn(0).setCellRenderer(new TableRenderer());
		mainTable.setSelectionMode((ListSelectionModel.SINGLE_SELECTION));
		mainTable.setShowGrid(false);
		
		scrollPane = new JScrollPane(mainTable);

		JMenuBar menuBar = new JMenuBar();
		mainPanel.add(menuBar, BorderLayout.PAGE_START);
		
		menuPlugin = new JMenu("Plugins");
		menuBar.add(menuPlugin);
		
		menuPlugin.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				if(listener != null) listener.onMenuClicked("Plugins");
				
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		setContentPane(mainPanel);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void setMouseListener(MouseListener listener) {

		mainTable.addMouseListener(listener);
		
	}
	
	@Override
	public JTable getMainTable() {
		return mainTable;
	}

	@Override
	public void setPluginMenu(List<String> plugins) {
		
		for(final String plugin : plugins) {
			
			JMenuItem menuItem = new JMenuItem(plugin);
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(listener != null) listener.onMenuClicked(plugin);
					
				}
				
			});
			menuPlugin.add(menuItem);
		}
		
	}

	@Override
	public void setControleurListener(ControleurVueListener listener) {
		this.listener = listener;
		
	}
	
}
