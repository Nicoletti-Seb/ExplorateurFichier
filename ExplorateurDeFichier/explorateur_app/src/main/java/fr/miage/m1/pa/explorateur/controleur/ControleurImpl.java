package fr.miage.m1.pa.explorateur.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JTextField;

import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPlugin;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginListener;
import fr.miage.m1.pa.explorateur.controleur.plugin.ManageurPluginVue;
import fr.miage.m1.pa.explorateur.interfaces.Controleur;
import fr.miage.m1.pa.explorateur.interfaces.ControleurVueListener;
import fr.miage.m1.pa.explorateur.interfaces.Modele;
import fr.miage.m1.pa.explorateur.interfaces.Vue;
import fr.miage.m1.pa.explorateur.modele.ModeleImpl;
import fr.miage.m1.pa.explorateur.vue.VueImpl;

public class ControleurImpl implements Controleur, KeyListener, ActionListener, MouseListener, ControleurVueListener,
		ManageurPluginListener {

	public static final String ACTION_PRECEDENT = "ACTION_PRECEDENT";

	private File currentFile;

	private ManageurPlugin managerPlugin;

	private Modele modele;
	private Vue vue;

	public ControleurImpl() {

		currentFile = new File(System.getProperty("user.dir"));

		managerPlugin = new ManageurPlugin();

		modele = new ModeleImpl(currentFile);
		vue = new VueImpl(modele);
		// vue.setPluginMenu(managerPlugin.getPlugins());
		vue.setMouseListener(this);
		vue.setControleurListener(this);
		vue.setActionListener(this);
		vue.setKeyListener(this);
	}

	@Override
	public Vue getVue() {
		return vue;
	}

	@Override
	public Modele getModele() {
		return modele;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(vue.getMainTable())) {
			if (e.getClickCount() == 2) {
				File f = modele.getFileAt(vue.getMainTable().getSelectedRow());
				if (f.isDirectory()) {
					if (modele.setCurrentPath(f)) {
						JTextField text = vue.getLabelNavigateur();
						text.setText(modele.getCurrentPath().getAbsolutePath());
					}
				}

			}
		}
	}

	@Override
	public void onMenuClicked(String name) {

		if (name.equals("Plugins")) {
			new ManageurPluginVue(this, managerPlugin);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void onPluginSelected(String plugin) {

		managerPlugin.onPluginClicked(plugin, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (ACTION_PRECEDENT.equals(e.getActionCommand())) {
			if (modele.setCurrentPath(modele.getCurrentPath().getParentFile())) {
				JTextField text = vue.getLabelNavigateur();
				text.setText(modele.getCurrentPath().getAbsolutePath());
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == vue.getLabelNavigateur() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			JTextField text = vue.getLabelNavigateur();

			if (modele.setCurrentPath(new File(text.getText()))) {
				text.setText(modele.getCurrentPath().getAbsolutePath());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
