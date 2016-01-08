package fr.miage.m1.pa.explorateur.controleur.plugin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
			mainPanel.add(getComponentForPlugin(plugin, managerPlugin.pluginEstActive(plugin)));
		}
				
		setContentPane(mainPanel);
		setLocation(100, 100);
		setAlwaysOnTop(true);

		pack();
		setVisible(true);
	}
	
	private JCheckBox getComponentForPlugin(final String plugin, boolean active) {
		
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

}
