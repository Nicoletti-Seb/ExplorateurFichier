package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.miage.m1.pa.explorateur.abstracts.VueExplorer;
import fr.miage.m1.pa.explorateur.abstracts.VueNavigator;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.FileReader;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.interfaces.VueExplorerListener;
import fr.miage.m1.pa.explorateur.interfaces.VueNavigatorListener;

public class VueImpl extends JFrame implements Vue, Observer {

	private static final long serialVersionUID = 2636414275903248790L;

	private JMenu menuPlugin;
	private VueNavigator vueNavigator;
	private VueExplorer vueExplorer;

	private ControleurVueListener controleurlistener;

	public VueImpl() {
		vueExplorer = new VueExplorerImpl();
		vueNavigator = new VueNavigatorImpl();

		getContentPane().add(vueExplorer, BorderLayout.CENTER);
		getContentPane().add(vueNavigator, BorderLayout.NORTH);

		initMenu();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				controleurlistener.onClose();
				super.windowClosed(e);
			}
		});

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuPlugin = new JMenu("Plugins");
		menuBar.add(menuPlugin);

		menuPlugin.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				controleurlistener.onMenuClicked("Plugins");
				System.out.println();
			}
		});
	}

	@Override
	public void setPluginMenu(List<String> plugins) {
		for (final String plugin : plugins) {
			JMenuItem menuItem = new JMenuItem(plugin);
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (controleurlistener != null) {
						controleurlistener.onMenuClicked(plugin);
					}
				}

			});
			menuPlugin.add(menuItem);
		}
	}

	@Override
	public void addControllerVueListener(ControleurVueListener listener) {
		this.controleurlistener = listener;
	}

	@Override
	public void setVueNavigator(VueNavigator vue) {
		getContentPane().remove(vueNavigator);
		
		this.vueNavigator = vue;
		getContentPane().add(vueNavigator, BorderLayout.NORTH);
	}

	@Override
	public VueNavigator getVueNavigator() {
		return vueNavigator;
	}

	@Override
	public void setVueExplorer(VueExplorer vue) {
		getContentPane().remove(vueExplorer);
		
		this.vueExplorer = vue;
		getContentPane().add(vueExplorer, BorderLayout.CENTER);
	}

	@Override
	public VueExplorer getVueExplorer() {
		return vueExplorer;
	}

	@Override
	public void addVueExplorerListener(VueExplorerListener listener) {
		vueExplorer.addVueExplorerListener(listener);
	}

	@Override
	public void addVueNavigatorListener(VueNavigatorListener listener) {
		vueNavigator.addVueNavigateurListener(listener);
	}

	@Override
	public void update(Observable o, Object obj) {
		FileReader fileReader = (FileReader) obj;
		
		//update vue explorer
		vueExplorer.clean();
		for( FileReader f : fileReader.getListFileReader() ){
			vueExplorer.addFileToDiplay(f);
		}
		
		//update vue navigator
		vueNavigator.setPathFile(fileReader.getPath());
	}
	
	public void addControleurVueListener(ControleurVueListener listener){
		controleurlistener = listener;
	}
}
