package fr.miage.m1.pa.explorateur.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface VueNavigatorListener extends ActionListener, KeyListener{
	
	public void changePath();
	public void goToUpFolder();
	
}
