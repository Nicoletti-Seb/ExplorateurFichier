package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import fr.miage.m1.pa.explorateur.abstracts.VueNavigator;
import fr.miage.m1.pa.explorateur.controleur.ControleurImpl;
import fr.miage.m1.pa.explorateur.interfaces.VueNavigatorListener;

public class VueNavigatorImpl extends VueNavigator{
	
	private static final long serialVersionUID = 1L;
	
	private JButton boutonPrec;
	private JTextField textChemin;
	
	public VueNavigatorImpl() {
		boutonPrec = new JButton("<");
		boutonPrec.setActionCommand(ControleurImpl.ACTION_PRECEDENT);
		
		textChemin = new JTextField();
		textChemin.setFocusable(true);
		
		setLayout(new BorderLayout());
		add(boutonPrec, BorderLayout.WEST);
		add(textChemin, BorderLayout.CENTER);
	}

	@Override
	public void setPathFile(String path) {
		textChemin.setText(path);
	}

	@Override
	public void addVueNavigatorListener(VueNavigatorListener listener) {
		super.addVueNavigatorListener(listener);
		boutonPrec.addActionListener(listener);
		textChemin.addKeyListener(listener);
	}
	
	@Override
	public void removeNavigatorListener(){
		super.removeNavigatorListener();
		boutonPrec.removeActionListener(getVueNavigateurListener());
		textChemin.removeKeyListener(getVueNavigateurListener());
	}

	@Override
	public String getPathFile() {
		return textChemin.getText();
	}
}
