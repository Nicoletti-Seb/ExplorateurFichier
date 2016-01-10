package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.miage.m1.pa.explorateur.controleur.ControleurImpl;

public class BarNavigateur extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton boutonPrec;
	private JTextField textChemin;
	
	public BarNavigateur() {
		boutonPrec = new JButton("<");
		boutonPrec.setActionCommand(ControleurImpl.ACTION_PRECEDENT);
		
		textChemin = new JTextField();
		
		setLayout(new BorderLayout());
		add(boutonPrec, BorderLayout.WEST);
		add(textChemin, BorderLayout.CENTER);
	}

	public void setChemin(String chemin){
		textChemin.setText(chemin);
	}
	
	
	public void setMouseListener(MouseListener listener) {
		boutonPrec.addMouseListener(listener);
	}

	public void setActionListener(ActionListener listener){
		boutonPrec.addActionListener(listener);
	}
}
