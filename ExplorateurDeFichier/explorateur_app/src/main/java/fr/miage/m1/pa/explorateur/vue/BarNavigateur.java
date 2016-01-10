package fr.miage.m1.pa.explorateur.vue;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarNavigateur extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton boutonPrec;
	private JTextField textChemin;
	
	public BarNavigateur() {
		boutonPrec = new JButton("<");
		textChemin = new JTextField();
		
		setLayout(new BorderLayout());
		add(boutonPrec, BorderLayout.WEST);
		add(textChemin, BorderLayout.CENTER);
	}

	public void setChemin(String chemin){
		textChemin.setText(chemin);
	}
}
