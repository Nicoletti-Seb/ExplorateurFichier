package fr.miage.m1.pa.explorateur.controleur.plugin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.miage.m1.pa.explorateur.interfaces.Plugin;


public class ManageurPluginVue extends JFrame {

	private static final long serialVersionUID = -6053520725570901740L;
	
	private JPanel mainPanel;
	private ManageurPluginListener listener;
	private ManageurPlugin managerPlugin;
	
	
	public ManageurPluginVue(ManageurPluginListener listener, ManageurPlugin managerPlugin) {
	
		this.listener = listener;
		this.managerPlugin = managerPlugin;
		init();
	}
	
	private void init() {
		
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 2));
		
		List<String> plugins = managerPlugin.getPlugins();
		for(String plugin : plugins) {
			
			Plugin p = managerPlugin.getPlugin(plugin);
			mainPanel.add(getComponentForPlugin(p));
			
		}
				
		setContentPane(mainPanel);
		setLocation(100, 100);
		setAlwaysOnTop(true);

		pack();
		setVisible(true);
	}
	
	private JComponent getComponentForPlugin(Plugin plugin) {
		
		if(plugin.getPanelConfig() != null) {
			return getPanelForPlugin(plugin, managerPlugin.pluginEstActive(plugin.getNom()));
		} else {
			return getCheckBoxForPlugin(plugin.getNom(), managerPlugin.pluginEstActive(plugin.getNom()));
		}
	}
	
	private JCheckBox getCheckBoxForPlugin(final String plugin, boolean active) {
		
		JCheckBox checkBox = new JCheckBox(plugin);
		checkBox.setSelected(active);
		
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(listener != null) listener.onPluginSelected(plugin);
				
			}
		});
		
		return checkBox;
	}
	
	private JPanel getPanelForPlugin(Plugin plugin, boolean active) {
		
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(getCheckBoxForPlugin(plugin.getNom(), active), BorderLayout.NORTH);
		panel.add(plugin.getPanelConfig(), BorderLayout.CENTER);
		
		return panel;
	}

}
