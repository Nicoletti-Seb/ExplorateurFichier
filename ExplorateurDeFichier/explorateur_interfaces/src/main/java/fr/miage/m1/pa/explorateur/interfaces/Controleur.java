package fr.miage.m1.pa.explorateur.interfaces;

public interface Controleur {
	
	public Vue getVue();
	public Modele getModele();
	public void setModele(Modele model);
	public void update();
}
