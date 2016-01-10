package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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

public class VueImpl extends JFrame implements Vue {

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
	}

	@Override
	public void setMouseListener(MouseListener listener) {
		mainPanel.setMouseListener(listener);
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

}
