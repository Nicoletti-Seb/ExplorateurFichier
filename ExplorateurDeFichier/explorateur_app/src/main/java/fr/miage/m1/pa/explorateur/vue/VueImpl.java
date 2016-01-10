package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;

public class VueImpl extends JFrame implements Vue, WindowListener {

	private static final long serialVersionUID = 2636414275903248790L;

	private ExplorateurPanel mainPanel;
	private JMenu menuPlugin;
	private BarNavigateur barNavigateur;
	private ControleurVueListener listener;

	public VueImpl(Modele modele) {
		mainPanel = new ExplorateurPanel(modele);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		initMenu();

		barNavigateur = new BarNavigateur();
		barNavigateur.setChemin(modele.getCurrentPath().getAbsolutePath());
		this.add(barNavigateur, BorderLayout.NORTH);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuPlugin = new JMenu("Plugins");
		menuBar.add(menuPlugin);

		menuPlugin.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				if (listener != null) {
					listener.onMenuClicked("Plugins");
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
		
		setContentPane(mainPanel);

		pack();
		addWindowListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void setMouseListener(MouseListener listener) {
		mainPanel.setMouseListener(listener);
		barNavigateur.setMouseListener(listener);
	}

	@Override
	public JTable getMainTable() {
		return mainPanel.getMainTable();
	}

	@Override
	public void setPluginMenu(List<String> plugins) {
		for (final String plugin : plugins) {
			JMenuItem menuItem = new JMenuItem(plugin);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (listener != null) {
						listener.onMenuClicked(plugin);
					}
				}

			});
			menuPlugin.add(menuItem);
		}
	}

	@Override
	public void setControleurListener(ControleurVueListener listener) {
		this.listener = listener;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		listener.onClose();
		
	}

	public void setActionListener(ActionListener listener) {
		barNavigateur.setActionListener(listener);
	}

}
