package fr.miage.m1.pa.explorateur_plugins.helper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PluginHelper {

	public static JFrame getBaseFrame(String nomPlugin, boolean active, JPanel pluginPanel, ActionListener listener) {
		
		JFrame result = new JFrame(nomPlugin);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		final JCheckBox checkBox = new JCheckBox();
		PluginHelper.setCheckBoxTitle(checkBox, active);
		checkBox.setSelected(active);
		
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PluginHelper.setCheckBoxTitle(checkBox, checkBox.isSelected());
				if(listener != null) listener.actionPerformed(e);
				
			}
		});
		
		mainPanel.add(checkBox, BorderLayout.NORTH);
		if(pluginPanel != null) mainPanel.add(pluginPanel, BorderLayout.CENTER);
		
		result.setContentPane(mainPanel);
		result.setLocation(100, 100);
		result.setAlwaysOnTop(true);

		result.pack();
		
		return result;
	}
	
	public static void setCheckBoxTitle(JCheckBox cb, boolean active) {
		
		cb.setText(active ? "Désactiver" : "Activer");
	}
}
