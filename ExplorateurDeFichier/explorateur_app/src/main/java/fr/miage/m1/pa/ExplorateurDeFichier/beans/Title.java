package fr.miage.m1.pa.ExplorateurDeFichier.beans;

public enum Title {
	
	NAME("Nom"),
	MODIFIED("Modifi� le"),
	TYPE("Type"),
	SIZE("Taille");

	private String name = "";

	//Constructeur
	Title(String name){
		this.name = name;
	}

	public String toString(){
		return name;
	}
}
